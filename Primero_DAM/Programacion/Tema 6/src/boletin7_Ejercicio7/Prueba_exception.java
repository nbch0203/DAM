package boletin7_Ejercicio7;

public class Prueba_exception extends Exception {
	String entrada;

	public Prueba_exception(String e) {
		entrada = e;
	}

	@Override
	public String toString() {
		return "Prueba_exception error :" + entrada;
	}

}
