package controller;

import java.io.IOException;

import app.Metodos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistroController {

    @FXML
    private TextField ApellidosId;

    @FXML
    private DatePicker FechaNacId;

    @FXML
    private PasswordField confirmarContraseñaId;

    @FXML
    private TextField confirmarEmailId;

    @FXML
    private PasswordField contraseñaId;

    @FXML
    private TextField emailId;

    @FXML
    private TextField nicknameId;

    @FXML
    private TextField nombreId;

    @FXML
    private AnchorPane pane;

    @FXML
    void btonCancelar(ActionEvent event) {
    	try {
			Metodos.cambiarEscena(event, "/view/LogIn.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void btonRegistro(ActionEvent event) {
    	try {
			Metodos.cambiarEscena(event, "/view/LogIn.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
