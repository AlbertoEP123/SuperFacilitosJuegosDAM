package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import api.RawgApiClient;
import app.Metodos;
import excepcion.ExcepcionNullPointer;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Auxiliar;
import model.Games;

public class HomeController2 {
	private RawgApiClient client;
	@FXML
	private ResourceBundle resources;
	private String filtro = "";

	@FXML
	private Menu menuGenerosID;

	@FXML
	private Label labelUsuario;
	@FXML
	private Pane panelCerrarSesion;
	@FXML
	private TextField textFieldBuscador;
	@FXML
	private ScrollPane scrollPane;

	@FXML
	private CheckMenuItem terrorGenero;

	@FXML
	private CheckMenuItem nintendoPlataforma;

	@FXML
	private VBox gamesContainer;
	private HBox currentHBox;
	private static final int COLUMNS = 3;

	private HashMap<ImageView, Games> hashMapJuegos = new HashMap<>();
	private int currentPage = 1;
	private static final int GAMES_PER_PAGE = 12;
	private boolean isLoading = false;

	@FXML
	void initialize() {
		
		client = new RawgApiClient();

		// Configurar el scroll para carga dinámica
		scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.doubleValue() == 1.0 && !isLoading) {
				loadMoreGames();
			}
		});

		principalGames();
	}

	@FXML
	void ventanaUsuario(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/Usuario.fxml", "Usuario");

	}

	@FXML
	void logOut(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");

	}

	@FXML
	void textFieldBusqueda(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			searchGames();
		}
	}

	private void loadMoreGames() {
		isLoading = true;
		currentPage++;
		List<Games> moreGames = client.searchByFilter(currentPage, GAMES_PER_PAGE, filtro);

		if (moreGames != null && !moreGames.isEmpty()) {
			addGamesToContainer(moreGames);
		}

		isLoading = false;
	}

	private void addGamesToContainer(List<Games> games) {
		for (Games game : games) {
			// Crear nuevos elementos si es necesario
			if (currentHBox == null || currentHBox.getChildren().size() >= COLUMNS) {
				currentHBox = new HBox();
				currentHBox.setSpacing(150); // Espacio entre columnas
				currentHBox.setAlignment(Pos.CENTER);
				gamesContainer.getChildren().add(currentHBox);
			}

			// Crear ImageView
			ImageView imageView = new ImageView();
			imageView.setFitWidth(200);
			imageView.setPreserveRatio(true);
			imageView.setOnMouseClicked(this::clickDetails);
			addLightEffectOnMouseEntered(imageView);
			setImageWithFixedSize(imageView, game.getImageUrl());
			hashMapJuegos.put(imageView, game);

			// Crear VBox para imagen + título (una columna)
			VBox gameBox = new VBox(15); // Espacio entre imagen y título
			gameBox.setAlignment(Pos.TOP_CENTER);

			// Crear Label
			Label titleLabel = new Label(game.getTitle());
			titleLabel.setMaxWidth(200);
			titleLabel.setWrapText(true);

			// Agregar elementos
			gameBox.getChildren().addAll(imageView, titleLabel);
			currentHBox.getChildren().add(gameBox);
		}
	}

	public void principalGames() {
		List<Games> games = client.topJuegos(GAMES_PER_PAGE);
		if (games != null && !games.isEmpty()) {
			gamesContainer.getChildren().clear(); // Limpiar contenedor
			addGamesToContainer(games);
		}
	}

	public void searchGames() {
		filtro = "&search=" + textFieldBuscador.getText().replace(" ", "%20");
		currentPage = 1; // Resetear a la primera página
		List<Games> games = client.searchByFilter(currentPage, GAMES_PER_PAGE, filtro);

		if (games != null && !games.isEmpty()) {
			gamesContainer.getChildren().clear();
			addGamesToContainer(games);
		} else {
			try {
				throw new ExcepcionNullPointer("Null pointer, no se encuentra el juego");
			} catch (ExcepcionNullPointer e) {
				Metodos.mostrarMensajeError(e.getMessage());
			}
		}
	}

	// Mantén los demás métodos como están...
	@FXML
	void clickDetails(MouseEvent event) {
		ImageView caratula = (ImageView) event.getSource();
		Games infoJuego = hashMapJuegos.get(caratula);
		System.out.println(infoJuego.getTitle());
		Auxiliar.juego = infoJuego;
		Auxiliar.caratula = caratula;
		Metodos.cambiarEscena(event, "/view/Detalles.fxml", "Detalles");
	}

	private void addLightEffectOnMouseEntered(ImageView imageView) {
		double a = imageView.getFitWidth();

		imageView.setOnMouseEntered(t -> {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(0.3);
			imageView.setEffect(colorAdjust);
			imageView.setCursor(Cursor.HAND);
		});
		imageView.setOnMouseExited(t -> {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(0);
			imageView.setEffect(colorAdjust);
			imageView.setCursor(Cursor.DEFAULT);

		});
	}

	@FXML
	void menuGeneros(ActionEvent event) {
		ArrayList<String> generosSeleccionados = new ArrayList<String>();
		for (MenuItem item : menuGenerosID.getItems()) {
			if (item instanceof RadioMenuItem) { // Verifica si es un CheckMenuItem
				RadioMenuItem checkItem = (RadioMenuItem) item;
				if (checkItem.isSelected()) { // Si está marcado
					System.out.println("Marcado: " + checkItem.getText());
					generosSeleccionados.add(checkItem.getText());
				}
			}
		}
		principalGamesGenre(generosSeleccionados);

	}

	private void setImageWithFixedSize(ImageView imageView, String imageUrl) {
		if (imageUrl == null || imageUrl.isEmpty()) {
			imageUrl = "https://via.placeholder.com/200x300.png?text=No+Image";
		}
		Image image = new Image(imageUrl, 200, // ancho máximo
				300, // alto máximo
				true, // preservar ratio
				true, // filtro de calidad
				true // cargar en segundo plano
		);

		imageView.setImage(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
	}

	private void borrarContenido() {
		currentPage = 1;
		gamesContainer.getChildren().clear();
		currentHBox = null;
	}

	public void principalGamesGenre(List<String> generos) {
	    borrarContenido();
	    
	    if (generos == null || generos.isEmpty()) {
	        principalGamesGenres();
	        return;
	    }
	    
	    // Convertir la lista de géneros a un string de filtro
	    String filtroGeneros = generos.stream()
	                                .map(String::toLowerCase)
	                                .map(g -> g.replace(" ", "-"))
	                                .collect(Collectors.joining(","));
	    
	    filtro = "&genres=" + filtroGeneros;
	    List<Games> games = client.searchByFilter(currentPage, GAMES_PER_PAGE, filtro);
	    System.out.println(games);
	    
	    if (games != null && !games.isEmpty()) {
	        addGamesToContainer(games);
	    } 
	}

	public void principalGamesGenres() {
		borrarContenido();
		List<Games> games = client.searchByFilter(currentPage, GAMES_PER_PAGE, filtro);
		System.out.println(games.size());
		if (games != null && !games.isEmpty()) {
			addGamesToContainer(games);
		}
	}
}