package br.com.galdino.testClassGenerator.util;

import org.apache.commons.lang3.StringUtils;

public class Sair {
	
	public static void verificarSaida( String command ){
		
		if ( StringUtils.isNotBlank( command ) ){
			
			if ( command.equalsIgnoreCase( Constantes.COMANDOS_SAIDA.EXIT ) ||
				 command.equalsIgnoreCase( Constantes.COMANDOS_SAIDA.QUIT ) ||
				 command.equalsIgnoreCase( Constantes.COMANDOS_SAIDA.SAIR ) ){
				
				System.out.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP +"Encerrando apicacao!!!");
				System.exit( 0 );
				
			}
		}
	}
}
