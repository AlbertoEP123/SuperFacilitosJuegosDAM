package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Biblioteca;
import model.EntradaDeBiblioteca;
import model.Games;

public class DaoBiblioteca {

	/**
	 * añade  una entrada a la biblioteca 
	 * @param idUsuario
	 * @param entrada
	 * @param comentario
	 */
    public static void addEntradaBiblioteca(int idUsuario, EntradaDeBiblioteca entrada, String comentario) {
        Connection connection = Conection.conectar();
        
        String insertQuery = "INSERT INTO biblioteca (idUsuario, idGame, imagen, titulos, pendiente, obtenido, jugado, comentario, nota, fechaJugado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, idUsuario);
            insertStatement.setInt(2, entrada.getIdGame()); 
            insertStatement.setString(3, entrada.getImagen()); 
            insertStatement.setString(4, entrada.getTitulos()); 
            insertStatement.setString(5, entrada.getPendiente());
            insertStatement.setString(6, entrada.getObtenido()); 
            insertStatement.setString(7, entrada.getJugado()); 
            insertStatement.setString(8, comentario); 
            insertStatement.setInt(9, entrada.getNota()); 
            insertStatement.setDate(10, entrada.getFechaJugado());
            
            insertStatement.executeUpdate();
            System.out.println("Juego añadido a la biblioteca");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de un juego en la biblioteca
    /**
     * actualiza  una entrada en especifico 
     * @param idUsuario
     * @param idGame
     * @param entrada
     * @param comentario
     */
    public static void updateEntradaBiblioteca(int idUsuario, int idGame, EntradaDeBiblioteca entrada, String comentario) {
        Connection connection = Conection.conectar();
        
        String updateQuery = "UPDATE biblioteca SET pendiente = ?, obtenido = ?, jugado = ?, comentario = ?, nota = ? "
                + "WHERE idUsuario = ? AND idGame = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, entrada.getPendiente());
            updateStatement.setString(2, entrada.getObtenido());
            updateStatement.setString(3, entrada.getJugado());
            updateStatement.setString(4, comentario); 
            updateStatement.setInt(5, entrada.getNota()); 
            updateStatement.setInt(6, idUsuario);
            updateStatement.setInt(7, idGame);
            
            int filasAfectadas = updateStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Juego actualizado correctamente");
            } else {
                System.out.println("No se encontró el juego");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * borra una entrada en concreto de un usuario en función de su id
     * @param idUsuario
     * @param idGame
     */
    public static void deleteEntradaBiblioteca(int idUsuario, int idGame) {
        Connection connection = Conection.conectar();
        
        String deleteQuery = "DELETE FROM biblioteca WHERE idUsuario = ? AND idGame = ?";

        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, idUsuario);
            deleteStatement.setInt(2, idGame);

            int filasAfectadas = deleteStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Juego eliminado ");
            } else {
                System.out.println("No se encontró ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todas las entradas de una biblioteca de un usuario
    public static List<EntradaDeBiblioteca> loadBiblioteca(int idUsuario) {
        Connection connection = Conection.conectar();
        List<EntradaDeBiblioteca> entradas = new ArrayList<>();
        
        String selectQuery = "SELECT idGame, imagen, titulos, pendiente, obtenido, jugado, comentario, "
                + "nota, fechaJugado FROM biblioteca WHERE idUsuario = ?";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, idUsuario);
            
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {

                    EntradaDeBiblioteca entrada = new EntradaDeBiblioteca(
                            idUsuario,
                            resultSet.getInt("idGame"),
                            resultSet.getString("imagen"),
                            resultSet.getString("titulos"),
                            resultSet.getString("pendiente"),
                            resultSet.getString("obtenido"),
                            resultSet.getString("jugado"),
                            resultSet.getString("comentario"),
                            resultSet.getInt("nota"),
                            resultSet.getDate("fechaJugado")
                    );

                    entradas.add(entrada);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return entradas;
    }


}