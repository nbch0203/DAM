package modelo;

public class Trabajador extends Persona {
	private String puesto;
	private double sueldo;
	private int horas_trab;

	public Trabajador(String nombre, String apellido, String dni, String genero, boolean mayor_edad, String puesto,
			double sueldo, int horas_trab) {
		super(nombre, apellido, dni, genero, mayor_edad);
		this.puesto = puesto;
		this.sueldo = sueldo;
		this.horas_trab = horas_trab;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public int getHoras_trab() {
		return horas_trab;
	}

	public void setHoras_trab(int horas_trab) {
		this.horas_trab = horas_trab;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return super.toString() + " " + "Trabajador [getSueldo()=" + getSueldo() + ", getHoras_trab()="
				+ getHoras_trab() + ", getPuesto()=" + getPuesto() + "]";
	}

}
