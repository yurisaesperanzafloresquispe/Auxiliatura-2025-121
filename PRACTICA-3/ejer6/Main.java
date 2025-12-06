package ejer6;
public class Main {
    public static void main(String[] args) {

        ArchLibro archL = new ArchLibro();
        ArchCliente archC = new ArchCliente();
        ArchPrestamo archP = new ArchPrestamo();

        archL.cargarArchivo();
        archC.cargarArchivo();
        archP.cargarArchivo();

        archL.getLibros().clear();
        archC.getClientes().clear();
        archP.getPrestamos().clear();

        archL.adicionar(new Libro(1,  "Harry Potter", 80));
        archL.adicionar(new Libro(2,  "El Principito", 50));
        archL.adicionar(new Libro(3,  "Cálculo", 120));
        archL.adicionar(new Libro(4,  "El Quijote", 90));
        archL.adicionar(new Libro(5,  "Astrología Moderna", 60));
        archL.adicionar(new Libro(6,  "El Arte de la Guerra", 45));
        archL.adicionar(new Libro(7,  "El Código Da Vinci", 110));
        archL.adicionar(new Libro(8,  "Inferno", 95));
        archL.adicionar(new Libro(9,  "Programación en Java", 150));
        archL.adicionar(new Libro(10, "Álgebra Lineal", 130));

        archC.adicionar(new Cliente(1, "111", "Juan",      "Perez"));
        archC.adicionar(new Cliente(2, "222", "Ana",       "Lopez"));
        archC.adicionar(new Cliente(3, "333", "Carlos",    "Rojas"));
        archC.adicionar(new Cliente(4, "444", "Maria",     "Vargas"));
        archC.adicionar(new Cliente(5, "555", "Pedro",     "Mendoza"));
        archC.adicionar(new Cliente(6, "666", "Lucia",     "Flores"));
        archC.adicionar(new Cliente(7, "777", "Sofia",     "Aguilar"));
        archC.adicionar(new Cliente(8, "888", "Javier",    "Cortez"));
        archC.adicionar(new Cliente(9, "999", "Daniel",    "Quispe"));
        archC.adicionar(new Cliente(10,"000", "Fernanda",  "Salinas"));

        archP.adicionar(new Prestamo(1, 1, "2024-01-10", 2));
        archP.adicionar(new Prestamo(2, 1, "2024-01-11", 1));
        archP.adicionar(new Prestamo(3, 2, "2024-01-12", 1));
        archP.adicionar(new Prestamo(4, 3, "2024-01-13", 3));
        archP.adicionar(new Prestamo(5, 4, "2024-01-14", 1));
        archP.adicionar(new Prestamo(6, 4, "2024-01-15", 2));
        archP.adicionar(new Prestamo(7, 5, "2024-01-16", 1));
        archP.adicionar(new Prestamo(8, 7, "2024-01-17", 4));
        archP.adicionar(new Prestamo(9, 7, "2024-01-18", 2));
        archP.adicionar(new Prestamo(10,7, "2024-01-19", 1));
        archP.adicionar(new Prestamo(2, 8, "2024-01-20", 1));
        archP.adicionar(new Prestamo(1, 10,"2024-01-21", 3));

        Operaciones op = new Operaciones();

        System.out.println("==========================================");
        System.out.println("     LIBROS ENTRE Bs 40 Y Bs 100");
        System.out.println("==========================================");
        op.librosEnRango(archL, 40, 100);

        System.out.println("\n==========================================");
        System.out.println("     INGRESO TOTAL DEL LIBRO 1");
        System.out.println("==========================================");
        System.out.println("Ingreso = Bs. " + op.ingresoPorLibro(1, archP, archL));

        System.out.println("\n==========================================");
        System.out.println("     LIBROS NUNCA PRESTADOS");
        System.out.println("==========================================");
        op.librosNuncaPrestados(archL, archP);

        System.out.println("\n==========================================");
        System.out.println("     CLIENTES QUE PRESTARON LIBRO 7");
        System.out.println("==========================================");
        op.clientesPorLibro(7, archC, archP);

        System.out.println("\n==========================================");
        System.out.println("      LIBRO MÁS PRESTADO");
        System.out.println("==========================================");
        System.out.println(op.libroMasPrestado(archL, archP));

        System.out.println("\n==========================================");
        System.out.println("      CLIENTE CON MÁS PRÉSTAMOS");
        System.out.println("==========================================");
        System.out.println(op.clienteMasPrestamos(archC, archP));
    }
}

