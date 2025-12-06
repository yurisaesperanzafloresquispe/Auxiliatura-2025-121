package ejer1;
import java.io.*;
import java.util.ArrayList;

public class OperacionesCharango {
    public static void grabar(ArrayList<Charango> lista) {
        try (
            FileOutputStream file = new FileOutputStream("charango.dat");
            ObjectOutputStream out = new ObjectOutputStream(file);
        ){
            out.writeObject(lista);
            System.out.println("Graba objetos...");
        } catch(IOException e){
        }
    }

    public static ArrayList<Charango> leer() {
        ArrayList<Charango> lista = new ArrayList<>();

        try (
            FileInputStream file = new FileInputStream("charango.dat");
            ObjectInputStream in = new ObjectInputStream(file);
        ){
            lista = (ArrayList<Charango>) in.readObject();
        } catch(Exception e){
        }

        return lista;
    }

    public static void eliminarMalEstado(ArrayList<Charango> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Charango c = lista.get(i);

            if (c.contarCuerdasFalse() > 6) {
                lista.remove(i);
                i--; 
            }
        }
    }

    public static void listarPorMaterial(ArrayList<Charango> lista, String material) {
        System.out.println("\nCharangos de material: " + material);

        for (int i = 0; i < lista.size(); i++) {
            Charango c = lista.get(i);

            if (c.material.equalsIgnoreCase(material)) {
                System.out.println(c);
            }
        }
    }

    public static void buscar10Cuerdas(ArrayList<Charango> lista) {
        System.out.println("\nCharangos con 10 cuerdas:");

        for (int i = 0; i < lista.size(); i++) {
            Charango c = lista.get(i);

            if (c.nroCuerdas == 10) {
                System.out.println(c);
            }
        }
    }

    public static void ordenarPorMaterial(ArrayList<Charango> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {

                Charango c1 = lista.get(i);
                Charango c2 = lista.get(j);

                if (c1.material.compareToIgnoreCase(c2.material) > 0) {
                    lista.set(i, c2);
                    lista.set(j, c1);
                }
            }
        }
    }
}
