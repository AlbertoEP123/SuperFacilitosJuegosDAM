package model;

public class Biblioteca {

	private int jugado;
	private int terminado;
	private int pendiente;
	private int obtenido;
	public Biblioteca(int jugado, int terminado, int pendiente, int obtenido) {
		super();
		this.jugado = jugado;
		this.terminado = terminado;
		this.pendiente = pendiente;
		this.obtenido = obtenido;
	}
	public int getJugado() {
		return jugado;
	}
	public void setJugado(int jugado) {
		this.jugado = jugado;
	}
	public int getTerminado() {
		return terminado;
	}
	public void setTerminado(int terminado) {
		this.terminado = terminado;
	}
	public int getPendiente() {
		return pendiente;
	}
	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}
	public int getObtenido() {
		return obtenido;
	}
	public void setObtenido(int obtenido) {
		this.obtenido = obtenido;
	}
	
	
}
