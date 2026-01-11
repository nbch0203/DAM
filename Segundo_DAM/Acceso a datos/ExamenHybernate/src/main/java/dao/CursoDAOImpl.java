// ============================================
// IMPLEMENTACIÓN CURSO DAO
// ============================================

package dao;

import jakarta.persistence.TypedQuery;
import modelos.Curso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import utils.HibernateUtil;

import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    private final SessionFactory sessionFactory;

    public CursoDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void crear(Curso curso) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al crear curso", e);
        }
    }

    @Override
    public Curso obtenerPorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Curso.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener curso por ID", e);
        }
    }

    @Override
    public Curso obtenerPorIdConInscripciones(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c " +
                         "LEFT JOIN FETCH c.inscripciones i " +
                         "LEFT JOIN FETCH i.estudiante " +
                         "WHERE c.id = :id";
            
            TypedQuery<Curso> query = session.createQuery(jpql, Curso.class);
            query.setParameter("id", id);
            
            Curso curso = query.getSingleResult();
            
            if (curso != null) {
                Hibernate.initialize(curso.getInscripciones());
                Hibernate.initialize(curso.getProfesor());
            }
            
            return curso;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener curso con inscripciones", e);
        }
    }

    @Override
    public void actualizar(Curso curso) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al actualizar curso", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Curso curso = session.get(Curso.class, id);
            if (curso != null) {
                session.remove(curso);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al eliminar curso", e);
        }
    }

    @Override
    public List<Curso> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c ORDER BY c.codigo";
            return session.createQuery(jpql, Curso.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los cursos", e);
        }
    }

    @Override
    public Curso obtenerPorCodigo(String codigo) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c WHERE c.codigo = :codigo";
            TypedQuery<Curso> query = session.createQuery(jpql, Curso.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Curso> buscarPorNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)";
            TypedQuery<Curso> query = session.createQuery(jpql, Curso.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por nombre", e);
        }
    }

    @Override
    public List<Curso> obtenerCursosConCupoDisponible() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c WHERE SIZE(c.inscripciones) < c.cupoMaximo";
            return session.createQuery(jpql, Curso.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cursos con cupo", e);
        }
    }

    @Override
    public List<Curso> obtenerCursosPorProfesor(Long profesorId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c FROM Curso c WHERE c.profesor.id = :profesorId";
            TypedQuery<Curso> query = session.createQuery(jpql, Curso.class);
            query.setParameter("profesorId", profesorId);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cursos por profesor", e);
        }
    }

    @Override
    public Long contarEstudiantesPorCurso(Long cursoId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT COUNT(i) FROM Inscripcion i WHERE i.curso.id = :cursoId";
            TypedQuery<Long> query = session.createQuery(jpql, Long.class);
            query.setParameter("cursoId", cursoId);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error al contar estudiantes", e);
        }
    }

    @Override
    public Double obtenerPromedioNotasCurso(Long cursoId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT AVG(i.notaFinal) FROM Inscripcion i " +
                         "WHERE i.curso.id = :cursoId AND i.notaFinal IS NOT NULL";
            TypedQuery<Double> query = session.createQuery(jpql, Double.class);
            query.setParameter("cursoId", cursoId);
            return query.getSingleResult();
        } catch (Exception e) {
            return 0.0;
        }
    }
}