package edu.mum.main;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

	public void displayView(String fxml, Stage stage, int width, int height) throws Exception  {
		Window mainStage = new Window(fxml, "Mana project", width, height );
		stage.setScene(mainStage.getScene());
	    stage.show();
	}
}
