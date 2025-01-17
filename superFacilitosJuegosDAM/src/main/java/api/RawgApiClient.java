package api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import excepcion.ExcepcionNullPointer;
import model.Games;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RawgApiClient {
    private static final String BASE_URL = "https://api.rawg.io/api";
    private static final String API_KEY = "40a20344994d45e5a8e4fb36eb09e961";

    private final OkHttpClient client;

    public RawgApiClient() {
        this.client = new OkHttpClient();
    }

    public List<Games> fetchTop5Games() {
        String url = BASE_URL + "/games?key=" + API_KEY + "&page_size=12";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonArray results = json.getAsJsonArray("results");
                List<Games> gamesList = new ArrayList<>();

                for (int i = 0; i < results.size(); i++) {
                    JsonObject gameJson = results.get(i).getAsJsonObject();
                    String title = gameJson.get("name").getAsString();
                    double rating = gameJson.has("rating") ? gameJson.get("rating").getAsDouble() : 0.0;
                    String releaseDate = gameJson.get("released").getAsString();
                    String imageUrl = gameJson.get("background_image").getAsString();
                    String description = gameJson.has("description") ? gameJson.get("description").getAsString() : "No description available";

                    JsonArray platformsJson = gameJson.getAsJsonArray("platforms");
                    List<String> platforms = new ArrayList<>();
                    for (int j = 0; j < platformsJson.size(); j++) {
                        JsonObject platform = platformsJson.get(j).getAsJsonObject().getAsJsonObject("platform");
                        platforms.add(platform.get("name").getAsString());
                    }

                    Games game = new Games(title, rating, releaseDate, description, imageUrl, platforms);
                    gamesList.add(game);
                }
                return (gamesList);
            } else {
                System.out.println("Error en la solicitud: " + response.code());
                return (null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
    }
    public List<Games> fetchTop5GamesGenre(String genero) {
    	String url = BASE_URL + "/genre?key=" + API_KEY + "&page_size=12&genre="+genero; 
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonArray results = json.getAsJsonArray("results");
                List<Games> gamesList = new ArrayList<>();

                for (int i = 0; i < results.size(); i++) {
                    JsonObject gameJson = results.get(i).getAsJsonObject();
                    String title = gameJson.get("name").getAsString();
                    double rating = gameJson.has("rating") ? gameJson.get("rating").getAsDouble() : 0.0;
                    String releaseDate = gameJson.get("released").getAsString();
                    String imageUrl = gameJson.get("background_image").getAsString();
                    String description = gameJson.has("description") ? gameJson.get("description").getAsString() : "No description available";

                    JsonArray platformsJson = gameJson.getAsJsonArray("platforms");
                    List<String> platforms = new ArrayList<>();
                    for (int j = 0; j < platformsJson.size(); j++) {
                        JsonObject platform = platformsJson.get(j).getAsJsonObject().getAsJsonObject("platform");
                        platforms.add(platform.get("name").getAsString());
                    }

                    Games game = new Games(title, rating, releaseDate, description, imageUrl, platforms);
                    gamesList.add(game);
                }
                return (gamesList);
            } else {
                System.out.println("Error en la solicitud: " + response.code());
                return (null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
    }


    /*public void fetchTop5Games() {
        String url = BASE_URL + "/games?key=" + API_KEY + "&page_size=10"; // Limita a 5 juegos
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

                // Obtener la lista de resultados
                JsonArray results = json.getAsJsonArray("results");
                System.out.println("Top 5 juegos populares:");
                for (int i = 0; i < results.size(); i++) {
                    JsonObject game = results.get(i).getAsJsonObject();
                    String name = game.get("name").getAsString();
                    double rating = game.get("rating").getAsDouble();
                    System.out.println((i + 1) + ". " + name + " (Rating: " + rating + ")");
                }
            } else {
                System.out.println("Error en la solicitud: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public List<Games> searchGameByName(String gameName) throws ExcepcionNullPointer {
        // Reemplazar espacios por '%20' para la URL
        String query = gameName.replace(" ", "%20");
        String url = BASE_URL + "/games?key=" + API_KEY + "&search=" + query;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonArray results = json.getAsJsonArray("results");

                // Verifica si no hay resultados
                if (results == null || results.size() == 0) {
                    throw new ExcepcionNullPointer("No se encontraron juegos para la búsqueda: " + gameName);
                }

                List<Games> gamesList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    JsonObject gameJson = results.get(i).getAsJsonObject();
                    
                    // Verificación de nulos para cada campo
                    String title = gameJson.has("name") && !gameJson.get("name").isJsonNull() ? gameJson.get("name").getAsString() : "Unknown Title";
                    
                    double rating = gameJson.has("rating") && !gameJson.get("rating").isJsonNull() ? gameJson.get("rating").getAsDouble() : 0.0;
                    
                    String releaseDate = gameJson.has("released") && !gameJson.get("released").isJsonNull() ? gameJson.get("released").getAsString() : "No Release Date";
                    
                    String imageUrl = gameJson.has("background_image") && !gameJson.get("background_image").isJsonNull() ? gameJson.get("background_image").getAsString() : "https://via.placeholder.com/200x300.png?text=No+Image";
                    
                    String description = gameJson.has("description") && !gameJson.get("description").isJsonNull() ? gameJson.get("description").getAsString() : "No description available";

                    // Verificar si platforms existe y no es nulo
                    JsonArray platformsJson = gameJson.has("platforms") && !gameJson.getAsJsonArray("platforms").isJsonNull() ? gameJson.getAsJsonArray("platforms") : new JsonArray();
                    List<String> platforms = new ArrayList<>();
                    for (int j = 0; j < platformsJson.size(); j++) {
                        JsonObject platform = platformsJson.get(j).getAsJsonObject().getAsJsonObject("platform");
                        platforms.add(platform.has("name") && !platform.get("name").isJsonNull() ? platform.get("name").getAsString() : "Unknown Platform");
                    }

                    Games game = new Games(title, rating, releaseDate, description, imageUrl, platforms);
                    gamesList.add(game);
                }
                return gamesList;
            } else {
                throw new ExcepcionNullPointer("Error en la solicitud a la API: " + response.code());
            }
        } catch (Exception e) {
            throw new ExcepcionNullPointer("Error al buscar juegos: " + e.getMessage());
        }
    }


    public void fetchPopularGames() {
        String url = BASE_URL + "/games?key=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                System.out.println(json);
            } else {
                System.out.println("Error en la solicitud: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
