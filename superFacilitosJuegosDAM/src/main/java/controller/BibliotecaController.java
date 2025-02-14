package controller;

import java.util.List;
import java.util.stream.Collectors;

import app.Metodos;
import db.DaoBiblioteca;
import db.DaoUsuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;
import model.EntradaDeBiblioteca;
import model.Games;

public class BibliotecaController {

    @FXML
    private ImageView btnHome;

    @FXML
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, 
                       imageView7, imageView8, imageView9, imageView10, imageView11, imageView12;

    @FXML
    private Label tituloJuego1, tituloJuego2, tituloJuego3, tituloJuego4, tituloJuego5, tituloJuego6, 
                  tituloJuego7, tituloJuego8, tituloJuego9, tituloJuego10, tituloJuego11, tituloJuego12;

    @FXML
    private Label labelUsuario, nPagina;

    @FXML
    private Pane panelCerrarSesion;

    @FXML
    private TextField searchField;
    
    private int currentPage = 1;
    private static final int GAMES_PER_PAGE = 12;
    private List<EntradaDeBiblioteca> juegosBiblioteca;
    private List<EntradaDeBiblioteca> juegosPendientes;
    
    @FXML
    void ClickPendientes(MouseEvent event) {
        juegosPendientes = juegosBiblioteca.stream()
                .filter(juego -> "1".equals(juego.getPendiente()))
                .collect(Collectors.toList());
        
        currentPage = 1;
        actualizarVistaPendientes();
    }
    
    private void actualizarVistaPendientes() {
        int startIndex = (currentPage - 1) * GAMES_PER_PAGE;
        int endIndex = Math.min(startIndex + GAMES_PER_PAGE, juegosPendientes.size());

        limpiarVista();

        for (int i = startIndex; i < endIndex; i++) {
            EntradaDeBiblioteca juego = juegosPendientes.get(i);
            setImageView(i - startIndex, juego.getImagen());
            setTituloLabel(i - startIndex, juego.getTitulos());
        }

        nPagina.setText("Página: " + currentPage);
    }
    
    @FXML
    void ClickTusJuegos(MouseEvent event) {
        // Restaurar la lista a todos los juegos cargados originalmente
        actualizarVista();
    }
    @FXML
    void buscar(KeyEvent event) {

    }



    @FXML
    void clickTerminados(MouseEvent event) {
        // Filtrar solo juegos terminados (jugado = "1")
        List<EntradaDeBiblioteca> juegosTerminados = juegosBiblioteca.stream()
            .filter(juego -> "1".equals(juego.getJugado()))
            .toList();

        // Mostrar la vista con juegos terminados
        mostrarJuegosFiltrados(juegosTerminados);
    }
    
    private void mostrarJuegosFiltrados(List<EntradaDeBiblioteca> juegosFiltrados) {
        int startIndex = (currentPage - 1) * GAMES_PER_PAGE;
        int endIndex = Math.min(startIndex + GAMES_PER_PAGE, juegosFiltrados.size());

        limpiarVista();

        for (int i = startIndex; i < endIndex; i++) {
            EntradaDeBiblioteca juego = juegosFiltrados.get(i);
            setImageView(i - startIndex, juego.getImagen());
            setTituloLabel(i - startIndex, juego.getTitulos());
        }

        nPagina.setText("Página: " + currentPage);
    }


    
    @FXML
    void clickDetails(MouseEvent event) {
        ImageView caratula = (ImageView) event.getSource();
        
        // Array de ImageView en el orden en que se muestran en la UI
        ImageView[] imageViews = {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6,
                                  imageView7, imageView8, imageView9, imageView10, imageView11, imageView12};

        // Buscar qué ImageView fue clicado
        int index = -1;
        for (int i = 0; i < imageViews.length; i++) {
            if (caratula == imageViews[i]) {
                index = i;
                break;
            }
        }

        // Si no encontró el índice, salir
        if (index == -1) return;

        // Calcular el índice real en juegosBiblioteca
        int realIndex = (currentPage - 1) * GAMES_PER_PAGE + index;

        // Verificar que el índice es válido
        if (realIndex >= juegosBiblioteca.size()) return;

        // Obtener la entrada correspondiente
        EntradaDeBiblioteca entrada = juegosBiblioteca.get(realIndex);

        System.out.println(entrada.getTitulos());

        // Pasar la información a Auxiliar
        Auxiliar.entrada = entrada;
        Auxiliar.caratula = caratula;

        // Cambiar de escena
        Metodos.cambiarEscena(event, "/view/DetallesBiblioteca.fxml", "Detalles");
    }

    @FXML
    void logOut(MouseEvent event) {

    }

    @FXML
    void initialize() {
        labelUsuario.setText(LogInController.loggedInUser.getNickname());
        cargarBiblioteca();
    }

    private void cargarBiblioteca() {
        juegosBiblioteca = db.DaoBiblioteca.loadBiblioteca(DaoUsuarios.getId(LogInController.loggedInUser.getEmail()));
        actualizarVista();
    }

    private void actualizarVista() {
        int startIndex = (currentPage - 1) * GAMES_PER_PAGE;
        int endIndex = Math.min(startIndex + GAMES_PER_PAGE, juegosBiblioteca.size());
        
        limpiarVista();
        
        for (int i = startIndex; i < endIndex; i++) {
            EntradaDeBiblioteca juego = juegosBiblioteca.get(i);
            setImageView(i - startIndex, juego.getImagen());
            setTituloLabel(i - startIndex, juego.getTitulos());
        }
        
        nPagina.setText("Página: " + currentPage);
    }

    private void limpiarVista() {
        for (int i = 0; i < GAMES_PER_PAGE; i++) {
            setImageView(i, null);
            setTituloLabel(i, "");
        }
    }

    private void setImageView(int index, String imageUrl) {
        ImageView[] imageViews = {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6,
                                  imageView7, imageView8, imageView9, imageView10, imageView11, imageView12};
        
        if (index >= imageViews.length) return;
        
        ImageView imageView = imageViews[index];
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "https://via.placeholder.com/200x300.png?text=No+Image";
        }
        imageView.setImage(new Image(imageUrl));
    }

    private void setTituloLabel(int index, String titulo) {
        Label[] labels = {tituloJuego1, tituloJuego2, tituloJuego3, tituloJuego4, tituloJuego5, tituloJuego6,
                          tituloJuego7, tituloJuego8, tituloJuego9, tituloJuego10, tituloJuego11, tituloJuego12};
        
        if (index >= labels.length) return;
        
        labels[index].setText(titulo);
    }

    @FXML
    void paginaSiguiente(MouseEvent event) {
        if (currentPage * GAMES_PER_PAGE < juegosBiblioteca.size()) {
            currentPage++;
            actualizarVista();
        }
    }

    @FXML
    void paginaAnterior(MouseEvent event) {
        if (currentPage > 1) {
            currentPage--;
            actualizarVista();
        }
    }
    

    @FXML
    void ventanaUsuario(MouseEvent event) {
    	Metodos.cambiarEscena(event, "/view/Usuario.fxml", "Usuario");
    }
}
