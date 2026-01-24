package datos_necesarios;

public class Profesor extends Empleado {

	private String departamento;

	public Profesor(String identificador, String departamento) {
		super(identificador);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Profesor [getDepartamento()=" + getDepartamento() + ", getIdentificador()=" + getIdentificador()
				+ ", toString()=" + super.toString() + ", getDni()=" + getDni() + ", getNombre()=" + getNombre()
				+ ", getApellido()=" + getApellido() + ", getTelefono()=" + getTelefono() + ", getGenero()="
				+ getGenero() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
