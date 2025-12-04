package ejercicio_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Mandar_Mensajes_Cliente {
	private InetAddress serverIP;
	private int serverPort;

	private Socket socket;

	private InputStream input_stream;
	private OutputStream output_stream;

	private DataInputStream data_input;
	private DataOutputStream data_output;

	public Mandar_Mensajes_Cliente(String serverIP, int serverPort) throws UnknownHostException {

		InetAddress ip = InetAddress.getByName(serverIP);
		this.serverIP = ip;
		this.serverPort = serverPort;
	}

	public void iniciar() throws UnknownHostException, IOException {
		System.out.println(" (Cliente) Estableciendo la conexion .............");

		socket = new Socket(serverIP, serverPort);

		output_stream = socket.getOutputStream();
		input_stream = socket.getInputStream();

		data_input = new DataInputStream(input_stream);
		data_output = new DataOutputStream(output_stream);

		System.out.println(" (Cliente) Conexion establecida");

	}

	public void stop() throws IOException {
		System.out.println(" (Cliente) Cerrando las conexiones......");

		input_stream.close();
		output_stream.close();
		data_input.close();
		data_output.close();
		socket.close();
		System.out.println(" (Cliente) Conexiones cerradas");
	}

	public void mandarMensaje(int mensaje) throws IOException {
		data_output.writeInt(mensaje);
	}

	public void recibirMensaje() throws IOException {
		int mensaje = data_input.readInt();
		System.out.println("El servidor dice: " + mensaje);
	}

	public static void main(String[] args) {

		try {
			Mandar_Mensajes_Cliente c = new Mandar_Mensajes_Cliente("localhost", 49171);
			Scanner sc = new Scanner(System.in);
			// Establece la conexión
			c.iniciar();

			int variableString=10;

			while (variableString != 0) {

				System.out.println("Escribe un mensaje para el servidor:");

				variableString = sc.nextInt(); // Lee lo que escribe el usuario

				c.mandarMensaje(variableString); // Envía el mensaje al servidor

				// Solo espera respuesta si no se escribió "fin"
				if (variableString != 0) {
					c.recibirMensaje(); // Recibe y muestra la respuesta del servidor
				}
			}

			// Cierra la conexión y libera recursos
			c.stop();
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
