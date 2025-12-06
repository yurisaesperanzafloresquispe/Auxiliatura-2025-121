package ejer2;
import java.io.*;
import java.util.*;

public class ArchivoTrabajador {
    private String nombreArch;
    private ArrayList<Trabajador> lista;

    public ArchivoTrabajador(String nombreArch) {
        this.nombreArch = nombreArch;
        this.lista = new ArrayList<>();
    }

    public void crearArchivo() {
        try {
            File archivo = new File(nombreArch);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }

    public void leerDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArch))) {
            lista = (ArrayList<Trabajador>) ois.readObject();
            System.out.println("Lista de trabajadores cargada desde archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
            crearArchivo();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarTrabajador(Trabajador t) {
        if (!lista.contains(t)) {
            lista.add(t);
            System.out.println("Trabajador guardado en memoria: " + t.getNombre());
        } else {
            System.out.println("Trabajador " + t.getNombre() + " ya existe en la lista.");
        }
    }

    public void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArch))) {
            oos.writeObject(lista);
            System.out.println("Lista de trabajadores guardada en archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aumentaSalario(double aumento, Trabajador t) {
        t.setSalario(t.getSalario() + aumento);
        System.out.println("Nuevo salario de " + t.getNombre() + ": " + t.getSalario());
    }

    public Trabajador buscarMayorSalario() {
        if (lista.isEmpty()) return null;
        Trabajador mayor = lista.get(0);
        for (Trabajador t : lista) {
            if (t.getSalario() > mayor.getSalario()) {
                mayor = t;
            }
        }
        return mayor;
    }

    public void ordenarPorSalario() {
        lista.sort(Comparator.comparingDouble(Trabajador::getSalario).reversed());
        System.out.println("Trabajadores ordenados por salario (mayor a menor).");
    }

    public void mostrar() {
        for (Trabajador t : lista) {
            System.out.println(t);
        }
    }

    public boolean existeTrabajador(Trabajador t) {
        return lista.contains(t);
    }
}
