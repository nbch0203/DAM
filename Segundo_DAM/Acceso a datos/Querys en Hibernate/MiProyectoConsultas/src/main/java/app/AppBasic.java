package app;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.*;
//import jakarta.persistence.Query;
import org.hibernate.query.Query;

public class AppBasic {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();
		
		  // EJEMPLO 1 - Consultas sencillas
		  
		  // Consulta 1 - Básica System.out.println("\n\nConsulta 1 - Básica");
		  
		  //Para evitar conversiones el método createQuery(String) está deprecated y
		  //ahora se pide el tipo de entity que devuelve la consulta 
		  Query query1 =  session.createQuery("select c from Customer c", Customer.class); 
		  // Query query =session.createQuery( // "select c from Customer c" // );
		  
		  List<Customer> listCustomer = (List<Customer>) query1.getResultList();
		  
		  
		  
		  System.out.println("LISTADO DE TODOS LOS CLIENTES");
		  System.out.println("============================="); 
		  for (Customer c : listCustomer) System.out.println(c.getCustomerName());
		  
		  // Consulta 2 - Selección de solo algunas propiedades
		  System.out.println("\n\nConsulta 2 - Selección de solo algunas propiedades");
		  
		  //Como el resultado es una proyección de una entity (no la entity entera) los
		  //resultados los devolvemos como genéricos Object 
		  //Esto hace que después tengamos que hacer conversiones, ojo con hacerlas bien 
		  query1 = session.createQuery("SELECT c.customerName, c.city, c.country FROM Customer c",Object[].class);
		  
		  List<Object[]> list = (List<Object[]>) query1.getResultList();
		  System.out.println("LISTADO DE TODOS LOS CLIENTES (Nombre, Ciudad y Pais)");
		  System.out.println("============================="); 
		  for (Object[] o : list){ 
			  System.out.print((String) o[0] + ", "); 
			  System.out.print((String) o[1] + ", "); System.out.println((String) o[2]); 
		  }
		 

		/* -------------------------------------------------------------------------- */

		// EJEMPLO 2 - Consultas sencillas con condiciones

		// Consulta 3 - Consulta con condiciones
		
		  System.out.println("\n\nConsulta 3 - Consulta con condiciones"); 
		  Query query3 =session.createQuery("SELECT e FROM Employee e WHERE e.jobTitle = 'Sales Rep'",Employee.class);
		  
		  List<Employee> listEmployee = (List<Employee>) query3.getResultList();
		  
		  System.out.println("LISTADO DE TODOS LOS EMPLEADOS CON jobTitle=Sales Rep");
		  System.out.println("============================="); 
		  for (Employee e :listEmployee) 
			  System.out.println(e.getFirstName() + " " + e.getLastName());
		 

		/* -------------------------------------------------------------------------- */

		// EJEMPLO 3 - Consultas con parámetros dinámicos

		System.out.println("\n\nConsulta 4 - Consulta con parámetros dinámicos");
		Query<Employee> query = session.createQuery("SELECT e FROM Employee e WHERE e.jobTitle = :jobTitle", Employee.class); //usando org.hibernate.query.Query

//		query.setParameter("jobTitle", "Sales Rep"); 
		query.setParameter("jobTitle", "President");

//		List<Employee> listEmployee = (List<Employee>) query.getResultList();//getResultList() es de jakarta.persistence
		Query<Employee> q=session.createQuery("FROM Employee e ORDER BY e.lastName DESC", Employee.class);
		List<Employee> listEmployee3=q.list(); //método list() es de org.hibernate.query.Query

		
		System.out.println("LISTADO DE TODOS LOS EMPLEADOS CON jobTitle=Sales Rep");
		System.out.println("=============================");
		for (Employee e : listEmployee3)
			System.out.println(e.getFirstName() + " " + e.getLastName());

		/* -------------------------------------------------------------------------- */

		
		  // EJEMPLO 4 - Consultas con parámetros dinámicos y ordenación 
		System.out.println("\n\nConsulta 5 - Consulta con parámetros dinámicos y ordenación ");
		  Query query4 = session.createQuery("SELECT o FROM Order o WHERE o.orderDate BETWEEN ?1 AND ?2 AND status = ?3 " + "ORDER BY o.orderDate DESC", Order.class);
		  
		  Calendar calendar = Calendar.getInstance(); calendar.set(2003, 0, 1); //fecha inicio
		  
		  query4.setParameter(1, calendar.getTime());
		  
		  calendar.set(2003, 5, 30); // fecha fin
		  
		  query4.setParameter(2, calendar.getTime());
		  
		  query4.setParameter(3, "Shipped");
		  
		  List<Order> listOrder = (List<Order>) query4.getResultList();
		  
		  System.out.println("LISTADO DE TODAS LAS VENTAS EN UN RANGO DE FECHAS Y ENVIADAS");
		  System.out.println(
		  "================================================================"); 
		  for(Order o : listOrder) { 
			  System.out.print(o.getOrderDate() + ", ");
			  System.out.print(o.getStatus() + ", ");
			  System.out.println(o.getCustomer().getCustomerName());
			  // Podemos navegar a través de las propiedades de orders, después a customers 
		  }
		  
		  
		 

		/* -------------------------------------------------------------------------- */

		// EJEMPLO 5 - Consultas con un solo resultado

		// Consulta 6 - count()
		
		  System.out.println("\n\nConsulta 6 - COUNT()"); 
		  Query query5 =session.createQuery( "select COUNT(p) from Product p",Object.class );
		  
		  long count = (long) query5.getSingleResult();
		  
		  System.out.println("CANTIDAD DE PRODUCTOS EN EL CATÁLOGO");
		  System.out.println("=====================================");
		  System.out.println("La cantidad de productos en el catálogo es " + count);
		  
		  //Consulta 7 - MAX() System.out.println("\n\nConsulta 7 - MAX()"); 
		  query5=session.createQuery( "select MAX(p.amount) FROM Payment p",Object.class );
		  
		  BigDecimal maxAmount = (BigDecimal) query5.getSingleResult();
		  
		  System.out.println("PAGO DE MAYOR CUANTÍA REALIZADO");
		  System.out.println("===============================");
		  System.out.println("La cuantía más grande que se ha abonado es " +
		  maxAmount.toPlainString());
		 
		  session.close();
		/* -------------------------------------------------------------------------- */

	}
}
