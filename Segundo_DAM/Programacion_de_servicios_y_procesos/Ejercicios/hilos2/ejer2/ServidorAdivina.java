package ejer2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorAdivina {
    
    public static void main(String[] args) {
        int puerto = 5001;
        Random rnd = new Random();

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de Adivinanza esperando conexiones en el puerto " + puerto + "...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                
                int numeroSecreto = rnd.nextInt(100) + 1; // Número entre 1 y 100
                System.out.println("DEBUG: Número secreto para este cliente: " + numeroSecreto);
                
                new HiloAdivina(socket, numeroSecreto).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor principal: " + e.getMessage());
        }
    }
}