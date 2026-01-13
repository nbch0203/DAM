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

public class TCP_Client {
	private int serverPort;
	private InetAddress serverIP;

	private Socket socket;

	private OutputStream output;
	private InputStream input;

	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	public TCP_Client(String serverIp, int serverPort) throws UnknownHostException {
		InetAddress ip = InetAddress.getByName(serverIp);
		this.serverIP = ip;
		this.serverPort = serverPort;
	}

	public void start() throws IOException {
		System.out.println(" (Cliente) Estableciendo la conexion .............");

		socket = new Socket(serverIP, serverPort);

		input = socket.getInputStream();
		output = socket.getOutputStream();

		dataInput = new DataInputStream(input);
		dataOutput = new DataOutputStream(output);

		System.out.println(" (Cliente) Conexion establecida");
	}

	public void stop() throws IOException {
		System.out.println(" (Cliente) Cerrando las conexiones......");

		input.close();
		output.close();

		dataInput.close();
		dataOutput.close();

		socket.close();

		System.out.println(" (Cliente) Conexiones cerradas");

	}

	public void leer() throws IOException {
		int valor = dataInput.readInt();
		System.out.println("El mensaje del servidor es: " + valor);

	}

	public void enviar(int mensaje) throws IOException {
		if (mensaje > 0) {
			dataOutput.writeInt(mensaje);

		} else {
			System.out.println("NO se puede enviar ese mensaje");
			return;
		}
	}

	public static void main(String[] args) {
		try {
			TCP_Client c = new TCP_Client("127.0.0.1", 49172);
			Scanner sc = new Scanner(System.in);

			c.start();

			int variable = 1;

			while (variable != 0) {

				System.out.println("Escribe un mensaje para el servidor:");

				variable = sc.nextInt();

				c.enviar(variable);

				if (variable != 0) {
					c.leer();
				}
			}

			c.stop();
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
