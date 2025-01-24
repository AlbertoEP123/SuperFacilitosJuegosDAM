package app;

import java.util.ArrayList;

import dao.DaoUsuarios;
import model.Usuario;

public class Launcher {

	public static void main(String[] args) {
		init();
		MainApp.main(args);
	}

	public static void init() {
		ArrayList<Usuario> user = new ArrayList<>();
		user.add(new Usuario("Alvaro", "Ruiz", "alvarorb", "a@gmail.com", "1234"));
		if(!DaoUsuarios.filtrarPorApodo(user.get(0).getNickname())) {
			DaoUsuarios.addUser(user.get(0));

		}
		Usuario.setUsuariosRegistrados(user);
	}


}
