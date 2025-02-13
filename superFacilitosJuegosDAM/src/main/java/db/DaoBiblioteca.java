package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Biblioteca;
import model.Games;

public class DaoBiblioteca {

    // Método para agregar un juego a la biblioteca de un usuario
    public static void addBiblioteca(int idUsuario, Games game, Biblioteca biblioteca) {
        Connection connection = Conection.conectar();
        
        String insertQuery = "INSERT INTO biblioteca (idUsuario, idGame, imagen, titulos, pendiente, obtenido, jugado, comentario, nota, fechaJugado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, idUsuario);
            insertStatement.setInt(2, game.getId()); // idGame desde la clase Games
            insertStatement.setString(3, game.getImageUrl()); // imagen desde la clase Games
            insertStatement.setString(4, game.getTitle()); // titulos desde la clase Games
            insertStatement.setString(5, String.valueOf(biblioteca.getPendiente())); // pendiente
            insertStatement.setString(6, String.valueOf(biblioteca.getObtenido())); // obtenido
            insertStatement.setString(7, String.valueOf(biblioteca.getJugado())); // jugado
            insertStatement.setString(8, ""); // comentario vacío (puedes modificarlo según el caso)
            insertStatement.setInt(9, 0); // nota por defecto
            insertStatement.setDate(10, null); // fechaJugado (puedes asignar la fecha si la tienes)
            
            // Ejecutar la consulta de inserción
            insertStatement.executeUpdate();
            System.out.println("Juego añadido a la biblioteca");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un juego en la biblioteca
    public static void updateBiblioteca(int idUsuario, int idGame, Biblioteca biblioteca) {
        Connection connection = Conection.conectar();
        
        String updateQuery = "UPDATE biblioteca SET pendiente = ?, obtenido = ?, jugado = ?, comentario = ?, nota = ? "
                + "WHERE idUsuario = ? AND idGame = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, String.valueOf(biblioteca.getPendiente()));
            updateStatement.setString(2, String.valueOf(biblioteca.getObtenido()));
            updateStatement.setString(3, String.valueOf(biblioteca.getJugado()));
            updateStatement.setString(4, ""); // comentario vacío (puedes modificarlo según el caso)
            updateStatement.setInt(5, 0); // nota por defecto
            updateStatement.setInt(6, idUsuario);
            updateStatement.setInt(7, idGame);
            
            // Ejecutar la consulta de actualización
            int filasAfectadas = updateStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Juego actualizado correctamente");
            } else {
                System.out.println("No se encontró el juego en la biblioteca");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un juego de la biblioteca
    public static void deleteBiblioteca(int idUsuario, int idGame) {
        Connection connection = Conection.conectar();
        
        String deleteQuery = "DELETE FROM biblioteca WHERE idUsuario = ? AND idGame = ?";

        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, idUsuario);
            deleteStatement.setInt(2, idGame);

            // Ejecutar la eliminación
            int filasAfectadas = deleteStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Juego eliminado de la biblioteca");
            } else {
                System.out.println("No se encontró el juego en la biblioteca");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los juegos de un usuario en la biblioteca
    public static List<Games> loadBiblioteca(int idUsuario) {
        Connection connection = Conection.conectar();
        List<Games> juegos = new ArrayList<>();
        
        String selectQuery = "SELECT idGame, imagen, titulos FROM biblioteca WHERE idUsuario = ?";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, idUsuario);
            
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Crear un objeto Games con los datos de la base de datos
                    Games game = new Games(
                            resultSet.getInt("idGame"),
                            resultSet.getString("titulos"),
                            0.0, // Por ahora no tenemos el promedio de calificación, puedes agregarlo si lo tienes
                            "",  // Aquí puedes agregar la fecha de lanzamiento si la tienes
                            "",  // Aquí puedes agregar la descripción si la tienes
                            resultSet.getString("imagen"),
                            new ArrayList<>() // Aquí puedes agregar las plataformas si las tienes
                    );

                    juegos.add(game);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return juegos;
    }
    
    public static List<Biblioteca> getBiblioteca(int idUsuario) {
        Connection connection = Conection.conectar();
        List<Biblioteca> biblioteca = new ArrayList<>();
        
        // Consulta para obtener los datos de la tabla biblioteca para un usuario específico
        String selectQuery = "SELECT b.idGame, b.imagen, b.titulos, b.pendiente, b.obtenido, b.jugado, b.comentario, "
                + "b.nota, b.fechaJugado "
                + "FROM biblioteca b "
                + "WHERE b.idUsuario = ?";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, idUsuario);
            
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Recuperar los datos de la tabla biblioteca
                    int idGame = resultSet.getInt("idGame");
                    String imagen = resultSet.getString("imagen");
                    String titulos = resultSet.getString("titulos");
                    int pendiente = resultSet.getInt("pendiente");
                    int obtenido = resultSet.getInt("obtenido");
                    int jugado = resultSet.getInt("jugado");
                    String comentario = resultSet.getString("comentario");
                    int nota = resultSet.getInt("nota");
                    Date fechaJugado = resultSet.getDate("fechaJugado");

                    // Crear un objeto Biblioteca con los datos obtenidos
                    Biblioteca entry = new Biblioteca(jugado, 0, pendiente, obtenido);  // Aquí puedes agregar más campos si lo deseas
                    entry.setJugado(jugado);
                    entry.setPendiente(pendiente);
                    entry.setObtenido(obtenido);
                    entry.setTerminado(nota);  // Si lo deseas, puedes usar "nota" como indicador de si está terminado
                    
                    // Agregar a la lista de resultados
                    biblioteca.add(entry);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return biblioteca;
    }


}