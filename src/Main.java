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

        System.out.println("Primer método\n");

        System.out.println(dao.obtenerLibro(3));

        System.out.println("\nSiguiente método\n");

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

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        Map<String, Double> lasMedias = dao.obtenerPrecioMedioPorGenero();
        for (String gen : lasMedias.keySet()) {
            System.out.println("Género: " + gen + " - Precio medio: " + lasMedias.get(gen));
        }

        System.out.println("\nSiguiente métodoooooooooooooooo\n");

        List<String> generosTop = dao.obtenerGenerosMuchoStock();
        if (generosTop.isEmpty()) {
            System.out.println("No hay ningún género que supere los 100 ejemplares.");
        } else {
            for (String g : generosTop) {
                System.out.println("Género top: " + g);
            }
        }
    }
}