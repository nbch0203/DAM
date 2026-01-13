package ejercicio_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_Client {
	private InetAddress serverIP;
	private int serverPort;

	private Socket socket;

	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	public TCP_Client(String serverIP, int serverPort) throws UnknownHostException {
		InetAddress ip = InetAddress.getByName(serverIP);
		this.serverIP = ip;
		this.serverPort = serverPort;
	}

	public void start() throws IOException {
		System.out.println("(Cliente) Conectando al servidor...");
		socket = new Socket(serverIP, serverPort);

		dataInput = new DataInputStream(socket.getInputStream());
		dataOutput = new DataOutputStream(socket.getOutputStream());

		System.out.println("(Cliente) Conectado al servidor");

	}

	public void stop() throws IOException {
		System.out.println("(Cliente) Cerrando conexiones...........");
		dataInput.close();
		dataOutput.close();

		socket.close();

		System.out.println("(Cliente) Conexión cerrada");

	}

	public void leer() throws IOException {
		int numeroCliente = dataInput.readInt();
		System.out.println("Cliente numero: " + numeroCliente);
	}

	public static void main(String[] args) {
		try {
			TCP_Client client = new TCP_Client("127.0.0.1", 49172);

			client.start();
			client.leer();
			client.stop();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
