package controller;

import app.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UsuarioController {
	
	boolean primera = true;
	int evento = 0;
    @FXML
    private Button botonCambiarApellidos;

    @FXML
    private Button botonCambiarContraseña;

    @FXML
    private Button botonCambiarNickname;

    @FXML
    private Button botonCambiarNombre;

    @FXML
    private ImageView imageJuegoFav1;

    @FXML
    private ImageView imageJuegoFav2;

    @FXML
    private ImageView imageJuegoFav3;

    @FXML
    private ImageView imageProfile;

    @FXML
    private Label juegoFav1;

    @FXML
    private Label juegoFav2;

    @FXML
    private Label juegoFav3;

    @FXML
    private Label labelApellidos;

    @FXML
    private Label labelContraseña;

    @FXML
    private Label labelNickame;

    @FXML
    private Label labelNickame2;

    @FXML
    private Label labelNombre;

    @FXML
    private Pane panelCerrarSesion;
    
    @FXML
    private PasswordField paswordField;

    @FXML
    private TextField textFieldApellidos;

    @FXML
    private TextField textFieldNickname;

    @FXML
    private TextField textFieldNombre;

    @FXML
    void clickBiblioteca(MouseEvent event) {
    	

    }

    @FXML
    void clickCambiarApellidos(MouseEvent event) {
    	// Implementar que se ha clicado dos veces el boton, al clicar por segunda vez se cierra el textField y setTex de los label
    	evento++;
    	if(primera) {
    		botonCambiarApellidos.setText("Guardar");
        	textFieldApellidos.setVisible(true);
        	LogInController.loggedInUser.setApellidos(textFieldApellidos.getText());
        	labelApellidos.setText(textFieldApellidos.getText());
    	}else {
    		
    	}
    	
    	
    }

    @FXML
    void clickCambiarNombre(MouseEvent event) {
//    	botonCambiarNombre.setText("Guardar");
//    	textFieldNombre.setVisible(true);
//    	LogInController.loggedInUser.setNombre(textFieldNombre.getText());
//    	labelNombre.setText(textFieldNombre.getText());
//    	textFieldNombre.setVisible(false);

    }

    @FXML
    void clickContraseña(MouseEvent event) {
    	//LogInController.loggedInUser.setContraseña(null);


    }

    @FXML
    void clickNickname(MouseEvent event) {
    	//LogInController.loggedInUser.setNickname(null);


    }

    @FXML
    void logOut(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");

    }
    @FXML
    void initialize() {
    	textFieldApellidos.setVisible(false);
    	textFieldNickname.setVisible(false);
    	textFieldNombre.setVisible(false);
    	paswordField.setVisible(false);

    	labelApellidos.setText(LogInController.loggedInUser.getApellidos());
    	labelNickame.setText(LogInController.loggedInUser.getNickname());
    	labelNickame2.setText(LogInController.loggedInUser.getNickname());
    	labelNombre.setText(LogInController.loggedInUser.getNombre());
    	labelContraseña.setText(LogInController.loggedInUser.getContraseña());
    	labelContraseña.setText("*");


    }

}
