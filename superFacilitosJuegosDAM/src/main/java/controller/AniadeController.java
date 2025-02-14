package controller;

import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import app.AppContext;
import app.Metodos;
import db.DaoBiblioteca;
import db.DaoUsuarios;
import javafx.event.ActionEvent;
//import db.DaoGamesAniadidos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Auxiliar;
import model.EntradaDeBiblioteca;
import model.Games;
//import model.Biblioteca;
import model.MyGames;

public class AniadeController {

	static public int obtenido;
	static public int jugado;
	static public int pendiente;
	static public int terminado;
	private String imagePath;

    @FXML
    private Button btnGuardar;

    @FXML
    private ImageView caratulaJuego;

    @FXML
    private Label fechaLanzamiento;

    @FXML
    private Label labelUsuario;

    @FXML
    private Pane panelCerrarSesion;

    @FXML
    private Pane panelCerrarSesion1;

    @FXML
    private RadioButton rdbtnObtenido;

    @FXML
    private RadioButton rdbtnPendiente;

    @FXML
    private RadioButton rdbtnTerminado;

    @FXML
    private Label textDescripcion;

    @FXML
    private Label tituloJuego;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextArea txtDescrption;

    @FXML
    private TextField txtPlataforma;

    @FXML
    private TextField txtTitulo;
    
    private String poseidoV = "0";
    private String pendienteV = "0";
    private String terminadoV = "0";
    


 


    @FXML
    void buttonJugado(MouseEvent event) {
    	jugado = event.getClickCount(); 
    }

    @FXML
    void buttonObtenido(MouseEvent event) {

    }

    @FXML
    void buttonPendiente(MouseEvent event) {

    }

    @FXML
    void buttonTerminado(MouseEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
    	EntradaDeBiblioteca entrada = new EntradaDeBiblioteca(DaoUsuarios.getId(LogInController.loggedInUser.getEmail()),
   			 DaoBiblioteca.getNextIdBiblioteca(), 
   			"/resourcess/cruz.png",txtTitulo.getText(), poseidoV,
   			pendienteV, terminadoV, txtDescrption.getText(),0 , null);
   	DaoBiblioteca.addEntradaBiblioteca(DaoUsuarios.getId(LogInController.loggedInUser.getEmail()), entrada, "ej");
   	Metodos.mostrarMensajeConfirmacion("Juego añadido a biblioteca");
   	Games game = new Games(obtenido, terminadoV, jugado, poseidoV, pendienteV, imagePath, null);
    }
    @FXML
    void obtener(ActionEvent event) {
        poseidoV = "1";
        pendienteV = "0";
        terminadoV = "0";
        
        rdbtnObtenido.setSelected(true);
        rdbtnPendiente.setSelected(false);
        rdbtnTerminado.setSelected(false);
    }
    
    @FXML
    private void pendiente(ActionEvent event) {
        poseidoV = "0";
        pendienteV = "1";
        terminadoV = "0";
        
        rdbtnObtenido.setSelected(false);
        rdbtnPendiente.setSelected(true);
        rdbtnTerminado.setSelected(false);
    }
    
    @FXML
    void terminado(ActionEvent event) {
        poseidoV = "1";
        pendienteV = "0";
        terminadoV = "1";
        
        rdbtnObtenido.setSelected(true);
        rdbtnPendiente.setSelected(false);
        rdbtnTerminado.setSelected(true);
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
