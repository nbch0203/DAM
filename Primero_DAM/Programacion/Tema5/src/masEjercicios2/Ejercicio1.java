package masEjercicios2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ejercicio1 {

	public static void main(String[] args) {

		ArrayList<Carta> cartas = new ArrayList<Carta>();
		Carta auxCarta = null;
		Carta aux;
		for (int i = 0; i < 10; i++) {
			do {
				aux = auxCarta;
				auxCarta = new Carta();
				if (aux != auxCarta) {
					cartas.add(auxCarta);
				}
			} while (cartas.contains(auxCarta));

		}
		System.out.println(cartas);
		
//		Collections.sort( cartas);

//		Collections.sort(cartas, new OrdenarPalos());
//
//		for (Carta c : cartas) {
//			System.out.println(c.getNumero() + " " + c.getPalo());
//		}
//		System.out.println("****************************************");
//		Collections.sort(cartas, new OrdenarNumeros());
//
//		for (Carta c : cartas) {
//			System.out.println(c.getNumero() + " " + c.getPalo());
//		}
	}
}
