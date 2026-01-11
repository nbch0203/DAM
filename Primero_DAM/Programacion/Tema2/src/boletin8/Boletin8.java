package boletin8;

import java.util.Scanner;

public class Boletin8 {

	public static void main(String[] args) {
//int multiplos = 0;
//int numero=0;
//do {
//	int resultado =multiplos*numero;
//	numero++;
//	System.out.println("Numero"+resultado);
//
//}while (numero > 0 && numero <100);

//		int intentos=1;
//		int combinacion;
//		Scanner entrada =new Scanner (System.in);
//		System.out.println("Dime la combinacion de la caja fuerte:");
//
//		do {
//			combinacion = entrada.nextInt();
//			intentos++;
//		
//		if(combinacion == 2403 ) {
//			System.out.println("Henorabuena");
//			break;
//		}else{
//			System.out.printf("Combinacion incorrecta");
//			}			
//		}while (intentos <= 4);

		int control = 0;
		int numero;
		Scanner entrada = new Scanner(System.in);
		numero = entrada.nextInt();
		do {
			System.out.printf("%d\t%d\t\t%d\n", numero, (numero * numero));
			numero++;
			control++;
			System.out.println("Dime un numero");

		} while (control < 5);

		entrada.close();

	}

}
