package ejercicio_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDP_Client {

	private int serverPort;
	private InetAddress ip;

	private DatagramSocket socket;

	private DatagramPacket packetEntrada;
	private DatagramPacket packetSalida;

	private byte[] lecturaBytes;
	private byte[] mensajeBytes;

	public UDP_Client(String server, int puerto) throws UnknownHostException {

		this.ip = InetAddress.getByName(server);
		this.serverPort = puerto;

	}

	public void start() throws UnknownHostException, SocketException {
		System.out.println("Iniciando la conexion con el servidor.......");

		System.out.println("Creando socket...........");

		socket = new DatagramSocket();
	}

	public void stop() {
		System.out.println("Cerrando socket........................");

		if (socket != null && !socket.isClosed()) {
			socket.close();
			System.out.println("Socket cerrado............");
		}

	}

	public void leer() throws IOException {
		lecturaBytes = new byte[64];

		System.out.println("Esperando respuesta del servidor.............");

		packetEntrada = new DatagramPacket(lecturaBytes, lecturaBytes.length);

		socket.receive(packetEntrada);

		String respuesta = new String(lecturaBytes, 0, packetEntrada.getLength()).trim();

		System.out.println("Mensaje del servidor: "+respuesta);
	}

	public void enviar(String mensaje) throws IOException {

		mensajeBytes = mensaje.getBytes();

		packetSalida = new DatagramPacket(mensajeBytes, mensaje.length(), ip, serverPort);

		socket.send(packetSalida);

		System.out.println("Mensaje enviado: " + mensaje);

	}

	public static void main(String[] args) {
		Scanner sc = null;
		UDP_Client socket = null;

		try {

			sc = new Scanner(System.in);

			socket = new UDP_Client("localhost", 49171);

			String texto = "";

			socket.start();

			System.out.println("Cliente iniciado. Escribe '*' para salir.");

			while (!texto.equalsIgnoreCase("*")) {
				System.out.println("\nEscribe un mensaje: ");
				texto = sc.nextLine();

				socket.enviar(texto);

				if (texto.equalsIgnoreCase("*")) {
					break;
				}

			}

			System.out.println("Saliendo...");
			socket.stop();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (socket != null) {
				socket.stop();
			}

		}

	}
}
