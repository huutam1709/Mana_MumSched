
package edu.mum.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

public class FXMain extends Application {
    
	   public static void main(String[] args) {
        Application.launch(FXMain.class, args);
    }
 
    @Override
    public void start(Stage stage) throws Exception {
    
    	ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
    	// Start up Welcome/Login view
    	ViewManager viewManager = new ViewManager();   	
    	int width = 400;
    	int height = 300;
    	viewManager.displayView(getClass().getResource("/view/login.fxml"), stage, context, width, height);
    	} 
}
