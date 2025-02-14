package db;

import java.beans.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.Metodos;
import model.Usuario;

public class DaoUsuarios {
	
	public static void addUser(Usuario user) {
		Connection connection = Conection.conectar();

		// Query de inserción en la tabla Usuarios, sin incluir el campo Id porque es
		// autoincremental
		String insertQuery = "INSERT INTO Usuarios (Nombre, Apellidos, fecha_Nacimiento, Apodo, Email, Contrasena) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

			// Asignar los valores del objeto Usuario a la consulta
			insertStatement.setString(1, user.getNombre());
			insertStatement.setString(2, user.getApellidos());
			insertStatement.setString(3, user.getFechaNac());
			insertStatement.setString(4, user.getNickname());
			insertStatement.setString(5, user.getEmail());
			insertStatement.setString(6, user.getContraseña());

			// Ejecutar la consulta
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de errores, puedes mejorarlo registrando logs o lanzando
									// excepciones personalizadas
		}

	}

	public static boolean existeUsuario(String apodo) {
		Connection connection = Conection.conectar();
		String selectQuery = "SELECT COUNT(*) FROM Usuarios WHERE Apodo = ?";

		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);

		) {
			selectStatement.setString(1, apodo);
			try (ResultSet resultSet = selectStatement.executeQuery()) {

				resultSet.next();
				int countQuery = resultSet.getInt(1);
				
				if(countQuery == 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}

		return true; 
	}
	// No se usa

	public static ArrayList<Usuario> loadUsers() {
		Connection connection = Conection.conectar();
		ArrayList<Usuario> usuarios = new ArrayList<>();

		// Query para obtener todos los usuarios
		String selectQuery = "SELECT  Nombre, Apellidos, fecha_Nacimiento, Apodo, Email, Contrasena FROM Usuarios";

		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
				ResultSet resultSet = selectStatement.executeQuery()) {

			while (resultSet.next()) {
				// Crear un objeto Usuario con los datos de cada fila
				Usuario user = new Usuario(resultSet.getString("Nombre"), resultSet.getString("Apellidos"),
						resultSet.getString("Apodo"), resultSet.getString("Email"), resultSet.getString("Contrasena"));

				user.setNombre(resultSet.getString("Nombre"));
				user.setApellidos(resultSet.getString("Apellidos"));
				user.setFechaNac(resultSet.getString("fecha_Nacimiento"));
				user.setNickname(resultSet.getString("Apodo"));
				user.setEmail(resultSet.getString("Email"));
				user.setContraseña(resultSet.getString("Contrasena"));

				// Agregar el usuario al ArrayList
				usuarios.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de errores
		}

		return usuarios; // Devolver la lista de usuarios
	}

	public static void updateCampo(String nickname, String nuevoValor,String campo) {
		Connection connection = Conection.conectar();
		String updateQuery = "UPDATE Usuarios SET "+campo + " = ? WHERE Apodo = ?";
		try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
			updateStatement.setString(1, nuevoValor);
			updateStatement.setString(2, nickname);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(campo+" cambiado en la db");

	}

	public static void updateApellidos(String nickname, String nuevosApellidos) {
		updateCampo(nickname, nuevosApellidos,"Apellidos");
	}

	public static void updateContraseña(String nickname, String nuevaContraseña) {
		updateCampo(nickname, nuevaContraseña, "Contrasena");

	}

	public static void updateNickname(String nickname, String nuevoNickname) {
		
		updateCampo(nickname, nuevoNickname, "Apodo");

	}
public static void updateNombre(String nickname, String nuevoNombre) {
		
		updateCampo(nickname, nuevoNombre, "Nombre");

	}

public static int getId(String email) {
    Connection connection = Conection.conectar();
    int idUsuario = -1; // Valor por defecto si no se encuentra el usuario

    String selectQuery = "SELECT Id FROM Usuarios WHERE Email = ?";

    try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
        selectStatement.setString(1, email);
        try (ResultSet resultSet = selectStatement.executeQuery()) {
            if (resultSet.next()) {
                idUsuario = resultSet.getInt("Id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idUsuario; // Retorna el ID o -1 si no se encuentra
}


}
