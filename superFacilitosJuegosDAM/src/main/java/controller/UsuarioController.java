package controller;

import java.io.File;

import app.Metodos;
import db.DaoUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UsuarioController {
	
	private boolean cambiarNombreActivo = false;
	private boolean cambiarApellidosActivo = false;
    private boolean cambiarContraseñaActivo = false;
    private boolean cambiarNicknameActivo = false;
    @FXML
    private Button botonCambiarApellidos;

    @FXML
    private Button botonCambiarContraseña;

    @FXML
    private Button botonCambiarNickname;
   
    @FXML
    private Label labelNickame2;

    @FXML
    private Button botonCambiarNombre;


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
    private Button botonCambiarImagen;
    
    @FXML
    void cambiarImagen(MouseEvent event) {
    	  FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Seleccionar Imagen de Perfil");
          fileChooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif")
          );
          
          Stage stage = (Stage) botonCambiarImagen.getScene().getWindow();
          File selectedFile = fileChooser.showOpenDialog(stage);
          
          if (selectedFile != null) {
              Image image = new Image(selectedFile.toURI().toString());
              imageProfile.setImage(image);
              
              
              LogInController.loggedInUser.setImagenPerfilPath(selectedFile.getAbsolutePath());
    }
    }

    @FXML
    void clickBiblioteca(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");

    }
    @FXML
    void irAHome(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Home.fxml", "Home");
    }
    
    @FXML
    void clickAniade(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Añadir.fxml", "Añadir Juego");
    }

    @FXML
    void clickCambiarApellidos(MouseEvent event) {
        if (!cambiarApellidosActivo) {
            // Primer clic: Activar modo de edición
            botonCambiarApellidos.setText("Guardar");
            textFieldApellidos.setVisible(true);
            textFieldApellidos.setText(labelApellidos.getText());
            labelApellidos.setVisible(false);
            cambiarApellidosActivo = true;
        } else {
            // Segundo clic: Guardar cambios
            String nuevosApellidos = textFieldApellidos.getText();
            if (!nuevosApellidos.isEmpty()) {
                LogInController.loggedInUser.setApellidos(nuevosApellidos);
                labelApellidos.setText(nuevosApellidos);
                DaoUsuarios.updateApellidos(LogInController.loggedInUser.getNickname(), nuevosApellidos);

            }
            botonCambiarApellidos.setText("Cambiar Apellidos");
            textFieldApellidos.setVisible(false);
            labelApellidos.setVisible(true);
            cambiarApellidosActivo = false;
        }
    }
    @FXML
    void clickCambiarNombre(MouseEvent event) {
        if (!cambiarNombreActivo) {
            // Primer clic
            botonCambiarNombre.setText("Guardar");
            textFieldNombre.setVisible(true);
            textFieldNombre.setText(labelNombre.getText());
            labelNombre.setVisible(false);
            cambiarNombreActivo = true;
        } else {
            // Segundo clic
            String nuevoNombre = textFieldNombre.getText();
            if (!nuevoNombre.isEmpty()) {
                LogInController.loggedInUser.setNombre(nuevoNombre);
                labelNombre.setText(nuevoNombre);
                DaoUsuarios.updateNombre(LogInController.loggedInUser.getNickname(), nuevoNombre);

            }
            botonCambiarNombre.setText("Cambiar Nombre");
            textFieldNombre.setVisible(false);
            labelNombre.setVisible(true);
            cambiarNombreActivo = false;
        }
    }

    @FXML
    void clickContraseña(MouseEvent event) {
        if (!cambiarContraseñaActivo) {
            // Primer clic: Activar modo de edición
            botonCambiarContraseña.setText("Guardar");
            paswordField.setVisible(true);
            paswordField.setText("");
            labelContraseña.setVisible(false);
            cambiarContraseñaActivo = true;
        } else {
            // Segundo clic: Guardar cambios
            String nuevaContraseña = paswordField.getText();
            if (!nuevaContraseña.isEmpty()) {
                LogInController.loggedInUser.setContraseña(nuevaContraseña);
                labelContraseña.setText("*".repeat(nuevaContraseña.length()));
                DaoUsuarios.updateContraseña(LogInController.loggedInUser.getNickname(), nuevaContraseña);

            }
            botonCambiarContraseña.setText("Cambiar Contraseña");
            paswordField.setVisible(false);
            labelContraseña.setVisible(true);
            cambiarContraseñaActivo = false;
        }
    }


    @FXML
    void clickNickname(MouseEvent event) {
        if (!cambiarNicknameActivo) {
            // Primer clic: Activar modo de edición
            botonCambiarNickname.setText("Guardar");
            textFieldNickname.setVisible(true);
            textFieldNickname.setText(labelNickame.getText());
            labelNickame.setVisible(false);
            cambiarNicknameActivo = true;
        } else {
            // Segundo clic: Guardar cambios
            String nuevoNickname = textFieldNickname.getText();
            if (!nuevoNickname.isEmpty()) {
                LogInController.loggedInUser.setNickname(nuevoNickname);
                labelNickame.setText(nuevoNickname);
                labelNickame2.setText(nuevoNickname);
                DaoUsuarios.updateNickname(LogInController.loggedInUser.getNickname(), nuevoNickname);

            }
            botonCambiarNickname.setText("Cambiar Nickname");
            textFieldNickname.setVisible(false);
            labelNickame.setVisible(true);
            cambiarNicknameActivo = false;
        }
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
        String contraseña = LogInController.loggedInUser.getContraseña();
        labelContraseña.setText("*".repeat(contraseña.length()));
    }
}
