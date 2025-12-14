package app;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Customer;
import clases.Office;
import clases.Payment;




public class AppDml 
{
    @SuppressWarnings({  "deprecation" })
	public static void main( String[] args )
    {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();
        
		session.getTransaction().begin();
        

        //UPDATE
		
		  //Incremento del 10% en el límite de crédito a todos los clientes 
//		 int  numUpdateResults = session.createQuery( "update Customer c " +
//		  " set c.creditLimit = c.creditLimit * 1.1").executeUpdate();
//		  System.out.println("Número de registros afectados: " + numUpdateResults);
		
		int numUpdateResults = session.createQuery(
			    "update Customer c set c.creditLimit = c.creditLimit * :incrementFactor")
			    .setParameter("incrementFactor", new BigDecimal("1.1"))
			    .executeUpdate();
		System.out.println("Número de registros afectados: " + numUpdateResults);
		  
		  //DELETE
		  
		  Date date = null; try { date = new
		  SimpleDateFormat("dd/MM/yyyy").parse("05/07/2003");
		  
		  int numDeleteResults =session.createQuery( "delete from Payment p " +
		  " where p.paymentDate = :fecha").setParameter("fecha", date).executeUpdate();
		  
		  System.out.println("Número de registros afectados: " + numDeleteResults);
		  
		  
		  } catch (ParseException e) {
		  System.err.println("Error en el parseo de la fecha"); }
		 
        
       session.getTransaction().commit();
        
       session.close();
       factory.close();
        
    }
}
