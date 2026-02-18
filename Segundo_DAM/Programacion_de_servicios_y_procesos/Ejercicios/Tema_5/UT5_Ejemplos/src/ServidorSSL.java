import java.io.*;
import javax.net.ssl.*;

public class ServidorSSL {
    public static void main(String[] args) {
        
        // --- 1. CONFIGURACIÓN DE IDENTIDAD (El "DNI" del Servidor) ---
        // Antes de nada, le decimos a la Máquina Virtual de Java dónde está nuestro llavero.
        // "keyStore": Es el fichero que contiene NUESTRA clave privada y certificado público.
        // Sin esto, el servidor no puede demostrar quién es al cliente.
    	
    	// Definir propiedades del almacén de claves (Keystore)
        System.setProperty("javax.net.ssl.keyStore", "almacen.jks");
        
        // "123456": Es la CONTRASEÑA con la que se protegió el fichero 'almacen.jks' al crearlo.
        // Si no coincide con la que pusiste en el comando keytool, el programa fallará al arrancar.
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");


        int puerto = 5555;

        try {
            // --- 2. CREACIÓN DEL SOCKET "MÁGICO" (Factory Pattern) ---
            // A diferencia de los Sockets normales (new ServerSocket...), en SSL necesitamos
            // una "Fábrica" (Factory) porque la configuración de seguridad es compleja.
            SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            
            // Usamos la fábrica para crear el socket. Hacemos casting (SSLServerSocket) para
            // poder usar los métodos específicos de seguridad si los necesitáramos.
            SSLServerSocket servidor = (SSLServerSocket) sfact.createServerSocket(puerto);

            System.out.println("Servidor SSL activo. Esperando handshake en puerto " + puerto + "...");

            // --- 3. ESPERA Y HANDSHAKE ---
            // accept() funciona igual que siempre: se detiene hasta que alguien llama.
            // PERO AQUÍ OCURRE LA MAGIA: En cuanto el cliente conecta, Java realiza automáticamente
            // el "Handshake" (negociación de claves, envío de certificados, etc.) antes de continuar.
            SSLSocket cliente = (SSLSocket) servidor.accept();
            
            System.out.println("¡Conexión cifrada establecida! Cliente: " + cliente.getInetAddress());

            // --- 4. CANALES DE COMUNICACIÓN (Igual que Sockets normales) ---
            // Una vez establecido el túnel seguro, usamos DataInputStream/DataOutputStream
            // exactamente igual que siempre hemos usado. Java encripta/desencripta por debajo de forma transparente.
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            // --- 5. LÓGICA DE LA APP ---
            // Leemos el mensaje (ya llega desencriptado aquí)
            String mensaje = flujoEntrada.readUTF();
            System.out.println("Mensaje secreto recibido: " + mensaje);

            // Respondemos
            flujoSalida.writeUTF("Servidor recibió tu mensaje seguro.");

            // --- 6. CIERRE ---
            // Cerrar flujos y sockets
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}