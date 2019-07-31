package com.akujas.vcs.domain.enumeration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WalletSubcategoryConverter implements AttributeConverter<WalletSubcategory, String> {
	
	public WalletSubcategoryConverter() {
	}
	
	@Override
	public String convertToDatabaseColumn(WalletSubcategory value) {
		return value.displayName();
	}

	@Override
	public WalletSubcategory convertToEntityAttribute(String value) {
		
		 for(WalletSubcategory e : WalletSubcategory.values()){
	            if(value == e.displayName() || value.equalsIgnoreCase(e.displayName())){ 
	            	return WalletSubcategory.valueOf(e.name());
	            }
	        }
		
		return null;
	}

}