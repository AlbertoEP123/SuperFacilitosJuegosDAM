package controller;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import api.RawgApiClient;
import app.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;


public class DetallesController {
	static public int obtenido;
	static public int jugado;
	static public int pendiente;
	static public int terminado;
	private RawgApiClient client ;
	// pasar double de la nota a string
	double valor = Auxiliar.juego.getAverageRating();
    String formato = String.format("%.2f", valor);
    
    public DetallesController() {
    }

  
    @FXML
    private Label nota;
    @FXML
    private ImageView caratulaJuego;

    @FXML
    private Label fechaLanzamiento;

    @FXML
    private Label labelUsuario;

    @FXML
    private Pane panelCerrarSesion;

    @FXML
    private Label plataformaJuego;

    @FXML
    private Label textDescripcion;

    @FXML
    private Label tituloJuego;

    @FXML
    void buttonJugado(MouseEvent event) {
    	jugado = event.getClickCount();   	
    	
    }
    

    @FXML
    void buttonNota(MouseEvent event) {
    	
    }

    @FXML
    void buttonObtenido(MouseEvent event) {
    	obtenido = event.getClickCount(); 

    }

    @FXML
    void buttonPendiente(MouseEvent event) {
    	pendiente = event.getClickCount(); 

    }

    @FXML
    void buttonTerminado(MouseEvent event) {
    	terminado = event.getClickCount(); 

    }

    @FXML
    void guardarJuego(MouseEvent event) {
    	// modificar ventana, solo mostrar detalles y que guarde en bilbioteca
    	// añadir a biblioteca, hacer insert a tabla bilioteca, 
    	// id juego, id usuario, imagen, titulo, botones..
    	// en biblioteca poder poner fehca jugada,nota numerica, comentario
    	 	
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
	    void irAHome(MouseEvent event) {
		 Metodos.cambiarEscena(event, "/view/Home.fxml", "Home");
	    }
  
    @FXML
   void initialize() {
    
       client = new RawgApiClient();
       caratulaJuego.setImage(Auxiliar.caratula.getImage());
       caratulaJuego.setFitWidth(333);
       caratulaJuego.setFitHeight(326);
       caratulaJuego.setPreserveRatio(true);
       
       fechaLanzamiento.setText(Auxiliar.juego.getReleaseDate());
       textDescripcion.setText(client.obtenerDescripcionPorId(Auxiliar.juego.getId()));
       tituloJuego.setText(Auxiliar.juego.getTitle());
       String platforms = formatPlatforms(Auxiliar.juego.getPlatforms());
       plataformaJuego.setText(platforms);       
       nota.setText(formato+"/5");

   }


    private String formatPlatforms(Object platforms) {
        if (platforms instanceof List) {
            // Si plataforma es una lista de string
            return String.join(", ", (List<String>) platforms);
        } else if (platforms instanceof String[]) {
            // Si plataforma es un array de string
            return String.join(", ", (String[]) platforms);
        } else {
            // Si no
            return "Platformas no disponibles";
        }
    
    }
    
   
}

