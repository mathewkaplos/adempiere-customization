package zenith.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

public class MResult extends X_hms_lab_results
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MResult(Properties ctx, int hms_lab_results_ID, String trxName)
	{
		super(ctx, hms_lab_results_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MResult(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void newResult(MSpecimenRequestLine line)
	{
		MParameter[] params = getParameters(line.gethms_specimens_ID());

		for (int i = 0; i < params.length; i++)
		{
			MResult result = new MResult(getCtx(), 0, get_TrxName());
			result.setC_BPartner_ID(line.getC_BPartner_ID());
			result.sethms_treatment_doc_ID(line.gethms_treatment_doc_ID());
			result.sethms_test_ID(line.gethms_test_ID());
			result.sethms_specimens_ID(line.gethms_specimens_ID());
			result.sethms_specimen_r_line_ID(line.gethms_specimen_r_line_ID());

			result.sethms_parameters_ID(params[i].gethms_parameters_ID());
			result.setrange(params[i].getmin_value() + " - " + params[i].getmax_value());
			result.setunits(params[i].getunits());

			result.save();
		}
	}

	public MParameter[] getParameters(int hms_specimens_id)
	{
		List<MParameter> list = new ArrayList<MParameter>();
		String sql = "SELECT hms_parameters_id FROM hms_parameters WHERE hms_specimens_id=? "; // #1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, hms_specimens_id);
			rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				int hms_parameters_id = rs.getInt(1);
				MParameter param = new MParameter(getCtx(), hms_parameters_id, get_TrxName());
				list.add(param);
			}
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		} finally
		{
			pstmt = null;
		}
		MParameter[] params = new MParameter[list.size()];
		return list.toArray(params);
	}
}
