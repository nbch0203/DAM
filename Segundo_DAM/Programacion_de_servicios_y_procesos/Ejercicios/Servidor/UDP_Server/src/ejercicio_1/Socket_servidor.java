package ejercicio_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Socket_servidor {
	private DatagramSocket socket;
	private DatagramPacket packet_entrada;
	private DatagramPacket packet_salida;
	private byte[] lectura;

	public void iniciar() throws SocketException {
		System.out.println("(Servidor): creando socket...");
		socket = new DatagramSocket(49171);
		System.out.println("(Servidor): Socket creado en el puerto 49171");
		System.out.println("(Servidor): Esperando mensajes de clientes...");
	}

	public void cerrar() {
		if (socket != null && !socket.isClosed()) {
			socket.close();
			System.out.println("(Servidor): Socket cerrado");
		}
	}

	public String leer() throws IOException {
		System.out.println("(Servidor): Esperando datagrama...");
		
		lectura = new byte[64];
		packet_entrada = new DatagramPacket(lectura, lectura.length);
		socket.receive(packet_entrada);
		
		String mensaje = new String(lectura, 0, packet_entrada.getLength()).trim();
		System.out.println("(Servidor): Mensaje recibido del cliente: " + mensaje);
		return mensaje;
	}

	public void enviarRespuesta(String mensajeRecibido) throws IOException {
		// Convertir el mensaje a mayúsculas
		String respuesta = mensajeRecibido.toUpperCase();
		
		byte[] mensajeBytes = respuesta.getBytes();
		packet_salida = new DatagramPacket(
			mensajeBytes, 
			mensajeBytes.length, 
			packet_entrada.getAddress(),
			packet_entrada.getPort()
		);
		
		socket.send(packet_salida);
		System.out.println("(Servidor): Respuesta enviada: " + respuesta);
	}

	public static void main(String[] args) {
		Socket_servidor server = null;
		
		try {
			server = new Socket_servidor();
			server.iniciar();

			String mensajeCliente = "";
			
			// El servidor sigue ejecutándose hasta recibir "*"
			while (!mensajeCliente.equalsIgnoreCase("*")) {
				mensajeCliente = server.leer();
				
				if (!mensajeCliente.equalsIgnoreCase("*")) {
					server.enviarRespuesta(mensajeCliente);
				} else {
					System.out.println("(Servidor): Cliente solicitó desconexión");
				}
			}

			server.cerrar();

		} catch (SocketException e) {
			System.err.println("Error de socket: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error de I/O: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (server != null) {
				server.cerrar();
			}
		}
	}
}