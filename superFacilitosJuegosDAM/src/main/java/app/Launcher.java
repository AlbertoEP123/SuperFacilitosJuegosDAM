package app;

import java.util.ArrayList;

import db.DaoUsuarios;
import model.Usuario;

public class Launcher {

	public static void main(String[] args) {
		init();
		MainApp.main(args);
	}

	public static void init() {
		
		Usuario user = new Usuario("Alvaro", "Ruiz", "alvarorb", "a@gmail.com", "1234");
		if(!DaoUsuarios.existeUsuario(user.getNickname())) {
			DaoUsuarios.addUser(user);
		}

	}


}
