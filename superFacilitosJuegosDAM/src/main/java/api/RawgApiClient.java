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

	public List<Games> topJuegos(int juegosPorPagina) {
		String url = BASE_URL + "/games?key=" + API_KEY + "&page_size=" + juegosPorPagina;
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
				JsonArray results = json.getAsJsonArray("results");
				List<Games> gamesList = new ArrayList<>();

				for (int i = 0; i < results.size(); i++) {
					JsonObject gameJson = results.get(i).getAsJsonObject();
					int id = gameJson.has("id") && !gameJson.get("id").isJsonNull() ? gameJson.get("id").getAsInt() : 0;

					String title = gameJson.get("name").getAsString();
					double rating = gameJson.has("rating") ? gameJson.get("rating").getAsDouble() : 0.0;
					String releaseDate = gameJson.get("released").getAsString();
					String imageUrl = gameJson.get("background_image").getAsString();
					String description = gameJson.has("description") ? gameJson.get("description").getAsString()
							: "No description available";

					JsonArray platformsJson = gameJson.getAsJsonArray("platforms");
					List<String> platforms = new ArrayList<>();
					for (int j = 0; j < platformsJson.size(); j++) {
						JsonObject platform = platformsJson.get(j).getAsJsonObject().getAsJsonObject("platform");
						platforms.add(platform.get("name").getAsString());
					}

					Games game = new Games(id, title, rating, releaseDate, description, imageUrl, platforms);
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

	public List<Games> searchByFilter(int nPagina, int juegosPorPagina, String filtro) throws ExcepcionNullPointer {
		String url = BASE_URL + "/games?key=" + API_KEY + filtro + "&page=" + nPagina + "&page_size=" + juegosPorPagina;
		System.out.println(url);
		return search(url);

	}

	public List<Games> search(String url) throws ExcepcionNullPointer {
		// Reemplazar espacios por '%20' para la URL

		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
				JsonArray results = json.getAsJsonArray("results");

				// Verifica si no hay resultados
				if (results == null || results.size() == 0) {
					throw new ExcepcionNullPointer("No se encontraron juegos para la búsqueda: " + url);
				}

				List<Games> gamesList = new ArrayList<>();
				for (int i = 0; i < results.size(); i++) {
					JsonObject gameJson = results.get(i).getAsJsonObject();

					int id = gameJson.has("id") && !gameJson.get("id").isJsonNull() ? gameJson.get("id").getAsInt() : 0;
					// Verificación de nulos para cada campo
					String title = gameJson.has("name") && !gameJson.get("name").isJsonNull()
							? gameJson.get("name").getAsString()
							: "Unknown Title";

					double rating = gameJson.has("rating") && !gameJson.get("rating").isJsonNull()
							? gameJson.get("rating").getAsDouble()
							: 0.0;

					String releaseDate = gameJson.has("released") && !gameJson.get("released").isJsonNull()
							? gameJson.get("released").getAsString()
							: "No Release Date";

					String imageUrl = gameJson.has("background_image") && !gameJson.get("background_image").isJsonNull()
							? gameJson.get("background_image").getAsString()
							: "https://via.placeholder.com/200x300.png?text=No+Image";

					String description = gameJson.has("description") && !gameJson.get("description").isJsonNull()
							? gameJson.get("description").getAsString()
							: "No description available";

					// Verificar si platforms existe y no es nulo
					JsonArray platformsJson = gameJson.has("platforms")
							&& !gameJson.getAsJsonArray("platforms").isJsonNull() ? gameJson.getAsJsonArray("platforms")
									: new JsonArray();
					List<String> platforms = new ArrayList<>();
					for (int j = 0; j < platformsJson.size(); j++) {
						JsonObject platform = platformsJson.get(j).getAsJsonObject().getAsJsonObject("platform");
						platforms.add(platform.has("name") && !platform.get("name").isJsonNull()
								? platform.get("name").getAsString()
								: "Unknown Platform");
					}

					Games game = new Games(id, title, rating, releaseDate, description, imageUrl, platforms);
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

	public String obtenerDescripcionPorId(int id) {
		String url = BASE_URL + "/games/" + id + "?key=" + API_KEY;
		String description = null;
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
				
				description = json.has("description") && !json.get("description").isJsonNull()
						? json.get("description").getAsString()
						: "No description available";
			} else {
				throw new ExcepcionNullPointer("Error en la solicitud a la API: " + response.code());
			}
		} catch (Exception e) {
			throw new ExcepcionNullPointer("Error al buscar juegos: " + e.getMessage());
		}
		return description;

	}

	public void fetchGenres() {
		String url = BASE_URL + "/genres?key=" + API_KEY;
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
				JsonArray genres = json.getAsJsonArray("results");

				System.out.println("Lista de géneros disponibles:");
				for (int i = 0; i < genres.size(); i++) {
					JsonObject genre = genres.get(i).getAsJsonObject();
					String name = genre.get("name").getAsString();
					String slug = genre.get("slug").getAsString();
					System.out.println("Nombre: " + name + ", Slug: " + slug);
				}
			} else {
				System.out.println("Error al obtener géneros: " + response.code());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}