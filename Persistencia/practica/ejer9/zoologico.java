package ejer9;


public class Zoologico {
    public int id;
    public String nombre;
    public int nroAnimales;
    public Animal[] animales = new Animal[30];

    public Zoologico(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.nroAnimales = 0;
    }

    public void agregarAnimal(Animal a) {
        if (nroAnimales < 30) {
            animales[nroAnimales++] = a;
        }
    }

    public boolean estaVacio() {
        return nroAnimales == 0;
    }

    public int cantidadDeEspecies() {
        return nroAnimales;
    }

    public void mostrarAnimalesPorEspecie(String especieBuscada) {
        for (int i = 0; i < nroAnimales; i++) {
            if (animales[i].especie.equalsIgnoreCase(especieBuscada)) {
                System.out.println(animales[i]);
            }
        }
    }

    public void moverAnimalesA(Zoologico destino) {
        for (int i = 0; i < nroAnimales; i++) {
            destino.agregarAnimal(animales[i]);
        }
        nroAnimales = 0;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + nroAnimales + " animales)";
    }
}
