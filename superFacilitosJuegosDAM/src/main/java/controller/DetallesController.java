package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DetallesController {
    
    private HomeController homeController;

    public DetallesController() {
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
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

    public void cargarImagen() {
        if (homeController != null && homeController.getImageView1() != null) {
            Image image = homeController.getImageView1().getImage(); 
            if (image != null) {
                caratulaJuego.setImage(image); 
                System.out.println("carga");
            }
        }
    }
}
