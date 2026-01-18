import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteActivo {

    // --- CONFIGURACIÓN DE RED ---
    private static final String IP_SERVIDOR = "127.0.0.1";
    private static final int PUERTO_TCP = 9000;
    private static final String IP_MULTICAST = "235.10.10.1";
    private static final int PUERTO_MULTICAST = 8080;
    private static final String IP_LOCAL = "127.0.0.1"; // <-- CAMBIAR SI ES EN VM

    public static void main(String[] args) {
        System.out.println("CLIENTE ACTIVO: Iniciando...");
        iniciarClienteActivo();
    }

    private static void iniciarClienteActivo() {
        try (Scanner scanner = new Scanner(System.in)) {
            
            // 1. ARRANCAR: Conectar TCP al servidor
            Socket socketTCP = new Socket(IP_SERVIDOR, PUERTO_TCP);
            System.out.println("CLIENTE ACTIVO: Conexión TCP establecida con el Servidor.");
            
            // 2. PROCESAR: Ejecutar el bucle de interacción
            ejecutarBuclePeticion(scanner, socketTCP);

        } catch (IOException e) {
            System.err.println("Error fatal de conexión/I/O: " + e.getMessage());
        }
    }

    // Ejecuta el bucle de envío TCP y recepción Multicast secuencial
    private static void ejecutarBuclePeticion(Scanner scanner, Socket socketTCP) {
        try (PrintWriter salida = new PrintWriter(socketTCP.getOutputStream(), true)) {
            String comando;

            while (true) {
                System.out.print("\nIntroduzca ID de producto (Ej: 1) o 'FIN' para salir: ");
                comando = scanner.nextLine().trim();

                if (comando.equalsIgnoreCase("FIN")) {
                    // Envío de señal de cierre al servidor
                    salida.println("FIN");
                    System.out.println("Mensaje FIN enviado. Terminando sesión.");
                    break;
                }

                String peticion = "CONSULTA:" + comando;
                
                // Envío TCP
                salida.println(peticion);
                System.out.println("  -> Petición TCP enviada: " + peticion);
                
                // Recepción Multicast (BLOQUEANTE)
                recibirRespuestaMulticast();
            }

        } catch (IOException e) {
            System.err.println("Error en la salida/entrada de TCP: " + e.getMessage());
        } finally {
            // 3. PARAR: Cerrar la conexión TCP
            pararSocket(socketTCP);
        }
    }
    
    // Método para recibir el Multicast (parte modular de red)
    private static void recibirRespuestaMulticast() {
        MulticastSocket ms = null;
        try {
            // Configurar Multicast
            InetAddress ipLocal = InetAddress.getByName(IP_LOCAL);
            InetAddress grupo = InetAddress.getByName(IP_MULTICAST);
            NetworkInterface netIf = NetworkInterface.getByInetAddress(ipLocal);
            
            ms = new MulticastSocket(PUERTO_MULTICAST);

            // Unirse al grupo
            ms.joinGroup(new InetSocketAddress(grupo, PUERTO_MULTICAST), netIf);
            
            byte[] buf = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);

            System.out.println("  <- Esperando respuesta Multicast (BLOQUEANTE)...");
            
            // Recepción (BLOQUEANTE)
            ms.receive(paquete);
            
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("  <- RESPUESTA MULTICAST recibida: [" + mensaje + "]");
            
            // Abandonar el grupo
            ms.leaveGroup(new InetSocketAddress(grupo, PUERTO_MULTICAST), netIf);

        } catch (IOException e) {
            System.err.println("Error en la recepción Multicast: " + e.getMessage());
        } finally {
            // 3. PARAR: Cerrar el MulticastSocket
            if (ms != null) ms.close();
        }
    }

    // Cierre de un Socket
    private static void pararSocket(Socket s) {
        if (s != null && !s.isClosed()) {
            try {
                s.close();
                System.out.println("CLIENTE ACTIVO: Socket TCP detenido.");
            } catch (IOException e) {
                System.err.println("Error al cerrar Socket: " + e.getMessage());
            }
        }
    }
}