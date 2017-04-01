package br.com.galdino.testClassGenerator.util;

import java.io.File;

public interface Constantes {
	
	public final String PATTERN_EXPRESSION = "\\s{0,}"+ 		 // Pode ter espacos em branco no comeco da linha ou nao
											 "private"+			 // Deve encontrar a palavra 'private'
											 "\\s{1,}"+			 // Tem que ter um ou mais espacos
											 "[a-zA-Z0-9_<>-]{1,}"+ // Definicao do tipo - Tem que ter pelo menos um caracter do conjunto [a-zA-Z0-9_]
											 "\\s{1,}"+          // Tem que ter um ou mais espacos
											 "[a-zA-Z0-9_-]{1,}"+ // Definicao do nome do atributo - Tem que ter pelo menos um caracter do conjunto [a-zA-Z0-9_]
											 "\\s{0,}"+          // Pode ter espacos em branco ou nao 
											 ";"+                // Tem que ter o caracter ';'
											 "\\s{0,}";          // Pode ter espacos em branco ou nao
//	Este padrao foi validado com a seguinte declaracao: 'atributo = "        private           String             nome          ;"'
	
	public interface COMANDOS_SAIDA {
		public final String EXIT = "EXIT";
		public final String QUIT = "QUIT";
		public final String SAIR = "SAIR";
	}
	
	public interface FOLDER_PATTERN {
		public final String ORIG_FOLDER = "src"+File.separator+"main"+File.separator+"java"; 
		public final String TEST_FOLDER = "src"+File.separator+"test"+File.separator+"java"; 
	}
	
	public interface FILE_PATTERN {
		public final String FILE_EXTENSION = ".java";
		public final String TEST_FILE_EXTENSION = "Test.java";
	}
	
	public interface TEXT_FORMATTERS {
		public final String TAB_SPACE_APP = "\t\t\t\t\t";
		public final String TAB_SPACE_FILE = "\t";
		public final String TAB_SPACE_FILE_2 = "\t\t";
		public final String LINE_BREAK = "\n";
		public final String LINE_BREAK_2 = "\n\n";
	}
	
	public interface TEST_CLASS {
		public final String IMPORTS = "import org.junit.Before;\n"+
									  "import org.junit.Test;"+
									  "import static org.junit.Assert.assertEquals;\n\n" +
									  "import java.util.List;\n"+
									  "import java.util.ArrayList;\n\n";
		
		public final String ATTRIBUTE_DECLARATION_MODEL = TEXT_FORMATTERS.TAB_SPACE_FILE +
				  									      "private :type :name"+
				  									      " = new :type();"+
				  									      TEXT_FORMATTERS.LINE_BREAK_2;
		
//		public final String ATTRIBUTE_DECLARATION_MODEL = TEXT_FORMATTERS.TAB_SPACE_FILE +
//				  									      "private :type :name;"+
//				  									      TEXT_FORMATTERS.LINE_BREAK_2;
		
		public final String GETTER_DECLARATION_MODEL = TEXT_FORMATTERS.TAB_SPACE_FILE +
			      									   "public void testGet:name() {"+
			      									   TEXT_FORMATTERS.LINE_BREAK;
		
		public final String SETTER_DECLARATION_MODEL = TEXT_FORMATTERS.TAB_SPACE_FILE +
													   "public void testSet:name() {"+
													   TEXT_FORMATTERS.LINE_BREAK;
		
		public final String ASSERT_EQUALS = Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE_2 +
										    "assertEquals( :testValue, " + 
										    ":className.get:attributeName() );"+
										    TEXT_FORMATTERS.LINE_BREAK;
		
		public final String ATTRIBUTE_INITIALIZATION = Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE_2 +
													   ":className.set:attributeName( :testValue );"+
													   TEXT_FORMATTERS.LINE_BREAK;
		
	}

}
