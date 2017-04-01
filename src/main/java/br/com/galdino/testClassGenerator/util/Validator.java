package br.com.galdino.testClassGenerator.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/*
 * Classe responsavel por realizar as 
 * validacoes gerais da aplicacao
 * 
 * */
public class Validator {

	public static boolean path( String path ) throws IllegalArgumentException {

		if (StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("Caminho em branco!");
		}

		File f = new File(path);
		if ( !f.exists() ) {
			throw new IllegalArgumentException("Nao foi possivel encontrar o arquivo/pacote indicado!");
		}

		return true;
	}
	
	public static boolean isFile( String path ){
		
		File f = new File( path );
		if ( f.isFile() ){
			return true;
		}
		return false;
	}

}
