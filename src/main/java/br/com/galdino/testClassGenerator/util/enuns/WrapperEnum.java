package br.com.galdino.testClassGenerator.util.enuns;

public enum WrapperEnum {
	
	BYTE       ( "Byte",    "N", "1" ),
	SHORT      ( "Short",   "N", "1" ),
	INT        ( "Int",     "N", "1" ),
	LONG       ( "Long",    "N", "1L" ),
	FLOAT      ( "Float",   "N", "1F" ),
	DOUBLE     ( "Double",  "N", "1D"),
	BOOLEAN    ( "Boolean", "b", "true"),
	CHAR       ( "Char",    "A", "\'A\'"),
	STRING     ( "String",  "A", "\"String\"");
	
	private String descName;
	
	private String type;
	
	private String testValue;

	private WrapperEnum(String descName, String type, String testValue) {
		this.type = type;
		this.descName = descName;
		this.testValue = testValue;
	}
	
	public static WrapperEnum getEnumByDescName( String descName ){
		
		for (WrapperEnum e : WrapperEnum.values() ) {
			
			if ( e.getDescName().equals( descName ) ){
				return e;
			}
		}
		return null;
	}

	public String getDescName() {
		return this.descName;
	}

	public String getTestValue() {
		return this.testValue;
	}

	public String getType() {
		return type;
	}
	
}
