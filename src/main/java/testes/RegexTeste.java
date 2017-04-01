package testes;

public class RegexTeste {

	public static void main(String[] args) {
		String atributo = "private List<String> nome-2;";
		
		System.out.println("Match: " + atributo.matches("\\s{0,}private\\s{1,}[a-zA-Z0-9_<>-]{1,}\\s{1,}[a-zA-Z0-9_-]{1,}\\s{0,};\\s{0,}") );

		String testPath = "C:\\Users\\Notebook\\workspace_Neon\\testClassGenerator\\src\\test\\java\\br\\com\\galdino\\testClassGenerator\\controller\\InterfaceController.java";
		
		String replaceAll = testPath.replaceAll("\\\\", ".");
		
		String[] split = replaceAll.split( "src.test.java.");
		
		String replace = split[1].replace(".java", "");
		
		System.out.println( replace.substring(0, replace.lastIndexOf(".") ) );
		
		String auxFile = testPath.replace(".java", "");
		System.out.println( testPath.substring( auxFile.lastIndexOf("\\") +1 ) );
		
		String auxName = "teste";
		
		auxName = auxName.substring(0,1).toUpperCase() + auxName.substring(1);
		
		System.out.println( auxName );
		

	}

}
