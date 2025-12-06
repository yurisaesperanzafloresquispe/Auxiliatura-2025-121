
package ejer6;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ArchLibro {
    private ArrayList<Libro> libros = new ArrayList<>();
    private String nombreArch = "libros.json";

    public void adicionar(Libro l) {
        libros.add(l);
        guardarArchivo();
    }

    public ArrayList<Libro> getLibros() { return libros; }

    public void guardarArchivo() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(libros));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarArchivo() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(nombreArch));
            libros = gson.fromJson(reader, new TypeToken<ArrayList<Libro>>(){}.getType());
            reader.close();
        } catch (Exception e) {
            libros = new ArrayList<>();
        }
    }
}
