package modelos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import operaciones.ProductoDAO;
import util.HibernateUtil;

public class ProductoDAOImpl implements ProductoDAO{
	
	private SessionFactory factory;

	public Operaciones() {

		factory = HibernateUtil.getSessionFactory();
	}

	public void insertar(ProductoDAO producto) {

		Session sesion = factory.openSession();

		Transaction tx = null;

		try {
			tx = sesion.beginTransaction();

			sesion.persist(producto);

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // o visualizamos mensaje de error
		} finally {
			sesion.close();
		}

	}

	public ProductoDAO buscar(Long id) {
		Session sesion = factory.openSession();
		ProductoDAO p = null;
		Transaction transaccion = null;

		try {
			transaccion = sesion.beginTransaction();

			p = sesion.get(ProductoDAO.class, id);

			transaccion.commit();
			return p;

		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaccion != null)
				transaccion.rollback();
			throw e;
		} finally {
			sesion.close();
		}

	}

	public void borrar(ProductoDAO producto) {
		Session sesion = factory.openSession();
		Transaction transaccion = null;
		try {
			transaccion = sesion.beginTransaction();

			sesion.remove(transaccion);

			transaccion.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			sesion.close();
		}

	}

	public ProductoDAO buscarProducto(Long id) {
		
		return null;
	}

}
