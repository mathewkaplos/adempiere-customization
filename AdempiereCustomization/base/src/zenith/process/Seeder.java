//package zenith.process;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Time;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
//import org.compiere.model.MAttributeSetInstance;
//import org.compiere.model.MInOut;
//import org.compiere.model.MInOutLine;
//import org.compiere.model.MProduct;
//import org.compiere.model.MProductPrice;
//import org.compiere.process.SvrProcess;
//import org.compiere.util.DB;
//import org.compiere.util.Env;
//
//import com.google.common.collect.MapDifference;
//import com.google.common.collect.Maps;
//
//import zenith.model.MTest;
//import zenith.model.MTreatmentDoc;
//import zenith.model.ZPrice;
//import zenith.model.ZProduct;
//import zenith.process.Seeder.ZPriceList;
//
//public class Seeder extends SvrProcess
//{
//
//	@Override
//	protected void prepare()
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected String doIt() throws Exception
//	{
//		// updatePrices();
//		// seedStock();
//		// createLabProducts();
//		// updateDocName();
//		// updateDob();
//		// updatePatientID();
//		// updateProduct();
//		// updateProductCat();
//		// updatePriceList();
//		// executeUpdate();
//
//		// updateEmptyPrice();
//		updateStorage();
//		return null;
//	}
//
//	private void updateStorage()
//	{
//		HashSet<Integer> set1 = new HashSet<>();
//
//		String s1 = "select M_Product_ID from M_Product";
//		PreparedStatement p1 = null;
//		ResultSet r1 = null;
//		try
//		{
//			p1 = DB.prepareStatement(s1, get_TrxName());
//			r1 = p1.executeQuery();
//			while (r1.next())
//			{
//
//				int M_Product_ID = r1.getInt(1);
//				set1.add(M_Product_ID);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 1: " + e.getMessage());
//		}
//
//		HashSet<Integer> set2 = new HashSet<>();
//		String s2 = "select M_Product_ID from M_Storage";
//		PreparedStatement p2 = null;
//		ResultSet r2 = null;
//		try
//		{
//			p2 = DB.prepareStatement(s2, get_TrxName());
//			r2 = p2.executeQuery();
//			while (r2.next())
//			{
//
//				int M_Product_ID = r2.getInt(1);
//				set2.add(M_Product_ID);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 2: " + e.getMessage());
//		}
//
//		set1.removeAll(set2);
//
//		System.out.println("Size:" + set1.size());
//		MInOut io = new MInOut(getCtx(), 1000468, get_TrxName());
//		for (Integer M_Product_ID : set1)
//		{
//
//			MProduct pr = MProduct.get(getCtx(), M_Product_ID);
//
//			if (pr.isItem())
//			{
//				MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
//				line.setM_InOut_ID(io.get_ID());
//				line.setM_Product_ID(M_Product_ID, true);
//				line.setQty(new BigDecimal(1000));
//				line.setM_Locator_ID(1000004);
//				line.setM_AttributeSetInstance_ID(MAttributeSetInstance
//						.create(getCtx(), MProduct.get(getCtx(), M_Product_ID), get_TrxName()).get_ID());
//				line.save(get_TrxName());
//				System.out.println(count);
//
//				System.out.println("..........");
//				count++;
//			}
//		}
//		//System.out.println(io.completeIt());
//		io.save(get_TrxName());
//	}
//
//	private void updateEmptyPrice()
//	{
//		HashSet<Integer> set1 = new HashSet<>();
//
//		String s1 = "select M_Product_ID from M_Product";
//		PreparedStatement p1 = null;
//		ResultSet r1 = null;
//		try
//		{
//			p1 = DB.prepareStatement(s1, get_TrxName());
//			r1 = p1.executeQuery();
//			while (r1.next())
//			{
//
//				int M_Product_ID = r1.getInt(1);
//				set1.add(M_Product_ID);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 1: " + e.getMessage());
//		}
//
//		HashSet<Integer> set2 = new HashSet<>();
//		String s2 = "select M_Product_ID from M_Productprice";
//		PreparedStatement p2 = null;
//		ResultSet r2 = null;
//		try
//		{
//			p2 = DB.prepareStatement(s2, get_TrxName());
//			r2 = p2.executeQuery();
//			while (r2.next())
//			{
//
//				int M_Product_ID = r2.getInt(1);
//				set2.add(M_Product_ID);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 2: " + e.getMessage());
//		}
//
//		set1.removeAll(set2);
//
//		System.out.println("Size:" + set1.size());
//		for (Integer M_Product_ID : set1)
//		{
//			BigDecimal bd = Env.ONEHUNDRED;
//			MProductPrice productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
//			productPrice.setM_Product_ID(M_Product_ID);
//			productPrice.setM_PriceList_Version_ID(1000003);
//			productPrice.setPriceList(bd);
//			productPrice.setPriceStd(bd);
//			productPrice.setPriceLimit(bd);
//			productPrice.saveEx(get_TrxName());
//
//			MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
//			productPrice2.setM_Product_ID(M_Product_ID);
//			productPrice2.setM_PriceList_Version_ID(1000004);
//			productPrice2.setPriceList(bd);
//			productPrice2.setPriceStd(bd);
//			productPrice2.setPriceLimit(bd);
//			productPrice2.saveEx(get_TrxName());
//
//			System.out.println(count);
//
//			System.out.println("..........");
//			count++;
//		}
//
//	}
//
//	private void executeUpdate()
//	{
//		System.out.println("zCategory:" + zCategory.size());
//		System.out.println("zPriceList:" + zPriceList.size());
//		System.out.println("zProduct:" + zProduct.size());
//
//		for (String string : zProduct.keySet())
//		{
//			ZProduct zproduct = zProduct.get(string);
//			MProduct product = new MProduct(getCtx(), 0, getName());
//
//			product.setValue(zproduct.getProduct_name());
//
//			product.setName(zproduct.getProduct_name());
//			if (zCategory.get(zproduct.getProduct_id()) != null)
//				product.setM_Product_Category_ID(zCategory.get(zproduct.getProduct_id()));
//			else
//				product.setM_Product_Category_ID(1000069);
//			product.setC_TaxCategory_ID(1000000);
//			product.setC_UOM_ID(100);
//			product.saveEx(get_TrxName());
//
//			ZPriceList zprice = zPriceList.get(string);
//			if (zprice != null)
//			{
//				MProductPrice productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
//				productPrice.setM_Product_ID(product.get_ID());
//				productPrice.setM_PriceList_Version_ID(1000003);
//				productPrice.setPriceList(zprice.getStorage_unitprice());
//				productPrice.setPriceStd(zprice.getStorage_unitprice());
//				productPrice.setPriceLimit(zprice.getStorage_unitprice());
//				productPrice.saveEx(get_TrxName());
//
//				MProductPrice productPrice2 = new MProductPrice(getCtx(), 0, get_TrxName());
//				productPrice2.setM_Product_ID(product.get_ID());
//				productPrice2.setM_PriceList_Version_ID(1000004);
//				productPrice2.setPriceList(zprice.getStorage_unitprice());
//				productPrice2.setPriceStd(zprice.getStorage_unitprice());
//				productPrice2.setPriceLimit(zprice.getStorage_unitprice());
//				productPrice2.saveEx(get_TrxName());
//			}
//
//			System.out.println(count);
//
//			System.out.println("..........");
//			count++;
//		}
//
//	}
//
//	HashMap<Integer, Integer> zCategory = new HashMap<>();
//	HashMap<String, ZPriceList> zPriceList = new HashMap<>();
//	Map<String, ZProduct> zProduct = new HashMap<>();
//
//	private void updatePriceList()
//	{
//
//		String s1 = "select product_id, product_name,storage_unitprice, storage_pricelistid from adempiere.z_pricelist pl "
//				+ "  join adempiere.z_product pr on pr.product_id = pl.storage_productid order by pr.product_id";
//		PreparedStatement p1 = null;
//		ResultSet r1 = null;
//		try
//		{
//			p1 = DB.prepareStatement(s1, get_TrxName());
//			r1 = p1.executeQuery();
//			while (r1.next())
//			{
//
//				int product_id = r1.getInt(1);
//				String product_name = r1.getString(2);
//				BigDecimal storage_unitprice = r1.getBigDecimal(3);
//				int storage_pricelistid = r1.getInt(1);
//
//				if (product_name != null)
//				{
//					ZPriceList zl = new ZPriceList(product_id, product_name.trim().toUpperCase(), storage_unitprice,
//							storage_pricelistid);
//					zPriceList.put(product_name.trim().toUpperCase(), zl);
//				}
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 1: " + e.getMessage());
//		}
//	}
//
//	class ZPriceList
//	{
//		private int product_id = 0;
//		private String product_name = "";
//		private BigDecimal storage_unitprice = Env.ZERO;
//		int storage_pricelistid = 0;
//
//		public ZPriceList(int product_id, String product_name, BigDecimal storage_unitprice, int storage_pricelistid)
//		{
//			super();
//			this.product_id = product_id;
//			this.product_name = product_name;
//			this.storage_unitprice = storage_unitprice;
//			this.storage_pricelistid = storage_pricelistid;
//		}
//
//		public int getProduct_id()
//		{
//			return product_id;
//		}
//
//		public void setProduct_id(int product_id)
//		{
//			this.product_id = product_id;
//		}
//
//		public String getProduct_name()
//		{
//			return product_name;
//		}
//
//		public void setProduct_name(String product_name)
//		{
//			this.product_name = product_name;
//		}
//
//		public BigDecimal getStorage_unitprice()
//		{
//			return storage_unitprice;
//		}
//
//		public void setStorage_unitprice(BigDecimal storage_unitprice)
//		{
//			this.storage_unitprice = storage_unitprice;
//		}
//
//		public int getStorage_pricelistid()
//		{
//			return storage_pricelistid;
//		}
//
//		public void setStorage_pricelistid(int storage_pricelistid)
//		{
//			this.storage_pricelistid = storage_pricelistid;
//		}
//
//		@Override
//		public String toString()
//		{
//			return "ZPriceList [product_id=" + product_id + ", product_name=" + product_name + ", storage_unitprice="
//					+ storage_unitprice + ", storage_pricelistid=" + storage_pricelistid + "]";
//		}
//
//	}
//
//	private void updateProductCat()
//	{
//		HashMap<String, Integer> map1 = new HashMap<>();
//
//		String s1 = "select category_id , category_name from z_category";
//		PreparedStatement p1 = null;
//		ResultSet r1 = null;
//		try
//		{
//			p1 = DB.prepareStatement(s1, get_TrxName());
//			r1 = p1.executeQuery();
//			while (r1.next())
//			{
//
//				int category_id = r1.getInt(1);
//				String category_name = r1.getString(2);
//				if (category_name != null)
//					map1.put(category_name.trim().toUpperCase(), category_id);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 1: " + e.getMessage());
//		}
//
//		HashMap<String, Integer> map2 = new HashMap<>();
//		String s2 = "select m_product_category_id, name from m_product_category";
//		PreparedStatement p2 = null;
//		ResultSet r2 = null;
//		try
//		{
//			p2 = DB.prepareStatement(s2, get_TrxName());
//			r2 = p2.executeQuery();
//			while (r2.next())
//			{
//
//				int m_product_category_id = r2.getInt(1);
//				String name = r2.getString(2);
//				if (name != null)
//					map2.put(name.trim().toUpperCase(), m_product_category_id);
//			}
//
//		} catch (Exception e)
//		{
//			System.out.println("error 2: " + e.getMessage());
//		}
//
//		MapDifference<String, Integer> diff = Maps.difference(map1, map2);
//
//		Set<String> common = diff.entriesDiffering().keySet();
//
//		System.out.println("Size:" + common.size());
//		for (String string : common)
//		{
//			int a = map1.get(string);
//			int b = map2.get(string);
//
//			zCategory.put(a, b);
//		}
//
//	}
//
//	private void updateProduct()
//	{
//
//		HashMap<String, ZProduct> map = new HashMap<>();
//
//		String select = "Select product_id,product_name,product_category from adempiere.z_product ";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(select, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				int product_id = rs.getInt(1);
//				String product_name = rs.getString(2);
//				int product_category = rs.getInt(3);
//
//				if (product_name != null)
//				{
//					ZProduct zp = new ZProduct(product_id, product_name.trim().toUpperCase(), product_category);
//					map.put(product_name.toUpperCase().trim(), zp);
//				}
//
//			}
//		} catch (Exception e)
//		{
//			System.out.println("error:" + e.getMessage());
//		}
//		// M_Product
//		HashMap<String, ZProduct> map2 = new HashMap<>();
//		String sql = "select name from adempiere.m_product ";
//		PreparedStatement p2 = null;
//		ResultSet r2 = null;
//		try
//		{
//			p2 = DB.prepareStatement(sql, get_TrxName());
//			r2 = p2.executeQuery();
//			while (r2.next())
//			{
//				String name = r2.getString(1);
//				map2.put(name.toUpperCase().trim(), null);
//			}
//		} catch (Exception e)
//		{
//			System.out.println("error 2:" + e.getMessage());
//		}
//
//		/// System.out.println(Arrays.asList(map));
//		/*
//		 * for (String name : map.keySet()) {
//		 * 
//		 * String key = name.toString(); String value =
//		 * map.get(name).toString(); System.out.println(key + " --- " + value);
//		 * 
//		 * }
//		 */
//
//		MapDifference<String, ZProduct> diff = Maps.difference(map, map2);
//		zProduct = diff.entriesOnlyOnLeft();
//
//	}
//
//	class ZProduct
//	{
//		private int product_id = 0;
//		private String product_name = "";
//		private int product_category = 0;
//
//		public ZProduct(int product_id, String product_name, int product_category)
//		{
//			super();
//			this.product_id = product_id;
//			this.product_name = product_name;
//			this.product_category = product_category;
//		}
//
//		public int getProduct_id()
//
//		{
//			return product_id;
//		}
//
//		public void setProduct_id(int product_id)
//		{
//			this.product_id = product_id;
//		}
//
//		public String getProduct_name()
//		{
//			return product_name;
//		}
//
//		public void setProduct_name(String product_name)
//		{
//			this.product_name = product_name;
//		}
//
//		public int getProduct_category()
//		{
//			return product_category;
//		}
//
//		public void setProduct_category(int product_category)
//		{
//			this.product_category = product_category;
//		}
//
//		@Override
//		public String toString()
//		{
//			return "ZProduct [product_id=" + product_id + ", product_name=" + product_name + ", product_category="
//					+ product_category + "]";
//		}
//
//	}
//
//	private void updatePatientID()
//	{
//		String select = "Select c_bpartner_id from adempiere.c_bpartner ";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(select, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				int id = rs.getInt(1);
//				String update = "update adempiere.c_bpartner set ref_id =" + id + " where c_bpartner_id =" + id;
//				DB.executeUpdate(update, get_TrxName());
//
//			}
//		} catch (Exception e)
//		{
//
//		}
//	}
//
//	private void updateDob()
//	{
//		String sql = "select C_BPartner_ID ,dob from C_BPartner";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				MTreatmentDoc doc = new MTreatmentDoc(getCtx(), rs, get_TrxName());
//
//				DateFormat tf = new SimpleDateFormat("EEE, MMM d,yyy  HH:mm");
//				Date date = new Date(doc.getCreated().getTime());
//				String textDate = tf.format(date);
//
//				doc.setName(textDate);
//
//				doc.save();
//			}
//		} catch (Exception e)
//		{
//
//		}
//	}
//
//	private void updateDocName()
//	{
//		String sql = "select * from hms_treatment_doc";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				MTreatmentDoc doc = new MTreatmentDoc(getCtx(), rs, get_TrxName());
//
//				DateFormat tf = new SimpleDateFormat("EEE, MMM d,yyy  HH:mm");
//				Date date = new Date(doc.getCreated().getTime());
//				String textDate = tf.format(date);
//
//				doc.setName(textDate);
//
//				doc.save();
//			}
//		} catch (Exception e)
//		{
//
//		}
//	}
//
//	int count = 1;
//
//	void updatePrices()
//	{
//		String sql = "select m_product_id from m_product";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				int m_product_id = rs.getInt(1);
//				String sql2 = "select * from m_productprice where m_product_id=" + m_product_id;
//				PreparedStatement pstmt2 = null;
//				ResultSet rs2 = null;
//				try
//				{
//					pstmt2 = DB.prepareStatement(sql2, get_TrxName());
//					rs2 = pstmt2.executeQuery();
//					if (!rs2.next())
//					{
//						MProductPrice pp = new MProductPrice(getCtx(), 0, get_TrxName());
//						pp.setM_Product_ID(m_product_id);
//						pp.setM_PriceList_Version_ID(1000003);
//						pp.setPriceList(new BigDecimal(20));
//						pp.setPriceStd(new BigDecimal(20));
//						pp.setPriceLimit(new BigDecimal(20));
//						pp.saveEx(get_TrxName());
//
//						MProductPrice pp2 = new MProductPrice(getCtx(), 0, get_TrxName());
//						pp2.setM_Product_ID(m_product_id);
//						pp2.setM_PriceList_Version_ID(1000004);
//						pp2.setPriceList(new BigDecimal(20));
//						pp2.setPriceStd(new BigDecimal(20));
//						pp2.setPriceLimit(new BigDecimal(20));
//						pp2.save(get_TrxName());
//						System.out.println(count);
//						count++;
//
//					}
//				} catch (Exception e)
//				{
//
//				}
//			}
//		} catch (Exception e)
//		{
//
//		}
//	}
//
//	void seedStock()
//	{
//		MInOut io = new MInOut(getCtx(), 1000628, get_TrxName());
//
//		String sql = "select m_product_id from m_product where producttype='I'";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				int m_product_id = rs.getInt(1);
//				// MProductPrice pp = MProductPrice.get(getCtx(), 1000003,
//				// m_product_id, get_TrxName());
//				/// BigDecimal bd = pp.getPriceList();
//
//				MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
//				line.setM_InOut_ID(io.get_ID());
//				line.setM_Product_ID(m_product_id, true);
//				line.setQty(new BigDecimal(1000));
//
//				line.setM_Locator_ID(1000004);
//
//				line.setM_AttributeSetInstance_ID(MAttributeSetInstance
//						.create(getCtx(), MProduct.get(getCtx(), m_product_id), get_TrxName()).get_ID());
//				line.save();
//				System.out.println(count);
//				count++;
//			}
//		} catch (Exception e)
//		{
//
//		}
//		io.completeIt();
//	}
//
//	void createLabProducts()
//	{
//		String sql = "select hms_test_id, name,price from hms_test where m_product_id is null";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//				int test_id = rs.getInt(1);
//				String name = rs.getString(2);
//				BigDecimal bd = rs.getBigDecimal(3);
//
//				MTest test = new MTest(getCtx(), test_id, get_TrxName());
//
//				MProduct product = new MProduct(getCtx(), 0, get_TrxName());
//				product.setName(name.toUpperCase());
//				product.setM_Product_Category_ID(1000082);
//				product.setValue(name.toUpperCase());
//				product.setC_UOM_ID(100);
//				product.setProductType("S");
//				product.setC_TaxCategory_ID(1000000);
//				product.saveEx(get_TrxName());
//
//				MProductPrice pp = new MProductPrice(getCtx(), 0, get_TrxName());
//				pp.setM_Product_ID(product.get_ID());
//				pp.setM_PriceList_Version_ID(1000003);
//				pp.setPriceList(bd);
//				pp.setPriceStd(bd);
//				pp.setPriceLimit(bd);
//				pp.saveEx(get_TrxName());
//
//				MProductPrice pp2 = new MProductPrice(getCtx(), 0, get_TrxName());
//				pp2.setM_Product_ID(product.get_ID());
//				pp2.setM_PriceList_Version_ID(1000004);
//				pp2.setPriceList(bd);
//				pp2.setPriceStd(bd);
//				pp2.setPriceLimit(bd);
//				pp2.save(get_TrxName());
//
//				test.setM_Product_ID(product.get_ID());
//				test.saveEx(get_TrxName());
//				System.out.println(count);
//				count++;
//			}
//		} catch (Exception e)
//		{
//
//		}
//
//	}
//}
