package app;

import model.Usuario;

public class AppContext {
    private static Usuario usuarioLogueado;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }
}