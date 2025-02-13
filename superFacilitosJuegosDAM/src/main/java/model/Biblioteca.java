package model;

import java.util.List;

public class Biblioteca {

    private List<EntradaDeBiblioteca> entradas; // Lista para almacenar las entradas de la biblioteca

    // Constructor
    public Biblioteca(List<EntradaDeBiblioteca> entradas) {
        this.entradas = entradas;
    }

    // Getter y Setter
    public List<EntradaDeBiblioteca> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaDeBiblioteca> entradas) {
        this.entradas = entradas;
    }

    // Método para agregar una entrada a la biblioteca
    public void agregarEntrada(EntradaDeBiblioteca entrada) {
        this.entradas.add(entrada);
    }

    // Método para eliminar una entrada de la biblioteca
    public void eliminarEntrada(EntradaDeBiblioteca entrada) {
        this.entradas.remove(entrada);
    }

    // Método toString para mostrar la información de la biblioteca y sus entradas
    @Override
    public String toString() {
        return "Biblioteca [entradas=" + entradas + "]";
    }
}

