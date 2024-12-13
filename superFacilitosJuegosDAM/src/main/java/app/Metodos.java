package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Metodos {

	
	 public static void cambiarEscena(ActionEvent event, String fxmlFile) throws IOException {

		 	FXMLLoader loader = new FXMLLoader(Metodos.class.getResource(fxmlFile));
	       
		 	Pane root = loader.load();
	        
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        
	        Scene scene = new Scene(root);
	        
	        stage.setScene(scene);
	        
	        stage.show();
	    }
	 public static void mostrarMensajeError(String mensaje) {
		    Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText(null);
		    alert.setContentText(mensaje);
		    alert.showAndWait();
		}
	 public static void mostrarMensajeConfirmacion(String mensaje) {
		    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Confirmado");
		    alert.setHeaderText(null);
		    alert.setContentText(mensaje);
		    alert.showAndWait();
		}

		
		
}
