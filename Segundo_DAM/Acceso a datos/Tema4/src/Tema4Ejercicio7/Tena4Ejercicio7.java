package Tema4Ejercicio7;

import java.util.Random;

public class Tena4Ejercicio7 {

	Random random = new Random();
	private int[] valores = { random.nextInt(100) + 1 };
	
	public void binarySearch(int numero) {
		System.out.println(valores[1]);
		for (int i = 0; i < valores.length; i++) {
			
			if (numero == valores[i]) {
				System.out.println("es igual a: " + valores[i]);
			} else {
				System.out.println("fallo");
			}
		}
	}

	public int[] getValores() {
		return valores;
	}

	public void setValores(int[] valores) {
		this.valores = valores;
	}
}
