package datos_necesarios;

public class Ayudante extends Empleado {
	private Profesor profesor;

	public Ayudante(String identificador, Profesor profesor) {
		super(identificador);
		this.profesor = profesor;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Ayudante [Identificador=" + getIdentificador() + ", Nombre=" + getNombre() + " " + getApellido()
				+ ", DNI=" + getDni().getNumber() + ", Teléfono=" + getTelefono() + ", Género=" + getGenero()
				+ ", Profesor asignado=" + getProfesor().getNombre() + " " + getProfesor().getApellido() + " (Depto: "
				+ getProfesor().getDepartamento() + ")]";
	}

}
