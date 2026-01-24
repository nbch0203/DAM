package ejemplos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTreadPool {

	public static void main(String[] args) {
		// Utilizo un pool de 2 hilos
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// Lanzo 10 tareas que se repartirán entre los 2 hilos
		for (int i = 0; i < 100; i++) {
			executor.execute(new Work());
		}
	}
}