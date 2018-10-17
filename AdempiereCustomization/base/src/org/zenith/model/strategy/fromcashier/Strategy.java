package org.zenith.model.strategy.fromcashier;

import java.util.Properties;

import org.compiere.model.MOrderLine;

public class Strategy {
	int _doc_ID = 0;
	MOrderLine _ol =null;
	Properties _ctx =null;

	protected Strategy(Properties ctx,MOrderLine ol,int doc_ID) {
		this._doc_ID = doc_ID;
		this._ol =ol;
		this._ctx =ctx;
	}

	public void execute(CashierStrategy cs) {
		cs.execute(_ctx,_ol,_doc_ID);
	}
}
