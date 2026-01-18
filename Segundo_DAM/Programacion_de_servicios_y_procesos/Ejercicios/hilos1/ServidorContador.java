package ejer1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorContador {
    
    // Recurso compartido que DEBE ser protegido
    public static int contadorVisitas = 0;

    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor Contador esperando conexiones en el puerto " + puerto + "...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                // Lanza un nuevo hilo por cada cliente
                new HiloContador(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor principal: " + e.getMessage());
        }
    }
}