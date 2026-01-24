package ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SocketUDPServer {
	public static void main(String[] args) {
		DatagramSocket socket;
		try {
			System.out.println("(Servidor): creando socket...");
			// datagramasocket asociado al puerto
			socket = new DatagramSocket(49171);
			System.out.println("(Servidor): Recibiendo datagrama...");
			// array de bytes para almacenar el mensaje y el datagrama pasándole el array
			byte[] bufferLectura = new byte[64];
			DatagramPacket datagramaEntrada = new DatagramPacket(bufferLectura, bufferLectura.length);
			// el socket recibe el datagrama, el servidor queda a la espera
			socket.receive(datagramaEntrada);

			System.out.println("(Servidor): Mensaje recibido: " + new String(bufferLectura));
			System.out.println("(Servidor): Enviando datagrama...");

			byte[] mensajeEnviado = new String("Mensaje enviado desde el servidor").getBytes();
			DatagramPacket datagramaSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length,
					datagramaEntrada.getAddress(), datagramaEntrada.getPort());

			// envía el datagrama de respuesta cuando se ha terminado la comunicación
			socket.send(datagramaSalida);
			System.out.println("(Servidor): Cerrando socket...");
			// se cierra el socket
			socket.close();
			System.out.println("(Servidor): socket cerrado.");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
