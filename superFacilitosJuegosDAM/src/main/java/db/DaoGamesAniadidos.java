package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.MyGames;

public class DaoGamesAniadidos {

	  // Método para agregar un juego a la base de datos
    public static boolean addGame(MyGames game) {
    	Connection connection = Conection.conectar();
        String sql = "INSERT INTO Games_Aniadidos (title, releaseDate, user, description, imageUrl, platforms) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getReleaseDate());
            ps.setString(3, game.getUser());
            ps.setString(4, game.getDescription());
            ps.setString(5, game.getImageUrl());
            ps.setString(6, game.getPlatforms());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para seleccionar todos los juegos añadidos
    public List<MyGames> getAllGames(String user) {
        Connection connection = Conection.conectar();
        List<MyGames> games = new ArrayList<>();
        String sql = "SELECT * FROM Games_Aniadidos WHERE user = ?";  // Modificamos para filtrar por el usuario

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            // Establecemos el parámetro de la consulta
            pst.setString(1, user);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    String releaseDate = rs.getString("releaseDate");
                    String description = rs.getString("description");
                    String imageUrl = rs.getString("imageUrl");
                    String platforms = rs.getString("platforms");

                    MyGames game = new MyGames(title, releaseDate, description, imageUrl, platforms);
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
