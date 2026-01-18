package app;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Customer;
import clases.Employee;




public class AppNative {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session session = factory.openSession();
		
		// CONSULTA 1

		List<Object[]> employees = session.createNativeQuery("SELECT employeeNumber, firstName, lastName FROM employees").getResultList();

		for (Object[] employee : employees) {
//			int employeeNumber = (int) employee[0];
			int employeeNumber = ((Number) employee[0]).intValue(); // Conversión explícita
			String firstName = (String) employee[1];
			String lastName = (String) employee[2];
			System.out.println(String.format("Employee %d: %s %s", employeeNumber, firstName, lastName));
		}

		// CONSULTA 2
		List<Customer> customers = session.createNativeQuery("SELECT * FROM customers", Customer.class).getResultList();

		for (Customer c : customers) {
			System.out.println(String.format("Customer %d: %s %s", c.getCustomerNumber(), c.getContactFirstName(),
					c.getContactLastName()));
		}

		
		// CONSULTA 3
		List<Employee> employeesList = session.createNativeQuery(
				"SELECT employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle FROM employees",
				Employee.class).getResultList();

		for (Employee employee : employeesList) {
			System.out.println(String.format("Employee %d: %s %s ", employee.getEmployeeNumber(),
					employee.getFirstName(), employee.getLastName()));
		}
		
		
		// CONSULTA 4
		List<Employee> employeesList2 = session.createNamedQuery("Employee.nativeFindAll").getResultList();

		for (Employee employee : employeesList2) {
			System.out.println(String.format("Employee %d: %s %s ", employee.getEmployeeNumber(),
					employee.getFirstName(), employee.getLastName()));
		}
		

		session.close();
		factory.close();

	}
}
