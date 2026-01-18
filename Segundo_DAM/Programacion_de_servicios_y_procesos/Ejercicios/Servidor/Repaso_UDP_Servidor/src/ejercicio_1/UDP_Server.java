package ejercicio_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDP_Server {

	private int serverPort;

	private DatagramSocket socket;

	private DatagramPacket packetEntrada;
	private DatagramPacket packetSalida;

	private byte[] lecturaBytes;
	private byte[] mensajeBytes;

	public UDP_Server(int puerto) {
		// TODO Auto-generated constructor stub
		this.serverPort = puerto;

	}

	public void start() throws SocketException {
		System.out.println("Creando socket del servidor....................");

		socket = new DatagramSocket(serverPort);

		System.out.println("Socket creado en el puerto : " + serverPort);

		System.out.println("Esperando mensajes del cliente..........................");

	}

	public void stop() {
		if (socket != null && !socket.isClosed()) {
			socket.close();

			System.out.println("Socket cerrado");

		}

	}

	public String leer() throws IOException {

		System.out.println("Esperando datagrama............");

		lecturaBytes = new byte[64];

		packetEntrada = new DatagramPacket(lecturaBytes, lecturaBytes.length);

		socket.receive(packetEntrada);

		String mensaje = new String(lecturaBytes, 0, packetEntrada.getLength()).trim();

		System.out.println("Mensaje recibido del cliente: " + mensaje);

		return mensaje;
	}

	public void enviar(String mensaje) throws IOException {

		String respuesta = mensaje.toUpperCase();

		mensajeBytes = respuesta.getBytes();

		packetSalida = new DatagramPacket(mensajeBytes, mensajeBytes.length, packetEntrada.getAddress(),
				packetEntrada.getPort());

		socket.send(packetSalida);

		System.out.println("Respuesta enviada: " + respuesta);
	}

	public static void main(String[] args) {
		UDP_Server server = new UDP_Server(49171);

		try {
			server.start();

			String mensaje = "";

			while (!mensaje.equalsIgnoreCase("*")) {
				mensaje = server.leer();

				if (!mensaje.equalsIgnoreCase("*")) {
					server.enviar(mensaje);

				} else {
					System.out.println("(Servidor): Cliente solicitó desconexión");
				}
			}

			server.stop();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (server != null) {
				server.stop();
			}
		}
	}
}
