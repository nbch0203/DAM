package app;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Customer;
import clases.Employee;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;




public class AppJoin 
{
	public static void main( String[] args )
    {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();
        
		//Ejemplo1

        Query query1 = session.createQuery("select c FROM Customer c",Customer.class);

		//Ejemplo2
        Query query2 = session.createQuery(
        		  "select cus "
        		+ "from Customer cus "
        		+ "left join fetch cus.orders",Customer.class);
        
		//Ejemplo3
        Query query3 = session.createQuery(
        		  "select c "
        		+ "from Customer c "
        		+ "left join fetch c.employee ",Customer.class);

		//Ejemplo4
//        TypedQuery<Customer> query = session.createQuery(
//        		"select c "
//        		+ "from Customer c "
//        		+ "where c.employee = :employee", Customer.class);
//       
//        query.setParameter("employee", session.find(Employee.class, 1370));
        
        
        
        List<Customer> listCustomer = query3.getResultList();
        System.out.println("RESULTADOS:");
        for(Customer c : listCustomer) {
        	System.out.print(c.getCustomerName() + " (");
        	System.out.print(c.getContactFirstName() + " " + c.getContactLastName()+ ") (");
        	System.out.println(c.getCity() + ", " + c.getCountry() + ")");
        }
        
        
        session.close();
        factory.close();
        
    }
}
