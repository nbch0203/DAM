package boletin3_Ficheros_Ejercicio2;

public class Empleado extends Persona {
	private double sueldo;

	public Empleado(String nif, String nombre, int edad, double sueldo) {
		super(nif, nombre, edad);
		this.sueldo = sueldo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Empleado ="+ super.toString() + ", sueldo= "+getSueldo();
	}
	
	
}
