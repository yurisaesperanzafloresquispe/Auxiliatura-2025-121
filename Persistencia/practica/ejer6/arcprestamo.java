package ejer6;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ArchPrestamo {
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private String nombreArch = "prestamos.json";

    public void adicionar(Prestamo p) {
        prestamos.add(p);
        guardarArchivo();
    }

    public ArrayList<Prestamo> getPrestamos() { return prestamos; }

    public void guardarArchivo() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(prestamos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarArchivo() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(nombreArch));
            prestamos = gson.fromJson(reader, new TypeToken<ArrayList<Prestamo>>(){}.getType());
            reader.close();
        } catch (Exception e) {
            prestamos = new ArrayList<>();
        }
    }
}
