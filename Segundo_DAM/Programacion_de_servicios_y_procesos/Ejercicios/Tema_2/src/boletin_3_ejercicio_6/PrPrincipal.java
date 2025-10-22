package boletin_3_ejercicio_6;

import java.util.ArrayList;

public class PrPrincipal {
	public static void main(String[] args) {

		ArrayList<String> estaciones = new ArrayList<String>();

		estaciones.add("Verano");
		estaciones.add("Invierno");
		estaciones.add("Otoño");
		estaciones.add("Primavera");

		for (String string : estaciones) {
			System.out.println(string);
		}
		System.out.println("**************");
		ThreadGroup grupo = new ThreadGroup("grupo");
		Estacion e = new Estacion();

		Thread h = new Thread(grupo, e);
		h.start();

	}

}
