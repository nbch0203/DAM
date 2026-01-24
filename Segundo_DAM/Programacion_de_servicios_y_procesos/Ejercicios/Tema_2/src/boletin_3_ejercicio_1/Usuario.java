package boletin_3_ejercicio_1;

import java.util.Scanner;

public class Usuario extends Thread {
	private String nombre;
	private double hora;

	public void comprobar(String nombre, Double hora) {
		if (hora > 8) {
			System.out.println(nombre + " ha llegador tarde");
		} else if (hora < 8) {
			System.out.println(nombre + " ha llegado temprano");
		} else {
			System.out.println(nombre + " ha llegado a hora");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getHora() {
		return hora;
	}

	public void setHora(double hora) {
		this.hora = hora;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String nombre;

		System.out.println("Dime tu nombre: ");
		nombre = sc.next();
		System.out.println("Ingrese la hora:");
		double hora = sc.nextDouble();

		comprobar(nombre, hora);

	}

}
