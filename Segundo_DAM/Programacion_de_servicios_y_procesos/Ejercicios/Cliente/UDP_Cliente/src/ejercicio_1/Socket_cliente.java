package ejercicio_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Socket_cliente {

	private Scanner sc;
	private int serverPort;
	private InetAddress inetaddress;
	private DatagramSocket socketUDP;
	private DatagramPacket peticion;

	public void iniciar() throws UnknownHostException, SocketException {
		System.out.println("(Cliente) Estableciendo conexion.....");
		inetaddress = InetAddress.getByName("localhost");
		serverPort = 49171;
		System.out.println("(Cliente) creando socket.......");
		
		// Creamos el socket sin puerto específico para que el sistema asigne uno automáticamente
		socketUDP = new DatagramSocket();
	}

	public void EnviarMensaje(String mensaje) throws IOException {
		byte[] mensajes = mensaje.getBytes();
		peticion = new DatagramPacket(mensajes, mensajes.length, inetaddress, serverPort);
		socketUDP.send(peticion);
		System.out.println("(Cliente): Mensaje enviado: " + mensaje);
	}

	public void LeerMensaje() throws IOException {
		System.out.println("(Cliente): Esperando respuesta del servidor...");
		byte[] buffer = new byte[64];
		peticion = new DatagramPacket(buffer, buffer.length);
		socketUDP.receive(peticion);
		String respuesta = new String(buffer, 0, peticion.getLength()).trim();
		System.out.println("(Cliente): Mensaje recibido del servidor: " + respuesta);
	}

	public void cerrar() {
		System.out.println("(Cliente): Cerrando socket...");
		if (socketUDP != null && !socketUDP.isClosed()) {
			socketUDP.close();
			System.out.println("(Cliente): Socket cerrado");
		}
		if (sc != null) {
			sc.close();
		}
	}

	public static void main(String[] args) {
		Scanner sc = null;
		Socket_cliente socket = null;
		
		try {
			sc = new Scanner(System.in);
			socket = new Socket_cliente();
			socket.sc = sc;
			
			String texto = "";
			socket.iniciar();
			
			System.out.println("Cliente iniciado. Escribe '*' para salir.");
			
			while (!texto.equalsIgnoreCase("*")) {
				System.out.println("\nEscriba un mensaje:");
				texto = sc.nextLine();
				
				if (!texto.equalsIgnoreCase("*")) {
					socket.EnviarMensaje(texto);
					socket.LeerMensaje();
				}
			}
			
			System.out.println("Saliendo...");
			socket.cerrar();

		} catch (SocketException e) {
			System.err.println("Error de socket: " + e.getMessage());
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.err.println("Host desconocido: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error de I/O: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.cerrar();
			}
		}
	}
}