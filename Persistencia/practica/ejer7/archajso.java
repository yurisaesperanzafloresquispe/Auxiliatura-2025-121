package ejer7;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ArchivoJsonNiños {

    private String nombreArchivo = "ninos.json";

    public void guardar(ArrayList<Niño> lista) {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(nombreArchivo);
            gson.toJson(lista, writer);
            writer.close();
            System.out.println("Datos guardados en " + nombreArchivo);
        } catch(IOException e){
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }

    public ArrayList<Niño> cargar() {
        ArrayList<Niño> lista = new ArrayList<>();

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo));

            lista = gson.fromJson(reader, new TypeToken<ArrayList<Niño>>(){}.getType());
            reader.close();
            System.out.println("Datos cargados desde JSON.");
        } catch(IOException e){
            System.out.println("No existe archivo JSON, iniciando vacío.");
        }
        return lista;
    }
}
