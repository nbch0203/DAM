package ejercicio_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_Multicliente {
    private ServerSocket serverSocket;
    private static final int MAX_CLIENTES = 3;

    public Servidor_Multicliente(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("(Servidor) Iniciado en el puerto " + puerto);
    }

    public void iniciar() throws IOException {
        int numeroCliente = 1;

        // Acepta hasta 3 clientes
        while (numeroCliente <= MAX_CLIENTES) {
            System.out.println("(Servidor) Esperando cliente " + numeroCliente + "...");
            
            // Acepta la conexión del cliente
            Socket clienteSocket = serverSocket.accept();
            System.out.println("(Servidor) Cliente " + numeroCliente + " conectado");

            // Crea un hilo para atender a este cliente
            Thread hiloCliente = new Thread(new ManejadorCliente(clienteSocket, numeroCliente));
            hiloCliente.start();

            numeroCliente++;
        }

        System.out.println("(Servidor) Se han conectado los 3 clientes. No se aceptan más conexiones.");
    }

    public void cerrar() throws IOException {
        serverSocket.close();
        System.out.println("(Servidor) Servidor cerrado");
    }

    // Clase interna para manejar cada cliente en un hilo separado
    private class ManejadorCliente implements Runnable {
        private Socket socket;
        private int numeroCliente;

        public ManejadorCliente(Socket socket, int numeroCliente) {
            this.socket = socket;
            this.numeroCliente = numeroCliente;
        }

        @Override
        public void run() {
            try {
                // Obtiene los streams de comunicación
                DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInput = new DataInputStream(socket.getInputStream());

                // Envía el número de cliente
                dataOutput.writeInt(numeroCliente);
                System.out.println("(Servidor) Número " + numeroCliente + " enviado al cliente");

                // Cierra la conexión con este cliente
                dataInput.close();
                dataOutput.close();
                socket.close();
                System.out.println("(Servidor) Conexión cerrada con cliente " + numeroCliente);

            } catch (IOException e) {
                System.err.println("Error al manejar cliente " + numeroCliente + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Servidor_Multicliente servidor = new Servidor_Multicliente(49172);
            servidor.iniciar();
            
            // Espera un momento antes de cerrar el servidor
            Thread.sleep(2000);
            servidor.cerrar();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}