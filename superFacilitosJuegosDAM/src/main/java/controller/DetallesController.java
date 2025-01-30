package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;

public class DetallesController {
    private HomeController home;

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
    	
    }

    @FXML
    void buttonNota(MouseEvent event) {
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
    void guardarJuego(MouseEvent event) {
    }

    @FXML
    void logOut(MouseEvent event) {
    }

    @FXML
    void ventanaUsuario(MouseEvent event) {
    }
  
    @FXML
   void initialize() {
       caratulaJuego.setImage(Auxiliar.caratula.getImage());

   }
    
    }

