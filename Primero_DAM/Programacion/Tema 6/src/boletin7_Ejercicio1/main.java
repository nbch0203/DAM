package boletin7_Ejercicio1;

import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numero[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		try {
			int pos = sc.nextInt();
			System.out.println("Su valor es :" + numero[pos]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Te has pasado del limite");
		}
		
	}

}
