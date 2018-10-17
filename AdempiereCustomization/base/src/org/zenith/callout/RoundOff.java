package org.zenith.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;
import java.util.function.BiFunction;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrderLine;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_C_OrderLine;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class RoundOff extends CalloutEngine
{
	// Roundings
	BigDecimal toOne = new BigDecimal(1);
	BigDecimal toFive = new BigDecimal(5);
	BigDecimal toTen = new BigDecimal(10);
	BigDecimal toFifty = new BigDecimal(50);
	BigDecimal toHundred = new BigDecimal(100);
	BigDecimal toTwoHundred = new BigDecimal(200);
	BigDecimal toFiveHundred = new BigDecimal(500);
	BigDecimal oneThousand = new BigDecimal(1000);

	public String round(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		// rounded_lineamt

		if (mField.getColumnName().equals("rounding_level"))
		{
			// Level Value...A for 5, B for 10 , C for 50, D for 100.
			// ... Otherwise default to 1
			
			String levelValue = value.toString();
			BigDecimal levelRounding = levelValue.equals("A") ? toFive
					: levelValue.equals("B") ? toTen
							: levelValue.equals("C") ? toFifty : levelValue.equals("D") ? toHundred : toOne;

			String trxName = Trx.createTrxName();
			int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
			final String whereClause = "C_BPartner_ID=? AND pay='Y' AND paid='N'";
			Query query = MTable.get(ctx, X_C_OrderLine.Table_ID).createQuery(whereClause, trxName);
			query.setParameters(C_BPartner_ID).setOnlyActiveRecords(true);
			List<MOrderLine> list = query.list();
			System.out.println(list.size());

			MOrderLine[] lines = list.toArray(new MOrderLine[list.size()]);
			for (int i = 0; i < lines.length; i++)
			{
				BigDecimal LineNetAmt = lines[i].getLineNetAmt();
				// Rounding Up
				BiFunction<BigDecimal, BigDecimal, BigDecimal> f1 = (num, div) -> div.compareTo(toOne) == 0 ? num
						: div.multiply(((num.divide(div)).setScale(0, RoundingMode.CEILING)));

				lines[i].setRoundedLineamt(f1.apply(LineNetAmt, levelRounding));
				lines[i].setRoundingTo(levelRounding);
				lines[i].save();
			}
			try
			{
				Trx.get(trxName, false).close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		return "";
	}
}
