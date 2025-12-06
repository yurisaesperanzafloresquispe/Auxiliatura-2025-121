
package ejer2;
import java.io.Serializable;

public class Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private final int carnet; 
    private double salario;

    public Trabajador(String nombre, int carnet, double salario) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.salario = salario;
    }

    public String getNombre() { return nombre; }
    public int getCarnet() { return carnet; }
    public double getSalario() { return salario; }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return nombre + " - " + carnet + " - " + salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajador)) return false;
        Trabajador t = (Trabajador) o;
        return carnet == t.carnet;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(carnet);
    }
}
