package org.zenith.util.lab;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;

import zenith.model.MParameter;
import zenith.model.MParameterRange;
import zenith.model.MResult;
import zenith.model.MSpecimen;
import zenith.model.MSpecimenRequest;
import zenith.model.MSpecimenRequestLine;
import zenith.model.MTest;

public class SaveParameters
{
	Properties ctx = null;
	MSpecimenRequest req = null;
	public static int patient_age = 0; // patient age in years
	private String trxName;

	public SaveParameters(MSpecimenRequest req, String trxName)
	{
		ctx = Env.getCtx();
		this.trxName = trxName;
		;
		this.req = req;
		// set patient age
		MBPartner bp = new MBPartner(Env.getCtx(), req.getC_BPartner_ID(), trxName);
		if (bp.getDob() != null)
			patient_age = calculateAge(bp.getDob());
	}

	public void save()
	{
		newRequestLine();
	}

	private void newRequestLine()
	{
		Integer[] specIDs = getSpecimenIDs(req.gethms_test_ID());
		for (int i = 0; i < specIDs.length; i++)
		{
			MSpecimenRequestLine line = new MSpecimenRequestLine(Env.getCtx(), 0, trxName);
			line.sethms_specimen_requests_ID(req.gethms_specimen_requests_ID());
			line.sethms_test_ID(req.gethms_test_ID());
			line.sethms_specimens_ID(specIDs[i]);
			line.setC_BPartner_ID(req.getC_BPartner_ID());
			line.sethms_treatment_doc_ID(req.gethms_treatment_doc_ID());
			line.save();
			newResult(line);
		}
	}

	private Integer[] getSpecimenIDs(int hms_test_id)
	{
		List<Integer> list = new ArrayList<Integer>();
		String sql = "SELECT hms_specimens_id FROM hms_specimens WHERE hms_test_id=? "; // #1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, hms_test_id);
			rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				int hms_lab_specimens_id = rs.getInt(1);
				list.add(hms_lab_specimens_id);
				x++;
			}
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		if (x == 0)
		{
			MTest test = new MTest(ctx, hms_test_id, trxName);
			MSpecimen s = new MSpecimen(ctx, 0, trxName);
			s.sethms_test_ID(test.get_ID());
			s.setName(test.getName());
			s.save();

			MParameter para = new MParameter(ctx, 0, trxName);
			para.sethms_specimens_ID(s.get_ID());
			para.setName(test.getName());
			para.save();
			list.add(s.get_ID());
		}
		Integer[] specIDs = new Integer[list.size()];
		return list.toArray(specIDs);
	}

	private void newResult(MSpecimenRequestLine line)
	{
		MParameter[] params = getParameters(line.gethms_specimens_ID());

		for (int i = 0; i < params.length; i++)
		{
			MResult result = new MResult(ctx, 0, trxName);
			result.setC_BPartner_ID(line.getC_BPartner_ID());
			result.sethms_treatment_doc_ID(line.gethms_treatment_doc_ID());
			result.sethms_test_ID(line.gethms_test_ID());
			result.sethms_specimens_ID(line.gethms_specimens_ID());
			result.sethms_specimen_r_line_ID(line.gethms_specimen_r_line_ID());
			result.sethms_specimen_requests_ID(line.gethms_specimen_requests_ID());
			result.sethms_parameters_ID(params[i].gethms_parameters_ID());
			result.setrange(getRange(params[i].get_ID()));
			result.setunits(params[i].getunits());
			result.save();
		}
	}

	/**
	 * Get Specimen Parameters
	 * 
	 * @param hms_specimens_id
	 * @return
	 */
	private MParameter[] getParameters(int hms_specimens_id)
	{
		List<MParameter> list = new ArrayList<MParameter>();
		String sql = "SELECT hms_parameters_id FROM hms_parameters WHERE hms_specimens_id=? "; // #1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try
		{

			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, hms_specimens_id);
			rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				int hms_parameters_id = rs.getInt(1);
				MParameter param = new MParameter(ctx, hms_parameters_id, trxName);
				list.add(param);
				x++;

			}
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		} finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		if (x == 0)
		{
			MSpecimen s = new MSpecimen(ctx, hms_specimens_id, trxName);

			MParameter para = new MParameter(ctx, 0, trxName);
			para.sethms_specimens_ID(s.get_ID());
			para.setName(s.getName());
			para.save();
			list.add(para);

		}
		MParameter[] params = new MParameter[list.size()];
		return list.toArray(params);
	}

	private String getRange(int hms_parameters_ID)
	{
		StringBuilder sb = new StringBuilder();
		MParameter param = new MParameter(ctx, hms_parameters_ID, trxName);
		if (param.ishas_range())
		{
			String sql = "SELECT * FROM hms_parameter_range WHERE hms_parameters_ID=" + hms_parameters_ID;
			PreparedStatement pstmt = null;
			// BigDecimal age = BigDecimal.valueOf(55);
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					MParameterRange range = new MParameterRange(ctx, rs, trxName);
					if (param.is_age_based())
					{
						if (BigDecimal.valueOf(patient_age).compareTo(range.getage_from()) >= 0
								&& BigDecimal.valueOf(patient_age).compareTo(range.getage_to()) < 0)
						{
							sb.append(range.getmin_value().setScale(2) + " --> " + range.getmax_value().setScale(2));
							if (range.ishas_power())
							{
								sb = new StringBuilder("(" + sb + ")");
								sb.append(" X " + range.getpower());
							}
							break;
						}
					} else
					{ // select only one
						sb.append(range.getmin_value().setScale(2) + " --> " + range.getmax_value().setScale(2));
						if (range.ishas_power())
						{
							sb = new StringBuilder("(" + sb + ")");
							sb.append(" X " + range.getpower());
						}
						break;
					}
				}
			} catch (Exception e)
			{
			}
			try
			{
				if (pstmt != null)
					pstmt.close();
				pstmt = null;

			} catch (Exception e)
			{
				pstmt = null;
			} finally
			{
				DB.close(pstmt);
				pstmt = null;
			}
		}
		return sb.toString();
	}

	private static int calculateAge(Timestamp birthDate)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		LocalDate today = now.toLocalDateTime().toLocalDate();
		LocalDate dob = birthDate.toLocalDateTime().toLocalDate();
		if ((dob != null) && (today != null))
		{
			return Period.between(dob, today).getYears();
		} else
		{
			return 0;
		}
	}

}
