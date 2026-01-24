package Ejercicio_repaso_XML.Repaso_XML;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class App {
	public static void main(String[] args) {
		String ruta = "res/concesionario.xml";

		File directorio = new File("res");
		if (!directorio.exists()) {
			directorio.mkdir();
		}

		DNI dni = new DNI("Española", "12345678A", "11/6/2027");
		Departamento depar = new Departamento("IT", 1000.56, 3, Arrays.asList(new Trabajador("nix", "cruz", 21, dni)));

		guardarxml(depar, ruta);

		Departamento concesionarioLeido = leerDesdeXML(ruta);
		if (concesionarioLeido != null) {
			mostrarConcesionario(concesionarioLeido);
		}

	}

	public static void guardarxml(Departamento depa, String ruta) {
		try {
			JAXBContext contexto = JAXBContext.newInstance(Departamento.class, Trabajador.class, DNI.class);
			Marshaller marshaler = contexto.createMarshaller();
			marshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaler.marshal(depa, new File(ruta));
			System.out.println("Se ha creado el archivo xml: " + ruta);
		} catch (JAXBException e) {
			System.out.println("Error al crear XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static Departamento leerDesdeXML(String rutaArchivo) {
		try {
			JAXBContext contexto = JAXBContext.newInstance(Departamento.class, Trabajador.class, DNI.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			return (Departamento) unmarshaller.unmarshal(new File(rutaArchivo));
		} catch (JAXBException e) {
			System.out.println("Error al leer el XML: " + e.getMessage());
			return null;
		}
	}

	private static void mostrarConcesionario(Departamento departamento) {
		System.out.println("\nCONCESIONARIO: " + departamento.getNombre());
		System.out.println("Ubicación: " + departamento.getNombre());
		List<Trabajador> trabajadores = departamento.getTrabajado();

		System.out.println("Vehículos disponibles:" + trabajadores.size());

		for (Trabajador v : trabajadores) {
			System.out.println(v);
		}
	}
}