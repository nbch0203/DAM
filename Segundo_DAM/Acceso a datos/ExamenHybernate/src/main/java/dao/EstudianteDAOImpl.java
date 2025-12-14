// ============================================
// IMPLEMENTACIÓN ESTUDIANTE DAO
// ============================================

package dao;

import jakarta.persistence.TypedQuery;
import modelos.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import utils.HibernateUtil;

import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {

    private final SessionFactory sessionFactory;

    public EstudianteDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void crear(Estudiante estudiante) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(estudiante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al crear estudiante", e);
        }
    }

    @Override
    public Estudiante obtenerPorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Estudiante.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiante por ID", e);
        }
    }

    @Override
    public Estudiante obtenerPorIdConInscripciones(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e " +
                         "LEFT JOIN FETCH e.inscripciones i " +
                         "LEFT JOIN FETCH i.curso " +
                         "WHERE e.id = :id";
            
            TypedQuery<Estudiante> query = session.createQuery(jpql, Estudiante.class);
            query.setParameter("id", id);
            
            Estudiante estudiante = query.getSingleResult();
            
            if (estudiante != null) {
                Hibernate.initialize(estudiante.getInscripciones());
            }
            
            return estudiante;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiante con inscripciones", e);
        }
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(estudiante);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al actualizar estudiante", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Estudiante estudiante = session.get(Estudiante.class, id);
            if (estudiante != null) {
                session.remove(estudiante);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al eliminar estudiante", e);
        }
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre";
            return session.createQuery(jpql, Estudiante.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los estudiantes", e);
        }
    }

    @Override
    public Estudiante obtenerPorMatricula(String matricula) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e WHERE e.matricula = :matricula";
            TypedQuery<Estudiante> query = session.createQuery(jpql, Estudiante.class);
            query.setParameter("matricula", matricula);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e " +
                         "WHERE LOWER(e.nombre) LIKE LOWER(:nombre) " +
                         "OR LOWER(e.apellido) LIKE LOWER(:nombre)";
            TypedQuery<Estudiante> query = session.createQuery(jpql, Estudiante.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por nombre", e);
        }
    }

    @Override
    public List<Estudiante> buscarPorCarrera(String carrera) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e WHERE LOWER(e.carrera) LIKE LOWER(:carrera)";
            TypedQuery<Estudiante> query = session.createQuery(jpql, Estudiante.class);
            query.setParameter("carrera", "%" + carrera + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por carrera", e);
        }
    }

    @Override
    public List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM Estudiante e " +
                         "JOIN e.inscripciones i " +
                         "WHERE i.curso.id = :cursoId";
            TypedQuery<Estudiante> query = session.createQuery(jpql, Estudiante.class);
            query.setParameter("cursoId", cursoId);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiantes por curso", e);
        }
    }

    @Override
    public Long contarInscripcionesPorEstudiante(Long estudianteId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT COUNT(i) FROM Inscripcion i WHERE i.estudiante.id = :estudianteId";
            TypedQuery<Long> query = session.createQuery(jpql, Long.class);
            query.setParameter("estudianteId", estudianteId);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error al contar inscripciones", e);
        }
    }

    @Override
    public Double obtenerPromedioEstudiante(Long estudianteId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT AVG(i.notaFinal) FROM Inscripcion i " +
                         "WHERE i.estudiante.id = :estudianteId AND i.notaFinal IS NOT NULL";
            TypedQuery<Double> query = session.createQuery(jpql, Double.class);
            query.setParameter("estudianteId", estudianteId);
            return query.getSingleResult();
        } catch (Exception e) {
            return 0.0;
        }
    }
}