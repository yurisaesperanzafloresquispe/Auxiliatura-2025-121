package ejer10;

public class Jugador {
    private String nombre;
    private int nivel;
    private int puntaje;

    public Jugador(String nombre, int nivel, int puntaje) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + "," + nivel + "," + puntaje;
    }
}
