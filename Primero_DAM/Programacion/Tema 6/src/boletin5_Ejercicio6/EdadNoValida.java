package boletin5_Ejercicio6;

public class EdadNoValida extends Exception{

	public EdadNoValida(int edad) {
		super("Esta edad no es valida: "+edad);
	}

	@Override
	public String toString() {
		return "EdadNoValida [getMessage()=" + getMessage() + "]";
	}
	
	
}
