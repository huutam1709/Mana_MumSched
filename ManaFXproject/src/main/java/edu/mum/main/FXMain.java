
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
        ViewManager viewManager = new ViewManager();       
        int width = 400;
        int height = 300;
        viewManager.displayView("login", stage, width, height);
        } 
}