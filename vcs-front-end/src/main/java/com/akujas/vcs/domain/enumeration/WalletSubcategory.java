package com.akujas.vcs.domain.enumeration;

/**
 * The WalletSubcategory enumeration.
 */
public enum WalletSubcategory {
        
    PROMOTION1("PROMOTION 1"),
    PROMOTION2("PROMOTION 2"),
    PROMOTION3("PROMOTION 3");

    private String displayName;

    WalletSubcategory(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }
    
    

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }
    
    
}
