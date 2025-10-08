package Tema4Ejercicio7;

import java.util.Scanner;

public class Tema3Ejercicio7Test {

	public static void main(String[] args) {
		Tena4Ejercicio7 num1 = new Tena4Ejercicio7();
		Scanner sc =new Scanner(System.in);
		int numero=sc.nextInt();
		
		num1.binarySearch(numero);
		
		sc.close();
	}

}
