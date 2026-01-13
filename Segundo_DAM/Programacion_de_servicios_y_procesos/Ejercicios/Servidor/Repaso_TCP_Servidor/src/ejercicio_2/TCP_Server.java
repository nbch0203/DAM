package ejercicio_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Server {
	private int puerto;

	private ServerSocket server;
	private Socket socket;

	private InputStream input;
	private OutputStream output;

	private DataOutputStream dataOutput;
	private DataInputStream dataInput;

	public TCP_Server(int puerto) throws IOException {
		server = new ServerSocket(puerto);
	}

	public void start() throws IOException {
		System.out.println(" (Servidor) Esperando conexiones...");

		socket = server.accept();

		// Una vez conectado, obtiene los streams del socket
		input = socket.getInputStream();
		output = socket.getOutputStream();

		// Envuelve los streams en DataStreams para manejar Strings fácilmente
		dataInput = new DataInputStream(input);
		dataOutput = new DataOutputStream(output);

		System.out.println(" (Servidor) Conexión establecida.");
	}

	public void stop() throws IOException {
		System.out.println(" (Servidor) Cerrando conexiones");

		socket.close();
		server.close();

		input.close();
		output.close();

		dataInput.close();
		dataOutput.close();

		System.out.println(" (Servidor) Cerrado con exito");

	}

	public int leer() throws IOException {
		int valor = dataInput.readInt();
		System.out.println("Valor enviado por el cliente: " + valor);
		int cuadrado = valor * valor;
		return cuadrado;
	}

	public void enviar(int mensaje) throws IOException {
		System.out.println("Valor al cuadrado enviado: " + mensaje);
		dataOutput.writeInt(mensaje);

	}

	public static void main(String[] args) {
		try {
			TCP_Server server = new TCP_Server(49172);
			Scanner sc = new Scanner(System.in);

			server.start();

			int mensajeRecibido = 1;

			while (mensajeRecibido != 0) {

				mensajeRecibido = server.leer();
				System.out.println("Cliente dice: " + mensajeRecibido);

				if (mensajeRecibido != 0) {
					server.enviar(mensajeRecibido);
				}
			}

			server.stop();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
