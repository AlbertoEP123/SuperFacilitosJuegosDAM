package controller;

import java.io.IOException;

import app.Metodos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

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
        String username = texFieldUsername.getText();
        String password = passwordField.getText();
        
        boolean loginSuccess = false;
        try {
			for (Usuario user : Usuario.getUsuariosRegistrados()) {
			    if (user.getNickname().equals(username) && user.getContraseña().equals(password)) {
			        loginSuccess = true;
			        break;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if (loginSuccess) {
        	Metodos.mostrarMensajeConfirmacion("Te has logueado "+username);
            Metodos.cambiarEscena(event, "/view/Home.fxml", "home");
        } else {
            Metodos.mostrarMensajeError("Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    void actionButtonRegister(ActionEvent event) {
    	Metodos.cambiarEscena(event, "/view/Registro.fxml", "Registro");

    }

}

