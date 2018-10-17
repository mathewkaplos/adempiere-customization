package zenith.imports;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import zenith.model.X_hms_stock_adjustment;
import zenith.model.X_hms_stock_adjustment_line;
import zenith.util.DateUtil;

public class SeedStock extends SvrProcess
{
	
	@Override
	protected void prepare()
	{

	}

	@Override
	protected String doIt() throws Exception
	{
		seedStock();
		return null;
	}

	int count = 1;

	void seedStock()
	{
		MInOut io = new MInOut(getCtx(), 1000689, get_TrxName());

		String sql = "select M_Product_ID FROM adempiere.M_Product where producttype ='I' "
				+ " AND M_Attributeset_ID >0 AND M_Product_ID NOT IN (select M_Product_ID from adempiere.m_storage)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int m_product_id = rs.getInt(1);
				// MProductPrice pp = MProductPrice.get(getCtx(), 1000003,
				// m_product_id, get_TrxName());
				/// BigDecimal bd = pp.getPriceList();

				MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
				line.setM_InOut_ID(io.get_ID());
				line.setM_Product_ID(m_product_id, true);
				line.setQty(new BigDecimal(1000));

				line.setM_Locator_ID(1000007);
				MAttributeSetInstance asi = MAttributeSetInstance.create(getCtx(), MProduct.get(getCtx(), m_product_id),
						get_TrxName());
				Timestamp time = null;
				try
				{
					String date = "2018-12-31 16:32:39";
					SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
					Date pd = df.parse(date);
					time = new Timestamp(pd.getTime());

				} catch (Exception e)
				{

				}
				asi.setGuaranteeDate(time);
				line.setM_AttributeSetInstance_ID(asi.get_ID());
				line.save();
				System.out.println(count);
				count++;
			}
		} catch (Exception e)
		{

		}
		io.completeIt();
	}

	private void adjustment()
	{
		X_hms_stock_adjustment adj = new X_hms_stock_adjustment(getCtx(), 0, get_TrxName());
	}
}
