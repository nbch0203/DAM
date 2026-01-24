package ejercicio_3;

import java.io.Serializable;

public class Fecha {
	private int dia;
	private String mes;
	private int año;

	public Fecha(int dia, String mes, int año) {
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	@Override
	public String toString() {
		return "Fecha [getDia()=" + getDia() + ", getMes()=" + getMes() + ", getAño()=" + getAño() + "]";
	}

}
