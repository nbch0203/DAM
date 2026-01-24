package examen;

public abstract class Paciente implements Facturable {
	private int id;
	private String nombre;
	private int edad;
	private String diagnos;
	private boolean ingresado;

	public Paciente(int id, String nombre, int edad, String diagnos, boolean ingresado) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.diagnos = diagnos;
		this.ingresado = ingresado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDiagnos() {
		return diagnos;
	}

	public void setDiagnos(String diagnos) {
		this.diagnos = diagnos;
	}

	public Boolean getIngresado() {
		return ingresado;
	}

	public void setIngresado(Boolean ingresado) {
		this.ingresado = ingresado;
	}

	public void ingresar() {
		setIngresado(true);
	}

	public void darAlta() {
		setIngresado(false);
	}

	public abstract Double CosteFinal();

	public String mostrarInfo() {
		return "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", diagnostico=" + diagnos + ", ingresado="
				+ ingresado;
	}

}
