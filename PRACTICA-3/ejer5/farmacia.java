package ejer5;
import java.util.Scanner;

public class Farmacia {
    private String nombreFarmacia;
    private String sucursal; 
    private String direccion;
    private int nroMedicamentos;
    private Medicamento m[] = new Medicamento[100];

    public Farmacia() {}

    public void leer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre farmacia: ");
        nombreFarmacia = sc.nextLine();

        System.out.print("Sucursal: ");
        sucursal = sc.nextLine();

        System.out.print("Dirección: ");
        direccion = sc.nextLine();

        boolean valido = false;
        while(!valido) {
            System.out.print("Cantidad de medicamentos: ");
            if(sc.hasNextInt()) {
                nroMedicamentos = sc.nextInt();
                sc.nextLine(); 
                valido = true;
            } else {
                System.out.println("Error: debe ingresar un número entero.");
                sc.nextLine(); 
            }
        }

        for(int i=0; i<nroMedicamentos; i++){
            m[i] = new Medicamento();
            System.out.println("\n--- Medicamento " + (i+1) + " ---");
            m[i].leer();
        }
    }

    public void mostrar() {
        System.out.println("\nFarmacia: " + nombreFarmacia);
        System.out.println("Sucursal: " + sucursal);
        System.out.println("Dirección: " + direccion);
        System.out.println("Medicamentos:");
        for(int i=0; i<nroMedicamentos; i++)
            m[i].mostrar();
    }

    public String getDireccion() { return direccion; }
    public String getSucursal() { return sucursal; }
    public String getNombreFarmacia() { return nombreFarmacia; }

    public void mostrarMedicamentos(String tipo) {
        for(int i=0;i<nroMedicamentos;i++){
            if(m[i].getTipo().equalsIgnoreCase(tipo)){
                m[i].mostrar();
            }
        }
    }

    public boolean buscaMedicamento(String nombre) {
        for(int i=0;i<nroMedicamentos;i++) {
            if(m[i].getNombre().equalsIgnoreCase(nombre))
                return true;
        }
        return false;
    }

    public void moverMedicamentosTipo(Farmacia destino, String tipo) {
        for(int i=0;i<nroMedicamentos;i++){
            if(m[i].getTipo().equalsIgnoreCase(tipo)){
                destino.agregarMedicamento(m[i]);
                m[i] = null;
            }
        }
        Medicamento[] temp = new Medicamento[100];
        int j=0;
        for(int i=0;i<nroMedicamentos;i++){
            if(m[i] != null) temp[j++] = m[i];
        }
        m = temp;
        nroMedicamentos = j;
    }

    public void agregarMedicamento(Medicamento med) {
        m[nroMedicamentos++] = med;
    }
}

