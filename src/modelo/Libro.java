package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anyoPublicacion;
    private String genero;
    private double precio;
    private int ejemplaresDisponibles;
    private boolean esBestSeller;

    public Libro() {
    }

    public Libro(String titulo, String autor, String isbn, int anyoPublicacion, String genero, double precio, int ejemplaresDisponibles, boolean esBestSeller) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anyoPublicacion = anyoPublicacion;
        this.genero = genero;
        this.precio = precio;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.esBestSeller = esBestSeller;
    }

    public Libro(int id, String titulo, String autor, String isbn, int anyoPublicacion, String genero, double precio, int ejemplaresDisponibles, boolean esBestSeller) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anyoPublicacion = anyoPublicacion;
        this.genero = genero;
        this.precio = precio;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.esBestSeller = esBestSeller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(int anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public boolean isEsBestSeller() {
        return esBestSeller;
    }

    public void setEsBestSeller(boolean esBestSeller) {
        this.esBestSeller = esBestSeller;
    }

    @Override
    public String toString() {
        return "Titulo: " + this.getTitulo() + ", Autor: " + this.getAutor() + ", isbn: " + this.getIsbn() + ", publicado en: " + this.getAnyoPublicacion()
                + ", del género: " + this.getGenero() + ", con un precio de: " + this.getPrecio() + ", teniendo " + this.getEjemplaresDisponibles() +" ejemplares disponibles: "  + ", esBestSeller: " + this.isEsBestSeller();
    }
}
