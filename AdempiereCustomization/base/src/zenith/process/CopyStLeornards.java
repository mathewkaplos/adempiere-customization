package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.ZBama;

public class CopyStLeornards extends SvrProcess
{
	int x = 1;

	@Override
	protected void prepare()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception
	{
		// copy();
		copyProductCategories();
		return null;
	}

	void copy()
	{

		String sql = "SELECT * FROM z_bama_patients";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 1;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				System.out.println(count);
				count++;
				ZBama bama = new ZBama(getCtx(), rs, get_TrxName());

				MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
				bp.setOldPatientNo(bama.getpatient_no());
				bp.setName(bama.getpatient_fullname());
				if (bama.getpatient_reg() != null)
					bp.setDor(bama.getpatient_reg());
				if (bama.getpatient_gender() != null)
				{
					if (bama.getpatient_gender().trim().equalsIgnoreCase("Male"))
						bp.setGender("M");
					if (bama.getpatient_gender().trim().equalsIgnoreCase("Female"))
						bp.setGender("F");
				}

				if (bama.getpatient_natioanalid() != null)
				{
					if (isInteger(bama.getpatient_natioanalid()))
					{
						bp.setIDNo(bama.getpatient_natioanalid());
					}
				}
				if (bama.getpatient_tel() != null)
				{
					bp.setTelNo("0" + bama.getpatient_tel());
				}
				if (bama.getpatient_dob() != null)
					bp.setDob(bama.getpatient_dob());
				if (bama.getpatient_nhifno() != null)
					bp.setNHIFNo(bama.getpatient_nhifno());
				if (bama.getpetient_pricelistid() == 1 || bama.getpetient_pricelistid() == 0)
					bp.setC_BP_Group_ID(1000001);
				else if (bama.getpetient_pricelistid() == 2)
					bp.setC_BP_Group_ID(1000002);
				else if (bama.getpetient_pricelistid() == 3)
					bp.setC_BP_Group_ID(1000003);
				bp.setC_Country_ID(219);
				bp.setIDNo(bama.getpatient_natioanalid());
				bp.saveEx(get_TrxName());

			}
		} catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		} finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	public static boolean isInteger(String s)
	{
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix)
	{
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++)
		{
			if (i == 0 && s.charAt(i) == '-')
			{
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return false;
	}

	private void copyProductCategories()
	{
		String sql = "SELECT category_id ,category_name FROM z_category";
		PreparedStatement ps = null;
		try
		{
			ps = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				int category_id = rs.getInt(1);
				String category_name = rs.getString(2).toUpperCase();

				MProductCategory productCategory = new MProductCategory(getCtx(), 0, getName());
				productCategory.setValue(category_name);
				productCategory.setName(category_name);

			  //  productCategory.setImport_category_id(category_id);

				productCategory.saveEx(get_TrxName());

				copyProduct(category_id, productCategory);

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			DB.close(ps);
			ps = null;
		}
	}

	private void copyProduct(int category_id, MProductCategory productCategory)
	{
		String sql2 = "SELECT product_id,product_name,product_category,product_code FROM z_product WHERE product_category ="
				+ category_id;
		PreparedStatement ps2 = null;

		try
		{
			ps2 = DB.prepareStatement(sql2, get_TrxName());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next())
			{
				System.out.println(x++);
				int product_id = rs2.getInt(1);
				String product_name = rs2.getString(2);
				int product_category = rs2.getInt(3);
				String product_code = rs2.getString(4);

				MProduct product = new MProduct(getCtx(), 0, getName());
				product.setValue(product_name);

				product.setName(product_name);
				product.setM_Product_Category_ID(productCategory.getM_Product_Category_ID());
				product.setC_TaxCategory_ID(1000000);
				product.setC_UOM_ID(100);

				product.setImport_category_id(product_category);
				product.setImport_product_id(product_id);

				product.saveEx(get_TrxName());
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			DB.close(ps2);
			ps2 = null;
		}
	}
}
