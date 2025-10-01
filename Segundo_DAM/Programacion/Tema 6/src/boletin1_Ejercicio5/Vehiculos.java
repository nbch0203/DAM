package boletin1_Ejercicio5;

public abstract class Vehiculos {
	private String matricula;
	private String modelo;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public abstract void imprimir();

}
