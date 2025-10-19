package app;

import javax.xml.bind.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import clases.Concesionario;
import clases.Vehiculo;

public class GestorConcesionario {

	public static void main(String[] args) {
		String rutaArchivo = "res/concesionario.xml";

		// Crear la carpeta "res" si no existe
		File directorio = new File("res");
		if (!directorio.exists()) {
			directorio.mkdir();
		}

		// Crear datos de concesionario con vehículos
		Concesionario concesionario = new Concesionario("Autos Deluxe",
				Arrays.asList(new Vehiculo("Toyota", "Corolla", 2022, 25000, "Juan Pérez"),
						new Vehiculo("Ford", "Focus", 2023, 32000, "Ana Gómez")),
				"Calle Mayor 45, Madrid");

		// 1. Guardar en XML
		guardarEnXML(concesionario, rutaArchivo);

		// 2. Leer y mostrar el XML
		Concesionario concesionarioLeido = leerDesdeXML(rutaArchivo);
		if (concesionarioLeido != null) {
			mostrarConcesionario(concesionarioLeido);
		}
	}

	private static void guardarEnXML(Concesionario concesionario, String rutaArchivo) {
		try {
			JAXBContext contexto = JAXBContext.newInstance(Concesionario.class);
			Marshaller marshaller = contexto.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(concesionario, new File(rutaArchivo));
			System.out.println("Archivo XML generado: " + rutaArchivo);
		} catch (JAXBException e) {
			System.out.println("Error al guardar el XML: " + e.getMessage());
		}
	}

	private static Concesionario leerDesdeXML(String rutaArchivo) {
		try {
			JAXBContext contexto = JAXBContext.newInstance(Concesionario.class);
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			return (Concesionario) unmarshaller.unmarshal(new File(rutaArchivo));
		} catch (JAXBException e) {
			System.out.println("Error al leer el XML: " + e.getMessage());
			return null;
		}
	}

	private static void mostrarConcesionario(Concesionario concesionario) {
		System.out.println("\nCONCESIONARIO: " + concesionario.getNombre());
		System.out.println("Ubicación: " + concesionario.getUbicacion());
		List<Vehiculo> vehiculos = concesionario.getVehiculos();
		System.out.println("Vehículos disponibles:" + vehiculos.size());

		for (Vehiculo v : vehiculos) {
			System.out.println(v);
		}
	}
}
