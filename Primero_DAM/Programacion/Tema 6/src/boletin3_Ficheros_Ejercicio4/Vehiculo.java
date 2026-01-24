package boletin3_Ficheros_Ejercicio4;

import java.io.Serializable;

public class Vehiculo implements Serializable {
	private int id;
	private String matricula;
	private long km;
	private int caballos;

	public Vehiculo(int id, String matricula, long km, int caballos) {
		this.id = id;
		this.matricula = matricula;
		this.km = km;
		this.caballos = caballos;
	}

	public Vehiculo() {
		this.matricula = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public long getKm() {
		return km;
	}

	public void setKm(long km) {
		this.km = km;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	@Override
	public String toString() {
		return "Vehiculo [id= " + id + " matricula= " + matricula + ", km=" + km + ", caballos=" + caballos + "]";
	}

}
