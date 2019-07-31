package com.akujas.vcs.domain.enumeration;

/**
 * The TransactionStatus enumeration.
 */
public enum TransactionStatus {
    SUCCESS, FAIL;
	
	public static TransactionStatus getTransactionType(String str) {
		for(TransactionStatus ts:TransactionStatus.values()) {
			if(ts.toString().startsWith(str))
				return ts;
		}
		return null;
	}
}
