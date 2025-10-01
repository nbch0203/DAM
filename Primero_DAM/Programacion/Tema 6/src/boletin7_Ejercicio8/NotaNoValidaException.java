package boletin7_Ejercicio8;

public class NotaNoValidaException extends Exception {

	public NotaNoValidaException(double nota) {
		super("La nota " + nota + " no es válida. Debe estar entre 0 y 10.");
	}

	@Override
	public String toString() {
		return "NotaNoValidaException [getMessage()=" + getMessage() + "]";
	}

}
