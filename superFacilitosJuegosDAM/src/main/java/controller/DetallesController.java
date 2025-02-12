package controller;


import java.util.List;

import app.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;
import model.Biblioteca;

public class DetallesController {
	static public int obtenido;
	static public int jugado;
	static public int pendiente;
	static public int terminado;
	static Biblioteca datosJuego;
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
    	// nuevo objeto para guardar elementos, si es 1 es que esta pulsado
    	datosJuego = new Biblioteca(jugado,terminado,pendiente,obtenido);
    	

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
       caratulaJuego.setImage(Auxiliar.caratula.getImage());
       caratulaJuego.setFitWidth(200); 
       caratulaJuego.setPreserveRatio(true); 
       fechaLanzamiento.setText(Auxiliar.juego.getReleaseDate());
       textDescripcion.setText(Auxiliar.juego.getDescription());
       System.out.println(Auxiliar.juego.getDescription());
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

