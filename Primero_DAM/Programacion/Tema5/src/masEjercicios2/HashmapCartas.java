package masEjercicios2;

import java.util.HashMap;
import java.util.Random;

public class HashmapCartas {
	public static void main(String[] args) {

		HashMap<Integer, String> baraja = new HashMap<Integer, String>();

		Random numran = new Random();
		Carta2 auxCarta = null;
		for (int i = 0; i < 5; i++) {
			int numeroale = numran.nextInt(5);
			do {
				if (baraja.containsKey(numeroale)) {
					
				}
			} while (baraja.containsValue(auxCarta));

		}

		System.out.println(baraja);
	}
}
