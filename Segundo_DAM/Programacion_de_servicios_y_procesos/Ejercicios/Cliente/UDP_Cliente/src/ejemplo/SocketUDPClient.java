package ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketUDPClient {

	public static void main(String[] args) {
		String strMensaje = "Mensaje enviado desde el cliente";
		DatagramSocket socketUDP;

		try {
			System.out.println("(Cliente) : Estableciendo parámetros de conexión...");
			// se obtiene la dirección del servidor con getByName de InetAddess
			InetAddress hostServidor = InetAddress.getByName("localhost");
			int puertoservidor = 49171;
			System.out.println("(Cliente): creando socket...");
			// se crea el cocket UDP mediante un DatagramSocket
			socketUDP = new DatagramSocket();
			System.out.println("(Cliente): Enviando datagrama...");
			byte[] mensaje = strMensaje.getBytes();
			// se genera el datagrama mediante un Datagramapacket con el mensaje a enviar
			DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoservidor);
			// se envía el mensaje
			socketUDP.send(peticion);
			System.out.println("(Cliente): Recibiendo datagrama.");
			byte[] buffer = new byte[64];
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, hostServidor, puertoservidor);
			// se recibe la respuesta
			socketUDP.receive(respuesta);
			System.out.println("(Cliente): Mensaje recibido: " + new String(buffer));
			System.out.println("(Cliente): Cerrando socket...");
			// se cierra el socket
			socketUDP.close();
			System.out.println("(Cliente): Socket cerrado");
		} catch (SocketException e) {
			e.printStackTrace();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
