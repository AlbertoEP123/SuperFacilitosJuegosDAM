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
            Metodos.mostrarMensajeConfirmacion(Auxiliar.entrada.getTitulos()+" ha sido eliminado");
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
            Metodos.mostrarMensajeConfirmacion("No hay cambios");
            return;
        }

        EntradaDeBiblioteca entradaActualizada;
        if (rdbtnPendiente.isSelected()) {
            entradaActualizada = new EntradaDeBiblioteca(
                idUsuario,
                idGame,
                Auxiliar.entrada.getImagen(),
                Auxiliar.entrada.getTitulos(),
                "1", // Pendiente
                Auxiliar.entrada.getObtenido(),
                "0", // No terminado
                "",
                Auxiliar.entrada.getNota(),
                null
            );
        } else if (rdbtnTerminado.isSelected()) {
            // Si el estado es "Terminado", validar campos obligatorios
            LocalDate localDate = dateFEchaTerminado.getValue();
            String comentario = txtComentario.getText();
            String notaPersonal = txtNotaPersonal.getText();

            // Validar que los campos no estén vacíos
            if (localDate == null || comentario == null || comentario.trim().isEmpty() || notaPersonal == null || notaPersonal.trim().isEmpty()) {
                Metodos.mostrarMensajeError("Por favor, rellena todos los campos");
                return;
            }

            // Validar que la nota sea un número válido
            double nota;
            try {
                nota = Double.parseDouble(notaPersonal);
            } catch (NumberFormatException e) {
                Metodos.mostrarMensajeError("La nota debe ser un número válido.");
                return;
            }

            // Crear la entrada actualizada
            Date fechaJugado = Date.valueOf(localDate);
            entradaActualizada = new EntradaDeBiblioteca(
                idUsuario,
                idGame,
                Auxiliar.entrada.getImagen(),
                Auxiliar.entrada.getTitulos(),
                "0", // No pendiente
                Auxiliar.entrada.getObtenido(),
                "1", // Terminado
                comentario,
                nota,
                fechaJugado
            );
        } else {
            return; // No debería llegar aquí
        }

        // Guardar los cambios en la base de datos
        DaoBiblioteca.updateEntradaBiblioteca(idUsuario, idGame, entradaActualizada, txtComentario.getText());
        Metodos.mostrarMensajeConfirmacion("Cambios guardados correctamente.");
        Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");
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
        String descripcionTexto = descripcionHTML.replaceAll("<[^>]*>", ""); // Elimina todas las etiquetas HTML de la descr

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

        // Cargar el estado del RadioButton basado en la entrada actual
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
        } else if ("1".equals(Auxiliar.entrada.getPendiente())) {
            rdbtnPendiente.setSelected(true);
        }
    }

    private void toggleFields() {
        boolean terminado = rdbtnTerminado.isSelected();
        dateFEchaTerminado.setDisable(!terminado);
        txtComentario.setDisable(!terminado);
        txtNotaPersonal.setDisable(!terminado);
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
}

