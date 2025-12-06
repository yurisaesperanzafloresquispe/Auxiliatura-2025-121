package ejer6;

public class Operaciones {

    public void librosEnRango(ArchLibro arch, double x, double y) {
        for (Libro l : arch.getLibros()) {
            if (l.getPrecio() >= x && l.getPrecio() <= y) {
                System.out.println(l);
            }
        }
    }

    public double ingresoPorLibro(int codLibro, ArchPrestamo archP, ArchLibro archL) {
        double precio = 0;
        for (Libro l : archL.getLibros()) {
            if (l.getCodLibro() == codLibro)
                precio = l.getPrecio();
        }

        double total = 0;
        for (Prestamo p : archP.getPrestamos()) {
            if (p.getCodLibro() == codLibro)
                total += p.getCantidad() * precio;
        }
        return total;
    }

    public void librosNuncaPrestados(ArchLibro archL, ArchPrestamo archP) {
        for (Libro l : archL.getLibros()) {
            boolean prestado = false;

            for (Prestamo p : archP.getPrestamos()) {
                if (p.getCodLibro() == l.getCodLibro()) {
                    prestado = true;
                    break;
                }
            }
            if (!prestado)
                System.out.println(l);
        }
    }

    public void clientesPorLibro(int codLibro, ArchCliente archC, ArchPrestamo archP) {
        for (Prestamo p : archP.getPrestamos()) {
            if (p.getCodLibro() == codLibro) {

                for (Cliente c : archC.getClientes()) {
                    if (c.getCodCliente() == p.getCodCliente())
                        System.out.println(c);
                }
            }
        }
    }

    public Libro libroMasPrestado(ArchLibro archL, ArchPrestamo archP) {
        int max = -1;
        Libro mas = null;

        for (Libro l : archL.getLibros()) {
            int total = 0;

            for (Prestamo p : archP.getPrestamos()) {
                if (p.getCodLibro() == l.getCodLibro())
                    total += p.getCantidad();
            }

            if (total > max) {
                max = total;
                mas = l;
            }
        }
        return mas;
    }

    public Cliente clienteMasPrestamos(ArchCliente archC, ArchPrestamo archP) {
        int max = -1;
        Cliente mejor = null;

        for (Cliente c : archC.getClientes()) {
            int total = 0;

            for (Prestamo p : archP.getPrestamos()) {
                if (p.getCodCliente() == c.getCodCliente())
                    total += p.getCantidad();
            }

            if (total > max) {
                max = total;
                mejor = c;
            }
        }
        return mejor;
    }
}

