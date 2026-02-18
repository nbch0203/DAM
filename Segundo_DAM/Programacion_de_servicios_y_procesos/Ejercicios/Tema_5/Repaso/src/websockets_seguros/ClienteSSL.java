package websockets_seguros;

import java.io.*;
import javax.net.ssl.*;

public class ClienteSSL {
	public static void main(String[] args) {

		// Indicamos que confiamos en el certificado que está en almacen.jks
//		System.setProperty("javax.net.ssl.trustStore", "almacen.jks");

		/*
		 * Si el certificado del servidor no esta en la lista mostrara (pero si tiene la
		 * contraseña), una excepcion de: java.security.NoSuchAlgorithmException: Error
		 * constructing implementation (algorithm: Default, provider: SunJSSE, class:
		 * sun.security.ssl.SSLContextImpl$DefaultSSLContext) java.net.SocketException:
		 * java.security.NoSuchAlgorithmException:
		 * 
		 * En caso de que no tenga ni una ni otra cosa:
		 * javax.net.ssl.SSLHandshakeException: Received fatal alert:
		 * certificate_unknown
		 * 
		 * 
		 */

		// "123456": Es la contraseña necesaria para ABRIR el fichero de confianza que
		// pusimos en el comando del PASO1
		// Funciona como el PIN de una tarjeta: sin ella, no podemos leer los
		// certificados de dentro.
//		System.setProperty("javax.net.ssl.trustStorePassword", "123456");

		String host = "localhost";
		int puerto = 5555;

		try {
			System.out.println("Iniciando handshake con el servidor...");

			// --- 2. CREACIÓN DEL SOCKET SEGURO ---
			// Igual que en el servidor, usamos una Factory para crear el socket cliente.
			SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();

			// Al ejecutar esta línea, el cliente conecta y valida el certificado del
			// servidor.
			// Si el certificado es falso o no confiamos en él, saltará una Excepción aquí
			// mismo.
			SSLSocket cliente = (SSLSocket) sfact.createSocket(host, puerto);

			// --- 3. FLUJO DE DATOS ---
			// A partir de aquí, es programar sockets normales. La encriptación es invisible
			// para nosotros.
			DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

			// Enviar mensaje
			System.out.println("Enviando datos cifrados...");
			flujoSalida.writeUTF("Hola Servidor, esto viaja cifrado con TLS.");

			// Recibir respuesta
			String respuesta = flujoEntrada.readUTF();
			System.out.println("Servidor responde: " + respuesta);

			// Cerrar conexión
			cliente.close();

		} catch (Exception e) {
			System.out.println("Error en la conexión: " + e.getMessage());
			// Es vital ver el trace para saber si es fallo de red o fallo de certificado
			e.printStackTrace();
		}
	}
}