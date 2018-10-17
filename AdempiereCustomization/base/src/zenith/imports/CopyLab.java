package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.MParameter;
import zenith.model.MSpecimen;
import zenith.model.MTest;
import zenith.process.CreateHospitalDefaults;

public class CopyLab extends SvrProcess
{

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		copy();
		// addParameters();
		return null;
	}

	int x = 1;
	private int ad_org_ID = CreateHospitalDefaults.ultra_soundDepartmentID;

	private void copy()
	{
		// lab id = AD_Org_ID=1000007
		// theatre id = AD_Org_ID=1000015
		// ultra id =M_Product_Category_ID=1000206
		String sql = "select id, name, price from adempiere.z_bama_lab ";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(x++);
				try
				{
					int id = rs.getInt(1);
					String name = rs.getString(2).toUpperCase();
					BigDecimal bd = rs.getBigDecimal(3);
					//MTest test = new MTest(getCtx(), 0, get_TrxName());
				////	test.setName(name);
					
					//test.setAD_Org_ID(ad_org_ID); 
					///test.setPrice(bd);
					///test.save();

					// M_Product_Category_ID=1000202
					MProduct product = new MProduct(getCtx(), 0, getName());
					product.setValue(name);

					product.setName(name);
					product.setM_Product_Category_ID(200);
					product.setC_TaxCategory_ID(1000000);
					product.setC_UOM_ID(100);
					product.setProductType("S");
					product.setAD_Org_ID(ad_org_ID); 
					product.saveEx(get_TrxName());

					MProductPrice pp = new MProductPrice(getCtx(), 0, get_TrxName());
					pp.setM_PriceList_Version_ID(1000003);
					pp.setM_Product_ID(product.getM_Product_ID());
					pp.setPriceLimit(bd);
					pp.setPriceList(bd);
					pp.setPriceStd(bd);
					pp.save();
					//
					MProductPrice pp2 = new MProductPrice(getCtx(), 0, get_TrxName());
					pp2.setM_PriceList_Version_ID(1000004);
					pp2.setM_Product_ID(product.getM_Product_ID());
					pp2.setPriceLimit(bd);
					pp2.setPriceList(bd);
					pp2.setPriceStd(bd);
					pp2.save();

					//
					MProductPrice pp3 = new MProductPrice(getCtx(), 0, get_TrxName());
					pp3.setM_PriceList_Version_ID(1000006);
					pp3.setM_Product_ID(product.getM_Product_ID());
					pp3.setPriceLimit(bd);
					pp3.setPriceList(bd);
					pp3.setPriceStd(bd);
					pp3.save();

					///test.setM_Product_ID(product.get_ID());
					//test.save();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		catch (Exception e)
		{

		}
	}

	int y = 1000;

	private void addParameters()
	{
		String sql = "select hms_test_id  ,name from adempiere.hms_test  ";
		PreparedStatement stm = null;
		try
		{
			stm = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(y++);
				int testID = rs.getInt(1);
				String name = rs.getString(2);

				MSpecimen sp = new MSpecimen(getCtx(), 0, get_TrxName());
				sp.setName(name);
				sp.sethms_test_ID(testID);
				sp.save();

				MParameter para = new MParameter(getCtx(), 0, get_TrxName());
				para.setName(name);
				para.sethms_specimens_ID(sp.get_ID());
				para.save();
			}
		} catch (Exception e)
		{

		}
	}

}
