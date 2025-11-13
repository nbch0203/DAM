package boletin_7_ejercicio_2;

import java.util.concurrent.Semaphore;

public class Paciente implements Runnable {
	private int idCliente;
	private Consultorio consultorioMedico;
	public static final int NUMPERMISOS = 5; // Numero de probadores.
	static Semaphore semaforo = new Semaphore(NUMPERMISOS); // Semaforo barrera para controlar que entren hasta 5
															// clientes

	public Paciente(int id, Consultorio consultorioMedico) {
		this.idCliente = id;
		this.consultorioMedico = consultorioMedico;
	}

	@Override
	public void run() {

		try {
			semaforo.acquire();
			this.consultorioMedico.entrarConsulta(this.idCliente);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
