package ejer1;

import java.io.*;
import java.net.Socket;

class HiloContador extends Thread {
    private Socket socket;
    private BufferedReader lector;
    private BufferedWriter escritor;

    public HiloContador(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Ejecución modular
        if (inicializarStreams()) {
            procesarPeticion();
        }
        cerrarConexion();
    }
    
    /** Inicializa los streams de entrada/salida del socket. */
    private boolean inicializarStreams() {
        try {
            this.lector = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            this.escritor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            return true;
        } catch (IOException e) {
            System.err.println("Error inicializando streams para " + socket.getInetAddress());
            return false;
        }
    }
    
    /** Lógica principal: incremento sincronizado y respuesta al cliente. */
    private void procesarPeticion() {
        int visitaActual;
        
        // Bloque sincronizado: solo un hilo accede a la vez
        synchronized (ServidorContador.class) {
            ServidorContador.contadorVisitas = ServidorContador.contadorVisitas + 1;
            visitaActual = ServidorContador.contadorVisitas;
            System.out.println("DEBUG: Contador incrementado a " + visitaActual);
        }

        try {
            String mensaje = "Bienvenido! Usted es la visita número " + visitaActual + ".\n";
            escritor.write(mensaje);
            escritor.flush();
            
            // Esperar el comando de desconexión del cliente
            String textoRecibido=lector.readLine();

            System.out.println("Servidor recibe del cliente:"+textoRecibido);
        } catch (IOException e) {
            System.err.println("Error procesando petición: " + e.getMessage());
        }
    }
    
    /** Cierra el socket y maneja el fin de la conexión. */
    private void cerrarConexion() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.err.println("Error al cerrar socket: " + ex.getMessage());
        }
        System.out.println("Cliente desconectado.");
    }
}