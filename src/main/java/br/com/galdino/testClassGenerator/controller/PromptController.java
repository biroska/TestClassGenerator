package br.com.galdino.testClassGenerator.controller;

import java.util.Scanner;

import br.com.galdino.testClassGenerator.util.Constantes;
import br.com.galdino.testClassGenerator.util.Sair;
import br.com.galdino.testClassGenerator.util.Validator;

/**
 * @author Notebook
 * 
 * Classe responsavel pelo desacoplamento da 
 * logica da aplicacao da camada de apresentacao
 * 
 */
public class PromptController  extends InterfaceController {
	
	private String path;
	
	@Override
	public String userInterface() throws Exception {
		String path = drawInstructions();
		return path;
	}
	
	private String drawInstructions(){
		
		Scanner sc = null;
		
		System.out.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "================================================================");
		System.out.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "| Este programa gerara classes de teste para classe de dominio |");
		System.out.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "|                                                              |");
		System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "| Modo de usar:                                                |");
		System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "| Input: Caminho do arquivo ou do pacote que contem as classes |");
		System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "| Output: Classe(s) de teste geradas na estrutra src/test/java |");
		System.out.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "================================================================");
		System.out.print("\n \n \n");
		System.out.print(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Forneca o arquivo/diretorio de entrada: ");
		
		try {
			sc = new Scanner( System.in );
			
			path = sc.next();
			
			System.out.print(Constantes.TEXT_FORMATTERS.LINE_BREAK);
			
			Sair.verificarSaida( path );
			
			Validator.path( path );
			
		} catch ( IllegalArgumentException e ){
			e.printStackTrace();
		} catch ( Exception e ){
			e.printStackTrace();
		} finally {
			if ( sc != null ){
				sc.close();
			}
		}
		
		return path;
	}
}
