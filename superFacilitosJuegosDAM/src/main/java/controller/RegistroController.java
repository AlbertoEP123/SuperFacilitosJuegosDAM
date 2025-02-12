package controller;

import app.Metodos;
import db.DaoUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class RegistroController {

    @FXML
    private TextField ApellidosId;

    @FXML
    private DatePicker FechaNacId;
    

    @FXML
    private Button btnRegistro;


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
    void btonCancelar(ActionEvent event) {
    	Metodos.cambiarEscena(event, "/view/LogIn.fxml","LogIn");

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
        DaoUsuarios.addUser(usuario);

        Metodos.mostrarMensajeConfirmacion("Se ha registrado el usuario "+ nombreId.getText());
        Metodos.cambiarEscena(event, "/view/LogIn.fxml","LogIn");
    }
   



}
