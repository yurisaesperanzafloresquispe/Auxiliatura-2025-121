package ejer7;

import java.util.Scanner;

public class Persona {
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected int ci;

    public Persona() {}

    public void leer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Apellido paterno: ");
        apellidoPaterno = sc.nextLine();
        System.out.print("Apellido materno: ");
        apellidoMaterno = sc.nextLine();
        System.out.print("Carnet (CI): ");
        ci = sc.nextInt();
        sc.nextLine();
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
        System.out.println("CI: " + ci);
    }

    public int getCi() { return ci; }
}
