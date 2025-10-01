package ejercicio6;

public class Profesores extends Empleado {

	String departamento;

	public Profesores(String nombre, String apellidos, String dNI, String estado_civil, String año_incorporacion,
			String numero_desp, String departamento) {
		super(nombre, apellidos, dNI, estado_civil, año_incorporacion, numero_desp);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void cambioDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Departamento=" + departamento + ", año_incorporacion=" + año_incorporacion + ", numero_desp="
				+ numero_desp + ", nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI + ", estado_civil="
				+ estado_civil;
	}

}
