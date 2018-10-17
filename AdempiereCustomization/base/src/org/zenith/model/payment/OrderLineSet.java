package org.zenith.model.payment;

import java.math.BigDecimal;

import org.compiere.util.Env;

public class OrderLineSet
{
	/*
	 * 
	 * line.setM_Product_ID(set[i].getProductID());
	 * line.setLineNetAmt(set[i].getPrice());
	 * 
	 * line.setLineNetAmt(set[i].getPrice()); line.setPrice(set[i].getPrice());
	 * line.setPriceLimit(set[i].getPrice());
	 * line.setPriceList(set[i].getPrice());
	 * line.setPriceEntered(set[i].getPrice()); line.setQty(set[i].getQty());
	 * line.setitem_type(Hpo.labItemType);
	 */
	private int _c_orderline_id_source = 0;
	private int _product_id = 0;
	private int _warehouse_d = 0;
	private BigDecimal _linenetAmt = Env.ZERO;
	private BigDecimal _price = Env.ZERO;
	private BigDecimal _priceList = Env.ZERO;
	private BigDecimal _priceEntered = Env.ZERO;
	private BigDecimal _qty = Env.ZERO;
	private String _item_type = null;
	private String _desc = null;

	private int _M_AttributeSetInstance_ID = 0;

	public OrderLineSet(int C_Orderline_ID, int product_id, int warehouse_id, BigDecimal linenetAmt, BigDecimal price,
			BigDecimal priceList, BigDecimal priceEntered, BigDecimal qty, String item_type, String desc,
			int M_AttributeSetInstance_ID)
	{
		this._product_id = product_id;
		this._warehouse_d = warehouse_id;
		this._linenetAmt = linenetAmt;
		this._price = price;
		this._priceList = priceList;
		this._priceEntered = priceEntered;
		this._qty = qty;
		this._item_type = item_type;
		this._c_orderline_id_source = C_Orderline_ID;
		this._desc = desc;
		this._M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
	}

	public int get_M_AttributeSetInstance_ID()
	{
		return _M_AttributeSetInstance_ID;
	}

	public void set_M_AttributeSetInstance_ID(int _M_AttributeSetInstance_ID)
	{
		this._M_AttributeSetInstance_ID = _M_AttributeSetInstance_ID;
	}

	public int getOrderline_id_source()
	{
		return _c_orderline_id_source;
	}

	public String getItemType()
	{
		return _item_type;
	}

	public BigDecimal getQty()
	{
		return _qty;
	}

	public BigDecimal getPriceEntered()
	{
		return _priceEntered;
	}

	public BigDecimal getPricelist()
	{
		return _priceList;
	}

	public BigDecimal getLinenetAmt()
	{
		return _linenetAmt;
	}

	public BigDecimal getPrice()
	{
		return _price;
	}

	public int getProductID()
	{
		return _product_id;
	}

	public int getWarehouseID()
	{
		return _warehouse_d;
	}

	public String getDescription()
	{
		return _desc;
	}
}
