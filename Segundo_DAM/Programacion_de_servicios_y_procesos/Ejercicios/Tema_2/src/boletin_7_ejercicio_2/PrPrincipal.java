package boletin_7_ejercicio_2;

public class PrPrincipal {
	public static void main(String[] args) {

		Paciente[] pacientes = new Paciente[10];
		Consultorio t = new Consultorio();

		for (int i = 0; i < 10; i++) {
			pacientes[i] = new Paciente(i, t);
			pacientes[i].run();
		}

		for (int i = 0; i < 10; i++)
			try {
				pacientes[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		System.out.println("Todos los pacientes se han atendido");

	}
}
