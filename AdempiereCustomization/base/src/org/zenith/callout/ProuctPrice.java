package org.zenith.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class ProuctPrice extends CalloutEngine
{
	public String priceList(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		BigDecimal m_Value = (BigDecimal) value;

		mTab.setValue("PriceLimit", m_Value);
		mTab.setValue("PriceStd", m_Value);
		return "";
	}

}
