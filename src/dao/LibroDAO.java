package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Libro;

import java.util.List;

public class LibroDAO {
    private EntityManagerFactory emf;

    public LibroDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertarLibro(Libro l) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarLibro(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Libro lb = em.find(Libro.class, id);
        if (lb != null) {
            em.remove(lb);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Libro obtenerLibro(int id) {
        EntityManager em = emf.createEntityManager();
        Libro lb = em.find(Libro.class, id);
        em.close();
        return lb;
    }

    public List<Libro> obtenerLibros() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l", Libro.class);
        List<Libro> resultado = query.getResultList();
        em.close();
        return resultado;
    }

    public List<Libro> obtenerLibros(String genero) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l where l.genero = :genero", Libro.class);
        query.setParameter("genero", genero);
        List<Libro> resultado = query.getResultList();
        em.close();
        return resultado;
    }

    public List<Libro> obtenerLibrosMenosDe3EjemplaresDisponibles() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l where l.ejemplaresDisponibles < 3", Libro.class);
        List<Libro> res = query.getResultList();
        em.close();
        return res;
    }

    public List<Libro> obtenerPublicados2000() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l where l.anyoPublicacion >= 2000", Libro.class);
        List<Libro> res = query.getResultList();
        em.close();
        return res;
    }

    public List<Libro> obtenerLibrosAutorTexto(String texto) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l where l.autor like :texto", Libro.class);
        query.setParameter("texto", "%" + texto + "%");
        List<Libro> res = query.getResultList();
        em.close();
        return res;
    }

    //Obtener los 5 libros con el precio más alto
    public List<Libro> obtenerLibrosPrecioMasAlto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l order by l.precio desc limit 5", Libro.class);
        List<Libro> res = query.getResultList();
        em.close();
        return res;
    }

    //
}
