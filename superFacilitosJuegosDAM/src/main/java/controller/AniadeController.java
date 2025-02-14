package controller;

import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import app.AppContext;
import app.Metodos;
//import db.DaoGamesAniadidos;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
//import model.Biblioteca;
import model.MyGames;

public class AniadeController {

	static public int obtenido;
	static public int jugado;
	static public int pendiente;
	static public int terminado;
	private String imagePath;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="caratulaJuego"
    private ImageView caratulaJuego; // Value injected by FXMLLoader

    @FXML // fx:id="fechaLanzamiento"
    private Label fechaLanzamiento; // Value injected by FXMLLoader

    @FXML // fx:id="labelUsuario"
    private Label labelUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="panelCerrarSesion"
    private Pane panelCerrarSesion; // Value injected by FXMLLoader

    @FXML // fx:id="panelCerrarSesion1"
    private Pane panelCerrarSesion1; // Value injected by FXMLLoader

    @FXML // fx:id="textDescripcion"
    private Label textDescripcion; // Value injected by FXMLLoader

    @FXML // fx:id="tituloJuego"
    private Label tituloJuego; // Value injected by FXMLLoader

    @FXML // fx:id="txtDate"
    private DatePicker txtDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtDescrption"
    private TextArea txtDescrption; // Value injected by FXMLLoader

    @FXML // fx:id="txtPlataforma"
    private TextField txtPlataforma; // Value injected by FXMLLoader

    @FXML // fx:id="txtTitulo"
    private TextField txtTitulo; // Value injected by FXMLLoader

    @FXML
    void buttonJugado(MouseEvent event) {
    	jugado = event.getClickCount(); 
    }

    @FXML
    void buttonObtenido(MouseEvent event) {
    	obtenido = event.getClickCount();
    }

    @FXML
    void buttonPendiente(MouseEvent event) {
    	terminado = event.getClickCount();
    }

    @FXML
    void buttonTerminado(MouseEvent event) {
    	terminado = event.getClickCount(); 
    }

    @FXML
    void guardarJuego(MouseEvent event) {
    	MyGames game = new MyGames();
    	if (!txtTitulo.getText().isEmpty()) {
    		game.setTitle(txtTitulo.getText());
    	}
    	if (txtDate.getValue() != null) {
    		game.setReleaseDate(txtDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    	}
    	if (!txtDescrption.getText().isEmpty()) {
    		game.setDescription(txtDescrption.getText());
    	}
    	if (!txtPlataforma.getText().isEmpty()) {
    		game.setPlatforms(txtPlataforma.getText());
    	}
    	if (caratulaJuego.getImage() != null) {
            game.setImageUrl(caratulaJuego.getImage().getUrl());
        }
    	game.setUser(AppContext.getUsuarioLogueado().getNickname());
    	Metodos.mostrarMensajeConfirmacion("Juego añadido");
//    	DaoGamesAniadidos.addGame(game);
//    	datosJuego = new Biblioteca(jugado,terminado,pendiente,obtenido);
    }

    @FXML
    void irAHome(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Home.fxml", "Home");
    }

    @FXML
    void logOut(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");
    }

    @FXML
    void ventanaUsuario(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Usuario.fxml", "Usuario");
    }
    
    @FXML
    private void seleccionarCaratula(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath();
            caratulaJuego.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert caratulaJuego != null : "fx:id=\"caratulaJuego\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert fechaLanzamiento != null : "fx:id=\"fechaLanzamiento\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert labelUsuario != null : "fx:id=\"labelUsuario\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert panelCerrarSesion != null : "fx:id=\"panelCerrarSesion\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert panelCerrarSesion1 != null : "fx:id=\"panelCerrarSesion1\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert textDescripcion != null : "fx:id=\"textDescripcion\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert tituloJuego != null : "fx:id=\"tituloJuego\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert txtDescrption != null : "fx:id=\"txtDescrption\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert txtPlataforma != null : "fx:id=\"txtPlataforma\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'Añadir.fxml'.";

    }

}
