package ejercicio6;

public class Persona {
	String nombre;
	String apellidos;
	String DNI;
	String estado_civil;

	public Persona(String nombre, String apellidos, String dNI, String estado_civil) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		DNI = dNI;
		this.estado_civil = estado_civil;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void cambioEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI + ", estado_civil=" + estado_civil;
	}

}
