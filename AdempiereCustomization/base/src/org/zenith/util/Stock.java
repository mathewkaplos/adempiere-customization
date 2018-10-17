package org.zenith.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class Stock
{
	private int M_Warehouse_ID = 0;
	private int M_Locator_ID = 0;
	private int M_Product_ID = 0;
	private int M_AttributeSetInstance_ID = 0;
	private boolean stockReserved = false;

	public Stock(int M_Warehouse_ID, int M_Locator_ID, int M_Product_ID)
	{
		this.M_Warehouse_ID = M_Warehouse_ID;
		this.M_Locator_ID = M_Locator_ID;
		this.M_Product_ID = M_Product_ID;
		this.stockReserved = HmsSetup.getSetup().isreserve_drugs();
	}

	/*
	 * 
	 */
	public BigDecimal getQtyAvailable()
	{
		BigDecimal qty = MStorage.getQtyAvailable(M_Warehouse_ID, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				null);
		return new BigDecimal(qty.stripTrailingZeros().toPlainString());
	}

	public String getStockInformation()
	{
		StringBuilder info = new StringBuilder();
		info.append(getLocatorName().toUpperCase() + "\n");
		if (stockReserved)
		{
			info.append("All Stock:       " + getQtyAll() + "\n");
			info.append("Reserved:     " + getQtyReserved() + "\n");
		}
		info.append("Available:      " + getQtyAvailable() + "\n");
		info.append(getExpiryDateAsString());
		return info.toString();
	}

	private String getLocatorName()
	{
		MLocator locator = new MLocator(Env.getCtx(), M_Locator_ID, null);
		return locator.getValue();
	}

	private BigDecimal getQtyReserved()
	{
		BigDecimal qty = MStorage.getQtyReserved(M_Warehouse_ID, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				null);
		return new BigDecimal(qty.stripTrailingZeros().toPlainString());
	}

	private BigDecimal getQtyAll()
	{
		BigDecimal qty = MStorage.getQtyAll(M_Warehouse_ID, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				null);
		return new BigDecimal(qty.stripTrailingZeros().toPlainString());
	}

	public Timestamp getExpiryDate()
	{
		Timestamp timestamp = null;
		List<Timestamp> expiryDates = new ArrayList<>();
		MStorage[] storages = MStorage.getAll(Env.getCtx(), M_Product_ID, M_Locator_ID, null);
		for (int i = 0; i < storages.length; i++)
		{
			MStorage storage = storages[i];
			int asi_ID = storage.getM_AttributeSetInstance_ID();
			MAttributeSetInstance asi = new MAttributeSetInstance(Env.getCtx(), asi_ID, null);
			Timestamp date = asi.getGuaranteeDate();
			if (date != null)
				expiryDates.add(date);
		}
		if (expiryDates.size() > 0)
		{
			Collections.sort(expiryDates);
			Timestamp expiry = expiryDates.get(0);
			timestamp = expiry;
		}
		return timestamp;
	}

	public Integer daysToExpiry()
	{
		Integer days = null;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Timestamp expiry = getExpiryDate();
		if (expiry == null)
			days = null;
		else
		{
			Date dateNow = new Date(now.getTime());
			Date dateExpiry = new Date(expiry.getTime());
			long diff = dateExpiry.getTime() - dateNow.getTime();
			days = (Integer) (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}
		return days;

	}

	private String getExpiryDateAsString()
	{
		Timestamp expiry = getExpiryDate();
		if (expiry != null)
		{
			String s = new SimpleDateFormat("dd-MM-yyyy").format(expiry);
			return "Expiry Date:  " + s;
		}
		return "";
	}

	Map<Integer, BigDecimal> map = null;
	List<Integer> list = null;
	BigDecimal _qty = Env.ZERO;

	public Stock updateStock(BigDecimal Qty)
	{
		map = new HashMap<>();
		list = new ArrayList<>();
		_qty = Qty;
		String sql = "select m_attributesetinstance_id,qtyonhand from adempiere.m_storage where m_product_id= "
				+ M_Product_ID + " and m_locator_id = " + M_Locator_ID + " ORDER BY qtyonhand DESC";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, null);
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int m_attributesetinstance_id = rs.getInt(1);
				BigDecimal qtyonhand = rs.getBigDecimal(2);
				map.put(m_attributesetinstance_id, qtyonhand);
				list.add(m_attributesetinstance_id);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 
	 */
	public void updateQtyOnHand()
	{
		BigDecimal required = _qty;
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				updateStorageOnHand(map.get(asi), asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				updateStorageOnHand(map.get(asi), asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				updateStorageOnHand(required, asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			updateStorageOnHand(required, 0);
		}
	}

	private void updateStorageOnHand(BigDecimal Qty, int asi)
	{
		MStorage.add(Env.getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, asi, 0, Qty.negate(), Env.ZERO, Env.ZERO,
				null);
	}

	/**
	 * 
	 */
	public void updateQtyReserved()
	{
		BigDecimal required = _qty;
		for (Integer asi : list)
		{
			if (required.compareTo(map.get(asi)) == 1)
			{
				updateStorageReserved(map.get(asi), asi);
				required = required.subtract(map.get(asi));
			} else if (required.compareTo(map.get(asi)) == 0)
			{
				updateStorageReserved(map.get(asi), asi);
				required = Env.ZERO;
				break;
			} else if (required.compareTo(map.get(asi)) == -1)
			{
				updateStorageReserved(required, asi);
				required = Env.ZERO;
				break;
			}
		}
		if (required.compareTo(Env.ZERO) == 1)
		{
			updateStorageReserved(required, 0);
		}
	}

	private void updateStorageReserved(BigDecimal Qty, int asi)
	{
		MStorage.add(Env.getCtx(), M_Warehouse_ID, M_Locator_ID, M_Product_ID, asi, 0, Env.ZERO, Qty, Env.ZERO, null);
	}
}
