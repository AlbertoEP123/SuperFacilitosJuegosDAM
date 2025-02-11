package controller;


import app.Metodos;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    public DetallesController() {
    }

  

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
    	// nuevo objeto para guardar elementos
    	Biblioteca datosJuego = new Biblioteca(jugado,terminado,pendiente,obtenido);
    	

    }

    @FXML
    void back(MouseEvent event) {
    	//((Node) event.getSource()).getScene().getWindow().();  
    	}
    @FXML
    void logOut(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/login.fxml", "LogIn");
    	
    }

	@FXML
	void ventanaUsuario(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/Usuario.fxml", "Usuario");

	}
  
    @FXML
   void initialize() {
       caratulaJuego.setImage(Auxiliar.caratula.getImage());
       fechaLanzamiento.setText(Auxiliar.juego.getReleaseDate());
       textDescripcion.setText(Auxiliar.juego.getDescription());
       tituloJuego.setText(Auxiliar.juego.getTitle());
       //plataformaJuego.setText(Auxiliar.juego.getPlatforms([]));
       System.out.println(Auxiliar.juego.getDescription());

   }
    
    }

