package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.Metodos;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Usuario;

public class HomeController {

    @FXML
    private Label labelUsuario;
    
    @FXML
    private Pane panelCerrarSesion;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ScrollBar scrollVideojuegosHome;
    
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;

  
    
    private static final String API_KEY = "747e000cee8c464e90d9c9034863aec5";

    @FXML
    public void initialize() {
        labelUsuario.setText(LogInController.loggedInUser.getNickname());
    }

    @FXML
    void ventanaUsuario(MouseEvent event) {
    }
    
    @FXML
    void click(MouseEvent event) {
        Metodos.cambiarEscena(event, "/view/LogIn.fxml", "LogIn");
    }

    // Este método maneja la búsqueda
    @FXML
    public void searchGames(ActionEvent event) {
        String query = searchField.getText();
        if (!query.isEmpty()) {
            searchGamesOnRawg(query);
        }
    }

    // Este método maneja la búsqueda mientras el usuario escribe.
    @FXML
    void textFieldBusqueda(KeyEvent event) {
        String query = searchField.getText();
        if (!query.isEmpty()) {
            searchGamesOnRawg(query);
        } else {
            clearImageViews();
        }
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
