package br.com.galdino.testClassGenerator.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.galdino.testClassGenerator.model.Attribute;
import br.com.galdino.testClassGenerator.util.Constantes;
import br.com.galdino.testClassGenerator.util.Converter;
import br.com.galdino.testClassGenerator.util.Util;
import br.com.galdino.testClassGenerator.util.Validator;
import br.com.galdino.testClassGenerator.util.Constantes.TEXT_FORMATTERS;
import br.com.galdino.testClassGenerator.util.enuns.CollectionEnum;
import br.com.galdino.testClassGenerator.util.enuns.PrimitiveEnum;
import br.com.galdino.testClassGenerator.util.enuns.WrapperEnum;

class FileController {
	
	public void generateTests( String path ){
		
//		A chave contem o nome dos arquivos, o valor contem uma lista de atributos contidos no respectivo arquivo
		Map<String, List<Attribute> > mapOfAttributes = new HashMap<String, List<Attribute>>(0);
		List<Attribute> listAttributes = null;
		
		mapOfAttributes = buildFileAttributesToProcess( path );
		
		for ( String keyFileName : mapOfAttributes.keySet() ) {
			listAttributes = mapOfAttributes.get( keyFileName );
			
			if ( listAttributes != null && !listAttributes.isEmpty() ){
				generateTestClass( keyFileName, listAttributes );
			}
			
		}
		
	}
	
	private void generateTestClass( String filePath, List<Attribute> attList ){
		
		if ( !(new File( filePath ).isFile()) || attList == null || attList.isEmpty() ){
			return;
		}
		
//		Define o nome da classe de teste
		String destPath = createTestClassName( filePath );
		
//		Cria o diretorio onde a classe de teste sera gerada
		createTestFolderStructure( destPath );

		File f = new File( destPath );
		if ( !f.exists() ){
			try {
				System.out.println( Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Criando o arquivo: " + destPath );
				f.createNewFile();
				
//				Escreve o conteudo da classe de teste no arquivo recem criado ( f )
				writeTestClass( f, attList );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void writeTestClass( File file, List<Attribute> attList ){
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		String className = null;
		String classDeclaration = null;
		
		try {

			fw = new FileWriter( file ); 
			bw = new BufferedWriter(fw);
			
			// Escreve o comado package
			bw.write( buildPackageName( file ) );
			
			// Escreve os imports do Junit
			bw.write( Constantes.TEST_CLASS.IMPORTS );
			
			// Escreve o nome da classe
			className = buildClassName( file );
			classDeclaration = buildClassDeclaration( className );
			bw.write( classDeclaration );
			
			className = className.replace("Test", "");
			// Escreve o corpo da classe
			bw.write( buildTestClassBody( className, file, attList ) );
			
			// Encerra a classe de Teste
			bw.write( finishTestClass( file ) );

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	private String buildClassName( File file ){
		
		String absolutePath = file.getAbsolutePath();
		
		String auxFile = absolutePath.substring( absolutePath .lastIndexOf("\\") +1 );
		
		auxFile = auxFile.replace( Constantes.FILE_PATTERN.FILE_EXTENSION , "");
		
		return auxFile;
	}
	
	private String buildClassDeclaration( String fileName ){
		return "public class " + fileName + " { " + Constantes.TEXT_FORMATTERS.LINE_BREAK_2;
	}
	
	private String buildTestClassBody( String className, File file, List<Attribute> attList ){
		
		String body = "";
		
//		body = buildAtributesOfTestClass( className, file, attList );
		
		body = buildAtributeInitialization( className );
		
		body += buildBeforeMethod( body );
		
		body += buildTestMethods( className, file, attList );
		
		return body;
	}

	private String buildBeforeMethod(String body) {
		body = Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "@Before" + Constantes.TEXT_FORMATTERS.LINE_BREAK;
		body += Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "public void setUp() throws Exception {"+ Constantes.TEXT_FORMATTERS.LINE_BREAK;
		body += Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "}"+ Constantes.TEXT_FORMATTERS.LINE_BREAK_2;
		return body;
	}
	
	private String buildAtributesOfTestClass( String className, File file, List<Attribute> attList ){
		
		String body = "";
		for (Attribute att : attList) {
			if ( att == null ){
				continue;
			}
			body += Constantes.TEST_CLASS.ATTRIBUTE_DECLARATION_MODEL.replace( ":type", att.getType() ).replace(":name", att.getName() );
		}
		
		return body;
	}
	
	private String buildAtributeInitialization( String className ){
		
		String body = "";
		body += Constantes.TEST_CLASS.ATTRIBUTE_DECLARATION_MODEL.replace( ":type", className ).replace(":name", Util.primeiraMinuscula( className ) );
		
		return body;
	}
	
	private String buildTestMethods( String className, File file, List<Attribute> attList ){
		
		String body = "";
		String auxName = "";
		String testAnnotantion = Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "@Test" + Constantes.TEXT_FORMATTERS.LINE_BREAK;
		
		for (Attribute att : attList) {
			if ( att == null ){
				continue;
			}
			auxName = buildPrimeiraMaiuscula( att ); 
			
			body += testAnnotantion;
			body +=  Constantes.TEST_CLASS.GETTER_DECLARATION_MODEL.replace( ":name", auxName );
			body += buildGetterBody( className, att );
			body +=  Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "}" + Constantes.TEXT_FORMATTERS.LINE_BREAK_2;
			
			body += testAnnotantion;
			body += Constantes.TEST_CLASS.SETTER_DECLARATION_MODEL.replace( ":name", auxName );
			body += buildSetterBody( className, att );
			body +=  Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE + "}" + Constantes.TEXT_FORMATTERS.LINE_BREAK_2;
		}
		
		return body;
	}

	private String buildPrimeiraMaiuscula(Attribute att) {
		String auxName;
		auxName = att.getName().substring(0,1).toUpperCase() + att.getName().substring(1);
		return auxName;
	}
	
	private String buildGetterBody( String className, Attribute att ){
		
		String test = "";
		
		if ( Util.isPrimitive( att.getType() ) ){
			
			PrimitiveEnum priEnum = PrimitiveEnum.getEnumByDescName( att.getType() );
			test = Constantes.TEST_CLASS.ATTRIBUTE_INITIALIZATION.
					replace(":className", Util.primeiraMinuscula( className ) ).
					replace(":attributeName", Util.primeiraMaiuscula( att.getName() )).
					replace(":testValue", priEnum.getTestValue());
			
			// writing test
			String testValue = priEnum.getTestValue();
			
			test += Constantes.TEST_CLASS.ASSERT_EQUALS.
					replace( ":testValue" , testValue ).
					replace(":className", Util.primeiraMinuscula( className ) ).
					replace(":attributeName", buildPrimeiraMaiuscula( att ) );
			
		} else 
			if ( Util.isWrapper( att.getType() ) ){
				
				WrapperEnum wraEnum = WrapperEnum.getEnumByDescName( att.getType() );
				// Setting value
				test = Constantes.TEST_CLASS.ATTRIBUTE_INITIALIZATION.
					   replace(":className", Util.primeiraMinuscula( className ) ).
					   replace(":attributeName", Util.primeiraMaiuscula( att.getName() )).
					   replace(":testValue", wraEnum.getTestValue());
				
				// writing test
				String testValue = "new " + wraEnum.getDescName() +
						           "(" + wraEnum.getTestValue() + ")"; 
				
				test += Constantes.TEST_CLASS.ASSERT_EQUALS.
						replace( ":testValue" , testValue ).
						replace(":className", Util.primeiraMinuscula( className ) ).
						replace(":attributeName", buildPrimeiraMaiuscula( att ) );
			} else
				if ( Util.isCollection( att.getType() ) ){
					CollectionEnum colEnum = CollectionEnum.getEnumByDescName( att.getType() );

					// writing test
					String genericType = att.getType().substring( att.getType().indexOf("<"),
							             att.getType().indexOf(">") +1 );

					String testValue = "new " + colEnum.getImplementation() +
							            genericType + "();" +
							            Constantes.TEXT_FORMATTERS.LINE_BREAK;
					
					test = Constantes.TEXT_FORMATTERS.TAB_SPACE_FILE_2 +
						   colEnum.getDescName() + " collection = " + testValue;
					
					
					test += Constantes.TEST_CLASS.ATTRIBUTE_INITIALIZATION.
						    replace(":className", Util.primeiraMinuscula( className ) ).
						    replace(":attributeName", Util.primeiraMaiuscula( att.getName() )).
						    replace(":testValue", "collection" );
					
					test += Constantes.TEST_CLASS.ASSERT_EQUALS.
							replace( ":testValue" , "collection" ).
							replace(":className", Util.primeiraMinuscula( className ) ).
							replace(":attributeName", buildPrimeiraMaiuscula( att ) );
				}
		
		return test;
	}
	
	private String buildSetterBody( String className, Attribute att ){
		
		String test = "";
		
		if ( Util.isPrimitive( att.getType() ) ){
			
			PrimitiveEnum priEnum = PrimitiveEnum.getEnumByDescName( att.getType() );
			// writing test
			test  = Constantes.TEST_CLASS.ATTRIBUTE_INITIALIZATION.
					replace(":className", Util.primeiraMinuscula( className ) ).
					replace(":attributeName", Util.primeiraMaiuscula( att.getName() )).
					replace(":testValue", priEnum.getTestValue());
			
		} else 
			if ( Util.isWrapper( att.getType() ) ){
				
				WrapperEnum wraEnum = WrapperEnum.getEnumByDescName( att.getType() );
				// writing test
				test =  Constantes.TEST_CLASS.ATTRIBUTE_INITIALIZATION.
						replace(":className", Util.primeiraMinuscula( className ) ).
						replace(":attributeName", Util.primeiraMaiuscula( att.getName() )).
						replace(":testValue", wraEnum.getTestValue());
			}
		Util.isCollection( att.getType() );
		
		return test;
	}
	
	private String finishTestClass( File file ){
		return "}";
	}
	
	private String createTestClassName( String oriPath ){
		
		String destPath = oriPath.replace( Constantes.FOLDER_PATTERN.ORIG_FOLDER, Constantes.FOLDER_PATTERN.TEST_FOLDER );
		destPath = destPath.replace(Constantes.FILE_PATTERN.FILE_EXTENSION, Constantes.FILE_PATTERN.TEST_FILE_EXTENSION);
		
		return destPath;
	}
	
	private String buildPackageName( File file ){
		
		String path = file.getAbsolutePath();
		String packageName = null;
		String[] split = null;
		
		split = path.replaceAll("\\\\", ".").split( "src.test.java.");
		if ( file.isDirectory() ){
			packageName = split[1];
		} else
			if ( file.isFile() ){
				packageName = split[1].replace(".java", "");
				packageName = packageName.substring(0, packageName.lastIndexOf(".") );
			}
		
		packageName = "package " + packageName + ";"+Constantes.TEXT_FORMATTERS.LINE_BREAK_2;
		
		return packageName;
	}
	
	private void createTestFolderStructure( String testPath ){
		String destFolder = testPath.substring(0, testPath.lastIndexOf( File.separator ) );
		
		File folder = new File( destFolder );
		if ( !folder.exists() ){
			System.out.println( Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Criando o diretorio: " + destFolder );
			folder.mkdirs();
		}
	}
	
	private void createFolder( String oriPath ){
		
		String destPath = oriPath.replace("src/main/java", "src/test/java" );
		
	}

	private Map<String, List<Attribute> > buildFileAttributesToProcess(String path) {
		
		List<String> listaCaminhoArquivos = null;
		List<Attribute> attributesList = null;
		Map<String, List<Attribute> > mapOfAttributes = new HashMap<String, List<Attribute>>(0);
		
//		Monta uma lista com o nome dos arquivos que serao processados
		listaCaminhoArquivos = buildListWithFileNames( path );
		
		if ( listaCaminhoArquivos == null || listaCaminhoArquivos.isEmpty() ){
			return null;
		}

//		Para cada arquivo que sera processado monta-se uma lista dos atributos de cada arquivo
		for (String item : listaCaminhoArquivos) {
			attributesList = buildListAttributes( item );
			
			if ( attributesList != null && !attributesList.isEmpty() ){
				mapOfAttributes.put( item , attributesList );
			}
		}
		
		return mapOfAttributes;
	}

	private List<Attribute> buildListAttributes( String item ) {
		List<Attribute> attributesList = new ArrayList<Attribute>(0);
		System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Analisando arquivo: " + item);
		
		File f = new File( item );
		if ( f.canRead() ){
			attributesList = buildattributesList( item );
		}
		return attributesList;
	}
	
	private List<String> buildListWithFileNames( String path ){
		
		List<String> listaCaminhoArquivos = new ArrayList<String>(0);
		
		if ( Validator.isFile( path ) ){
			listaCaminhoArquivos.add( path );
		} else {
			listaCaminhoArquivos.addAll( getFilesFromPacote( path ) );
		}
		
		return listaCaminhoArquivos;
		
	}
	
	
	private List<String> getFilesFromPacote( String path ){
		
		String absolutePath = null;
		List<String> lista = new ArrayList<String>(0);
		
		File file = new File( path );
		
		if ( !file.canRead() ){
			throw new IllegalAccessError( "Nao foi possivel ler o diretorio" );
		}
		
		File[] listFiles = file.listFiles();
		
//		Adiciona o nome dos arquivos java na lista de arquivos que serao processados
		for (File fileItem : listFiles) {
			absolutePath = fileItem.getAbsolutePath();
			if ( absolutePath.contains( Constantes.FILE_PATTERN.FILE_EXTENSION )  ){
				lista.add( absolutePath );
			}
		}
		
		return lista;
	}
	
	private List<Attribute> buildattributesList( String path ){
		
		List<Attribute> attributesList = new ArrayList<Attribute>(0);
		Attribute att = null;
		
		BufferedReader br = null;
		FileReader fr = null;
		
			try {
				fr = new FileReader( path );
				br = new BufferedReader(fr);
	
				String currentLine;
	
				br = new BufferedReader( new FileReader( path ) );
	
				System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + "Atributos encontrados");
				while ( ( currentLine = br.readLine() ) != null) {
					
					if ( currentLine.matches( Constantes.PATTERN_EXPRESSION ) ){
						
						att = Converter.toAttribute( currentLine );
						attributesList.add( att );
						System.err.println(Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + Constantes.TEXT_FORMATTERS.TAB_SPACE_APP + att );
					}
				}
			} catch (IOException e){
				e.printStackTrace();
			} finally {

				try {

					if (br != null)
						br.close();

					if (fr != null)
						fr.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}
		
		return attributesList;
	}
	
}
