package ejer6;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ArchCliente {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private String nombreArch = "clientes.json";

    public void adicionar(Cliente c) {
        clientes.add(c);
        guardarArchivo();
    }

    public ArrayList<Cliente> getClientes() { return clientes; }

    public void guardarArchivo() {
        try (FileWriter writer = new FileWriter(nombreArch)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(clientes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarArchivo() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(nombreArch));
            clientes = gson.fromJson(reader, new TypeToken<ArrayList<Cliente>>(){}.getType());
            reader.close();
        } catch (Exception e) {
            clientes = new ArrayList<>();
        }
    }
}
