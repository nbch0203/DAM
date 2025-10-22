package boletin_3_ejercicio_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Estacion implements Runnable {

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introudce un nombre:");
		String nombre = sc.next();
		sc.close();

		try {
			buscar(nombre);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}

	}

	public static void buscar(String nombre) throws InterruptedException {
		
		/*
		  
		 Otra Manera de hacer este ejercicio

		HashMap<String, ArrayList<String>> estaciones = new HashMap<>();

		estaciones.put("verano", new ArrayList<>(Arrays.asList("Junio", "Julio", "Agosto")));
		estaciones.put("invierno", new ArrayList<>(Arrays.asList("Diciembre", "Enero", "Febrero")));
		estaciones.put("primavera", new ArrayList<>(Arrays.asList("Marzo", "Abril", "Mayo")));
		estaciones.put("otoño", new ArrayList<>(Arrays.asList("Septiembre", "Octubre", "Noviembre")));

		// Buscar directamente sin recorrer
		String nombreLower = nombre.toLowerCase();
		if (estaciones.containsKey(nombreLower)) {
			ArrayList<String> meses = estaciones.get(nombreLower);
			for (String mes : meses) {
				System.out.println(mes);
			}
		}

 */

		switch (nombre) {
		case "Verano":
			System.out.println("Junio");
			Thread.currentThread().sleep(2000);
			System.out.println("Julio");
			Thread.currentThread().sleep(3000);
			System.out.println("Agosto");
			Thread.currentThread().sleep(4000);
			break;
		case "Invierno":
			System.out.println("Diciembre");
			Thread.currentThread().sleep(2000);
			System.out.println("Enero");
			Thread.currentThread().sleep(3000);
			System.out.println("Febrero");
			Thread.currentThread().sleep(4000);
			break;
		case "Primavera":
			System.out.println("Marzo");
			Thread.currentThread().sleep(2000);
			System.out.println("Abril");
			Thread.currentThread().sleep(3000);
			System.out.println("Mayo");
			Thread.currentThread().sleep(4000);
			break;
		case "Otoño":
			System.out.println("Septiembre");
			Thread.currentThread().sleep(2000);
			System.out.println("Octubre");
			Thread.currentThread().sleep(3000);
			System.out.println("Noviembre");
			Thread.currentThread().sleep(4000);
			break;

		}

	}

}
