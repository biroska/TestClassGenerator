package br.com.galdino.testClassGenerator.util;

import br.com.galdino.testClassGenerator.util.enuns.CollectionEnum;
import br.com.galdino.testClassGenerator.util.enuns.PrimitiveEnum;
import br.com.galdino.testClassGenerator.util.enuns.WrapperEnum;

public class Util {
	
	public static boolean isPrimitive( String type ){
		
		return PrimitiveEnum.getEnumByDescName( type ) != null;
	}
	
	public static boolean isWrapper( String type ){
		
		return WrapperEnum.getEnumByDescName( type ) != null;
	}
	
	public static boolean isCollection( String type ){
		
		return CollectionEnum.getEnumByDescName( type ) != null;
	}
	
	public static String primeiraMinuscula( String name ){
		
		return name.substring(0, 1).toLowerCase()+name.substring(1);
	}
	
	public static String primeiraMaiuscula( String name ){
		
		return name.substring(0, 1).toUpperCase()+name.substring(1);
	}

}
