
package ejer8;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ArchRefriApp {

    static Scanner sc = new Scanner(System.in);
    static ArchRefri arch = new ArchRefri();
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {
            menu();
            int op = leerInt("Opción: ");

            switch (op) {
                case 1 -> arch.crear(leerAlimento());
                case 2 -> {
                    System.out.print("Nombre a modificar: ");
                    String nom = sc.nextLine();
                    if (arch.modificarPorNombre(nom, leerAlimento()))
                        System.out.println("Modificado.");
                    else
                        System.out.println("No encontrado.");
                }
                case 3 -> {
                    System.out.print("Nombre a eliminar: ");
                    String nom = sc.nextLine();
                    if (arch.eliminarPorNombre(nom))
                        System.out.println("Eliminado.");
                    else
                        System.out.println("No existe.");
                }
                case 4 -> {
                    System.out.print("Fecha límite (dd/MM/yyyy): ");
                    String x = sc.nextLine();
                    List<Alimento> lista = arch.caducadosAntes(x);
                    lista.forEach(System.out::println);
                }
                case 5 -> System.out.println("Eliminados: " + arch.eliminarCantidadCero());
                case 6 -> arch.buscarVencidos().forEach(System.out::println);
                case 7 -> {
                    Alimento m = arch.maxCantidad();
                    System.out.println(m == null ? "Vacío" : m);
                }
                case 8 -> arch.mostrarTodos();
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void menu() {
        System.out.println("\n=== MENÚ REFRIGERADOR ===");
        System.out.println("1. Crear alimento");
        System.out.println("2. Modificar alimento por nombre");
        System.out.println("3. Eliminar alimento por nombre");
        System.out.println("4. Mostrar caducados antes de X");
        System.out.println("5. Eliminar alimentos con cantidad 0");
        System.out.println("6. Buscar vencidos");
        System.out.println("7. Alimento con mayor cantidad");
        System.out.println("8. Mostrar todos");
        System.out.println("0. Salir");
    }

    private static Alimento leerAlimento() {
        System.out.print("Nombre: ");
        String nom = sc.nextLine();

        String fecha;
        while (true) {
            System.out.print("Fecha de vencimiento (dd/MM/yyyy): ");
            fecha = sc.nextLine();
            try {
                LocalDate.parse(fecha, fmt);
                break;
            } catch (Exception e) {
                System.out.println("Fecha inválida.");
            }
        }

        int cant = leerInt("Cantidad: ");
        return new Alimento(nom, fecha, cant);
    }

    private static int leerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese número válido.");
            }
        }
    }
}
