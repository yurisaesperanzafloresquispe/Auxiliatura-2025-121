package ejer9;

public class Animal {
    public String especie;
    public String nombre;
    public int cantidad;

    public Animal(String especie, String nombre, int cantidad) {
        this.especie = especie;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return especie + " - " + nombre + " (" + cantidad + ")";
    }
}
