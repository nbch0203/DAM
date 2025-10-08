package prueba_xml.prueba_xml;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class App {
	private static final String RUTA_ARCHIVO = "src/main/resources/empleados.xml";

	public static void main(String[] args) {
		try {
			// Crear datos de ejemplo
			Empresa empresa = crearEmpresaEjemplo();

			// Generar XML
			generarXML(empresa);

			// Leer XML y mostrar resultados
			leerYMostrarXML();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Empresa crearEmpresaEjemplo() {
		Empresa empresa = new Empresa();
		empresa.setCif("A58818501");
		empresa.setNombre("TECNOMUR S.L.");

		// Primer empleado con cargo
		Cargo cargo1 = new Cargo("Administrativo", 2);
		Empleado empleado1 = new Empleado("12345678C", "Carlos Pérez Ruíz", 29, cargo1);

		// Segundo empleado sin cargo
		Empleado empleado2 = new Empleado("87654321C", "Claudia Ortiz Zaldo", 31);

		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);

		return empresa;
	}

	private static void generarXML(Empresa empresa) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Empresa.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		// Asegurar que el directorio existe
		new File("src/main/resources").mkdirs();

		marshaller.marshal(empresa, new File(RUTA_ARCHIVO));
		System.out.println("Archivo XML generado: " + RUTA_ARCHIVO);
	}

	private static void leerYMostrarXML() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Empresa.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Empresa empresaLeida = (Empresa) unmarshaller.unmarshal(new File(RUTA_ARCHIVO));

		// Mostrar salida requerida
		System.out.println("\nSalida desde el fichero XML:");
		System.out.println(empresaLeida.toString());

		int contador = 1;
		for (Empleado empleado : empresaLeida.getEmpleados()) {
			System.out.println("Empleado " + contador + ": " + empleado.toString());
			contador++;
		}
	}
}