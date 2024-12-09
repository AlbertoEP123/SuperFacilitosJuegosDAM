package controller;

import java.io.IOException;

import app.Metodos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInController {
	Parent root;
	Scene scene;
	Stage stage;
	
    @FXML
    private Button buttonEnterLogIn;

    @FXML
    private Button buttonRegister;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField texFieldUsername;

    @FXML
    void actionButtonEnterLogIn(ActionEvent event) {
    	try {
			Metodos.cambiarEscena(event, "/view/Home.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	

    }

    @FXML
    void actionButtonRegister(ActionEvent event) {
    	try {
			Metodos.cambiarEscena(event, "/view/Registro.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

