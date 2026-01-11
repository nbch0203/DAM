package ejemplo_multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.io.IOException;

public class ClienteMulticastUDP {
	
	private final int PUERTO = 5007;
	private final String IP_GRUPO = "224.1.1.1";
	// Si el cliente está en otra máquina, esta IP debe ser la IP de su 
	// interfaz en la misma subred.
	private final String IP_INTERFAZ_LOCAL = "192.168.56.1"; 
	
	private MulticastSocket socket;
	private InetAddress grupo;
	private byte[] buffer = new byte[1024];
	private NetworkInterface interfaz;

	public void arrancar() {
	    try {
	        // 1. Obtener la NetworkInterface a partir de la dirección IP local
	        InetAddress direccionLocal = InetAddress.getByName(IP_INTERFAZ_LOCAL);
	        interfaz = NetworkInterface.getByInetAddress(direccionLocal);

	        // 2. Inicializar el Socket y el Grupo
	        socket = new MulticastSocket(PUERTO);
	        grupo = InetAddress.getByName(IP_GRUPO);
	        
	        // 3. Unirse al grupo multicast usando la interfaz específica. A partir de Java14 joinGroup(InetAddress) está en desuso
	        socket.joinGroup(new InetSocketAddress(grupo, PUERTO), interfaz);

	        System.out.println("Cliente arrancado. Grupo: " + grupo.getHostAddress() + ", Puerto: " + PUERTO);
	        System.out.println("Usando interfaz para RECIBIR: " + interfaz.getDisplayName() + " (" + direccionLocal.getHostAddress() + ")");
	        
	    } catch (IOException e) {
	        System.err.println("Error al arrancar el cliente: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	public void parar() {
		try {
			if (socket != null && !socket.isClosed()) {
				// Dejamos el grupo (con version de interfaz)
				socket.leaveGroup(new InetSocketAddress(grupo, PUERTO), interfaz);
				socket.close();
				System.out.println("Cliente UDP salió del grupo multicast y cerró el socket.");
			}
		} catch (IOException e) {
			System.err.println("Error al detener el cliente: " + e.getMessage());
		}
	}


	public void recibirMensajes() {
		try {
			boolean activo = true;
			while (activo) {
				DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
				
				// El socket se bloquea esperando un paquete
				socket.receive(paqueteRecibido);

				String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
				System.out.println("--> Mensaje recibido por multicast: " + mensaje);

				if (mensaje.trim().equalsIgnoreCase("FIN")) {
					activo = false;
					System.out.println("Recibido mensaje 'FIN'. Finalizando recepción...");
					// Parar inmediatamente al recibir FIN
					parar(); 
				}
			}
		} catch (IOException e) {
			// Si la excepción ocurre porque se llamó a parar() (cierre limpio), se ignora.
			if (!socket.isClosed()) {
				System.err.println("Error en la recepción de mensajes: " + e.getMessage());
				e.printStackTrace();
			} else {
				System.out.println("Fin de la escucha: Socket cerrado.");
			}
		} 
	}

	public static void main(String[] args) {
		ClienteMulticastUDP cliente = new ClienteMulticastUDP();
		cliente.arrancar();
		cliente.recibirMensajes();
	}
}