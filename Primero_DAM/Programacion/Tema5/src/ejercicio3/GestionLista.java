package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionLista {
	Scanner sc = new Scanner(System.in);

	List<Integer> Listanumeros = new ArrayList<Integer>();

	public GestionLista(List<Integer> listanumeros) {
		Listanumeros = listanumeros;
	}

	public List<Integer> getListanumeros() {
		return Listanumeros;
	}

	public void setListanumeros(List<Integer> listanumeros) {
		Listanumeros = listanumeros;
	}

	public void add(ArrayList<Integer> Lista) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		while (num > 0) {
			System.out.println("Introduce un num negativo para terminar");
			num = sc.nextInt();
			Lista.add(num);
		}
		sc.close();
	}

	public void comprobar0(List<Integer> Lista) {
		for (int i = 0; i < Lista.size(); i++) {
			int aux = Lista.get(i);

			for (int o = i + 1; o < Lista.size(); o++) {
				if (aux == Lista.get(i)) {
					Lista.set(o, 0);
				}
			}
		}
	}

	public void mostar(List<Integer> Lista) {
		for (Integer n : Lista) {
			System.out.println(n + ";");
		}

	}

	@Override
	public String toString() {
		return "Ejercicio3 [" + (Listanumeros != null ? "Listanumeros=" + Listanumeros : "") + "]";
	}

	public void pedirnumeros() {
		System.out.println("Dime otro numero (Para terminar introduce un numero negativo)");
		int inputt = sc.nextInt();
		do {
			sc.nextInt();

		} while (inputt > 0);
	}
}
