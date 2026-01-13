package ejercicio_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
	private ServerSocket server;

	private int maximoClientes = 3;

	public TCP_Server(int puerto, int maximoClientes) throws IOException {
		server = new ServerSocket(puerto);
		this.maximoClientes = maximoClientes;
	}

	public void start() throws IOException {
		System.out.println("(Servidor) Conectando con clientes...........");
		int numeroCliente = 1;
		while (numeroCliente <= maximoClientes) {

			System.out.println("(Servidor) Esperando cliente " + numeroCliente + "...");

			Socket clienteSocket = server.accept();
			System.out.println("(Servidor) Cliente " + numeroCliente + " conectado");

			Thread hiloCliente = new Thread(new ManejadorClientes(clienteSocket, numeroCliente));

			hiloCliente.start();

			numeroCliente++;

			System.out.println("(Servidor) Conexion realizada con exito con el cliente: " + numeroCliente);
		}

	}

	public void stop() throws IOException {
		System.out.println("(Servidor) Cerrando conexiones.........");

		server.close();
		System.out.println("(Servidor) Conexiones cerradas con exito");
	}

	public static void main(String[] args) {
		try {
			int clientes = Integer.parseInt(args[0]);
			TCP_Server server = new TCP_Server(49172, clientes);
			server.start();

			Thread.sleep(10000);
			server.stop();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
