package zenith.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPrice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import zenith.model.MInsuranceCompany;
import zenith.model.MInsuredCompany;
import zenith.model.MParameter;
import zenith.model.MSpecimen;
import zenith.model.MTest;
import zenith.model.ZBama;
import zenith.model.ZCategory;
import zenith.model.ZInsurance;
import zenith.model.ZInsuranceCompany;
import zenith.model.ZPrice;
import zenith.model.ZProduct;

public class Copier extends SvrProcess
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
		// AddLoc();
		 importProductCat();
		// importInsurance();
		// updateProductCode();
		// lab();

		// System.out.println("hey...");
		return null;
	}

	void lab()
	{
		String sql = "SELECT laboratory_id,laboratory_specimen,laboratory_fee FROM z_bama_lab";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getInt(1) + "--" + rs.getString(2) + "--" + rs.getBigDecimal(3));
				MTest test = new MTest(getCtx(), 0, get_TrxName());
				test.setName(rs.getString(2));
				test.setPrice(rs.getBigDecimal(3));
				test.setAD_Org_ID(1000007);
				test.save();
				labSpeciment(test, rs.getInt(1));
			}
		} catch (Exception e)
		{

		}
	}

	void labSpeciment(MTest test, int labID)
	{
		MSpecimen s = new MSpecimen(getCtx(), 0, get_TrxName());
		s.sethms_test_ID(test.get_ID());
		s.setName(test.getName());
		s.setAD_Org_ID(1000007);
		s.save();
		labPara(test, s.get_ID(), labID);
	}

	void labPara(MTest test, int specID, int labID)
	{
		String sql = "SELECT name FROM z_bama_lab_details where laboratory_id =" + labID;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{

				MParameter para = new MParameter(getCtx(), 0, get_TrxName());
				para.setName(rs.getString(1));
				para.sethms_specimens_ID(specID);
				para.setAD_Org_ID(1000007);
				para.save();
				x++;
			}
		} catch (Exception e)
		{

		}
		if (x == 0)
		{
			MParameter para = new MParameter(getCtx(), 0, get_TrxName());
			para.setName(test.getName());
			para.sethms_specimens_ID(specID);
			para.setAD_Org_ID(1000007);
			para.save();
		}
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
				System.out.println(count++);

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

	public void AddLoc()
	{
		String sql = "SELECT * FROM C_BPartner";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			int count = 1;
			while (rs.next())
			{
				System.out.println(count);
				count++;
				AddDefaultLocation(new MBPartner(getCtx(), rs, get_TrxName()));
			}
		} catch (Exception e)
		{

		}
	}

	// MBPartnerLocation
	private void AddDefaultLocation(MBPartner bp)
	{
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs.length < 1)
		{
			MBPartnerLocation bl = new MBPartnerLocation(getCtx(), 0, get_TrxName());
			bl.setC_BPartner_ID(bp.get_ID());
			bl.setC_Location_ID(1000031);// Default
			bl.setName(bp.getName() + " Location");
			bl.save();

		}
	}

	//
	void importProductCat()
	{
		String sql = "select * from z_bama_pro_cat";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			int count = 1;
			while (rs.next())
			{
				System.out.println("cat:" + count);
				count++;
				ZCategory zcat = new ZCategory(getCtx(), rs, get_TrxName());
				MProductCategory productCategory = new MProductCategory(getCtx(), 0, getName());
				productCategory.setValue(zcat.getcategory_name());
				productCategory.setName(zcat.getcategory_name());

				productCategory.saveEx(get_TrxName());
				importProduct(zcat.getz_bama_pro_cat_ID(), productCategory.get_ID());
			}
		} catch (Exception e)
		{

		}
	}

	void importProduct(int zCatID, int ademCatID)
	{
		String sql = "select * from z_bama_product where product_category =" + zCatID;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			int count = 1;
			while (rs.next())
			{
				System.out.println("Pro:" + count);
				count++;
				ZProduct zproduct = new ZProduct(getCtx(), rs, get_TrxName());
				MProduct product = new MProduct(getCtx(), 0, getName());
				if (zproduct.getproduct_code() != null)
					product.setValue(zproduct.getproduct_code());
				else
					product.setValue(zproduct.getproduct_name());

				product.setName(zproduct.getproduct_name());
				product.setM_Product_Category_ID(ademCatID);
				product.setC_TaxCategory_ID(1000000);
				product.setC_UOM_ID(100);
				product.saveEx(get_TrxName());
				importPrice(zproduct, product);

			}
		} catch (Exception e)
		{

		}
	}

	int count = 1;

	void importPrice(ZProduct zproduct, MProduct product)
	{

		String sql = "select * from z_bama_price where storage_productid =" + zproduct.getz_bama_product_ID();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				System.out.println("Price:" + count);
				count++;

				ZPrice zprice = new ZPrice(getCtx(), rs, get_TrxName());
				MProductPrice productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
				productPrice.setM_Product_ID(product.get_ID());
				if (zprice.getstorage_pricelistid() == 1)
					productPrice.setM_PriceList_Version_ID(1000003);
				else if (zprice.getstorage_pricelistid() == 2)
					productPrice.setM_PriceList_Version_ID(1000004);

				productPrice.setPriceList(zprice.getstorage_unitprice());
				productPrice.setPriceStd(zprice.getstorage_unitprice());
				productPrice.setPriceLimit(zprice.getstorage_unitprice());

				productPrice.saveEx(get_TrxName());
			}
		} catch (Exception e)
		{
			System.out.println("message:::" + e.getMessage());
		}

	}

	private void importInsurance()
	{
		String sql = "SELECT * FROM z_bama_ins ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				ZInsurance zi = new ZInsurance(getCtx(), rs, get_TrxName());
				MInsuranceCompany mins = new MInsuranceCompany(getCtx(), 0, get_TrxName());
				mins.setcompanyname(zi.getinsurance_name());
				mins.setemailadd(zi.getinsurance_email());
				mins.setpaddress(zi.getinsurance_town());
				mins.save();
				importInsuranceComm(zi.get_ID(), mins.get_ID());

			}
		} catch (Exception ex)
		{

		}
	}

	private void importInsuranceComm(int oldID, int ademID)
	{
		String sql = "SELECT * FROM z_bama_ins_co WHERE inscomp_insuranceid = " + oldID;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				ZInsuranceCompany zi = new ZInsuranceCompany(getCtx(), rs, get_TrxName());
				MInsuredCompany mins = new MInsuredCompany(getCtx(), 0, get_TrxName());
				mins.sethms_insco_ID(ademID);
				mins.seticoname(zi.getinscomp_name());
				mins.setinscomp_boxno(zi.getinscomp_boxno());

				mins.setinscomp_email(zi.getinscomp_email());
				mins.setinscomp_fax(zi.getinscomp_fax());
				mins.setinscomp_town(zi.getinscomp_town());
				mins.setinscomp_tel(zi.getinscomp_tel());

				mins.save();
				System.out.println(count);
				count++;

			}
		} catch (Exception ex)
		{

		}
	}

	void updateProductCode()
	{
		String sql = "select * from M_product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next())
			{

				MProduct product = new MProduct(getCtx(), rs, get_TrxName());
				product.setValue(product.getName());
				product.saveEx();
				System.out.println(count);
				count++;
			}
		} catch (Exception e)
		{

		}

	}
}
