package Parte1;

public class Numero {
	int numero;
	int valor;

	public Numero(int num) {
		numero = num;
	}

	void getValor(int val) {
		valor = val;
		numero += val;
	}

	void getResta() {
		numero -= 2;
	}

	int valor() {
		return numero;
	}

	int getDoble() {
		return numero * 2;
	}

	int getTriple() {
		return numero * 3;
	}

	void setNumero(int num1) {
		numero = num1;
	}

}