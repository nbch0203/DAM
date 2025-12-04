package ejercicio_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Mandar_Mensaje_Server {
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

	public Mandar_Mensaje_Server(int puerto) throws IOException {
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
	public void enviar_Mensaje(int valor) throws IOException {
	    System.out.println(" (Servidor) Calculando cuadrado...");
	    int cuadrado = valor * valor;
	    System.out.println(" (Servidor) Cuadrado calculado: " + cuadrado);
	    data_output.writeInt(cuadrado);
	}

	// Lee un mensaje del cliente conectado
	public int leer_mensaje() throws IOException {
		int mensaje = data_input.readInt(); // BLOQUEA hasta recibir un mensaje
		return mensaje;
	}

	public static void main(String[] args) {
		try {
			// Crea el servidor en el puerto 49171
			Mandar_Mensaje_Server server = new Mandar_Mensaje_Server(49171);
			Scanner sc = new Scanner(System.in);

			// Espera a que se conecte un cliente (bloquea aquí hasta que llegue uno)
			server.iniciar();

			int mensajeRecibido = 1;

			// Bucle principal: recibe mensaje del cliente → responde → repite
			while (mensajeRecibido != 0) {

				// PRIMERO: recibe mensaje del cliente (bloquea hasta que llegue)
				mensajeRecibido = server.leer_mensaje();
				System.out.println("Cliente dice: " + mensajeRecibido);
				
				if (mensajeRecibido!=0) {
					server.enviar_Mensaje(mensajeRecibido);
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