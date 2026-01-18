package ejer2;

import java.io.*;
import java.net.Socket;

class HiloAdivina extends Thread {
    private Socket socket;
    private final int numeroSecreto;
    private int intentos;
    private BufferedReader lector;
    private BufferedWriter escritor;

    public HiloAdivina(Socket socket, int secreto) {
        this.socket = socket;
        this.numeroSecreto = secreto;
        this.intentos = 0;
    }

    @Override
    public void run() {
        if (inicializarStreams()) {
            ejecutarJuego();
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
    
    /** Lógica principal del juego. */
    private void ejecutarJuego() {
        try {
            escribirMensaje("Adivina el número entre 1 y 100. Escribe 'bye' para salir.\n");

            String linea;
            while ((linea = lector.readLine()) != null) {
                
                if (linea.equalsIgnoreCase("bye")) {
                    escribirMensaje("Adiós! El número secreto era " + this.numeroSecreto + ".\n");
                    break;
                }
                
                procesarIntento(linea);

                if (linea.contains("CORRECTO")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        }
    }

    /** Procesa la entrada del cliente y envía la respuesta. */
    private void procesarIntento(String linea) throws IOException {
        String respuesta;
        try {
            this.intentos = this.intentos + 1;
            int intento = Integer.parseInt(linea.trim());

            if (intento < this.numeroSecreto) {
                respuesta = "El número secreto es MAYOR que " + intento + ". Intento: " + this.intentos + "\n";
            } else if (intento > this.numeroSecreto) {
                respuesta = "El número secreto es MENOR que " + intento + ". Intento: " + this.intentos + "\n";
            } else {
                respuesta = "¡CORRECTO! Lo adivinaste en " + this.intentos + " intentos.\n";
            }
            escribirMensaje(respuesta);
            
        } catch (NumberFormatException e) {
            escribirMensaje("Entrada no válida. Por favor, introduce un número.\n");
        }
    }

    /** Método auxiliar para simplificar la escritura. */
    private void escribirMensaje(String mensaje) throws IOException {
        escritor.write(mensaje);
        escritor.flush();
    }
    
    /** Cierra el socket. */
    private void cerrarConexion() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            // Ignorar
        }
        System.out.println("Juego terminado con cliente.");
    }
}