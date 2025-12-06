package ejer6;

public class Cliente {
    private int codCliente;
    private String ci;
    private String nombre;
    private String apellido;

    public Cliente(int codCliente, String ci, String nombre, String apellido) {
        this.codCliente = codCliente;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCodCliente() { return codCliente; }

    @Override
    public String toString() {
        return codCliente + " - " + nombre + " " + apellido;
    }
}
