package controller;

import java.io.IOException;
import java.time.LocalDate;

import app.Metodos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

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
			Metodos.cambiarEscena(event, "/view/LogIn.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void btonRegistro(ActionEvent event) {
        if (nombreId.getText().isEmpty() || ApellidosId.getText().isEmpty() || FechaNacId.getValue() == null || 
            nicknameId.getText().isEmpty() || emailId.getText().isEmpty() || confirmarEmailId.getText().isEmpty() || 
            contraseñaId.getText().isEmpty() || confirmarContraseñaId.getText().isEmpty()) {
            Metodos.mostrarMensajeError("Por favor, complete todos los campos.");
            return;
        }

        if (!contraseñaId.getText().equals(confirmarContraseñaId.getText())) {
            Metodos.mostrarMensajeError("Las contraseñas no coinciden.");
            return;
        }

        if (!emailId.getText().equals(confirmarEmailId.getText())) {
            Metodos.mostrarMensajeError("Los correos electrónicos no coinciden.");
            return;
        }

        if (!emailId.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            Metodos.mostrarMensajeError("El correo electrónico no es válido.");
            return;
        }

     

        Usuario usuario = new Usuario(
            nombreId.getText(),
            ApellidosId.getText(),
            FechaNacId.getConverter().toString(),
            nicknameId.getText(),
            emailId.getText(),
            confirmarEmailId.getText(),
            contraseñaId.getText(),
            confirmarContraseñaId.getText()
        );
        
        Usuario.add(usuario);
        Metodos.mostrarMensajeConfirmacion("Se ha registrado el usuario "+ nombreId.getText());
        try {
            Metodos.cambiarEscena(event, "/view/LogIn.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 

}