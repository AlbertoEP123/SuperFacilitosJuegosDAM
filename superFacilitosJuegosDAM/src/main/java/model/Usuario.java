package model;

import java.util.ArrayList;

import db.DaoUsuarios;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String fechaNac;
	private String nickname;
	private String email;
	private String confEmail;
	private String contraseña;
    private String imagenPerfilPath;

	private String confContraseña;

    public Usuario(String nombre, String apellidos, String nickname, String email,
			String contraseña) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nickname = nickname;
		this.email = email;
		this.contraseña = contraseña;
	}

	public Usuario(String nombre, String apellidos, String fechaNac, String nickname, String email, String confEmail,
			String contraseña, String confContraseña) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.nickname = nickname;
		this.email = email;
		this.confEmail = confEmail;
		this.contraseña = contraseña;
		this.confContraseña = confContraseña;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfEmail() {
		return confEmail;
	}
	public void setConfEmail(String confEmail) {
		this.confEmail = confEmail;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	  public String getImagenPerfilPath() {
	        return imagenPerfilPath;
	    }

	    public void setImagenPerfilPath(String absolutePath) {
	        this.imagenPerfilPath = absolutePath;
	    }
	public String getConfContraseña() {
		return confContraseña;
	}
	public void setConfContraseña(String confContraseña) {
		this.confContraseña = confContraseña;
	}
	 
	  
	  




}
