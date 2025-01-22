package controller;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button btonRegistro;

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
    void actionRegister(ActionEvent event) {

    }

    @FXML
    void salirAction(ActionEvent event) {
    	
    	
    }

}
