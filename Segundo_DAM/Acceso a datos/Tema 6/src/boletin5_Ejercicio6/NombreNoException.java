package boletin5_Ejercicio6;

public class NombreNoException extends Exception {
	
	public NombreNoException(String nombre) {
		super("EL nombre debe contener al menos 3 caracteres : " + nombre +" este nombre no es valido");
	}

	@Override
	public String toString() {
		return "NombreNoException [getMessage()=" + getMessage() + "]";
	}
	
}
