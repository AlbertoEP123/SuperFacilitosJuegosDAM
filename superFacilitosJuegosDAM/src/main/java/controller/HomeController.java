package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.Metodos;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import model.Usuario;
import api.RawgApiClient;

public class HomeController {
	
	private RawgApiClient client;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    // Este método maneja la búsqueda
    @FXML
    void buscar(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
            searchGames();
        }
    }

    @FXML
    void click(MouseEvent event) {
    	 Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");
    }

    // Este método maneja la búsqueda mientras el usuario escribe.
    @FXML
    void textFieldBusqueda(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
            searchGames();
        }
    }

    @FXML
    void ventanaUsuario(MouseEvent event) {

    }

    @FXML
    void initialize() {
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

    // Limpia las imágenes de los ImageView cuando el campo de búsqueda está vacío.
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
        }
    }

    public void searchGames() {
        List<Games> games = client.searchGameByName(searchField.getText());
        System.out.println(games.size());
        if (games != null && !games.isEmpty()) {
            updateGameView(games);
        }
    }

    private void updateGameView(List<Games> games) {
    	int size = games.size();
    	if (size > 1) setImageWithFixedSize(imageView1, games.get(0).getImageUrl());
 	    if (size > 2) setImageWithFixedSize(imageView2, games.get(1).getImageUrl());
 	    if (size > 3) setImageWithFixedSize(imageView3, games.get(2).getImageUrl());
 	    if (size > 4) setImageWithFixedSize(imageView4, games.get(3).getImageUrl());
 	    if (size > 5) setImageWithFixedSize(imageView5, games.get(4).getImageUrl());
 	    if (size > 6) setImageWithFixedSize(imageView6, games.get(5).getImageUrl());
 	    if (size > 7) setImageWithFixedSize(imageView7, games.get(6).getImageUrl());
 	    if (size > 8) setImageWithFixedSize(imageView8, games.get(7).getImageUrl());
 	    if (size > 9) setImageWithFixedSize(imageView9, games.get(8).getImageUrl());
 	    if (size > 10) setImageWithFixedSize(imageView10, games.get(9).getImageUrl());
 	    if (size > 11) setImageWithFixedSize(imageView11, games.get(10).getImageUrl());
 	    if (size > 12) setImageWithFixedSize(imageView12, games.get(11).getImageUrl());
    }

    private void setImageWithFixedSize(ImageView imageView, String imageUrl) {
        Image image = new Image(imageUrl);
        imageView.setImage(image);
        imageView.setFitWidth(125); // Establece el ancho deseado
        imageView.setFitHeight(125); // Establece el alto deseado
    }

   /* private ScrollPane imprimirJuegos(List<Games> juegos) throws IOException {
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

        ScrollPane scrollPane = new ScrollPane(hbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(350);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return scrollPane;
    }
    // Método para buscar juegos en RAWG usando la API.
    private void searchGamesOnRawg(String query) {
        String urlString = "https://api.rawg.io/api/games?key=" + API_KEY + "&page_size=4&search=" + query;

        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray results = jsonResponse.getJSONArray("results");

                Platform.runLater(() -> {
                    for (int i = 0; i < results.length(); i++) {
                        try {
                            JSONObject game = results.getJSONObject(i);

                            if (game.has("background_image") && !game.isNull("background_image")) {
                                String imageUrl = game.getString("background_image");
                                if (imageUrl != null && !imageUrl.isEmpty()) {
                                    ImageView targetImageView = getImageView(i);
                                    if (targetImageView != null) {
                                        try {
                                            Image image = new Image(imageUrl);
                                            targetImageView.setImage(image);
                                        } catch (Exception e) {
                                            System.out.println("Error al cargar la imagen: " + e.getMessage());
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            System.out.println("Error al obtener el juego de la respuesta: " + e.getMessage());
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println("Error al hacer la solicitud a la API: " + e.getMessage());
            }
        }).start();
    }*/

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
