package ejercicio10;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio10 {

	// Ejercicio 10 adaptado y repaso de algunos métodos de mapas

	public static void main(String[] args) {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("club", "club");
		mapa.put("ordenador", "computer");
		mapa.put("gato", "cat");
		mapa.put("rojo", "red");
		mapa.put("pingüino", "penguin");

		System.out.println("Mostraré la palabra en español y usted tendrá que traducirla al inglés.");
		int puntos = 0;

		Scanner sc = new Scanner(System.in);
		String palabraIntro = null;
		Iterator<String> it = mapa.keySet().iterator();

		while (it.hasNext()) {
			String clave = it.next();
			System.out.println("Palabra:" + clave);
			System.out.println("Traducción...");
			palabraIntro = sc.nextLine();
			if (mapa.get(clave).equals(palabraIntro)) {
				System.out.println("Acertaste");
				puntos++;
			} else {
				System.out.println("Ooooh no es correcto");
				System.out.println("La palabra correcta es:" + mapa.get(clave));
			}
		}

		System.out.println("Los puntos que has obtenido son:" + puntos);

		System.out.println("********************************");
		System.out.println("Muestra los valores del mapa:");
		Collection<String> valores = mapa.values();
		Iterator<String> it2 = valores.iterator();

		while (it2.hasNext()) {
			String valor = it2.next();

			if (mapa.containsKey(valor)) {
				System.out.println("El valor '" + valor + "' es también clave en el mapa");
			} else
				System.out.println(valor);

		}

		System.out.println("********************************");
		System.out.println("Muestra las claves del mapa:");
		Set<String> claves = mapa.keySet();
		for (String s : claves) {
			System.out.println(s);
		}

		System.out.println("********************************");
		System.out.println("Elimino una palabra...");
		String palabraBorrar = sc.nextLine();
		if (mapa.containsKey(palabraBorrar))
			mapa.remove(palabraBorrar);
		else
			System.out.println("No existe esa clave en el mapa");

		System.out.println("********************************");
		System.out.println("Muestro mapa entero");
		Set<String> claves1 = mapa.keySet();
		for (String s : claves1) {
			System.out.println("clave" + s + "->" + mapa.get(s));
		}
		sc.close();

	}

}
