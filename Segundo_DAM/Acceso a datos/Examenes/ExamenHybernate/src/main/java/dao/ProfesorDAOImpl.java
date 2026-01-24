package dao;

import jakarta.persistence.TypedQuery;
import modelos.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import utils.HibernateUtil;

import java.util.List;

public class ProfesorDAOImpl implements ProfesorDAO {

    private final SessionFactory sessionFactory;

    public ProfesorDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void crear(Profesor profesor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(profesor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al crear profesor", e);
        }
    }

    @Override
    public Profesor obtenerPorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Profesor.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener profesor por ID", e);
        }
    }

    @Override
    public Profesor obtenerPorIdConRelaciones(Long id) {
        try (Session session = sessionFactory.openSession()) {
            // Query JPQL con JOIN FETCH para inicializar relaciones lazy
            String jpql = "SELECT p FROM Profesor p " +
                         "LEFT JOIN FETCH p.oficina " +
                         "LEFT JOIN FETCH p.cursos " +
                         "WHERE p.id = :id";
            
            TypedQuery<Profesor> query = session.createQuery(jpql, Profesor.class);
            query.setParameter("id", id);
            
            Profesor profesor = query.getSingleResult();
            
            // Inicializar explícitamente las colecciones lazy
            if (profesor != null) {
                Hibernate.initialize(profesor.getCursos());
                if (profesor.getOficina() != null) {
                    Hibernate.initialize(profesor.getOficina());
                }
            }
            
            return profesor;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener profesor con relaciones", e);
        }
    }

    @Override
    public void actualizar(Profesor profesor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(profesor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al actualizar profesor", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Profesor profesor = session.get(Profesor.class, id);
            if (profesor != null) {
                session.remove(profesor);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error al eliminar profesor", e);
        }
    }

    @Override
    public List<Profesor> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p ORDER BY p.apellido, p.nombre";
            return session.createQuery(jpql, Profesor.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los profesores", e);
        }
    }

    @Override
    public Profesor obtenerPorEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p WHERE p.email = :email";
            TypedQuery<Profesor> query = session.createQuery(jpql, Profesor.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p WHERE p.especialidad = :especialidad";
            TypedQuery<Profesor> query = session.createQuery(jpql, Profesor.class);
            query.setParameter("especialidad", especialidad);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por especialidad", e);
        }
    }

    @Override
    public List<Profesor> buscarPorNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p " +
                         "WHERE LOWER(p.nombre) LIKE LOWER(:nombre) " +
                         "OR LOWER(p.apellido) LIKE LOWER(:nombre)";
            TypedQuery<Profesor> query = session.createQuery(jpql, Profesor.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por nombre", e);
        }
    }

    @Override
    public List<Profesor> obtenerProfesoresConOficina() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p WHERE p.oficina IS NOT NULL";
            return session.createQuery(jpql, Profesor.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener profesores con oficina", e);
        }
    }

    @Override
    public List<Profesor> obtenerProfesoresSinOficina() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p WHERE p.oficina IS NULL";
            return session.createQuery(jpql, Profesor.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener profesores sin oficina", e);
        }
    }

    @Override
    public Long contarCursosPorProfesor(Long profesorId) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT COUNT(c) FROM Curso c WHERE c.profesor.id = :profesorId";
            TypedQuery<Long> query = session.createQuery(jpql, Long.class);
            query.setParameter("profesorId", profesorId);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error al contar cursos", e);
        }
    }

    @Override
    public List<Profesor> obtenerProfesoresConMasDe(int numeroCursos) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT p FROM Profesor p " +
                         "WHERE SIZE(p.cursos) > :numero " +
                         "ORDER BY SIZE(p.cursos) DESC";
            TypedQuery<Profesor> query = session.createQuery(jpql, Profesor.class);
            query.setParameter("numero", numeroCursos);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener profesores con más cursos", e);
        }
    }
}