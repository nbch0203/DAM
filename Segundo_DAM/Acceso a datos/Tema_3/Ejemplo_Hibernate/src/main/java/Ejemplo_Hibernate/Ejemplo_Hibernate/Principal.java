package Ejemplo_Hibernate.Ejemplo_Hibernate;

public class Principal {

	// CRUD: create, retrieve, update, delete
	public static void main(String[] args) {
		Modelo modelo = new Modelo(); //Tenemos creada la factoria
		
		Departamento d = new Departamento(50, "TEST_DEPNO");
		
		//modelo.inserta(d);
		
		Departamento d2= modelo.buscar(50);
		System.out.println(d2);
		
		modelo.borrar(d);
		
		d2= modelo.buscar(50);
		System.out.println(d2);

////		System.out.println("Conexión exitosa con Oracle utilizando Hibernate!");
//		session.beginTransaction();
////        Test t=new Test(1);
//
//		Departamento d = new Departamento(12, "ENTORNOS");
//
////        session.save(d);//obsoleto
//		session.persist(d); // create
//		// Commit de la transacción
//		session.getTransaction().commit();
//
//		Departamento d2 = session.find(Departamento.class, 3); // retrieve
//		System.out.println(d2);
//
//		Departamento d3 = new Departamento(12, "ENTORNOS");
//		d3.setNombre("ENT");
//		session.beginTransaction();
//		session.merge(d3); // inserta o actualiza
//		session.getTransaction().commit();
//
//		session.beginTransaction();
//		session.remove(session.find(Departamento.class, 11)); // borrado
//		session.getTransaction().commit();
//
//		session.close();
//		HibernateUtil.shutdown();
	}

}
