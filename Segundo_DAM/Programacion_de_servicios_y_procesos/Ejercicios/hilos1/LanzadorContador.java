package ejer1;

public class LanzadorContador {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("--- LANZADOR: PRUEBA EJERCICIO 1 (2 HILOS) ---");
        
        // Hilo Cliente 1
        ClienteContador cliente1 = new ClienteContador(HOST, PUERTO, 1);
        
        // Hilo Cliente 2
        ClienteContador cliente2 = new ClienteContador(HOST, PUERTO, 2);
        
        // Iniciamos la ejecución de forma concurrente
        cliente1.start();
        cliente2.start();

        try {
            cliente1.join();
            cliente2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("--- LANZADOR FINALIZADO. ---");
    }
}