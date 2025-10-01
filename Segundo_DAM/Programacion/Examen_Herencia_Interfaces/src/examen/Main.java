package examen;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		ArrayList<Paciente> lista = new ArrayList<Paciente>();

		// Pacientes del ambulatorio
		lista.add(new PacienteAmbulatorio(1, "Nixon", 21, "Ibuprofeno", false, 2));
		lista.add(new PacienteAmbulatorio(2, "Julian", 35, "Ibuprofeno", false, 2));

		// Pacientes hospitalizados
		lista.add(new PacienteHospitalizado(3, "Filip", 19, "Ibuprofeno", true, 2, 2.5, 1));
		lista.add(new PacienteHospitalizado(4, "Adrian", 25, "Ibuprofeno", true, 2, 5.0, 3));

		// Bucle para mostrar la informacion de cada paciente de la lista
		for (Paciente paciente : lista) {
			System.out.println(paciente.mostrarInfo());
			System.out.println("----------------------------------------------------------------------");

		}

		System.out.println("\n\n");

		// Bucle para probar la generacion de las facturas de cada paciente
		for (Paciente paciente : lista) {
			paciente.generarFactura();
			System.out.println("***********************************************************");

		}

		System.out.println("\n\n");

		// Cambio de estado de ingreso de un paciente
		lista.get(0).setIngresado(true);
		System.out.println(lista.get(0).mostrarInfo());
		System.out.println("\n\n");
		// Bucle para saber que pacientes son pacientes hospitalizados y mostrar el
		// tratamiento
		for (Paciente paciente : lista) {
			if (paciente instanceof PacienteHospitalizado) {
				PacienteHospitalizado pah = (PacienteHospitalizado) paciente;

				System.out.println(pah.mostrarInfo() + ", tratamiento= " + pah.recibirTratamiento(" Paracetamol"));
				System.out.println(
						"--------------------------------------------------------------------------------------------");
			}
		}

	}

}
