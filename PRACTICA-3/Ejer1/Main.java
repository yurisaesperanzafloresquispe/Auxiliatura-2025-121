package ejer1;
import java.util.ArrayList;

public class MainCharango {

    public static void main(String[] args) {

        ArrayList<Charango> lista = new ArrayList<>();

        lista.add(new Charango("Madera", 10,
                new boolean[]{true,true,true,false,true,false,true,true,false,false}));

        lista.add(new Charango("Plastico", 8,
                new boolean[]{true,true,true,true,true,true,true,true,true,true}));

        lista.add(new Charango("Carbono", 10,
                new boolean[]{false,false,false,false,false,false,false,false,false,false}));

        lista.add(new Charango("Cuero", 10,
                new boolean[]{true,true,true,true,true,true,true,false,true,true}));

        lista.add(new Charango("Roble", 10,
                new boolean[]{false,false,false,false,false,false,false,true,true,true}));

        lista.add(new Charango("Madera", 8,
                new boolean[]{true,true,true,true,true,true,true,true,true,true}));

        lista.add(new Charango("Aluminio", 10,
                new boolean[]{true,false,true,true,true,true,true,true,true,true}));

        OperacionesCharango.grabar(lista);

        ArrayList<Charango> datos = OperacionesCharango.leer();

        System.out.println("\n===== DATOS LEÍDOS DEL ARCHIVO =====");
        for (Charango c : datos)
            System.out.println(c);

        OperacionesCharango.eliminarMalEstado(datos);

        System.out.println("\n===== DESPUÉS DE ELIMINAR  =====");
        for (Charango c : datos)
            System.out.println(c);

        System.out.println("\n===== LISTAR MATERIAL: Madera =====");
        OperacionesCharango.listarPorMaterial(datos, "Madera");

        System.out.println("\n===== CHARANGOS CON 10 CUERDAS =====");
        OperacionesCharango.buscar10Cuerdas(datos);

        OperacionesCharango.ordenarPorMaterial(datos);

        System.out.println("\n===== ORDENADOS POR MATERIAL (A-Z) =====");
        for (Charango c : datos)
            System.out.println(c);
    }
}
