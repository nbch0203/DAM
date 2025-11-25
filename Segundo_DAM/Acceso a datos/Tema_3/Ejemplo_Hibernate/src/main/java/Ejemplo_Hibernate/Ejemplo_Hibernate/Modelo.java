package Ejemplo_Hibernate.Ejemplo_Hibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

/**
 * En esta clase se interacciona con el modelo de datos de la aplicación Se
 * utiliza la clase HibernateUtil para crear las sesiones de conexión con
 * Hibernate
 */
public class Modelo {
	private SessionFactory factory;

	public Modelo() {
		// Hacemos una primera conexión para que la primera consulta no tarde en
		// realizarse
		factory = HibernateUtil.getSessionFactory();
	}

	// insertamos un departamento
	public void inserta(Departamento dep) {
		Session sess = factory.openSession(); // No consume recursos de BD hasta que no es necesario

		Transaction tx = null;
		try {
			tx = sess.beginTransaction();// inicio mi transaccion
			// inserto departamento
			sess.persist(dep);

			tx.commit(); // Se hacen efectivos los cambios en BD
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // o visualizamos mensaje de error
		} finally {
			sess.close();
		}

	}

	// buscamos un departamento
	public Departamento buscar(int id) {
		Session sess = factory.openSession(); // No consume recursos de BD hasta que no es necesario
		Departamento d = null;
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();// inicio mi transaccion
			// busco departamento que conozco que existe
			d = sess.find(Departamento.class, id);

			tx.commit(); // Se hacen efectivos los cambios en BD
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // o visualizamos mensaje de error
		} finally {
			sess.close();
		}
		return d;

	}
	
	
	// borramos un departamento
	public void borrar(Departamento dep) {
		Session sess = factory.openSession(); // No consume recursos de BD hasta que no es necesario

		Transaction tx = null;
		try {
			tx = sess.beginTransaction();// inicio mi transaccion
			// borro departamento
			sess.remove(dep);

			tx.commit(); // Se hacen efectivos los cambios en BD
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // o visualizamos mensaje de error
		} finally {
			sess.close();
		}

	}

}
