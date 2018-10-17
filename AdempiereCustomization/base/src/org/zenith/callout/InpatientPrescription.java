package org.zenith.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class InpatientPrescription extends CalloutEngine
{
	// Unit Per Frequency
	public String unitsPerFrequency(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		BigDecimal m_value = (BigDecimal) value;
		BigDecimal scaled = m_value.setScale(0, BigDecimal.ROUND_HALF_UP);
		if (scaled == null || scaled.intValue() <= 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Value cannot be Zero(0) or less than Zero(0)");
			mTab.setValue("units_per_frequency", new BigDecimal(1));
		}
		mTab.setValue("units_per_frequency", scaled);
		String freq = "1";
		try
		{
			freq = (String) mTab.getValue("ward_frequency");
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BigDecimal freqBD = Env.ZERO;
		;
		try
		{
			freqBD = new BigDecimal(freq);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mTab.setValue("QtyOrdered", freqBD.multiply(scaled));

		if ((boolean) mTab.getValue("morning"))
		{
			mTab.setValue("morning_qty", scaled);
		}
		if ((boolean) mTab.getValue("midmorning"))
		{
			mTab.setValue("midmorning_qty", scaled);
		}
		if ((boolean) mTab.getValue("midday"))
		{
			mTab.setValue("midday_qty", scaled);
		}
		if ((boolean) mTab.getValue("afternoon"))
		{
			mTab.setValue("afternoon_qty", scaled);
		}
		if ((boolean) mTab.getValue("evenning"))
		{
			mTab.setValue("evenning_qty", scaled);
		}
		if ((boolean) mTab.getValue("midnight"))
		{
			mTab.setValue("midnight_qty", scaled);
		}

		return "";
	}

	// Frequency
	public String frequency(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		String m_value = (String) value;

		BigDecimal units_per_freq = (BigDecimal) mTab.getValue("units_per_frequency");

		BigDecimal freqBD = new BigDecimal(m_value);

		mTab.setValue("QtyOrdered", freqBD.multiply(units_per_freq));

		if (freqBD.compareTo(new BigDecimal(1)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", false);
			mTab.setValue("midday", false);
			mTab.setValue("afternoon", false);
			mTab.setValue("evenning", false);
			mTab.setValue("midnight", false);
		}

		if (freqBD.compareTo(new BigDecimal(2)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", false);
			mTab.setValue("midday", false);
			mTab.setValue("afternoon", false);
			mTab.setValue("evenning", true);
			mTab.setValue("midnight", false);
		}

		if (freqBD.compareTo(new BigDecimal(3)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", false);
			mTab.setValue("midday", true);
			mTab.setValue("afternoon", false);
			mTab.setValue("evenning", true);
			mTab.setValue("midnight", false);
		}

		if (freqBD.compareTo(new BigDecimal(4)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", true);
			mTab.setValue("midday", true);
			mTab.setValue("afternoon", false);
			mTab.setValue("evenning", true);
			mTab.setValue("midnight", false);
		}

		if (freqBD.compareTo(new BigDecimal(5)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", true);
			mTab.setValue("midday", true);
			mTab.setValue("afternoon", true);
			mTab.setValue("evenning", true);
			mTab.setValue("midnight", false);
		}

		if (freqBD.compareTo(new BigDecimal(6)) == 0)
		{
			mTab.setValue("morning", true);
			mTab.setValue("midmorning", true);
			mTab.setValue("midday", true);
			mTab.setValue("afternoon", true);
			mTab.setValue("evenning", true);
			mTab.setValue("midnight", true);
		}
		return "";
	}

	// Select Time
	public String selectTime(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		boolean m_value = (boolean) value;
		String qty_Column = mField.getColumnName() + "_qty";
		BigDecimal units_per_freq = (BigDecimal) mTab.getValue("units_per_frequency");
		if (m_value)
		{
			mTab.setValue(qty_Column, units_per_freq);
		} else
		{
			mTab.setValue(qty_Column, Env.ZERO);
		}
		//System.out.println(m_value);

		int count = 0;

		if ((boolean) mTab.getValue("morning"))
		{
			count++;
		}
		if ((boolean) mTab.getValue("midmorning"))
		{
			count++;
		}
		if ((boolean) mTab.getValue("midday"))
		{
			count++;
		}
		if ((boolean) mTab.getValue("afternoon"))
		{
			count++;
		}
		if ((boolean) mTab.getValue("evenning"))
		{
			count++;
		}
		if ((boolean) mTab.getValue("midnight"))
		{
			count++;
		}

		//System.out.println(count);

		if (count > 0)
		{
			mTab.setValue("ward_frequency", String.valueOf(count));
		} else
		{
			mTab.setValue("QtyOrdered", Env.ZERO);
		}
		if (count == 1)
		{
			mTab.setValue("QtyOrdered", units_per_freq);
		}
		return "";
	}

	// Timely Quantity
	public String timelyQuantity(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value)
	{
		BigDecimal m_value = (BigDecimal) value;
		BigDecimal scaled = m_value.setScale(0, BigDecimal.ROUND_HALF_UP);
		if (scaled == null || scaled.intValue() < 0)
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Value cannot be less than Zero(0)");
			mTab.setValue("units_per_frequency", new BigDecimal(1));
		}

		BigDecimal morning_qty = Env.ZERO;
		try
		{
			morning_qty = ((BigDecimal) mTab.getValue("morning_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal midmorning_qty = Env.ZERO;
		try
		{
			midmorning_qty = ((BigDecimal) mTab.getValue("midmorning_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal midday_qty = Env.ZERO;
		try
		{
			midday_qty = ((BigDecimal) mTab.getValue("midday_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal afternoon_qty = Env.ZERO;
		try
		{
			afternoon_qty = ((BigDecimal) mTab.getValue("afternoon_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal evenning_qty = Env.ZERO;
		try
		{
			evenning_qty = ((BigDecimal) mTab.getValue("evenning_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal midnight_qty = Env.ZERO;
		try
		{
			midnight_qty = ((BigDecimal) mTab.getValue("midnight_qty")).setScale(0);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(morning_qty);
		//System.out.println(midmorning_qty);
		//System.out.println(midday_qty);
		//System.out.println(afternoon_qty);
		//System.out.println(evenning_qty);
		//System.out.println(midnight_qty);

		BigDecimal sum = morning_qty.add(midmorning_qty).add(midday_qty).add(afternoon_qty).add(evenning_qty)
				.add(midnight_qty);

		mTab.setValue("QtyOrdered", sum);

		// BigDecimal newQtyOrdered =
		// mTab.setValue("QtyOrdered", newQtyOrdered);
		return "";
	}
}
