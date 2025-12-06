package ejer10;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorJugadores {
    private final String ARCHIVO = "jugadores.json";
    private List<Jugador> jugadores;

    public GestorJugadores() {
        jugadores = new ArrayList<>();
        cargarJugadores();
    }

    public void guardarJugador(Jugador jugador) {
        jugadores.add(jugador);
        guardarEnArchivo();
        System.out.println("Jugador guardado correctamente.");
    }

    public void mostrarJugadores() {
        if (jugadores.isEmpty()) {
            System.out.println("No existen jugadores registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE JUGADORES ---");
        for (Jugador j : jugadores) {
            System.out.println(
                "Nombre: " + j.getNombre() +
                " | Nivel: " + j.toString().split(",")[1] +
                " | Puntaje: " + j.toString().split(",")[2]
            );
        }
    }

    public void buscarJugador(String nombreBuscado) {
        for (Jugador j : jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("\nJugador encontrado:");
                System.out.println(
                    "Nombre: " + j.getNombre() +
                    " | Nivel: " + j.toString().split(",")[1] +
                    " | Puntaje: " + j.toString().split(",")[2]
                );
                return;
            }
        }
        System.out.println("Jugador NO encontrado.");
    }

    private void guardarEnArchivo() {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            Gson gson = new Gson();
            gson.toJson(jugadores, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo JSON.");
        }
    }

    private void cargarJugadores() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Jugador>>(){}.getType();
            jugadores = gson.fromJson(reader, tipoLista);
            if (jugadores == null) jugadores = new ArrayList<>();
        } catch (FileNotFoundException e) {
            jugadores = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer archivo JSON.");
        }
    }
}
