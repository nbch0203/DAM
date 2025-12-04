package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelos.DetalleLibro;
import utils.HibernateUtil;

public class DetalleLibroDAOImpl implements DetalleLibroDAO {

	private SessionFactory factory;
	private Session sesion;
	private Transaction transaccion;

	public DetalleLibroDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void creacion(DetalleLibro detalle) {
		// TODO Auto-generated method stub
		sesion = factory.openSession();

		Transaction transaccion = null;
		try {
			transaccion = sesion.beginTransaction();

			sesion.persist(detalle);

			transaccion.commit();

		} catch (RuntimeException e) {
			if (transaccion != null)
				transaccion.rollback();
		} finally {
			sesion.close();
		}

	}

	@Override
	public void actualizacion(DetalleLibro detalle) {
		// TODO Auto-generated method stub

		sesion = factory.openSession();

		transaccion = null;
		try {
			transaccion = sesion.beginTransaction();

			sesion.merge(detalle);
			transaccion.commit();

		} catch (RuntimeException e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
		} finally {
			sesion.close();
		}
	}
	
	

}
