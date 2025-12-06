package ejer7;

import java.util.Scanner;

public class Niño extends Persona {
    private int edad;
    private double peso;
    private double talla;

    public Niño() {}

    @Override
    public void leer() {
        super.leer();
        Scanner sc = new Scanner(System.in);
        System.out.print("Edad: ");
        edad = sc.nextInt();
        System.out.print("Peso (kg): ");
        peso = sc.nextDouble();
        System.out.print("Talla (cm): ");
        talla = sc.nextDouble();
    }

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Edad: " + edad);
        System.out.println("Peso: " + peso);
        System.out.println("Talla: " + talla);
        System.out.println("------------------------");
    }

    public int getEdad() { return edad; }
    public double getPeso() { return peso; }
    public double getTalla() { return talla; }
}
