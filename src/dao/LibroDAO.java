package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import modelo.Libro;

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


}
