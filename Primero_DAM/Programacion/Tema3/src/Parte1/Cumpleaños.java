package Parte1;

public class Cumpleaños {
	static int cumpleaños(int edad) {
		edad++;
		return edad;
	}

	public static void main(String[] args) {
		int edad = 18;
		edad = cumpleaños(edad);
		System.out.println(edad);
	}
}