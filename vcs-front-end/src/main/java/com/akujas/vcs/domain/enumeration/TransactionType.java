package com.akujas.vcs.domain.enumeration;

/**
 * The TransactionType enumeration.
 */
public enum TransactionType {
    CREDIT, DEBIT,CB_TO_MERCHANT,REVENUE_SETTLE,SETTLE;
	
	public static TransactionType getTransactionType(String str) {
		for(TransactionType tt:TransactionType.values()) {
			if(tt.toString().startsWith(str))
				return tt;
		}
		return null;
	}
}
