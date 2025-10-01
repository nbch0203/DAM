package Tema4Ejercicio2;

import java.util.Scanner;

public class Tema4Ejercicio2 {

public static void main(String[] args) {
	Scanner ent=new Scanner(System.in);
	int [] numeros= {1,2,3,4,5};
	int	entrada= ent.nextInt();
	
	for(int i=0;i<numeros.length;i++) {
		
		
		if(entrada==numeros[i]) {
			System.out.println(numeros[i]+" es igual a la entrada");
		}
	}
}
}
