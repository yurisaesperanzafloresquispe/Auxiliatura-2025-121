package ejer3;
import java.io.*;
import java.util.ArrayList;

public class ArchivoProducto {
    private String nombreArchivo;
    private ArrayList<Producto> lista;

    public ArchivoProducto(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        lista = new ArrayList<>();
        leerDesdeArchivo();
    }

    public void crearArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo, true))) {
            // solo crea el archivo si no existe
        } catch (IOException e) {
            System.out.println("Archivo creado: " + nombreArchivo);
        }
    }

    public void guardarProducto(Producto p) {
        lista.add(p); 
        System.out.println("Producto guardado en memoria: " + p);
    }

    public ArrayList<Producto> buscaProductos(int codigo) {
        ArrayList<Producto> encontrados = new ArrayList<>();
        for (Producto p : lista) {
            if (p.getCodigo() == codigo) {
                encontrados.add(p);
            }
        }
        return encontrados; // puede ser vacío si no hay coincidencias
    }

    public double promedioPrecios() {
        if (lista.isEmpty()) return 0;
        double suma = 0;
        for (Producto p : lista) {
            suma += p.getPrecio();
        }
        return suma / lista.size();
    }

    public Producto productoMasCaro() {
        if (lista.isEmpty()) return null;
        Producto caro = lista.get(0);
        for (Producto p : lista) {
            if (p.getPrecio() > caro.getPrecio()) {
                caro = p;
            }
        }
        return caro;
    }

    public void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            for (Producto p : lista) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerDesdeArchivo() {
        lista.clear(); // limpia la lista antes de leer
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            while (true) {
                Producto p = (Producto) ois.readObject();
                lista.add(p);
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public void mostrar() {
        for (Producto p : lista) {
            System.out.println(p);
        }
    }
}
