package ejer2;

import java.io.*;
import java.net.Socket;

class ClienteAdivina extends Thread {
    private String host;
    private int puerto;
    private int idCliente;
    
    private Socket socket;
    private BufferedReader lector;
    private BufferedWriter escritor;

    public ClienteAdivina(String host, int puerto, int id) {
        this.host = host;
        this.puerto = puerto;
        this.idCliente = id;
    }

    @Override
    public void run() {
        if (conectar()) {
            leerMensajeInicial();
            ejecutarLogicaAdivina();
        }
        desconectar();
    }
    
    /** Establece la conexión e inicializa los streams. */
    private boolean conectar() {
        try {
            this.socket = new Socket(this.host, this.puerto);
            this.lector = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            this.escritor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            System.out.println("[C" + this.idCliente + "] Conexión establecida con " + this.puerto);
            return true;
        } catch (IOException e) {
            System.err.println("[C" + this.idCliente + "] Error al conectar: " + e.getMessage());
            return false;
        }
    }
    
    /** Lee y muestra el mensaje de bienvenida. */
    private void leerMensajeInicial() {
        try {
            String mensajeServidor = lector.readLine();
            if (mensajeServidor != null) {
                System.out.println("[C" + this.idCliente + "] Servidor: " + mensajeServidor);
            }
        } catch (IOException e) {
            System.err.println("[C" + this.idCliente + "] Error leyendo bienvenida.");
        }
    }
    
    /** Lógica de búsqueda binaria para adivinar el número. */
    private void ejecutarLogicaAdivina() {
        int intento = 50;
        int max = 100;
        int min = 1;
        String respuestaServidor;

        try {
            System.out.println("[C" + this.idCliente + "] Iniciando juego, primer intento: " + intento);
            
            while (true) {
                String intentoStr = String.valueOf(intento);
                escritor.write(intentoStr + "\n");
                escritor.flush();

                respuestaServidor = lector.readLine();
                if (respuestaServidor == null) {
                    break;
                }
                System.out.println("[C" + this.idCliente + "] Servidor: " + respuestaServidor);

                if (respuestaServidor.contains("CORRECTO")) {
                    break; 
                }

                if (respuestaServidor.contains("MAYOR")) {
                    min = intento + 1;
                } else if (respuestaServidor.contains("MENOR")) {
                    max = intento - 1;
                }
                
                intento = (min + max) / 2;
                
                if (min > max) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("[C" + this.idCliente + "] Error durante el juego: " + e.getMessage());
        }
    }
    
    /** Envía el comando final y cierra el socket. */
    private void desconectar() {
        try {
            if (escritor != null) {
                escritor.write("bye\n");
                escritor.flush();
                System.out.println("[C" + this.idCliente + "] Desconexión enviada.");
                
                String mensajeFinal = lector.readLine();
                if (mensajeFinal != null) {
                    System.out.println("[C" + this.idCliente + "] Servidor final: " + mensajeFinal);
                }
            }
        } catch (IOException e) {
            // Falla al desconectar
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                // Ignorar
            }
        }
    }
}