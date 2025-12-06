package ejer9;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ArchZoo {

    private String nombreArchivo;
    private ArrayList<Zoologico> lista = new ArrayList<>();

    public ArchZoo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        cargar();
    }

    public void guardar() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(nombreArchivo);
            gson.toJson(lista, writer);
            writer.close();
            System.out.println("✓ Datos guardados en JSON.");
        } catch (IOException e) {
            System.out.println("Error al guardar archivo.");
        }
    }

    public void cargar() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo));
            lista = gson.fromJson(reader, new TypeToken<ArrayList<Zoologico>>(){}.getType());
            reader.close();
            if (lista == null) lista = new ArrayList<>();
            System.out.println("✓ Datos cargados del archivo JSON.");
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, iniciando vacío.");
        }
    }

    public void crear(Zoologico z) {
        lista.add(z);
        guardar();
    }

    public void modificar(int id, String nuevoNombre) {
        for (Zoologico z : lista) {
            if (z.id == id) {
                z.nombre = nuevoNombre;
                guardar();
                return;
            }
        }
    }

    public void eliminar(int id) {
        lista.removeIf(z -> z.id == id);
        guardar();
    }

    public void listarMayorVariedad() {
        int max = 0;
        for (Zoologico z : lista) {
            if (z.cantidadDeEspecies() > max)
                max = z.cantidadDeEspecies();
        }

        System.out.println("\nZoológicos con mayor variedad (" + max + " especies):");
        for (Zoologico z : lista) {
            if (z.cantidadDeEspecies() == max)
                System.out.println(z);
        }
    }

    public void listarYEliminarVacios() {
        System.out.println("\nZoológicos vacíos:");
        for (Zoologico z : lista) {
            if (z.estaVacio())
                System.out.println(z);
        }
        lista.removeIf(Zoologico::estaVacio);
        guardar();
    }

    public void mostrarEspecieEnTodos(String especie) {
        System.out.println("\nAnimales de especie: " + especie);
        for (Zoologico z : lista) {
            System.out.println("Zoo: " + z.nombre);
            z.mostrarAnimalesPorEspecie(especie);
        }
    }

    public void moverAnimales(int idOrigen, int idDestino) {
        Zoologico origen = null, destino = null;

        for (Zoologico z : lista) {
            if (z.id == idOrigen) origen = z;
            if (z.id == idDestino) destino = z;
        }

        if (origen != null && destino != null) {
            origen.moverAnimalesA(destino);
            guardar();
            System.out.println("✓ Animales movidos correctamente.");
        }
    }

    public ArrayList<Zoologico> getLista() {
        return lista;
    }
}
