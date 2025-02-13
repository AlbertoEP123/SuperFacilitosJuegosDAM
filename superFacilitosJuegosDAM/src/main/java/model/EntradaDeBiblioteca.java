package model;


import java.sql.Date;

public class EntradaDeBiblioteca {

    private int idUsuario;
    private int idGame;
    private String imagen;
    private String titulos;
    private String pendiente;
    private String obtenido;
    private String jugado;
    private String comentario;
    private int nota;
    private Date fechaJugado;

    // Constructor
    public EntradaDeBiblioteca(int idUsuario, int idGame, String imagen, String titulos, String pendiente, 
                               String obtenido, String jugado, String comentario, int nota, Date fechaJugado) {
        this.idUsuario = idUsuario;
        this.idGame = idGame;
        this.imagen = imagen;
        this.titulos = titulos;
        this.pendiente = pendiente;
        this.obtenido = obtenido;
        this.jugado = jugado;
        this.comentario = comentario;
        this.nota = nota;
        this.fechaJugado = fechaJugado;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getObtenido() {
        return obtenido;
    }

    public void setObtenido(String obtenido) {
        this.obtenido = obtenido;
    }

    public String getJugado() {
        return jugado;
    }

    public void setJugado(String jugado) {
        this.jugado = jugado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getFechaJugado() {
        return fechaJugado;
    }

    public void setFechaJugado(Date fechaJugado) {
        this.fechaJugado = fechaJugado;
    }

    // ToString para mostrar la entrada
    @Override
    public String toString() {
        return "EntradaDeBiblioteca [idUsuario=" + idUsuario + ", idGame=" + idGame + ", imagen=" + imagen 
               + ", titulos=" + titulos + ", pendiente=" + pendiente + ", obtenido=" + obtenido + ", jugado=" 
               + jugado + ", comentario=" + comentario + ", nota=" + nota + ", fechaJugado=" + fechaJugado + "]";
    }
}