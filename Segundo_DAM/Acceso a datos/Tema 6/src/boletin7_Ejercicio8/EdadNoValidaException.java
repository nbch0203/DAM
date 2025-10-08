package boletin7_Ejercicio8;

public class EdadNoValidaException extends Exception {

	public EdadNoValidaException(int edad) {
		super("La edad " + edad + " no es validad");
	}

}
