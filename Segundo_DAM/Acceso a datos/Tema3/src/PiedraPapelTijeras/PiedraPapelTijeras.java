package PiedraPapelTijeras;

import java.math.*;

public class PiedraPapelTijeras {

	public static void main(String[] args) {

		double valorale = (int) Math.random() * 3 + 1;
		if (valorale > 1 && valorale < 2) {
			System.out.println("Piedra");
		} else if (valorale > 2 && valorale < 3) {
			System.out.println("Papel");
		} else if (valorale > 3) {
			System.out.println("Tijeras");
		}
		System.out.println("Aleatorio" + valorale);

		

	}
}
