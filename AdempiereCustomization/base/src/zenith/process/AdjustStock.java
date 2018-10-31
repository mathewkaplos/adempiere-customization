package zenith.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.StockAdjustmentLine;

public class AdjustStock extends SvrProcess
{

	private BigDecimal QtyCount = Env.ZERO;
	private int M_Product_ID = 0;
	private int M_Locator_ID = 0;
	private int M_AttributeSetInstance_ID = 0;
	private String Lot = "";
	private Timestamp GuaranteeDate = null;
	private BigDecimal QtyOnHand = Env.ZERO;
	private String Description = "";

	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameterName() == null)
				;
			else if (name.equals("QtyCount"))
			{
				QtyCount = ((BigDecimal) para[i].getParameter()).setScale(0);
			} else if (name.equals("M_Product_ID"))
			{
				M_Product_ID = para[i].getParameterAsInt();
			} else if (name.equals("M_Locator_ID"))
			{
				M_Locator_ID = para[i].getParameterAsInt();
			} else if (name.equals("M_AttributeSetInstance_ID"))
			{
				M_AttributeSetInstance_ID = para[i].getParameterAsInt();
			} else if (name.equals("Lot"))
			{
				Lot = para[i].getParameterAsString();
			} else if (name.equals("GuaranteeDate"))
			{
				GuaranteeDate = para[i].getParameterToAsTimestamp();
			} else if (name.equals("QtyOnHand"))
			{
				QtyOnHand = ((BigDecimal) para[i].getParameter()).setScale(0, BigDecimal.ROUND_HALF_DOWN);
			} else if (name.equals("Description"))
			{
				Description = para[i].getParameterAsString();
			} else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// NEVER TODO Auto-generated method stub
	}

	@Override
	protected String doIt() throws Exception
	{
		newLine();
		updateStorage();
		return null;
	}

	void newLine()
	{
		StockAdjustmentLine line = new StockAdjustmentLine(getCtx(), 0, get_TrxName());
		line.setM_Product_ID(M_Product_ID);
		line.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		line.setLot(Lot);
		line.setQtyBook(QtyOnHand);
		line.setQtyCount(QtyCount);
		line.setGuaranteeDate(GuaranteeDate);
		line.setM_Locator_ID(M_Locator_ID);
		line.setDescription(Description);
		//line.save();  ///commented 
	}

	void updateStorage()
	{
		String sql = "SELECT * FROM m_storage WHERE M_Product_ID ='" + M_Product_ID + "' AND"
				+ " M_AttributeSetInstance_ID ='" + M_AttributeSetInstance_ID + "' " + "AND M_Locator_ID ='"
				+ M_Locator_ID + "' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				MStorage storage = new MStorage(getCtx(), rs, get_TrxName());
				storage.setQtyOnHand(QtyCount);
				storage.save();
			}
		} catch (Exception e)
		{

		}

	}
}
