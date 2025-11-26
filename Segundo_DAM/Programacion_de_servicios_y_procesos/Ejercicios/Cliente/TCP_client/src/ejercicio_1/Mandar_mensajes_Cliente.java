package ejercicio_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Mandar_mensajes_Cliente {
	private String serverIP;
	private int serverPort;
	private Socket socket;
	private InputStream input_Stream;
	private OutputStream output_Stream;
	private DataInputStream data_input;
	private DataOutputStream data_out;
	private InputStream input;
	private OutputStream output;

	public Mandar_mensajes_Cliente(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	public void iniciar() throws UnknownHostException, IOException {
		System.out.println(" (Cliente) Estableciendo conexion......");
		socket = new Socket(serverIP, serverPort);
		output_Stream = socket.getOutputStream();
		input_Stream = socket.getInputStream();
		System.out.println(" (Cliente) Conexion establecida");
	}

	public void stop() throws IOException {
		System.out.println(" (Cliente) Cerrando conexiones.....");
		input_Stream.close();
		output_Stream.close();
		socket.close();
		System.out.println(" (Cliente) Conexiones cerradas");
	}

	public void enviar_Mesaje(String mensaje) throws IOException {
		data_out = new DataOutputStream(data_out);
		data_out.writeUTF(mensaje);
	}

	public void recibir_Mensaje() throws IOException {
		data_input = new DataInputStream(data_input);
		String mensaje = data_input.readUTF();
		System.out.println(mensaje);
	}

	public static void main(String[] args) {
		Mandar_mensajes_Cliente cliente = new Mandar_mensajes_Cliente("localhost", 49171);

		try {
			cliente.iniciar();
			cliente.enviar_Mesaje("(Cliente) Hola que tal");
			cliente.recibir_Mensaje();
			cliente.stop();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
