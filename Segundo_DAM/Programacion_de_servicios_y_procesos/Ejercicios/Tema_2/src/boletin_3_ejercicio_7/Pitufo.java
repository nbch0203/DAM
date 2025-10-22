package boletin_3_ejercicio_7;

import java.util.ArrayList;

public class Pitufo implements Runnable {

	@Override
	public void run() {
		ns();

	}

	public static void ns() {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("Pitufo tonto");
		lista.add("Pitufo vanidoso");
		lista.add("Pitufo fuerte");
		lista.add("Pitufo arquitecto");
		lista.add("Pitufo actor");
		lista.add("Pitufo bombero");
		lista.add("Pitufo bromista");
		lista.add("Pitufo suerte");
		lista.add("Bebe pitufo");
		lista.add("Papa pitufo");

		for (int i = 1; i < lista.size(); i++) {
			for (int j = 1; j <= 3; j++) {
				System.out.println(lista.get(i) + " come un " + j + " º pan con queso");
				if (j == 3) {
					System.out.println(lista.get(i) + " termino");

				}

			}

		}

	}

}
