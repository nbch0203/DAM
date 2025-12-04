package dao;

import java.awt.datatransfer.Transferable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelos.Libro;
import utils.HibernateUtil;

public class LibroDAOImpl implements LibroDAO {
	private SessionFactory factory;
	private Session sesion;
	private Transaction transaccion;

	public LibroDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void creacion(Libro libro) {
		// TODO Auto-generated method stub
		sesion = factory.openSession();
		transaccion = null;
		try {

			transaccion = sesion.beginTransaction();
			sesion.persist(libro);

			transaccion.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaccion != null) {
				transaccion.rollback();
			}
		} finally {
			sesion.close();
		}

	}

	@Override
	public void actualizar(Libro libro) {
		// TODO Auto-generated method stub
		sesion = factory.openSession();
		transaccion = null;
		try {
			transaccion = sesion.beginTransaction();

			sesion.merge(libro);

			transaccion.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaccion != null) {
				transaccion.rollback();
			}
		} finally {
			sesion.close();
		}
	}

	@Override
	public Libro buscarPorId(Long id) {
		// TODO Auto-generated method stub
		Libro l = null;
		try {
			sesion = factory.openSession();

			l = sesion.get(Libro.class, id);
			return l;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (sesion != null) {
				sesion.close();

			}
		}
		return l;

	}

}
