package controller;


import app.Metodos;
import dao.DaoUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Usuario;

public class LogInController {
	Parent root;
	Scene scene;
	Stage stage;
	 public static Usuario loggedInUser = null;
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

    	    // Usar el DAO para verificar el login
    	    Usuario user = DaoUsuarios.login(username, password);

    	    if (user != null) {
    	        loggedInUser = user; // Guardamos al usuario logueado
    	        Metodos.mostrarMensajeConfirmacion("Te has logueado " + user.getNickname());
    	        Metodos.cambiarEscena(event, "/view/Home.fxml", "home");
    	    } else {
    	        Metodos.mostrarMensajeError("Usuario o contraseña incorrectos.");
    	    }
    }

    @FXML
    void actionButtonRegister(ActionEvent event) {
    	Metodos.cambiarEscena(event, "/view/Registro.fxml", "Registro");

    }
    @FXML
    void clickOlvidarContraseña(MouseEvent event) {

    }

}

