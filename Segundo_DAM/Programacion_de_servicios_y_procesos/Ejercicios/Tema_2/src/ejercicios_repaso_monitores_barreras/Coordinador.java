package ejercicios_repaso_monitores_barreras;

import java.util.ArrayList;

public class Coordinador {

	ArrayList<Tarea> lista = new ArrayList<Tarea>();
	private Tarea t;
	private int contador = 0;

	public synchronized void iniciar() {

		lista.add(new Tarea());
		lista.add(new Tarea());
		lista.add(new Tarea());

		for (int i = 0; i < lista.size(); i++) {
			t = lista.get(i);
			Thread h1= new Thread(t);
			h1.start();
			contador++;
			
			System.out.println("Iniciado el hilo:"+h1.getId());
		}

	}

	public void finalizar() {
		if (contador == 3) {
			
			System.out.println("Todos los hilos han terminado la fase finalizar");
		}
	}
}
