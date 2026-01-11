package ejercicio7;

public class Empleado extends Persona {
	String año_incorporacion;
	String numero_desp;

	public Empleado(String nombre, String apellidos, String dNI, String estado_civil, String año_incorporacion,
			String numero_desp) {
		super(nombre, apellidos, dNI, estado_civil);
		this.año_incorporacion = año_incorporacion;
		this.numero_desp = numero_desp;
	}

	public String getNumero_desp() {
		return numero_desp;
	}

	public void reasignacionNumero_desp(String numero_desp) {
		this.numero_desp = numero_desp;
	}

	@Override
	public String toString() {
		return "Año_incorporacion=" + año_incorporacion + ", numero_desp=" + numero_desp;
	}

}
