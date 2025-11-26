package ejercicio_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Mandar_mensajes_Servidor {
	// ServerSocket: escucha conexiones entrantes en un puerto
	private ServerSocket serversocket;

	// Socket: representa la conexión específica con UN cliente
	private Socket socket;

	// Streams básicos para entrada/salida de bytes
	private InputStream input;
	private OutputStream output;

	// Streams de datos para enviar/recibir tipos primitivos (como String)
	private DataOutputStream data_output;
	private DataInputStream data_input;

	public Mandar_mensajes_Servidor(int puerto) throws IOException {
		serversocket = new ServerSocket(puerto); 
	}

	// Espera a que un cliente se conecte
	public void iniciar() throws IOException {
		System.out.println(" (Servidor) Esperando conexiones...");

		// accept() BLOQUEA el programa hasta que un cliente se conecte
		socket = serversocket.accept();

		// Una vez conectado, obtiene los streams del socket
		input = socket.getInputStream();
		output = socket.getOutputStream();

		// Envuelve los streams en DataStreams para manejar Strings fácilmente
		data_input = new DataInputStream(input);
		data_output = new DataOutputStream(output);

		System.out.println(" (Servidor) Conexión establecida.");
	}

	// Cierra todos los recursos
	public void stop() throws IOException {
		System.out.println(" (Servidor) Cerrando conexiones…");
		socket.close();
		input.close();
		serversocket.close(); 
		output.close();
		data_input.close();
		data_output.close();
		System.out.println(" (Servidor) Conexiones cerradas…");
	}

	// Envía un mensaje al cliente conectado
	public void enviar_Mensaje(String mensaje) throws IOException {
		System.out.println(" (Servidor) Enviando mensaje...");
		data_output.writeUTF(mensaje);
	}

	// Lee un mensaje del cliente conectado
	public String leer_mensaje() throws IOException {
		String mensaje = data_input.readUTF(); // BLOQUEA hasta recibir un mensaje
		return mensaje;
	}

	public static void main(String[] args) {
		try {
			// Crea el servidor en el puerto 49171
			Mandar_mensajes_Servidor server = new Mandar_mensajes_Servidor(49171);
			Scanner sc = new Scanner(System.in);

			// Espera a que se conecte un cliente (bloquea aquí hasta que llegue uno)
			server.iniciar();

			String mensajeRecibido = "";

			// Bucle principal: recibe mensaje del cliente → responde → repite
			while (!mensajeRecibido.equalsIgnoreCase("fin")) {

				// PRIMERO: recibe mensaje del cliente (bloquea hasta que llegue)
				mensajeRecibido = server.leer_mensaje();
				System.out.println("Cliente dice: " + mensajeRecibido);

				// Si el cliente no escribió "fin", el servidor responde
				if (!mensajeRecibido.equalsIgnoreCase("fin")) {
					System.out.println("Escribe un mensaje para el cliente:");
					String respuesta = sc.nextLine(); // Lee la respuesta del usuario
					server.enviar_Mensaje(respuesta); // Envía la respuesta al cliente
				}
			}

			// Cierra todo
			server.stop();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}