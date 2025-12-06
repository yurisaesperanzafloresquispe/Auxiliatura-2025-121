package ejer1;
import java.io.Serializable;
import java.util.Arrays;

public class Charango implements Serializable {

    private static final long serialVersionUID = 1L;

    public String material;
    public int nroCuerdas;
    public boolean[] cuerdas;

    public Charango(String material, int nroCuerdas, boolean[] cuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        this.cuerdas = cuerdas;
    }

    public int contarCuerdasFalse() {
        int cont = 0;
        for (int i = 0; i < cuerdas.length; i++) {
            if (cuerdas[i] == false) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    public String toString() {
        return "Charango [" + material + ", " + nroCuerdas +
                ", " + Arrays.toString(cuerdas) + "]";
    }
}
