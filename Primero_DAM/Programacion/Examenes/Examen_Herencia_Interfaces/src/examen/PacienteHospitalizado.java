package examen;

public class PacienteHospitalizado extends Paciente implements Atendible {

	private int diasHospitalizacion;
	private double costePorDia;
	private double costeBaseTratamiento;

	public PacienteHospitalizado(int id, String nombre, int edad, String diagnos, boolean ingresado,
			int diasHospitalizacion, double costePorDia, double costeBaseTratamiento) {
		super(id, nombre, edad, diagnos, ingresado);
		this.diasHospitalizacion = diasHospitalizacion;
		this.costePorDia = costePorDia;
		this.costeBaseTratamiento = costeBaseTratamiento;
	}

	public int getDiasHospitalizacion() {
		return diasHospitalizacion;
	}

	public void setDiasHospitalizacion(int diasHospitalizacion) {
		this.diasHospitalizacion = diasHospitalizacion;
	}

	public double getCostePorDia() {
		return costePorDia;
	}

	public void setCostePorDia(double costePorDia) {
		this.costePorDia = costePorDia;
	}

	public double getCosteBaseTratamiento() {
		return costeBaseTratamiento;
	}

	public void setCosteBaseTratamiento(double costeBaseTratamiento) {
		this.costeBaseTratamiento = costeBaseTratamiento;
	}

	// Metodo para mostrar el tratamiento que ha recibido el paciente hospitalizado
	@Override
	public String recibirTratamiento(String info) {
		return info;

	}

	// Generacion de la factura del paciente hospitalizado con su diagnostico y su
	// coste final
	@Override
	public void generarFactura() {
		System.out.println(mostrarInfo() + "\n");
		recibirTratamiento(getDiagnos());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("La factura total del paciente hospitalizado es de : " + CosteFinal());
	}

	// Metodo para calcular el coste final del paciente
	@Override
	public Double CosteFinal() {
		double aux = 0;
		aux = getDiasHospitalizacion() * getCostePorDia();
		return aux += getCosteBaseTratamiento();

	}

	// Metodo para mostrar toda la informacion del paciente junto con sus datos
	// personales
	public String mostrarInfo() {
		return "PacienteHospitalizado " + super.mostrarInfo() + ", diasHospitalizacion=" + diasHospitalizacion
				+ ", costePorDia=" + costePorDia + ", costeBaseTratamiento=" + costeBaseTratamiento;
	}

}
