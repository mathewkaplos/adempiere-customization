package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductCategory;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CopyStock extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		copyStock();
		return null;
	}

	private void copyStock()
	{
		
		String sql = "SELECT product_id, initial_stock  from z_product123";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{

				int product_id = rs.getInt(1);
				BigDecimal bd = rs.getBigDecimal(2);
				createPOLines(product_id, bd);
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
	}

	int x = 1;

	private void createPOLines(int importID, BigDecimal initial_stock)
	{
		MOrder order = new MOrder(getCtx(), 1001285, get_TrxName()); // hard-coded

		String sql = "SELECT M_Product_ID  from M_Product WHERE product_import_id = " + importID;
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(x++);
				int M_Product_ID = rs.getInt(1);

				MOrderLine ol = new MOrderLine(order);
				ol.setM_Product_ID(M_Product_ID);
				ol.setM_AttributeSetInstance_ID(1006575); // hard-coded
				ol.setQtyEntered(initial_stock);
				ol.setQty(initial_stock);
				ol.setQtyOrdered(initial_stock);
				ol.setC_Tax_ID(1000000);
				ol.save();
			}

		} catch (Exception e)
		{

		} finally
		{
			DB.close(stm);
			stm = null;
		}
	}
}
