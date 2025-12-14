package app;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Customer;
import clases.Employee;
import jakarta.persistence.Query;




public class AppNamedQuery 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();
        

       Query query = session.createNamedQuery("Customer.findAll",Customer.class);
        
        List<Customer> listCustomer = query.getResultList();
        for(Customer c : listCustomer) 
        	printCustomer(c);
       
        
        
        Query query2 = session.createNamedQuery("Customer.findByName",Customer.class);
        query2.setParameter("name","%Iberia%");

        List<Customer> listCustomer2 = query2.getResultList();
        for(Customer c : listCustomer2) 
        	printCustomer(c);
        
        
        Query query3 = session.createNamedQuery("Customer.findByEmployee",Customer.class);
        query3.setParameter("employee", session.find(Employee.class, 1370));
        List<Customer> listCustomer3 = query3.getResultList();
        for(Customer c : listCustomer3) 
        	printCustomer(c);
        
   
        session.close();
        factory.close();
        
    }
    
    public static void printCustomer(Customer c) {
    	System.out.print(c.getCustomerName() + " (");
    	System.out.print(c.getContactFirstName() + " " + c.getContactLastName()+ ") (");
    	System.out.println(c.getCity() + ", " + c.getCountry() + ")");
    }
}
