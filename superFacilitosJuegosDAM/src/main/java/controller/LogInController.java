package controller;

import java.io.IOException;

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

    }

    @FXML
    void actionButtonRegister(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
    	root = loader.load();
    	
    	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	scene=new Scene(root);
    	
    	
    	stage.setScene(scene);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	
    	stage.show();
    }

}

