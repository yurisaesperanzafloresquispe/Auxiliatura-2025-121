package ejer5;
import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ArchFarmacia {
    private String archivo;
    private ArrayList<Farmacia> lista = new ArrayList<>();

    public ArchFarmacia(String archivo) {
        this.archivo = archivo;
    }

    public void adicionar(Farmacia f) {
        lista.add(f);
    }

    public void listar() {
        for (Farmacia f : lista)
            f.mostrar();
    }

    public void mostrarMedicamentosTos(String sucX) {
        for (Farmacia f : lista) {
            if (f.getSucursal().equalsIgnoreCase(sucX)) {
                System.out.println("\nMedicamentos para la tos en sucursal " + sucX + ":");
                f.mostrarMedicamentos("Tos");
            }
        }
    }

    public void mostrarFarmaciasConTapsin() {
        for (Farmacia f : lista) {
            if (f.buscaMedicamento("Tapsin")) {
                System.out.println("Sucursal: " + f.getSucursal() + 
                                   " | Dirección: " + f.getDireccion());
            }
        }
    }

    public void buscarPorTipo(String tipo) {
        for (Farmacia f : lista) {
            System.out.println("\nFarmacia: " + f.getNombreFarmacia());
            f.mostrarMedicamentos(tipo);
        }
    }

    public void ordenarPorDireccion() {
        lista.sort((f1, f2) -> f1.getDireccion().compareToIgnoreCase(f2.getDireccion()));
    }

    public void guardar() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(archivo);
            gson.toJson(lista, writer);
            writer.close();
            System.out.println("Datos guardados en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Farmacia> cargar() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(archivo);
            Type tipoLista = new TypeToken<ArrayList<Farmacia>>(){}.getType();
            ArrayList<Farmacia> listaCargada = gson.fromJson(reader, tipoLista);
            reader.close();
            return listaCargada;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, comenzando con lista vacía.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Mover medicamentos de tipo entre sucursales
    public void moverMedicamentos(String tipo, String sucA, String sucZ) {
        Farmacia origen = null, destino = null;
        for(Farmacia f : lista){
            if(f.getSucursal().equalsIgnoreCase(sucA)) origen = f;
            if(f.getSucursal().equalsIgnoreCase(sucZ)) destino = f;
        }
        if(origen != null && destino != null){
            origen.moverMedicamentosTipo(destino, tipo);
            System.out.println("Medicamentos movidos de " + sucA + " a " + sucZ);
        } else {
            System.out.println("Sucursales no encontradas.");
        }
    }
}
