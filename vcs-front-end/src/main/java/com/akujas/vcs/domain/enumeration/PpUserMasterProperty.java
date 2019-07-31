package com.akujas.vcs.domain.enumeration;

public enum PpUserMasterProperty {
	ID("id"),
	NAME("name"),
	EMAIL("email"),
	MOBILE("mobile"),
	PARAM1("param1"),
	PARAM2("param2"),
	PARAM3("param3");
	
	private String property;

	private PpUserMasterProperty(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;
	}
	
	public static PpUserMasterProperty fromString(String text) {
        for (PpUserMasterProperty b : PpUserMasterProperty.values()) {
            if (b.property.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
