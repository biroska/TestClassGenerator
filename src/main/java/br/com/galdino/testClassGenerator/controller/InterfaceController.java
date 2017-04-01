package br.com.galdino.testClassGenerator.controller;

import br.com.galdino.testClassGenerator.util.Constantes;

/**
 * @author Notebook
 * 
 * Classe responsavel pelo desacoplamento da 
 * logica da aplicacao da camada de apresentacao
 * 
 */
public abstract class InterfaceController {
	
	protected abstract String userInterface() throws Exception;
	
	public final void run() throws Exception{
		
		String path = userInterface();
		
		System.out.println( Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Gerando a(s) classe(s) de teste a partir de: " + path);
		
		FileController fc = new FileController();
		fc.generateTests( path );
		
	}

	
}
