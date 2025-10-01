package ejercicio_3;

public class Alumno extends Persona {
	private String Clase;
	private transient Fecha fecha;

	public Alumno(String dni, String nombre, String apellido, String clase, Fecha fecha) {
		super(dni, nombre, apellido);
		Clase = clase;
		this.fecha = fecha;
	}

	public String getClase() {
		return Clase;
	}

	public void setClase(String clase) {
		Clase = clase;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return super.toString() + "Alumno [getClase()=" + getClase() + ", getFecha()=" + getFecha() + "]";
	}

}
