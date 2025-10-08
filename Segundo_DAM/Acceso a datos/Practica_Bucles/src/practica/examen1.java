package practica;


import java.util.*;
public class examen1 {

	public static void main(String[] args) {
		Scanner entrada=new Scanner (System.in);
		System.out.println("Ingrese el numero de filas que quires la piramide: ");
		int N=entrada.nextInt(); // pidiendo al usuario para que ingrese un numero
	for (int i=1; i<=N; i++ ) { //el bucle para ver hasta que altura va ser la piramide
		
		System.out.println(i +" "); 
	
	}for (int i2=1; i2<=N; i2++) { 
			System.out.println(i2 +" " + i2+ " ");
	
	}
}
}
	
