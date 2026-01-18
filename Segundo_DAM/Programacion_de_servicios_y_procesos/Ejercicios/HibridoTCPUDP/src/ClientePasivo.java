import java.io.IOException;
import java.net.*;

public class ClientePasivo {

	// --- CONFIGURACIÓN DE RED ---
	private static final String IP_MULTICAST = "235.10.10.1";
	private static final int PUERTO_MULTICAST = 8080;
	private static final String IP_LOCAL = "127.0.0.1"; // <-- CAMBIAR SI ES EN VM

	public static void main(String[] args) {
		System.out.println("CLIENTE PASIVO: Iniciando...");
		iniciarClientePasivo();
	}

	private static void iniciarClientePasivo() {
		MulticastSocket ms = null;
		try {
			// 1. ARRANCAR: Configurar y unirse al grupo
			ms = configurarYUnirMulticast();

			System.out.println("CLIENTE PASIVO: Escuchando anuncios en " + IP_MULTICAST + ":" + PUERTO_MULTICAST);

			// 2. PROCESAR: Entrar en el bucle de recepción
			ejecutarBucleRecepcion(ms);

		} catch (Exception e) {
			System.err.println("Error fatal en el cliente pasivo: " + e.getMessage());
		} finally {
			// 3. PARAR: Cerrar el MulticastSocket
			pararMulticastSocket(ms);
		}
	}

	// Configura y une el socket al grupo Multicast
	private static MulticastSocket configurarYUnirMulticast() throws IOException {
		MulticastSocket ms = new MulticastSocket(PUERTO_MULTICAST);
		InetAddress ipLocal = InetAddress.getByName(IP_LOCAL);
		InetAddress grupo = InetAddress.getByName(IP_MULTICAST);
		NetworkInterface netIf = NetworkInterface.getByInetAddress(ipLocal);

		if (netIf == null) {
			throw new IOException("Interfaz de red no encontrada para " + IP_LOCAL);
		}

		// Unirse al grupo en la interfaz específica
		ms.joinGroup(new InetSocketAddress(grupo, PUERTO_MULTICAST), netIf);
		return ms;
	}

	// Bucle de recepción de anuncios
	private static void ejecutarBucleRecepcion(MulticastSocket ms) throws IOException {
		while (true) {
			byte[] buf = new byte[1024];
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);

			// Recepción (BLOQUEANTE)
			ms.receive(paquete);

			String mensaje = new String(paquete.getData(), 0, paquete.getLength());

			if (mensaje.contains("SESION_FIN")) {
				System.out.println("  [ANUNCIO]: Fin de sesión TCP detectado: " + mensaje);
				break;
			} else {
				System.out.println("  [ANUNCIO]: Nueva consulta recibida: " + mensaje);
			}
		}
	}

	// Cierre del MulticastSocket
	private static void pararMulticastSocket(MulticastSocket ms) {
		if (ms != null) {
			try {
				// Abandonar el grupo antes de cerrar
				InetAddress grupo = InetAddress.getByName(IP_MULTICAST);
				InetAddress ipLocal = InetAddress.getByName(IP_LOCAL);
				NetworkInterface netIf = NetworkInterface.getByInetAddress(ipLocal);

				if (netIf != null) {
					ms.leaveGroup(new InetSocketAddress(grupo, PUERTO_MULTICAST), netIf);
				}

				ms.close();
				System.out.println("CLIENTE PASIVO: MulticastSocket detenido.");
			} catch (IOException e) {
				System.err.println("Error al cerrar MulticastSocket: " + e.getMessage());
			}
		}
	}
}