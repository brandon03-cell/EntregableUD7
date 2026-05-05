package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Libro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Libro> obtenerLibrosPrecioMasAlto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l order by l.precio desc", Libro.class);
        query.setMaxResults(5);
        List<Libro> res = query.getResultList();
        em.close();
        return res;
    }

    public int obtenerNumeroLibrosDiferentes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("select count(distinct l.isbn) from Libro l",  Long.class);
        Long resultado = query.getSingleResult();
        em.close();
        return resultado.intValue();
    }

    public double obtenerMediaPrecio() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> query = em.createQuery("select avg(l.precio) from Libro l", Double.class);
        double media = query.getSingleResult();
        em.close();
        return media;
    }

    public Libro obtenerLibroMasViejo() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Libro> query = em.createQuery("select l from Libro l order by l.anyoPublicacion asc", Libro.class);
        query.setMaxResults(1);
        Libro resultado = query.getSingleResult();
        em.close();
        return resultado;
    }

    //Obtener el número de libros diferentes agrupados por género
    public Map<String, Long> obtenerNumeroLibrosPorGenero() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("select l.genero, count(l) from Libro l group by l.genero", Object[].class);
        List<Object[]> resultado = query.getResultList();
        Map<String, Long> mapResultados = new HashMap<>();
        for (Object[] fila : resultado) {
            String genero = (String) fila[0];
            Long numero = (Long) fila[1];
            mapResultados.put(genero, numero);
        }
        em.close();
        return mapResultados;
    }
}
