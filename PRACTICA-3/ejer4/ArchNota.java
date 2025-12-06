package ejer4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchiNota {
    private String nombreArchi;
    private List<Nota> listaNotas;

    public ArchiNota(String nombreArchi) {
        this.nombreArchi = nombreArchi;
        listaNotas = new ArrayList<>();
        cargarArchivo(); // cargar notas existentes
    }

    public void agregarNota(Nota nota) {
        listaNotas.add(nota);
        guardarArchivo();
    }

    public double obtenerPromedioGeneral() {
        if (listaNotas.isEmpty()) return 0;
        double suma = 0;
        for (Nota n : listaNotas) suma += n.getNotaFinal();
        return suma / listaNotas.size();
    }

    public List<Nota> mejoresNotas() {
        List<Nota> mejores = new ArrayList<>();
        if (listaNotas.isEmpty()) return mejores;

        double max = -1;
        for (Nota n : listaNotas) if (n.getNotaFinal() > max) max = n.getNotaFinal();
        for (Nota n : listaNotas) if (n.getNotaFinal() == max) mejores.add(n);
        return mejores;
    }

    public void eliminarPorMateria(String materia) {
        listaNotas.removeIf(n -> n.getMateria().equalsIgnoreCase(materia));
        guardarArchivo();
    }

    public void mostrar() {
        for (Nota n : listaNotas) System.out.println(n);
    }

    private void guardarArchivo() {
        try (Writer writer = new FileWriter(nombreArchi)) {
            Gson gson = new Gson();
            gson.toJson(listaNotas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarArchivo() {
        try (Reader reader = new FileReader(nombreArchi)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Nota>>() {}.getType();
            listaNotas = gson.fromJson(reader, tipoLista);
            if (listaNotas == null) listaNotas = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo al guardar.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
