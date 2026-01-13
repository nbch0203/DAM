package ejercicio_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorClientes implements Runnable {
	private Socket socket;

	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	private int numeroCliente;

	public ManejadorClientes(Socket socket, int numeroCliente) {
		this.socket = socket;
		this.numeroCliente = numeroCliente;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	@Override
	public void run() {

		try {
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());

			dataOutput.writeInt(getNumeroCliente());
			System.out.println("(Servidor) Número " + getNumeroCliente() + " enviado al cliente");

			dataInput.close();
			dataOutput.close();
			socket.close();

			System.out.println("(Servidor) Conexión cerrada con cliente " + numeroCliente);

		} catch (IOException e) {
			System.err.println("Error al manejar cliente " + numeroCliente + ": " + e.getMessage());
		}
	}

}
