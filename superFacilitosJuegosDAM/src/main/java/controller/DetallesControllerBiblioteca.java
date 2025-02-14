package controller;

import java.sql.Date;
import java.time.LocalDate;

import api.RawgApiClient;
import app.Metodos;
import db.DaoBiblioteca;
import db.DaoUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;
import model.EntradaDeBiblioteca;

public class DetallesControllerBiblioteca {

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ImageView caratulaJuego;

    @FXML
    private DatePicker dateFEchaTerminado;

    @FXML
    private Pane panelCerrarSesion;

    @FXML
    private Pane panelVolver;
    
    @FXML
    private TextArea txtComentario;

    @FXML
    private RadioButton rdbtnPendiente;

    @FXML
    private RadioButton rdbtnTerminado;

    @FXML
    private Label textDescripcion;
    
    private RawgApiClient client;
    @FXML
    private TextField txtNotaPersonal;

    @FXML
    private Label lblfecha;

    @FXML
    void eliminarJuego(ActionEvent event) {
        int idUsuario = DaoUsuarios.getId(LogInController.loggedInUser.getEmail());  
        int idGame = Auxiliar.entrada.getIdGame();

        if (idGame > 0) {
            DaoBiblioteca.deleteEntradaBiblioteca(idUsuario, idGame);
            System.out.println("El juego con ID " + idGame + " ha sido eliminado.");
            Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");
        } else {
            System.out.println("No se pudo obtener el ID del juego.");
        }
    }

    @FXML
    void guardar(ActionEvent event) {

        int idUsuario = DaoUsuarios.getId(LogInController.loggedInUser.getEmail());
        int idGame = Auxiliar.entrada.getIdGame();

        if (!rdbtnPendiente.isSelected() && !rdbtnTerminado.isSelected()) {
            System.out.println("No hay cambios.");
            return;
        } else if (rdbtnPendiente.isSelected()) {
            EntradaDeBiblioteca entradaActualizada = new EntradaDeBiblioteca(
                idUsuario,
                idGame,
                Auxiliar.entrada.getImagen(),
                Auxiliar.entrada.getTitulos(),
                "1",
                Auxiliar.entrada.getObtenido(),
                "0",
                "",
                Auxiliar.entrada.getNota(),
                null
            );
            DaoBiblioteca.updateEntradaBiblioteca(idUsuario, idGame, entradaActualizada, txtComentario.getText());
            System.out.println("Cambios guardados correctamente. v.pendiente");
            Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");
        } else if (rdbtnTerminado.isSelected()) {
        	
        	LocalDate localDate = dateFEchaTerminado.getValue();
        	if (localDate != null) {
        	    System.out.println("Fecha seleccionada: " + localDate);
        	    Date fechaJugado = Date.valueOf(localDate);
        	    // Continuar con el guardado...
        	} else {
        	    System.out.println("No se ha seleccionado ninguna fecha.");
        	}
        	if (localDate != null) {
        	    System.out.println("Fecha seleccionada: " + localDate);
        	    Date fechaJugado = Date.valueOf(localDate);
                EntradaDeBiblioteca entradaActualizada = new EntradaDeBiblioteca(
                        idUsuario,
                        idGame,
                        Auxiliar.entrada.getImagen(),
                        Auxiliar.entrada.getTitulos(),
                        "0",
                        Auxiliar.entrada.getObtenido(),
                        "1",
                        txtComentario.getText(),
                        Double.parseDouble(txtNotaPersonal.getText()),
                        fechaJugado
                    );
                    DaoBiblioteca.updateEntradaBiblioteca(idUsuario, idGame, entradaActualizada, txtComentario.getText());
                    System.out.println("Cambios guardados correctamente. v. terminado");
                    Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");
        	} else {
        	    System.out.println("No se ha seleccionado ninguna fecha.");
        	}

        }
    }

    @FXML
    void logOut(MouseEvent event) {
        Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");
    }

    @FXML
    void irABiblioteca(MouseEvent event) {
        Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");
    }
    
    @FXML
    void guardarJuego(MouseEvent event) {
        System.out.println("Guardar juego clickeado");
    }

    @FXML
    void initialize() {
        client = new RawgApiClient();
        caratulaJuego.setImage(Auxiliar.caratula.getImage());
        caratulaJuego.setFitWidth(333);
        caratulaJuego.setFitHeight(326);
        caratulaJuego.setPreserveRatio(true);

        // Limpiar la descripción de HTML usando regex
        String descripcionHTML = client.obtenerDescripcionPorId(Auxiliar.entrada.getIdGame());
        String descripcionTexto = descripcionHTML.replaceAll("<[^>]*>", ""); // Elimina todas las etiquetas HTML

        textDescripcion.setText(descripcionTexto); // Asignar el texto limpio al Label

        dateFEchaTerminado.setDisable(true);
        txtComentario.setDisable(true);
        txtNotaPersonal.setDisable(true);

        rdbtnTerminado.setOnAction(event -> {
            rdbtnPendiente.setSelected(false);
            toggleFields();
        });

        rdbtnPendiente.setOnAction(event -> {
            rdbtnTerminado.setSelected(false);
            toggleFields();
        });

        if ("1".equals(Auxiliar.entrada.getJugado())) {
            rdbtnTerminado.setSelected(true);
            toggleFields();

            // Mostrar la fecha anterior si existe
            if (Auxiliar.entrada.getFechaJugado() != null) {
                LocalDate fechaLocal = Auxiliar.entrada.getFechaJugado().toLocalDate();
                dateFEchaTerminado.setValue(fechaLocal); // ← Aquí cargamos la fecha previa en el DatePicker
                lblfecha.setText(fechaLocal.toString());
            }

            txtComentario.setText(Auxiliar.entrada.getComentario());
            txtNotaPersonal.setText(String.valueOf(Auxiliar.entrada.getNota()));
        }
    }

    private void toggleFields() {
        boolean terminado = rdbtnTerminado.isSelected();
        dateFEchaTerminado.setDisable(!terminado);
        txtComentario.setDisable(!terminado);
        txtNotaPersonal.setDisable(!terminado);
    }
}
