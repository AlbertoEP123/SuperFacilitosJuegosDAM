package controller;

import java.util.List;

import api.RawgApiClient;
import app.Metodos;
import db.DaoBiblioteca;
import db.DaoUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Auxiliar;
import model.EntradaDeBiblioteca;

public class DetallesController {

	private RawgApiClient client;
	// pasar double de la nota a string

	double valor = Auxiliar.juego.getAverageRating();
	String formato = String.format("%.2f", valor);

	public DetallesController() {
	}

	@FXML
	private Button btnGuardar;

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
	void guardar(ActionEvent event) {
		EntradaDeBiblioteca entrada = new EntradaDeBiblioteca(
				DaoUsuarios.getId(LogInController.loggedInUser.getEmail()), Auxiliar.juego.getId(),
				Auxiliar.juego.getImageUrl(), Auxiliar.juego.getTitle(), "0", "1", "0", "comentario base",
				Auxiliar.juego.getAverageRating(), null);
		DaoBiblioteca.addEntradaBiblioteca(DaoUsuarios.getId(LogInController.loggedInUser.getEmail()), entrada, "ej");
		Metodos.mostrarMensajeConfirmacion(Auxiliar.juego.getTitle() + " ha sido añadido a la biblioteca");

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
		Metodos.cambiarEscena(event, "/view/Home2.fxml", "Home2");
	}

	@FXML
	void aniadir(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/Añadir.fxml", "Añadir");
	}

	@FXML
	void biblioteca(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/Biblioteca.fxml", "Biblioteca");

	}

	@FXML
	void initialize() {
		client = new RawgApiClient();
		caratulaJuego.setImage(Auxiliar.caratula.getImage());
		caratulaJuego.setFitWidth(333);
		caratulaJuego.setFitHeight(326);
		caratulaJuego.setPreserveRatio(true);

		fechaLanzamiento.setText(Auxiliar.juego.getReleaseDate());

		// Limpiar la descripción de HTML usando regex
		String descripcionHTML = client.obtenerDescripcionPorId(Auxiliar.juego.getId());
		String descripcionTexto = descripcionHTML.replaceAll("<[^>]*>", ""); // Elimina todas las etiquetas

		textDescripcion.setText(descripcionTexto);
		tituloJuego.setText(Auxiliar.juego.getTitle());
		String platforms = formatPlatforms(Auxiliar.juego.getPlatforms());
		plataformaJuego.setText(platforms);
		nota.setText(formato + "/5");
		labelUsuario.setText(LogInController.loggedInUser.getNickname());
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
