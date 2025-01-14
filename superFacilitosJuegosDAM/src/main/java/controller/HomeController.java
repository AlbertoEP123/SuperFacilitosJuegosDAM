package controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import api.RawgApiClient;
import app.Metodos;
import excepcion.PersonalizedNullPointerException;
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
    private ImageView imageView1;

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

    @FXML
    private ScrollBar scrollVideojuegosHome;

    @FXML
    private TextField searchField;
    @FXML
    private ScrollPane scrollGame;

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
/*
 * CORREGIR: SCROLLBAR,  GENEROS
 */



    @FXML
    void initialize() {

    	try {
        	labelUsuario.setText(LogInController.loggedInUser.getNickname());

		} catch (Exception e) {
			
		}


    	 assert imageView1 != null : "fx:id=\"imageView1\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView10 != null : "fx:id=\"imageView10\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView11 != null : "fx:id=\"imageView11\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView12 != null : "fx:id=\"imageView12\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView2 != null : "fx:id=\"imageView2\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView3 != null : "fx:id=\"imageView3\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView4 != null : "fx:id=\"imageView4\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView5 != null : "fx:id=\"imageView5\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView6 != null : "fx:id=\"imageView6\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView7 != null : "fx:id=\"imageView7\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView8 != null : "fx:id=\"imageView8\" was not injected: check your FXML file 'Home.fxml'.";
         assert imageView9 != null : "fx:id=\"imageView9\" was not injected: check your FXML file 'Home.fxml'.";
        assert labelUsuario != null : "fx:id=\"labelUsuario\" was not injected: check your FXML file 'Home.fxml'.";
        assert panelCerrarSesion != null : "fx:id=\"panelCerrarSesion\" was not injected: check your FXML file 'Home.fxml'.";
        assert scrollVideojuegosHome != null : "fx:id=\"scrollVideojuegosHome\" was not injected: check your FXML file 'Home.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'Home.fxml'.";
        client = new RawgApiClient();
        principalGames();

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

    public void searchGames() {
        List<Games> games = client.searchGameByName(searchField.getText());
        System.out.println(games.size());
        if (games != null && !games.isEmpty()) {
            updateGameView(games);
            updateGameTittle(games);
        }else {
            // Lanza la excepción personalizada, pero también muestra un mensaje al usuario
            try {
                throw new PersonalizedNullPointerException("Null pointer, no se encuentra el juego");
            } catch (PersonalizedNullPointerException e) {
            	Metodos.mostrarMensajeError(e.getMessage());
            }
        }
    }

    private void updateGameView(List<Games> games) {
    	int size = games.size();
    	if (size > 1) {
			setImageWithFixedSize(imageView1, games.get(4).getImageUrl());
		}
 	    if (size > 2) {
			setImageWithFixedSize(imageView2, games.get(5).getImageUrl());
		}
 	    if (size > 3) {
			setImageWithFixedSize(imageView3, games.get(6).getImageUrl());
		}
 	    if (size > 4) {
			setImageWithFixedSize(imageView4, games.get(7).getImageUrl());
		}
 	    if (size > 5) {
			setImageWithFixedSize(imageView5, games.get(0).getImageUrl());
		}
 	    if (size > 6) {
			setImageWithFixedSize(imageView6, games.get(1).getImageUrl());
		}
 	    if (size > 7) {
			setImageWithFixedSize(imageView7, games.get(2).getImageUrl());
		}
 	    if (size > 8) {
			setImageWithFixedSize(imageView8, games.get(3).getImageUrl());
		}
 	    if (size > 9) {
			setImageWithFixedSize(imageView9, games.get(8).getImageUrl());
		}
 	    if (size > 10) {
			setImageWithFixedSize(imageView10, games.get(9).getImageUrl());
		}
 	    if (size > 11) {
			setImageWithFixedSize(imageView11, games.get(10).getImageUrl());
		}
 	    if (size > 12) {
			setImageWithFixedSize(imageView12, games.get(11).getImageUrl());
		} else {
			// Scroll y cargar nuevas imagenes con las existentes
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
        Image image = new Image(imageUrl);

        imageView.setImage(image);        
        imageView.setFitWidth(200); // Establece el ancho deseado
        imageView.setFitHeight(200); // Establece el alto deseado
        
        imageView.setPreserveRatio(true);
    }

    private ScrollPane imprimirJuegos(List<Games> juegos) throws IOException {
        HBox hbox = new HBox(20);
        hbox.setStyle("-fx-padding: 10;");

        for (int i = 0; i < juegos.size() && i < 8; i++) {
            String imageUrl = juegos.get(i).getImageUrl();
            if (imageUrl == null || imageUrl.isEmpty()) {
                imageUrl = "https://via.placeholder.com/200x300.png?text=No+Image";
            }

            ImageView imageView = new ImageView(new Image(imageUrl));
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);

            VBox vbox = new VBox(imageView);
            vbox.setSpacing(20);
            hbox.getChildren().add(vbox);

            final int index = i;
            imageView.setOnMouseClicked(event -> {
                System.out.println("Has hecho clic en el juego: " + juegos.get(index).getTitle());
            });
        }

         scrollGame = new ScrollPane(hbox);
         scrollGame.setFitToWidth(true);
         scrollGame.setPrefHeight(350);
         scrollGame.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
         scrollGame.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return scrollGame;
    }


    // Método para obtener el ImageView correspondiente basado en el índice.
    private ImageView getImageView(int index) {
        switch (index) {
            case 0: return imageView1;
            case 1: return imageView2;
            case 2: return imageView3;
            case 3: return imageView4;

            default: return null;
        }
    }
}
