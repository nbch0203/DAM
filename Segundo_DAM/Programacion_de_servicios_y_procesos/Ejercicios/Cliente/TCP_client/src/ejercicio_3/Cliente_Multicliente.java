package ejercicio_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente_Multicliente {
	private InetAddress serverIP;
	private int serverPort;
	private Socket socket;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	public Cliente_Multicliente(String serverIP, int serverPort) throws UnknownHostException {
		this.serverIP = InetAddress.getByName(serverIP);
		this.serverPort = serverPort;
	}

	public void conectar() throws IOException {
		System.out.println("(Cliente) Conectando al servidor...");

		socket = new Socket(serverIP, serverPort);

		dataInput = new DataInputStream(socket.getInputStream());
		dataOutput = new DataOutputStream(socket.getOutputStream());

		System.out.println("(Cliente) Conectado al servidor");
	}

	public void recibirNumeroCliente() throws IOException {
		int numeroCliente = dataInput.readInt();
		System.out.println("Cliente número: " + numeroCliente);
	}

	public void cerrar() throws IOException {
		dataInput.close();
		dataOutput.close();
		socket.close();
		System.out.println("(Cliente) Conexión cerrada");
		
	}

	public static void main(String[] args) {
		try {
			Cliente_Multicliente cliente = new Cliente_Multicliente("localhost", 49172);

			cliente.conectar();
			cliente.recibirNumeroCliente();
			cliente.cerrar();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}