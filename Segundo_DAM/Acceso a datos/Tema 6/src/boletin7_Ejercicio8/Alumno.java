package boletin7_Ejercicio8;

public class Alumno {
	private String nombre;
	private int edad;
	private double nota;

	public Alumno(String nombre, int edad, double nota) throws EdadNoValidaException, NotaNoValidaException {
		this.nombre = nombre;

		// Validar la edad
		if (edad < 0 || edad > 20) {
			throw new EdadNoValidaException(edad);
		} else {
			this.edad = edad;
		}

		// Validar la nota
		if (nota < 0 || nota > 10) {
			throw new NotaNoValidaException(nota);
		} else {
			this.nota = nota;
		}
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) throws NotaNoValidaException {
		if (nota > 10) {
			throw new NotaNoValidaException(nota);
		} else {
			this.nota = nota;
		}
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", edad=" + edad + ", nota=" + nota + "]";
	}

}
