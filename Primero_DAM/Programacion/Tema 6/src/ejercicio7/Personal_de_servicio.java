package ejercicio7;

public class Personal_de_servicio extends Empleado {
	String seccion_asigna;

	public Personal_de_servicio(String nombre, String apellidos, String dNI, String estado_civil,
			String año_incorporacion, String numero_desp, String seccion_asigna) {
		super(nombre, apellidos, dNI, estado_civil, año_incorporacion, numero_desp);
		this.seccion_asigna = seccion_asigna;
	}

	public String getSeccion_asigna() {
		return seccion_asigna;
	}

	public void trasladoSeccion_asigna(String seccion_asigna) {
		this.seccion_asigna = seccion_asigna;
	}

	@Override
	public String toString() {
		return "Seccion_asigna=" + seccion_asigna + ", año_incorporacion=" + año_incorporacion + ", numero_desp="
				+ numero_desp + ", nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI + ", estado_civil="
				+ estado_civil;
	}

}
