package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_M_Locator;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MBed;
import zenith.model.MInpatient_Orders;

public class Request_inpatient_order extends SvrProcess
{

	int wardManagementID = 0;
	WardOrderSet[] wardOrderSets = null;
	int movementID = 0;

	@Override
	protected void prepare()
	{
		// NEVER TODO Auto-generated method stub
		wardManagementID = getRecord_ID();
		getOrderIDs(); // selects the order id for each line
	}

	@Override
	protected String doIt() throws Exception
	{

		int wardID = getWardID();
		int warehouseID = getWarehouseID(wardID);
		if (warehouseID == 0)
		{
			// add new default warehouse and locator
			String wardName = getWardName(wardID);
			int locID = newLocation(wardName);
			warehouseID = addWardWarehouse(wardName, locID);
			updateWard(warehouseID, wardID);
		}

		MovementSet[] set = getWardOrder(); // returns consolidated order
		if (set.length > 0)
		{
			stockMove(set, warehouseID); // creates movement document
		}

		// update the ward order details

		if (wardOrderSets.length > 0)
		{
			updateOrder(wardOrderSets, movementID);
		} else
		{
			// no order...
		}
		return null;
	}

	/**
	 * Check if the ward has defined warehouse, then return warehouseID
	 */

	private int getWarehouseID(int wardID)
	{
		int M_Warehouse_ID = 0;

		PreparedStatement pstmt2 = null;
		String sql2 = "SELECT M_Warehouse_ID from adempiere.hms_ward1 WHERE hms_ward1_id =" + wardID;
		try
		{
			pstmt2 = DB.prepareStatement(sql2.toString(), get_TrxName());
			ResultSet rs2 = pstmt2.executeQuery();
			if (rs2.next())
			{
				if (rs2.getInt(1) > 0)
					M_Warehouse_ID = rs2.getInt(1);
			}
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			DB.close(pstmt2);
			pstmt2 = null;
		}
		return M_Warehouse_ID;
	}

	// Add new ward warehouse
	private int addWardWarehouse(String name, int locID)
	{
		MWarehouse wh = new MWarehouse(getCtx(), 0, get_TrxName());
		wh.setName(name);
		wh.setValue(name);
		wh.setC_Location_ID(locID);
		wh.save();
		int warehouseID = wh.get_ID();
		// add default Locator for the warehouse
		//
		addDefaultLocator(name, warehouseID);
		return warehouseID;
	}

	// default locator
	private void addDefaultLocator(String value, int warehouseID)
	{
		MLocator loc = new MLocator(getCtx(), 0, get_TrxName());
		loc.setValue(value);
		loc.setM_Warehouse_ID(warehouseID);
		loc.setPriorityNo(50);
		loc.setIsDefault(true);
		loc.setXYZ("1", "0", "0");
		loc.save();
	}

	// new Location for the warehouse...
	// not to be confused with Locator
	private int newLocation(String name)
	{
		MLocation location = new MLocation(getCtx(), 0, get_TrxName());
		location.setAddress1(name);
		location.save();
		return location.get_ID();
	}

	private void updateWard(int warehouseID, int wardID)
	{
		String sql = "UPDATE adempiere.hms_ward1 SET M_Warehouse_ID =" + warehouseID + " WHERE hms_ward1_id =" + wardID;
		DB.executeUpdate(sql, get_TrxName());

	}

	private int getWardID()
	{
		PreparedStatement pstm = null;
		int hms_ward1_ID = 0;

		String sql = " SELECT m.hms_ward1_ID from adempiere.hms_ward_management1 m  WHERE  hms_ward_management1_ID="
				+ wardManagementID;

		try
		{
			pstm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstm.executeQuery();
			while (rs.next())
			{
				hms_ward1_ID = rs.getInt(1);
			}
		} catch (Exception e)
		{

		} finally
		{
			DB.close(pstm);
			pstm = null;
		}
		return hms_ward1_ID;
	}

	private String getWardName(int wardID)
	{
		PreparedStatement pstm = null;
		String wardName = null;
		String sql = "SELECT name FROM adempiere.hms_ward1 WHERE hms_ward1_id =" + wardID;
		try
		{
			pstm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstm.executeQuery();
			while (rs.next())
			{
				wardName = rs.getString(1);
			}
		} catch (Exception e)
		{

		} finally
		{
			DB.close(pstm);
		}
		return wardName;
	}

	private void stockMove(MovementSet[] set, int MWarehouseID)
	{
		// Requesting warehouse - ward
		// MWarehouse wh = MWarehouse.get(getCtx(), MWarehouseID);
		int M_Locator_To_ID = getLocatorID(MWarehouseID);

		// 1000004- pharmacy warehouse -- Source warehouse
		// hard coded---to changes this
		// MWarehouse wh2 = new MWarehouse(getCtx(), 1000004, get_TrxName());
		int M_Locator_ID = getLocatorID(1000004);

		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setC_DocType_ID(getMMDoctype());
		movement.setDocAction("CO");
		movement.setDocStatus("DR");
		movement.setMovementtype("WARD");

		movement.save();

		// line
		for (int i = 0; i < set.length; i++)
		{
			MMovementLine line = new MMovementLine(movement);
			line.setM_Locator_ID(M_Locator_ID);
			line.setM_LocatorTo_ID(M_Locator_To_ID);
			line.setM_Product_ID(set[i].getProductID());
			line.setMovementQty(set[i].getQuantity());
			line.save();
			line.setM_AttributeSetInstanceTo_ID(line.getM_AttributeSetInstance_ID());
			line.save();
		}
		movementID = movement.get_ID();
	}

	/**
	 * Get Locator ID
	 * 
	 * 
	 * @return locator ID
	 */
	public int getLocatorID(int M_Warehouse_ID)
	{
		int locID = 0;
		final String whereClause = "M_Warehouse_ID=?";
		List<MLocator> list = new Query(getCtx(), I_M_Locator.Table_Name, whereClause, null)
				.setParameters(M_Warehouse_ID).setOrderBy("X,Y,Z").list();
		if (list.size() > 0)
			locID = list.get(0).get_ID();

		return locID;
	}

	// MM Material Movement
	private int getMMDoctype()
	{
		int docTypeID = 0;
		int AD_Client_ID = Env.getContextAsInt(getCtx(), "#AD_Client_ID");
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=" + AD_Client_ID + " AND name ='"
				+ "Material Movement" + "'";
		docTypeID = DB.getSQLValue(get_TrxName(), sql);
		// 1000022 default for Material Movement ..don't use this
		if (docTypeID == 0)
			return 1000022;
		return docTypeID;
	}

	private MovementSet[] getWardOrder()
	{
		List<MovementSet> list = new ArrayList<MovementSet>();

		PreparedStatement stm = null;
		int productID = 0;
		BigDecimal quantity = Env.ZERO;

		String sql = "SELECT  pr.M_Product_ID, sum(io.qtyordered) FROM adempiere.hms_inpatient_orders io"
				+ " INNER JOIN adempiere.m_product pr  ON pr.m_product_id = io.m_product_id "
				+ "INNER JOIN adempiere.hms_treatment_doc doc ON doc.hms_treatment_doc_id = io.hms_treatment_doc_id "
				+ " INNER JOIN adempiere.hms_ward_management1 wm ON wm.hms_ward_management1_id = doc.hms_ward_management1_id"
				+ " WHERE wm.hms_ward_management1_ID = "+wardManagementID
				+ " AND io.order_nullified='N' AND io.order_sent ='N'"
				+ " GROUP BY  pr.M_Product_ID";

		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				productID = rs.getInt(1);
				quantity = rs.getBigDecimal(2);
				MovementSet movementSet = new MovementSet(productID, quantity);
				list.add(movementSet);
			}
		} catch (Exception e)
		{

		}
		// Array set
		return list.toArray(new MovementSet[list.size()]);

	}

	private void updateOrder(WardOrderSet[] set, int m_movement_id)
	{
		// set order to sent
		for (int i = 0; i < set.length; i++)
		{
			MInpatient_Orders io = new MInpatient_Orders(getCtx(), set[i].getOrderId(), get_TrxName());
			io.setorder_sent(true);
			io.setM_Movement_ID(m_movement_id);
			io.setbed_no(getBedNo(set[i].getBedID()));
			io.save();
			System.out.println(set[i].getOrderId());
			System.out.println(m_movement_id);
		}
	}

	private BigDecimal getBedNo(int bed_ID)
	{
		MBed bed = new MBed(getCtx(), bed_ID, get_TrxName());
		return bed.getbed_no();
	}

	private WardOrderSet[] getOrderIDs()
	{
		List<WardOrderSet> list = new ArrayList<WardOrderSet>();
		PreparedStatement stm = null;
		String sql = "select io.hms_inpatient_orders_id, doc.hms_ward_bed1_id from "
				+ "adempiere.hms_inpatient_orders io INNER JOIN adempiere.hms_treatment_doc  doc "
				+ "ON doc.hms_treatment_doc_id =io.hms_treatment_doc_id "
				+ "INNER JOIN adempiere.hms_ward_management1 wm"
				+ " ON wm.hms_ward_management1_id = doc.hms_ward_management1_id WHERE wm.hms_ward_management1_id ="
				+ wardManagementID;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				int hms_inpatient_orders_id = rs.getInt(1);
				int hms_ward_bed1_id = rs.getInt(2);
				WardOrderSet set = new WardOrderSet(hms_inpatient_orders_id, hms_ward_bed1_id);
				list.add(set);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
		wardOrderSets = list.toArray(new WardOrderSet[list.size()]);
		return wardOrderSets;
	}

	/**
	 * class to hold stock move attributes, e.g M_Product_ID, Quantity
	 * 
	 */
	private class MovementSet
	{
		private int _M_Product_ID = 0;
		private BigDecimal _quantity = Env.ZERO;
		private int _M_Locator_ID = 0;
		private int _M_Locator_To_ID = 0;

		// constructor
		private MovementSet(int M_Product_ID, BigDecimal quantity)
		{
			this._M_Product_ID = M_Product_ID;
			this._quantity = quantity;
		}

		// constructor
		MovementSet(int M_Product_ID, BigDecimal quantity, int M_Locator_ID, int M_Locator_To_ID)
		{
			this._M_Product_ID = M_Product_ID;
			this._quantity = quantity;
			this._M_Locator_ID = M_Locator_ID;
			this._M_Locator_To_ID = M_Locator_To_ID;
		}

		// get productID
		public int getProductID()
		{
			return _M_Product_ID;
		}

		// get quantity
		public BigDecimal getQuantity()
		{
			return _quantity;
		}

		// get source locator
		public int getMLocatorID()
		{
			return _M_Locator_ID;
		}

		// get destination Locator
		public int getMLocatorToID()
		{
			return _M_Locator_To_ID;
		}
	}

	private class WardOrderSet
	{
		private int _orderID = 0;

		private int _bedID = 0;

		private WardOrderSet(int orderID, int bedID)
		{
			this._orderID = orderID;
			this._bedID = bedID;
		}

		public int getOrderId()
		{
			return _orderID;
		}

		public int getBedID()
		{
			return _bedID;
		}
	}
}
