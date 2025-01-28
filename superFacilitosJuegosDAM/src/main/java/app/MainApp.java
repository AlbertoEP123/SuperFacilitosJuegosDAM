package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(MainApp.class.getResource("/view/LogIn.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("applicattion.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}


<<<<<<< HEAD
}
=======
}
>>>>>>> 79ca8467521d8d5831d40aa813339c229dc3679b
