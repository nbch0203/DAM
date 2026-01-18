package ejer2;

public class LanzadorAdivina {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5001; // Puerto del ServidorAdivina

    public static void main(String[] args) {
        System.out.println("--- LANZADOR: PRUEBA EJERCICIO 2 (2 HILOS) ---");
        
        // Hilo Cliente 1
        ClienteAdivina cliente1 = new ClienteAdivina(HOST, PUERTO, 1);
        
        // Hilo Cliente 2
        ClienteAdivina cliente2 = new ClienteAdivina(HOST, PUERTO, 2);
        
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