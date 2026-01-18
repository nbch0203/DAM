
import java.io.*;
import java.net.*;
import java.sql.*;

public class ServidorCentral {

    // --- CONFIGURACIÓN DE RED/BD (Constantes) ---
    private static final int PUERTO_TCP = 9000;
    private static final String IP_MULTICAST = "235.10.10.1";
    private static final int PUERTO_MULTICAST = 8080;
    private static final String IP_LOCAL = "127.0.0.1"; // <-- CAMBIAR SI ES EN VM
    private static final String DB_URL = "jdbc:sqlite:inventario.db";

    public static void main(String[] args) {
        System.out.println("SERVIDOR: Iniciando. Esperando conexiones TCP...");
        iniciarServidor();
    }

    // Método principal para el arranque y bucle de escucha TCP
    private static void iniciarServidor() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PUERTO_TCP);
            // Bucle principal para aceptar nuevas sesiones (secuencial y bloqueante)
            while (true) {
                Socket socketCliente = null;
                try {
                    // 1. ARRANCAR: Espera a que un cliente se conecte (BLOQUEANTE)
                    socketCliente = serverSocket.accept();
                    String ipCliente = socketCliente.getInetAddress().getHostAddress();
                    System.out.println("\nSESIÓN ABIERTA: Conexión TCP aceptada desde " + ipCliente);
                    
                    // 2. PROCESAR: Manejar la sesión persistente
                    manejarSesionPersistente(socketCliente, ipCliente);

                } catch (IOException e) {
                    System.err.println("Error al aceptar conexión: " + e.getMessage());
                    pararSocket(socketCliente);
                } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (IOException e) {
            System.err.println("Error fatal al iniciar ServerSocket: " + e.getMessage());
        } finally {
            pararServerSocket(serverSocket);
        }
    }

    // Lógica del bucle de sesión TCP persistente
    private static void manejarSesionPersistente(Socket socketCliente, String ipCliente) throws SQLException {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))) {
            String peticion;
            
            // Bucle secundario: Mantiene la conexión TCP abierta
            while ((peticion = entrada.readLine()) != null) {
                System.out.println("  -> PETICIÓN TCP recibida de " + ipCliente + ": " + peticion);

                if (peticion.trim().equalsIgnoreCase("FIN")) {
                    enviarMulticast("SESION_FIN|CLIENTE:" + ipCliente);
                    System.out.println("  <- Señal FIN recibida. Saliendo del bucle de sesión.");
                    break;
                } else if (peticion.toUpperCase().startsWith("CONSULTA:")) {
                    procesarConsulta(peticion, ipCliente);
                }
            }

        } catch (IOException e) {
            System.err.println("Error de I/O en la sesión con " + ipCliente + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Formato numérico inválido en la petición: " + e.getMessage());
        } finally {
            // 3. PARAR: Cerrar la conexión TCP del cliente
            pararSocket(socketCliente);
            System.out.println("SESIÓN CERRADA: Socket TCP cerrado para " + ipCliente);
        }
    }

    // Procesa la consulta de DB y envía Multicast
    private static void procesarConsulta(String peticion, String ipCliente) throws SQLException {
        String[] partes = peticion.split(":");
        if (partes.length == 2) {
            try {
                int idProducto = Integer.parseInt(partes[1].trim());
                String resultado = consultarInventario(idProducto);
                enviarMulticast(resultado + "|POR:" + ipCliente);
            } catch (NumberFormatException e) {
                enviarMulticast("ERROR: ID de producto no válido desde " + ipCliente);
            }
        } else {
            enviarMulticast("ERROR: Formato de petición incorrecto desde " + ipCliente);
        }
    }

    // Consulta la base de datos (parte modular de DB)
    private static String consultarInventario(int id) throws SQLException {
        String resultado = "ID:" + id + "|ERROR:Producto no encontrado";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT NOMBRE, STOCK FROM PRODUCTOS WHERE ID = ?")) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                int stock = rs.getInt("STOCK");
                resultado = "ID:" + id + "|NOMBRE:" + nombre + "|STOCK:" + stock;
            }
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error SQL en consultarInventario: " + e.getMessage());
            throw e; // Re-lanza para manejo superior
        }
        return resultado;
    }

    // Envío del paquete Multicast (parte modular de red)
    private static void enviarMulticast(String mensaje) {
        try (MulticastSocket ms = new MulticastSocket()) {
            InetAddress grupo = InetAddress.getByName(IP_MULTICAST);
            InetAddress ipLocal = InetAddress.getByName(IP_LOCAL);
            
            NetworkInterface netIf = NetworkInterface.getByInetAddress(ipLocal);
            if (netIf != null) {
                ms.setNetworkInterface(netIf);
            }
            
            byte[] buffer = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, PUERTO_MULTICAST);
            ms.send(paquete);
            
            System.out.println("  -> MULTICAST enviado: '" + mensaje + "'");

        } catch (IOException e) {
            System.err.println("Error al enviar Multicast: " + e.getMessage());
        }
    }

    // Cierre del ServerSocket
    private static void pararServerSocket(ServerSocket ss) {
        if (ss != null) {
            try {
                ss.close();
                System.out.println("\nSERVIDOR: ServerSocket detenido.");
            } catch (IOException e) {
                System.err.println("Error al cerrar ServerSocket: " + e.getMessage());
            }
        }
    }

    // Cierre de un Socket
    private static void pararSocket(Socket s) {
        if (s != null && !s.isClosed()) {
            try {
                s.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar Socket: " + e.getMessage());
            }
        }
    }
}