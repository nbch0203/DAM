package ejemplo_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ServidorMulticastUDP {
	
	private final int PUERTO = 5007;//El puerto debe ser el mismo en cliente-servidor
	private final String IP_GRUPO = "224.1.1.1";
	private final String IP_INTERFAZ_LOCAL = "192.168.56.1"; // Indica la IP de tu red (o subred)
	
	private InetAddress grupo;
	private MulticastSocket multicastSocket;
	private NetworkInterface interfaz;

	public void arrancar() {
	    try {
	        // 1. Obtener la NetworkInterface a partir de la dirección IP local
	        InetAddress direccionLocal = InetAddress.getByName(IP_INTERFAZ_LOCAL);
	        interfaz = NetworkInterface.getByInetAddress(direccionLocal);

	        // 2. Inicializar el Socket y el Grupo
	        multicastSocket = new MulticastSocket(PUERTO); 
	        grupo = InetAddress.getByName(IP_GRUPO);
	        
	        // 3. Configurar la Interfaz de ENVÍO 
	        multicastSocket.setNetworkInterface(interfaz);
	        
	        System.out.println("Servidor arrancado. Grupo: " + grupo.getHostAddress() + ", Puerto: " + PUERTO);
	        System.out.println("Usando interfaz de ENVÍO: " + interfaz.getDisplayName() + " (" + direccionLocal.getHostAddress() + ")");
	        
	    } catch (IOException e) {
	        System.err.println("Error al arrancar el servidor: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	public void parar() {
		try {
			if (multicastSocket != null && !multicastSocket.isClosed()) {
				multicastSocket.close();
				System.out.println("Servidor UDP detenido y socket cerrado.");
			}
		} catch (Exception e) {
			System.err.println("Error al detener el servidor: " + e.getMessage());
		}
	}


	public void enviarMensajes() {
		try {
			String[] mensajes = { 
				"Aviso 1: Bienvenidos al servidor multicast",
				"Aviso 2: Recordad que hoy hay práctica de multicast", 
				"FIN"
			};

			for (String msg : mensajes) {
				byte[] datos = msg.getBytes();
				DatagramPacket paquete = new DatagramPacket(datos, datos.length, grupo, PUERTO);

				multicastSocket.send(paquete);
				System.out.println("Mensaje multicast enviado: " + msg);

			}
			

		} catch (Exception e) {
			System.err.println("Error al enviar mensajes: " + e.getMessage());
			e.printStackTrace();
		} finally {
			parar();
		}
	}

	public static void main(String[] args) {
		ServidorMulticastUDP servidor = new ServidorMulticastUDP();
		servidor.arrancar();
		servidor.enviarMensajes();
	}
}