package br.com.galdino.testClassGenerator.util.enuns;

public enum PrimitiveEnum {
	
	BYTE       ( "byte",    "N", "1" ),
	SHORT      ( "short",   "N", "1" ),
	INT        ( "int",     "N", "1" ),
	LONG       ( "long",    "N", "1L" ),
	FLOAT      ( "float",   "N", "1F" ),
	DOUBLE     ( "double",  "N", "1D"),
	BOOLEAN    ( "boolean", "b", "true"),
	CHAR       ( "char",    "A", "\'A\'");
	
	private String descName;
	
	private String type;
	
	private String testValue;

	private PrimitiveEnum(String descName, String type, String testValue) {
		this.type = type;
		this.descName = descName;
		this.testValue = testValue;
	}
	
	public static PrimitiveEnum getEnumByDescName( String descName ){
		
		for (PrimitiveEnum e : PrimitiveEnum.values() ) {
			
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
