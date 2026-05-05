import dao.LibroDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Libro;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore.odb");
        LibroDAO dao = new LibroDAO(emf);
        /*
        dao.insertarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", "978-8437604947", 1967, "Novela", 45.50, 5, true));
        dao.insertarLibro(new Libro("Sapiens", "Yuval Noah Harari", "978-0062316097", 2011, "Ensayo", 30.00, 12, true));
        dao.insertarLibro(new Libro("El resplandor", "Stephen King", "978-0307743657", 1977, "Terror", 22.00, 2, false));
        dao.insertarLibro(new Libro("El principito", "Antoine de Saint-Exupéry", "978-0156012195", 1943, "Infantil", 15.00, 20, true));
        dao.insertarLibro(new Libro("La ciudad de los prodigios", "Eduardo Mendoza", "978-8432205552", 1986, "Novela", 18.90, 8, false));
        dao.insertarLibro(new Libro("It", "Stephen King", "978-1501142970", 1986, "Terror", 35.00, 4, true));
        dao.insertarLibro(new Libro("Breve historia del tiempo", "Stephen Hawking", "978-0553380163", 1988, "Ensayo", 28.00, 1, false));
        dao.insertarLibro(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "978-8478884451", 1997, "Infantil", 40.00, 15, true));
        dao.insertarLibro(new Libro("Patria", "Fernando Aramburu", "978-8490663196", 2016, "Novela", 24.50, 10, true));
        dao.insertarLibro(new Libro("El código Da Vinci", "Dan Brown", "978-0307474278", 2003, "Novela", 12.00, 25, true));
         */

        List<Libro> todosLosLibros = dao.obtenerLibros();
        for (Libro libro : todosLosLibros) {
            System.out.println(libro);
        }

        System.out.println("\nSiguiente método de obtener libros según un género dado como parámetro xd\n");

        List<Libro> librosGenero = dao.obtenerLibros("Novela");
        for (Libro libro : librosGenero) {
            System.out.println(libro.getTitulo() + " - " + libro.getGenero());
        }

        System.out.println("\nSiguiente método\n");

        List<Libro> librosMenosDe3EjemplaresDisponibles = dao.obtenerLibrosMenosDe3EjemplaresDisponibles();
        for (Libro libro : librosMenosDe3EjemplaresDisponibles) {
            System.out.println(libro.getTitulo() + " - " + libro.getEjemplaresDisponibles());
        }

        System.out.println("\nSiguiente método\n");

        List<Libro> obtenerPublicados2000 = dao.obtenerPublicados2000();
        for (Libro libro : obtenerPublicados2000) {
            System.out.println(libro.getTitulo() + " - " + libro.getAnyoPublicacion());
        }

        System.out.println("\nSiguiente método\n");

        List<Libro> obtenerLibrosAutorTexto = dao.obtenerLibrosAutorTexto("Stephen");
        for (Libro libro : obtenerLibrosAutorTexto) {
            System.out.println(libro.getAutor());
        }

        System.out.println("\nSiguiente método\n");

        List<Libro> obtenerLibrosPrecioMasAlto  = dao.obtenerLibrosPrecioMasAlto();
        for (Libro libro : obtenerLibrosPrecioMasAlto) {
            System.out.println("El libro " + libro.getTitulo() + " tiene un precio de " + libro.getPrecio() + "€");
        }

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        System.out.println("Número de libros diferentes: " + dao.obtenerNumeroLibrosDiferentes());

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        System.out.println("La media es: " + dao.obtenerMediaPrecio());

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        Libro masViejo = dao.obtenerLibroMasViejo();
        System.out.println("El más antiguo es " + masViejo.getTitulo() + " siendo publicado en " + masViejo.getAnyoPublicacion());

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        Map<String, Long> librosPorGenero = dao.obtenerNumeroLibrosPorGenero();

        librosPorGenero.forEach((genero, cantidad) -> {
            System.out.println("Género: " + genero + ", cantidad: " + cantidad);
        });
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige un género literario: ");
        String generoElegido = sc.nextLine();

        List<Libro> librosPorGenero = dao.obtenerLibros(generoElegido);
        if (librosPorGenero.isEmpty()) {
            System.out.println("Libros no encontrados");
        } else {
            for (Libro libro : librosPorGenero) {
                System.out.println(libro);
            }
        }
        sc.close();
        emf.close();

         */
    }
}