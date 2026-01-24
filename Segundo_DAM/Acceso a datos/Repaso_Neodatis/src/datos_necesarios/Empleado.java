package datos_necesarios;

public class Empleado extends Persona {
	private String identificador;

	public Empleado(String identificador) {
		super();
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public String toString() {
		return "Empleado [getIdentificador()=" + getIdentificador() + " " + super.toString() + "]";
	}

}
