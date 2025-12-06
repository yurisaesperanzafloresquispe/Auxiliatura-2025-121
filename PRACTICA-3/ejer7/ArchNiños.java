package ejer7;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchNiño {

    private ArrayList<Niño> lista;
    private ArchivoJsonNiños archJson = new ArchivoJsonNiños();

    public ArchNiño() {
        lista = archJson.cargar();
    }

    public void leerDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Cuántos niños registrar?: ");
        int cant = sc.nextInt();

        for (int i = 0; i < cant; i++) {
            System.out.println("Niño " + (i + 1));
            Niño x = new Niño();
            x.leer();
            lista.add(x);
        }

        archJson.guardar(lista);
    }

    public void listar() {
        System.out.println("\n=== LISTA DE NIÑOS ===");
        for (Niño n : lista) n.mostrar();
    }

    public int contarPesoAdecuado() {
        int cont = 0;
        for (Niño n : lista) {
            double pesoEsp = n.getEdad() * 2 + 8;
            if (Math.abs(n.getPeso() - pesoEsp) <= 2)
                cont++;
        }
        return cont;
    }

    public void mostrarNoAdecuados() {
        System.out.println("\nNiños NO adecuados:");
        for (Niño n : lista) {
            double pesoEsp = n.getEdad() * 2 + 8;
            double tallaEsp = n.getEdad() * 6 + 70;

            if (Math.abs(n.getPeso() - pesoEsp) > 2 ||
                Math.abs(n.getTalla() - tallaEsp) > 5) {
                n.mostrar();
            }
        }
    }

    public double promedioEdad() {
        if (lista.isEmpty()) return 0;
        double suma = 0;
        for (Niño n : lista) suma += n.getEdad();
        return suma / lista.size();
    }

    public Niño buscarPorCi(int ci) {
        for (Niño n : lista)
            if (n.getCi() == ci)
                return n;
        return null;
    }

    public void mostrarTallaMayor() {
        if (lista.isEmpty()) return;

        double max = 0;
        for (Niño n : lista)
            if (n.getTalla() > max)
                max = n.getTalla();

        System.out.println("\nNiños con la talla mayor:");
        for (Niño n : lista)
            if (n.getTalla() == max)
                n.mostrar();
    }
}

