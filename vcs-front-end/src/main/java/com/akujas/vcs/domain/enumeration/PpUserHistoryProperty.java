package com.akujas.vcs.domain.enumeration;

public enum PpUserHistoryProperty {
	USER_ID("user_id"),
	DESCRIPTION("description"),
	TRANSACTION_TYPE("transaction_type"),
	TRANSACTION_STATUS("transaction_status"),
	ORDER_ID("order_id"),
	PARAM1("param1"),
	PARAM2("param2"),
	PARAM3("param3"),
	SOURCE("source"),
	TRANSACTION_DATE("transaction_date"),
	MID("mid");
    
    private String property;

	private PpUserHistoryProperty(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;
	}
	
	public static PpUserHistoryProperty fromString(String text) {
        for (PpUserHistoryProperty b : PpUserHistoryProperty.values()) {
            if (b.property.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
