package app;

import java.io.IOException;

import dao.DaoUsuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(MainApp.class.getResource("/view/Home.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("applicattion.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("LogIn");
			primaryStage.show();
			Usuario.setUsuariosRegistrados(DaoUsuarios.loadUsers());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}


}
