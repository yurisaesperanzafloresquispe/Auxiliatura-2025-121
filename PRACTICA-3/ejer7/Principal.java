package ejer7;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        ArchNiño archivo = new ArchNiño(); 

        archivo.leerDatos(); 

        archivo.listar();

        System.out.println("\nb) Peso adecuado = " + archivo.contarPesoAdecuado());

        System.out.println("\nc) Niños NO adecuados:");
        archivo.mostrarNoAdecuados();

        System.out.println("\nd) Promedio de edad = " + archivo.promedioEdad());

        Scanner sc = new Scanner(System.in);
        System.out.print("\nIngrese CI a buscar: ");
        int x = sc.nextInt();

        Niño buscado = archivo.buscarPorCi(x);

        if (buscado != null) {
            System.out.println("\nNiño encontrado:");
            buscado.mostrar();
        } else {
            System.out.println("No existe ese CI.");
        }

        archivo.mostrarTallaMayor();
    }
}

