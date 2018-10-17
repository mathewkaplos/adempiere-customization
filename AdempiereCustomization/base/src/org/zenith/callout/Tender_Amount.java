package org.zenith.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class Tender_Amount extends CalloutEngine {
	public String Amount(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		BigDecimal m_value = (BigDecimal) value;
		if (m_value == null || m_value.intValue() == 0)
			return "";
		// amt_topay
		BigDecimal amt_topay = (BigDecimal) mTab.getValue("amt_topay");
		 BigDecimal Balance = Env.ZERO;
		  BigDecimal PayAmt = Env.ZERO;

		// when the tender amount is more than amt_topay
		if (m_value.compareTo(amt_topay) > 0) {
			PayAmt=amt_topay;
		} else {
			PayAmt=m_value;
		}
		Balance = m_value.subtract(amt_topay);
		// Balance
		// Balance = m_value.subtract(PayAmt);
		mTab.setValue("PayAmt", PayAmt);
		mTab.setValue("Balance", Balance);

		System.out.println("got this.." + amt_topay);
		return null;
	}
}
