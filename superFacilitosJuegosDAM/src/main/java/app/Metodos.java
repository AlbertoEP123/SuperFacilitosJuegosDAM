package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Metodos {

	
		public static void cambiarVentana(ActionEvent event,String resource) {
			Parent root;
			Scene scene;
			Stage stage;
			
			try {
				root = FXMLLoader.load(MainApp.class.getResource(resource));
				 scene = new Scene(root);
				 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    	
			    	scene=new Scene(root);

			    	
			    	stage.setScene(scene);
			    	
			    	stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
