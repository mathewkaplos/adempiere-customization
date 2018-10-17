package org.zenith.model.strategy.fromcashier;

import java.util.Properties;

import org.compiere.model.MOrderLine;

public interface CashierStrategy {
	void execute(Properties ctx,MOrderLine ol,int doc_ID);
}
