package Parte2Ejercicio12;

public class Ejercicio12Class {
	int cadenanumero;
	String cadenaletras1;
	float cadenafloat;
	String cadenaletras2;
	String cadenaletras;

	public Ejercicio12Class(String cadenaletras, int cadenanumero, float cadenafloat) {
		this.cadenaletras = cadenaletras;
		this.cadenanumero = cadenanumero;
		this.cadenafloat = cadenafloat;
	}

	public static int mayor(int... cadenanumero) {
		int resultado = 0;
		for (int i = 0; i < cadenanumero.length; i++) {
			if (resultado <= cadenanumero[i]) {
				resultado = cadenanumero[i];
			}
		}
		return resultado;
	}
	public static int menor(int... cadenanumero) {
		int resultado = 9999999;
		for (int i = 0; i < cadenanumero.length; i++) {
			if (resultado >= cadenanumero[i]) {
				resultado = cadenanumero[i];
			}
		}
		return resultado;
	}
	public static float mayor(float... cadenafloat) {
		float resultado = 0f;
		for (int i = 0; i < cadenafloat.length; i++) {
			if (resultado <= cadenafloat[i]) {
				resultado = cadenafloat[i];
			}
		}
		return resultado;
	}
	public static float menor(float... cadenafloat) {
		float resultado = 9999999f;
		for (int i = 0; i < cadenafloat.length; i++) {
			if (resultado >= cadenafloat[i]) {
				resultado = cadenafloat[i];
			}
		}
		return resultado;
	}
	public static String mayor(String... cadenaletras) {
		String resultado = "";
		for (int i = 0; i < cadenaletras.length; i++) {
			if (resultado.length() <= cadenaletras[i].length()) {
				resultado = cadenaletras[i];
			}
		}
		return resultado;
	}
	public static String menor(String... cadenaletras) {
		String resultado = "";
		for (int i = 0; i < cadenaletras.length; i++) {
			if (resultado.length() >= cadenaletras[i].length()) {
				resultado = cadenaletras[i];
			}
		}
		return resultado;
	}
	
}
