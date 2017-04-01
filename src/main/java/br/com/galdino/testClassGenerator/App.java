package br.com.galdino.testClassGenerator;

import br.com.galdino.testClassGenerator.controller.InterfaceController;
import br.com.galdino.testClassGenerator.controller.PromptController;

public class App 
{
    public static void main( String[] args )
    {
        InterfaceController controller = new PromptController();
        
        try {
			controller.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
