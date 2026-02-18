package ejemplo;

import org.basex.api.client.ClientSession;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;

public class Ejemplo {
	public static void main(String[] args) {
		// Configuración
		String host = "localhost";
		int puerto = 1984;
		String usuario = "admin";
		String clave = "admin";
		String baseDatos = "clase1";
		System.out.println("Conectando...");
		// Usamos try-with-resources para que cierre la conexión solo al acabar
		try (ClientSession session = new ClientSession(host, puerto, usuario, clave)) {
			System.out.println("¡CONECTADO AL SERVIDOR!");
			// 1. Abrimos la base de datos
			session.execute("OPEN " + baseDatos);
			System.out.println("Base de datos '" + baseDatos + "' abierta.");

// 2. Consulta XQuery
			System.out.println("Ejecutando consulta...");
			// Aquí pones tu consulta XQuery tal cual
			String query = "for $l in //libro return $l/titulo/text()";

// Ejecutar y obtener respuesta como String
			String resultado = session.execute("XQUERY " + query);
			System.out.println("\n--- RESULTADOS ---");
			System.out.println(resultado);
			System.out.println("------------------");
		} catch (java.net.ConnectException e) {
			System.err.println("ERROR DE CONEXIÓN: El servidor no responde.");
			System.err.println("Asegúrate de tener abierta la ventana negra de'./basexserver'.");
		} catch (Exception e) {
			System.err.println(" Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}