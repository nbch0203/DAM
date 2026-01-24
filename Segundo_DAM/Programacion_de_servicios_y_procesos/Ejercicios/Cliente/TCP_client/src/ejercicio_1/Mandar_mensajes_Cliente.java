package ejercicio_1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Mandar_mensajes_Cliente {
	// Datos de conexión
	private String serverIP;
	private int serverPort;
	
	// Socket principal para la conexión
	private Socket socket;
	
	// Streams básicos para entrada/salida de bytes
	private InputStream input_Stream;
	private OutputStream output_Stream;
	
	// Streams de datos para enviar/recibir tipos primitivos (como String)
	private DataInputStream data_input;
	private DataOutputStream data_out;

	// Constructor: guarda IP y puerto del servidor
	public Mandar_mensajes_Cliente(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	// Establece la conexión con el servidor
	public void iniciar() throws UnknownHostException, IOException {
		System.out.println(" (Cliente) Estableciendo conexion......");
		
		// Crea el socket y se conecta al servidor
		socket = new Socket(serverIP, serverPort);
		
		// Obtiene los streams básicos del socket
		output_Stream = socket.getOutputStream();
		input_Stream = socket.getInputStream();

		// Envuelve los streams básicos en DataStreams para poder enviar/recibir
		// Strings fácilmente
		data_input = new DataInputStream(input_Stream);
		data_out = new DataOutputStream(output_Stream);
		
		System.out.println(" (Cliente) Conexion establecida");
	}

	// Cierra todos los recursos
	public void stop() throws IOException {
		System.out.println(" (Cliente) Cerrando conexiones.....");
		input_Stream.close();
		output_Stream.close();
		socket.close();
		System.out.println(" (Cliente) Conexiones cerradas");
	}

	// Envía un mensaje al servidor
	public void enviar_Mesaje(String mensaje) throws IOException {
		data_out.writeUTF(mensaje);  // writeUTF envía el String con su longitud incluida
	}

	// Recibe un mensaje del servidor y lo imprime
	public void recibir_Mensaje() throws IOException {
		String mensaje = data_input.readUTF();  // readUTF lee un String enviado con writeUTF
		System.out.println(mensaje);
	}

	public static void main(String[] args) {
		// Crea el cliente apuntando a localhost puerto 49171
		Mandar_mensajes_Cliente cliente = new Mandar_mensajes_Cliente("localhost", 49171);
		Scanner sc = new Scanner(System.in);

		try {
			// Establece la conexión
			cliente.iniciar();

			String variableString = "";
			
			// Bucle principal: se repite hasta que el usuario escriba "fin"
			while (!variableString.equalsIgnoreCase("fin")) {
				
				System.out.println("Escribe un mensaje para el servidor:");
				
				variableString = sc.nextLine();  // Lee lo que escribe el usuario
				
				cliente.enviar_Mesaje(variableString);  // Envía el mensaje al servidor

				// Solo espera respuesta si no se escribió "fin"
				if (!variableString.equalsIgnoreCase("fin")) {
					cliente.recibir_Mensaje();  // Recibe y muestra la respuesta del servidor
				}
			}
			
			// Cierra la conexión y libera recursos
			cliente.stop();
			sc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}