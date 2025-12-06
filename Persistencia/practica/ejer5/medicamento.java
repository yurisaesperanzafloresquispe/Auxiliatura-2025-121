package ejer5;
public class Medicamento {
    private String nombre;
    private int codMedicamento;
    private String tipo;
    private double precio;

    public Medicamento() {}

    public void leer() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Código: ");
        codMedicamento = sc.nextInt(); sc.nextLine();
        System.out.print("Tipo: ");
        tipo = sc.nextLine();
        System.out.print("Precio: ");
        precio = sc.nextDouble(); sc.nextLine();
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre 
            + " | Código: " + codMedicamento 
            + " | Tipo: " + tipo 
            + " | Precio: " + precio);
    }

    public String getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public String getNombre() { return nombre; }
}
