package examen;

public class PacienteAmbulatorio extends Paciente {
	private int numeroConsultas;

	public PacienteAmbulatorio(int id, String nombre, int edad, String diagnos, boolean ingresado,
			int numeroConsultas) {
		super(id, nombre, edad, diagnos, ingresado);
		this.numeroConsultas = numeroConsultas;
	}

	public int getNumeroConsultas() {
		return numeroConsultas;
	}

	public void setNumeroConsultas(int numeroConsultas) {
		this.numeroConsultas = numeroConsultas;
	}

	// Generacion de la factura del paciente hospitalizado con su diagnostico y su
	// coste final
	@Override
	public Double CosteFinal() {
		double aux = getNumeroConsultas() * 100;
		return aux;
	}

	// Metodo para calcular el coste final del paciente
	@Override
	public void generarFactura() {
		System.out.println(mostrarInfo() + "\n");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("La factura total del paciente hospitalizado es de : " + CosteFinal());
	}

	@Override
	public String mostrarInfo() {
		return "PacienteAmbulatorio numeroConsultas= " + numeroConsultas + " " + super.mostrarInfo();
	}

}
