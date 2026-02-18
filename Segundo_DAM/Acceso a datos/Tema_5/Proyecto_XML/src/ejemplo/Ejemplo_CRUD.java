package ejemplo;

import org.basex.api.client.ClientSession;
import java.io.ByteArrayInputStream; // <--- NECESARIO PARA ENVIAR EL XML
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejemplo_CRUD {
	public static void main(String[] args) {
		// Conexión al servidor local
		try (ClientSession session = new ClientSession("localhost", 1984, "admin", "admin")) {
			System.out.println("--- INICIO DE GESTIÓN ---");
			// 1. GESTIÓN DE COLECCIÓN
			session.execute("CREATE DB Empresa");
			System.out.println("Base de datos 'Empresa' creada.");
			// 2. AÑADIR DOCUMENTO (CORREGIDO)
			String xmlEmpleado = "<empleado id='E1'><nombre>Laura</nombre><puesto>Jefa</puesto></empleado>";
			// Usamos el método .add() nativo de Java para enviar texto como si fuera un
			// archivo
			session.add("laura.xml", new ByteArrayInputStream(xmlEmpleado.getBytes()));

			System.out.println("Documento 'laura.xml' añadido desde Java.");
			añadirFicheroDesdeOrdenador(session);
			mostrarFicheros(session);
			mostrarContenidoFicheros(session);
			borrarDocumento(session);
			mostrarFicheros(session);
			// 3. MODIFICAR DATO (Update)
			String updateQuery = "replace value of node //empleado[@id='E1']/puesto with 'Directora'";
			session.execute("XQUERY " + updateQuery);
			System.out.println("Puesto actualizado.");
			// 4. VERIFICACIÓN (Consulta)
			String resultado = session.execute("XQUERY //empleado[@id='E1']");
			System.out.println("\n--- Estado actual del XML ---");
			System.out.println(resultado);
			// 5. LIMPIEZA
			session.execute("DROP DB Empresa");
			System.out.println("Colección borrada");
		} catch (java.net.ConnectException e) {
			System.err.println("Error: Servidor apagado. Ejecuta ./basexserver");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private static void borrarDocumento(ClientSession session) {
		// El nombre debe coincidir EXACTAMENTE con cómo aparece en el LIST
		String archivoABorrar = "bibliotecaServer.xml";
		try {
			session.execute("DELETE " + archivoABorrar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Archivo " + archivoABorrar + " eliminado correctamente.");
	}

	private static void mostrarContenidoFicheros(ClientSession session) {
		System.out.println("--- Contenido de todos los XML ---");
		// collection() sin argumentos coge la BD abierta por defecto
		String query = "for $doc in collection('Empresa') return $doc";
		try {
			System.out.println(session.execute("XQUERY " + query));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void mostrarFicheros(ClientSession session) {
		System.out.println("--- Archivos en la Base de Datos ---");
		try {
			System.out.println(session.execute("LIST Empresa"));
		} catch (IOException e) {
			System.out.println("Error al listar las colecciones");
		}
	}

	private static void añadirFicheroDesdeOrdenador(ClientSession session) {
		try {
			// Ruta donde está el archivo en TU ordenador
			File f = new File("biblioteca.xml");
			// Creamos un flujo de lectura desde ese archivo
			FileInputStream archivo = new FileInputStream(f);
			// Lo subimos a BaseX
			// Argumento 1: Nombre con el que se guardará en la BD
			// Argumento 2: El flujo de datos del archivo
			session.add("bibliotecaServer.xml", archivo);
			System.out.println("Archivo subido desde el disco correctamente.");
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Error: No encuentro el archivo en esa ruta.");
		} catch (IOException e) {
			System.out.println("Error al añadir a la colección el fichero");
		}
	}
}