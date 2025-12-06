
package ejer8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ArchRefri {

    private List<Alimento> lista;
    private final String nomArchi = "refri.json";
    private Gson gson = new Gson();
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ArchRefri() {
        lista = new ArrayList<>();
        cargar();   // Cargar JSON automáticamente
    }

    public void guardar() {
        try (FileWriter fw = new FileWriter(nomArchi)) {
            gson.toJson(lista, fw);
        } catch (IOException e) {
            System.out.println("ERROR al guardar JSON");
        }
    }

    private void cargar() {
        try (FileReader fr = new FileReader(nomArchi)) {
            Type tipoLista = new TypeToken<ArrayList<Alimento>>() {}.getType();
            lista = gson.fromJson(fr, tipoLista);
            if (lista == null) lista = new ArrayList<>();
            System.out.println("Archivo JSON cargado correctamente.");
        } catch (Exception e) {
            System.out.println("No existe archivo, se creará uno nuevo.");
            lista = new ArrayList<>();
        }
    }
    public void crear(Alimento a) {
        lista.add(a);
        guardar();
    }

    public boolean modificarPorNombre(String nombre, Alimento nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equalsIgnoreCase(nombre)) {
                lista.set(i, nuevo);
                guardar();
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPorNombre(String nombre) {
        boolean eliminado = lista.removeIf(a -> a.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) guardar();
        return eliminado;
    }

    public List<Alimento> caducadosAntes(String fechaX) {
        List<Alimento> salida = new ArrayList<>();
        LocalDate x = LocalDate.parse(fechaX, fmt);

        for (Alimento a : lista) {
            LocalDate f = LocalDate.parse(a.getFechaVencimiento(), fmt);
            if (f.isBefore(x))
                salida.add(a);
        }
        return salida;
    }
    public int eliminarCantidadCero() {
        int antes = lista.size();
        lista.removeIf(a -> a.getCantidad() == 0);
        int eliminados = antes - lista.size();
        if (eliminados > 0) guardar();
        return eliminados;
    }

    public List<Alimento> buscarVencidos() {
        LocalDate hoy = LocalDate.now();
        List<Alimento> vencidos = new ArrayList<>();

        for (Alimento a : lista) {
            LocalDate f = LocalDate.parse(a.getFechaVencimiento(), fmt);
            if (f.isBefore(hoy))
                vencidos.add(a);
        }
        return vencidos;
    }

    public Alimento maxCantidad() {
        if (lista.isEmpty()) return null;

        Alimento max = lista.get(0);
        for (Alimento a : lista) {
            if (a.getCantidad() > max.getCantidad())
                max = a;
        }
        return max;
    }

    public void mostrarTodos() {
        if (lista.isEmpty()) {
            System.out.println("Refri vacío.");
            return;
        }
        for (Alimento a : lista)
            System.out.println(a);
    }
}
