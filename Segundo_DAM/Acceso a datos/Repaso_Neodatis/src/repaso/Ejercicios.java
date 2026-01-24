package repaso;

import java.io.File;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.AbstractCriterion;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import datos_necesarios.Ayudante;
import datos_necesarios.DNI;
import datos_necesarios.Empleado;
import datos_necesarios.Profesor;

public class Ejercicios {

	private static ODB odb;
	private static final String baseDeDatos = "RepasoNeodatis.DB";

	private static void inicializar() {
		odb = ODBFactory.open(baseDeDatos);

		// Profesor 1
		Profesor profesor1 = new Profesor("PROF001", "Matemáticas");
		DNI dni1 = new DNI("12345678A");
		profesor1.setDni(dni1);
		profesor1.setNombre("Luis");
		profesor1.setApellido("Rodríguez");
		profesor1.setTelefono(634567891);
		profesor1.setGenero("Masculino");

		// Profesor 2
		Profesor profesor2 = new Profesor("PROF002", "Informática");
		DNI dni2 = new DNI("87654321B");
		profesor2.setDni(dni2);
		profesor2.setNombre("Elena");
		profesor2.setApellido("Sánchez");
		profesor2.setTelefono(687654321);
		profesor2.setGenero("Femenino");

		// Profesor 3
		Profesor profesor3 = new Profesor("PROF003", "Física");
		DNI dni3 = new DNI("45678912C");
		profesor3.setDni(dni3);
		profesor3.setNombre("Pedro");
		profesor3.setApellido("Gómez");
		profesor3.setTelefono(623456789);
		profesor3.setGenero("Masculino");

		// Profesor 4
		Profesor profesor4 = new Profesor("PROF004", "Química");
		DNI dni4 = new DNI("78912345D");
		profesor4.setDni(dni4);
		profesor4.setNombre("Carmen");
		profesor4.setApellido("Martínez");
		profesor4.setTelefono(656789123);
		profesor4.setGenero("Femenino");

		// Profesor 5
		Profesor profesor5 = new Profesor("PROF005", "Historia");
		DNI dni5 = new DNI("32165498E");
		profesor5.setDni(dni5);
		profesor5.setNombre("Javier");
		profesor5.setApellido("López");
		profesor5.setTelefono(612345678);
		profesor5.setGenero("Masculino");

		odb.store(profesor1);
		odb.store(profesor2);
		odb.store(profesor3);
		odb.store(profesor4);
		odb.store(profesor5);

		// Ayudante 1 - asignado al profesor1 (Luis Rodríguez - Matemáticas)
		Ayudante ayudante1 = new Ayudante("AYU001", profesor1);
		DNI dni6 = new DNI("11223344F");
		ayudante1.setDni(dni6);
		ayudante1.setNombre("Andrea");
		ayudante1.setApellido("Torres");
		ayudante1.setTelefono(645123456);
		ayudante1.setGenero("Femenino");

		// Ayudante 2 - asignado al profesor2 (Elena Sánchez - Informática)
		Ayudante ayudante2 = new Ayudante("AYU002", profesor2);
		DNI dni7 = new DNI("55667788G");
		ayudante2.setDni(dni7);
		ayudante2.setNombre("Miguel");
		ayudante2.setApellido("Ruiz");
		ayudante2.setTelefono(678234567);
		ayudante2.setGenero("Masculino");

		// Ayudante 3 - asignado al profesor3 (Pedro Gómez - Física)
		Ayudante ayudante3 = new Ayudante("AYU003", profesor3);
		DNI dni8 = new DNI("99887766H");
		ayudante3.setDni(dni8);
		ayudante3.setNombre("Laura");
		ayudante3.setApellido("Jiménez");
		ayudante3.setTelefono(634567123);
		ayudante3.setGenero("Femenino");

		// Ayudante 4 - asignado al profesor4 (Carmen Martínez - Química)
		Ayudante ayudante4 = new Ayudante("AYU004", profesor4);
		DNI dni9 = new DNI("22446688I");
		ayudante4.setDni(dni9);
		ayudante4.setNombre("Roberto");
		ayudante4.setApellido("Morales");
		ayudante4.setTelefono(667891234);
		ayudante4.setGenero("Masculino");

		// Ayudante 5 - asignado al profesor5 (Javier López - Historia)
		Ayudante ayudante5 = new Ayudante("AYU005", profesor5);
		DNI dni10 = new DNI("33557799J");
		ayudante5.setDni(dni10);
		ayudante5.setNombre("Sofía");
		ayudante5.setApellido("Navarro");
		ayudante5.setTelefono(698765432);
		ayudante5.setGenero("Femenino");

		odb.store(ayudante1);
		odb.store(ayudante2);
		odb.store(ayudante3);
		odb.store(ayudante4);
		odb.store(ayudante5);
		System.out.println("se ha hecho");
		odb.close();
	}

	private static void mostrarObjetos(String tipo) {
		odb = ODBFactory.open(baseDeDatos);

		if (tipo.equalsIgnoreCase("Profesor")) {

			IQuery query = new CriteriaQuery(Profesor.class);

			Objects<Profesor> profesores = odb.getObjects(query);

			while (profesores.hasNext()) {
				System.out.println(profesores.next());
			}
		} else {
			IQuery query = new CriteriaQuery(Ayudante.class);

			Objects<Profesor> ayudantes = odb.getObjects(query);

			while (ayudantes.hasNext()) {
				System.out.println(ayudantes.next());
			}
		}
		odb.close();
	}

	private static void modificar(String campo, String valorBusqueda, String dato) {

		odb = ODBFactory.open(baseDeDatos);

		ICriterion criterio = Where.equal(campo, valorBusqueda);

		Objects<Ayudante> persona = odb.getObjects(new CriteriaQuery(Ayudante.class, criterio));

		while (persona.hasNext()) {
			Ayudante a = persona.next();
			a.setNombre(dato);
			odb.store(a);
		}
		odb.close();

	}

	private static void eliminar(String dni) {

		odb = ODBFactory.open(baseDeDatos);

		odb.close(); 
	}

	public static void main(String[] args) {
//		inicializar();
//		mostrarObjetos("ayudante");
//		modificar("nombre", "Miguel", "pepe");

		eliminar();
		mostrarObjetos("ayudante");

	}

}
