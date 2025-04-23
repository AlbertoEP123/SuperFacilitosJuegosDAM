package app;


import db.DaoUsuarios;
import model.Usuario;
/**
 * TO DO:
 * 	HOME CONTROLLER: CAMBIAR FONDO DISEÑO, LETRAS, TAMAÑO IMAGENES, NAVEGACION, MAS PLATAFORMAS Y GENEROS
 * 	DETAIL CONTROLLER : CAMBIAR DISEÑO
 *  USUARIO CONTROLLER : CAMBIAR DISEÑO Y CAMBIAR IMAGEN FUNCIONAL
 */
public class Launcher {

	public static void main(String[] args) {
		init();
		MainApp.main(args);
	}

	public static void init() {
		
		Usuario user = new Usuario("Alvaro", "Ruiz", "al", "a@gmail.com", "1234");
		if(!DaoUsuarios.existeUsuario(user.getNickname())) {
			DaoUsuarios.addUser(user);
		}

	}


}
