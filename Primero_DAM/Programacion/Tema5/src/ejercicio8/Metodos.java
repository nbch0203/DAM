package ejercicio8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Metodos {

	public void mostrar(HashSet<Jugadores> Lista) {
		for (Jugadores jugador : Lista) {
			System.out.println(jugador.getNombre() + " " + jugador.getDorsal());
		}
		System.out.println("********************************");
	}

	public void esOno(HashSet<Jugadores> Lista) {
//			Jugadores neymar = new Jugadores("Neymar", 11);

//		if(Jugadores.contains(neymar)) {
//			System.out.println("Existe neymar");
//		}else
//			System.out.println("No existe");

		for (Jugadores jugador : Lista) {
			if (jugador.getNombre().equals("Neymar")) {
				System.out.println("Si esta Neymar");
			} else {
				System.out.println("No esta");
			}
		}
	}

	public void piquebusquets(HashSet<Jugadores> Lista) {
//		for (Jugadores j : Lista) {
//			Iterator<Jugadores> i = Jugadores.iterator();
//			boolean encontrado = false;
//			while (i.hasNext() && !encontrado) {
//				if (i.next().getNombre().equals(j.getNombre())) {
//					System.out.println();
//				}
//
//			}
//
//		}
	}

	public void compararlistas(HashSet<Jugadores> Lista) {
		for (Jugadores jugador : Lista) {
			if (Lista.equals(jugador)) {
			}
		}
	}
}
