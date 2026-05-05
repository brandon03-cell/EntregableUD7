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

        Libro libroPrueba = new Libro("Dune", "Frank Herbert", "978-8497596824", 1965, "Ciencia Ficción", 24.95, 10, true);
        dao.insertarLibro(libroPrueba);
        int idDinamico = libroPrueba.getId();
        System.out.println("Nuevo libro con id " + idDinamico + ": " + libroPrueba);
        dao.borrarLibro(idDinamico);
        System.out.println("El libro con Id: " + idDinamico + " fue eliminado para siempre, Press F to pay respects 🗿🙏🥀");

        System.out.println("\n-.Obtener un libro, según su ID");

        System.out.println(dao.obtenerLibro(3));

        System.out.println("\n-.Obtener todos los libros");

        List<Libro> todosLosLibros = dao.obtenerLibros();
        for (Libro libro : todosLosLibros) {
            System.out.println(libro);
        }

        System.out.println("\n-.Siguiente método de obtener libros según un género dado como parámetro xd");

        List<Libro> librosGenero = dao.obtenerLibros("Novela");
        for (Libro libro : librosGenero) {
            System.out.println(libro.getTitulo() + " - " + libro.getGenero());
        }

        System.out.println("\n-.Obtener los libros con menos de 3 ejemplares disponibles");

        List<Libro> librosMenosDe3EjemplaresDisponibles = dao.obtenerLibrosMenosDe3EjemplaresDisponibles();
        for (Libro libro : librosMenosDe3EjemplaresDisponibles) {
            System.out.println(libro.getTitulo() + " - " + libro.getEjemplaresDisponibles());
        }

        System.out.println("\n-.Obtener los libros publicados a partir del año 2000");

        List<Libro> obtenerPublicados2000 = dao.obtenerPublicados2000();
        for (Libro libro : obtenerPublicados2000) {
            System.out.println(libro.getTitulo() + " - " + libro.getAnyoPublicacion());
        }

        System.out.println("\n-.Obtener los libros cuyo autor contenga un texto dado por parámetro");

        List<Libro> obtenerLibrosAutorTexto = dao.obtenerLibrosAutorTexto("Stephen");
        for (Libro libro : obtenerLibrosAutorTexto) {
            System.out.println(libro.getAutor());
        }

        System.out.println("\n-.Obtener los libros cuyo autor contenga un texto dado por parámetro");

        List<Libro> obtenerLibrosPrecioMasAlto  = dao.obtenerLibrosPrecioMasAlto();
        for (Libro libro : obtenerLibrosPrecioMasAlto) {
            System.out.println("El libro " + libro.getTitulo() + " tiene un precio de " + libro.getPrecio() + "€");
        }

        System.out.println("\n-.Obtener el número de libros diferentes de la biblioteca");

        System.out.println("Número de libros diferentes: " + dao.obtenerNumeroLibrosDiferentes());

        System.out.println("\n-.Obtener el precio medio de los libros de la biblioteca");

        System.out.println("La media es: " + dao.obtenerMediaPrecio());

        System.out.println("\n-.Obtener el libro más antiguo");

        Libro masViejo = dao.obtenerLibroMasViejo();
        System.out.println("El más antiguo es " + masViejo.getTitulo() + " siendo publicado en " + masViejo.getAnyoPublicacion());

        System.out.println("\n-.Obtener el número de libros diferentes agrupados por género");

        Map<String, Long> librosPorGenero = dao.obtenerNumeroLibrosPorGenero();
        librosPorGenero.forEach((genero, cantidad) -> {
            System.out.println("Género: " + genero + ", cantidad: " + cantidad);
        });

        System.out.println("\n-.Obtener el precio medio de cada género");

        Map<String, Double> lasMedias = dao.obtenerPrecioMedioPorGenero();
        for (String gen : lasMedias.keySet()) {
            System.out.println("Género: " + gen + " - Precio medio: " + lasMedias.get(gen));
        }

        System.out.println("\n-.Obtener los géneros con más de 100 ejemplares disponibles");

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