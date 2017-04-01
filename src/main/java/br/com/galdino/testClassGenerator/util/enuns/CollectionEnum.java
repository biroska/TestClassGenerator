package br.com.galdino.testClassGenerator.util.enuns;

import org.apache.commons.lang3.StringUtils;

public enum CollectionEnum {
	
	LIST  ( "List", "ArrayList" ),
	MAP   ( "Map",  "HashMap" );
	
	private String descName;
	
	private String implementation;

	private CollectionEnum(String descName, String implementation) {
		this.descName = descName;
		this.implementation = implementation;
	}
	
	public static CollectionEnum getEnumByDescName( String descName ){
		
		if ( StringUtils.isBlank( descName ) ){
			return null;
		}
		
		for (CollectionEnum e : CollectionEnum.values() ) {
			
			if ( descName.contains( e.getDescName() ) ){
				return e;
			}
		}
		return null;
	}

	public String getDescName() {
		return this.descName;
	}

	public String getImplementation() {
		return this.implementation;
	}


}
