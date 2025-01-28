package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import api.RawgApiClient;
import app.Metodos;
import excepcion.ExcepcionNullPointer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Games;

public class HomeController {

	private RawgApiClient client;

	DetallesController detallesController;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label tituloJuego1;

	@FXML
	private Label tituloJuego10;

	@FXML
	private Label tituloJuego11;

	@FXML
	private Label tituloJuego12;

	@FXML
	private Label tituloJuego2;

	@FXML
	private Label tituloJuego4;

	@FXML
	private Label tituloJuego5;

	@FXML
	private Label tituloJuego6;

	@FXML
	private Label tituloJuego7;
	@FXML
	private Label tituloJuego3;

	@FXML
	private Label tituloJuego8;

	@FXML
	private Label tituloJuego9;

	@FXML
	public ImageView imageView1;

	@FXML
	private ImageView imageView10;

	@FXML
	private ImageView imageView11;

	@FXML
	private ImageView imageView12;

	@FXML
	private ImageView imageView2;

	@FXML
	private ImageView imageView3;

	@FXML
	private ImageView imageView4;

	@FXML
	private ImageView imageView5;

	@FXML
	private ImageView imageView6;

	@FXML
	private ImageView imageView7;

	@FXML
	private ImageView imageView8;

	@FXML
	private ImageView imageView9;

	@FXML
	private Label labelUsuario;

	@FXML
	private Pane panelCerrarSesion;

	private String filtro = "";

	@FXML
	private TextField searchField;


	private int currentPage = 1;
	private static final int GAMES_PER_PAGE = 12;

	// Este método maneja la búsqueda
	@FXML
	void buscar(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			searchGames();
		}
	}

	@FXML
	void ClickFPS(MouseEvent event) {
		principalGamesGenre("shooter");

	}

	@FXML
	void ClickTerror(MouseEvent event) {
		principalGamesGenre("casual");

	}

	@FXML
	void clickOlvidarContraseña(MouseEvent event) {

	}
	

	@FXML
	void clickPuzles(MouseEvent event) {
		principalGamesGenre("puzzle");
	}

	@FXML
	void ventanaUsuario(MouseEvent event) {

	}

	// Este método maneja la búsqueda mientras el usuario escribe.
	@FXML
	void textFieldBusqueda(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			searchGames();
		}
	}

	@FXML
	void logOut(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");

	}

    @FXML
    void paginaSiguiente(MouseEvent event) {
		currentPage++;
		actualizar();

    }

    @FXML
    void paginaAnterior(MouseEvent event) {
    	if(currentPage == 1) {
    		return;
    	}
    	currentPage--;
		actualizar();
    }
    
    @FXML
    void detalGame1(MouseEvent event) {
		Metodos.cambiarEscena(event, "/view/Detalles.fxml", "Detalles");
		detallesController.cargarImagen();
    	
    }
    public ImageView getImageView1() {
        return imageView1;
    }
	
	

	@FXML
	void initialize() {
		
		//labelUsuario.setText(LogInController.loggedInUser.getNickname());
		assert tituloJuego1 != null : "tituloJuego1 no se ha cargado correctamente!";
		assert tituloJuego2 != null : "tituloJuego2 no se ha cargado correctamente!";
		assert tituloJuego3 != null : "tituloJuego3 no se ha cargado correctamente!";
		assert tituloJuego4 != null : "tituloJuego4 no se ha cargado correctamente!";
		assert tituloJuego5 != null : "tituloJuego5 no se ha cargado correctamente!";
		assert tituloJuego6 != null : "tituloJuego6 no se ha cargado correctamente!";
		assert tituloJuego7 != null : "tituloJuego7 no se ha cargado correctamente!";
		assert tituloJuego8 != null : "tituloJuego8 no se ha cargado correctamente!";
		assert tituloJuego9 != null : "tituloJuego9 no se ha cargado correctamente!";
		assert tituloJuego10 != null : "tituloJuego10 no se ha cargado correctamente!";
		assert tituloJuego11 != null : "tituloJuego11 no se ha cargado correctamente!";
		assert tituloJuego12 != null : "tituloJuego12 no se ha cargado correctamente!";

		assert imageView1 != null : "imageView1 no se ha cargado correctamente!";
		
		assert imageView2 != null : "imageView2 no se ha cargado correctamente!";
		assert imageView3 != null : "imageView3 no se ha cargado correctamente!";
		assert imageView4 != null : "imageView4 no se ha cargado correctamente!";
		assert imageView5 != null : "imageView5 no se ha cargado correctamente!";
		assert imageView6 != null : "imageView6 no se ha cargado correctamente!";
		assert imageView7 != null : "imageView7 no se ha cargado correctamente!";
		assert imageView8 != null : "imageView8 no se ha cargado correctamente!";
		assert imageView9 != null : "imageView9 no se ha cargado correctamente!";
		assert imageView10 != null : "imageView10 no se ha cargado correctamente!";
		assert imageView11 != null : "imageView11 no se ha cargado correctamente!";
		assert imageView12 != null : "imageView12 no se ha cargado correctamente!";

		assert labelUsuario != null : "labelUsuario no se ha cargado correctamente!";
		assert panelCerrarSesion != null : "panelCerrarSesion no se ha cargado correctamente!";
	
		assert searchField != null : "searchField no se ha cargado correctamente!";
		assert labelUsuario != null : "fx:id=\"labelUsuario\" was not injected: check your FXML file 'Home.fxml'.";
		assert panelCerrarSesion != null
				: "fx:id=\"panelCerrarSesion\" was not injected: check your FXML file 'Home.fxml'.";

		assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'Home.fxml'.";
		client = new RawgApiClient();
		principalGames();


	}

	private void loadNextPage() {
		// Incrementar la página actual para cargar más juegos
		currentPage++;

		// Llamada a la función de búsqueda de juegos con paginación (se pasa el texto
		// de búsqueda, la página actual y la cantidad de juegos por página)
		searchGames();
	}

	private void clearImageViews() {
		for (int i = 0; i < 4; i++) {
			ImageView targetImageView = getImageView(i);
			if (targetImageView != null) {
				targetImageView.setImage(null);
			}
		}
	}

	public void principalGames() {
		List<Games> games = client.fetchTop5Games();
		System.out.println(games.size());
		if (games != null && !games.isEmpty()) {
			updateGameView(games);
			updateGameTittle(games);
		}
	}

	public void principalGamesGenre(String genero) {
		filtro ="&genres="+genero;
		List<Games> games = client.searchByFilter( currentPage, GAMES_PER_PAGE, filtro);
		System.out.println(games.size());
		if (games != null && !games.isEmpty()) {
			updateGameView(games);
			updateGameTittle(games);
		}
	}
	public void actualizar() {
		List<Games> games = client.searchByFilter( currentPage, GAMES_PER_PAGE, filtro);
		System.out.println(games.size());
		if (games != null && !games.isEmpty()) {
			updateGameView(games);
			updateGameTittle(games);
		}
	}

	public void searchGames() {
		filtro = "&search="+searchField.getText().replace(" ","%20");
		List<Games> games = client.searchByFilter( currentPage, GAMES_PER_PAGE,filtro);
		System.out.println(games.size());
		if (games != null && !games.isEmpty()) {
			updateGameView(games);
			updateGameTittle(games);
		} else {
			try {
				throw new ExcepcionNullPointer("Null pointer, no se encuentra el juego");
			} catch (ExcepcionNullPointer e) {
				Metodos.mostrarMensajeError(e.getMessage());
			}
		}
	}
	
	private void detailGame() {
		
	}

	private void updateGameView(List<Games> games) {
		// Limpiar las imágenes de las ImageView
		// Las primeras 12 imágenes se asignarán a las imageView1, imageView2,...
		// imageView12
		int size = games.size();

		// Asignar las imágenes a las ImageView ya existentes
		if (size > 0) {
			setImageWithFixedSize(imageView1, games.get(0).getImageUrl());
			
		}
		
		if (size > 1) {
			setImageWithFixedSize(imageView2, games.get(1).getImageUrl());
		}
		if (size > 2) {
			setImageWithFixedSize(imageView3, games.get(2).getImageUrl());
		}
		if (size > 3) {
			setImageWithFixedSize(imageView4, games.get(3).getImageUrl());
		}
		if (size > 4) {
			setImageWithFixedSize(imageView5, games.get(4).getImageUrl());
		}
		if (size > 5) {
			setImageWithFixedSize(imageView6, games.get(5).getImageUrl());
		}
		if (size > 6) {
			setImageWithFixedSize(imageView7, games.get(6).getImageUrl());
		}
		if (size > 7) {
			setImageWithFixedSize(imageView8, games.get(7).getImageUrl());
		}
		if (size > 8) {
			setImageWithFixedSize(imageView9, games.get(8).getImageUrl());
		}
		if (size > 9) {
			setImageWithFixedSize(imageView10, games.get(9).getImageUrl());
		}
		if (size > 10) {
			setImageWithFixedSize(imageView11, games.get(10).getImageUrl());
		}
		if (size > 11) {
			setImageWithFixedSize(imageView12, games.get(11).getImageUrl());
		}

		for (int i = 0; i < Math.min(size, 12); i++) {
			// Obtener la ImageView correspondiente
			ImageView imageView = getImageViewByIndex(i);

			// Configurar la imagen en la ImageView
			setImageWithFixedSize(imageView, games.get(i).getImageUrl());
		}
	}

	private ImageView getImageViewByIndex(int index) {
		switch (index) {
		case 0:
			return imageView1;
		case 1:
			return imageView2;
		case 2:
			return imageView3;
		case 3:
			return imageView4;
		case 4:
			return imageView5;
		case 5:
			return imageView6;
		case 6:
			return imageView7;
		case 7:
			return imageView8;
		case 8:
			return imageView9;
		case 9:
			return imageView10;
		case 10:
			return imageView11;
		case 11:
			return imageView12;
		default:
			return null;
		}
	}

	private void updateGameTittle(List<Games> games) {
		int size = games.size();

		if (size > 0) {
			tituloJuego1.setText(games.get(0).getTitle());
		}
		if (size > 1) {
			tituloJuego2.setText(games.get(1).getTitle());
		}
		if (size > 2) {
			tituloJuego3.setText(games.get(2).getTitle());
		}
		if (size > 3) {
			tituloJuego4.setText(games.get(3).getTitle());
		}
		if (size > 4) {
			tituloJuego5.setText(games.get(4).getTitle());
		}
		if (size > 5) {
			tituloJuego6.setText(games.get(5).getTitle());
		}
		if (size > 6) {
			tituloJuego7.setText(games.get(6).getTitle());
		}
		if (size > 7) {
			tituloJuego8.setText(games.get(7).getTitle());
		}
		if (size > 8) {
			tituloJuego9.setText(games.get(8).getTitle());
		}
		if (size > 9) {
			tituloJuego10.setText(games.get(9).getTitle());
		}
		if (size > 10) {
			tituloJuego11.setText(games.get(10).getTitle());
		}
		if (size > 11) {
			tituloJuego12.setText(games.get(11).getTitle());
		}
	}

	private void setImageWithFixedSize(ImageView imageView, String imageUrl) {
		if (imageUrl == null || imageUrl.isEmpty()) {
			imageUrl = "https://via.placeholder.com/200x300.png?text=No+Image";
		}
		Image image = new Image(imageUrl);
		imageView.setImage(image);
		imageView.setFitWidth(200); // Establece el tamaño de la imagen
		imageView.setPreserveRatio(true); // Mantén la relación de aspecto
	}

	

	private ImageView getImageView(int index) {
		switch (index) {
		case 0:
			return imageView1;
		case 1:
			return imageView2;
		case 2:
			return imageView3;
		case 3:
			return imageView4;

		default:
			return null;
		}
	}
<<<<<<< HEAD

	public ImageView imageView1() {
		// TODO Auto-generated method stub
		return imageView1;
	}
}
=======
}
>>>>>>> 62246659a4c7d10894946f950f5986c1fcc5f7f3
