package br.com.galdino.testClassGenerator.util;

import org.apache.commons.lang3.StringUtils;

import br.com.galdino.testClassGenerator.model.Attribute;

public class Converter {
	
	public static Attribute toAttribute( String line ){
		
		Attribute att = new Attribute ();
		String lineAux = null;
		String[] split = null;
		
		if ( StringUtils.isNotBlank( line ) ){
			
			lineAux = line.replace("private", "").replace(";", "").trim();
			split = lineAux.split("\\s");
			
			if ( split != null && split.length == 2 ){
				att.setType( split[0] );
				att.setName( split[1] );
			}
		}
		
		return att;
	}

}
