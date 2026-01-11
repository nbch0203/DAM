package Tema4Ejercicio3;

import java.util.Scanner;

public class PruebaTest {
	static String respuesta;

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Test test1=new Test();
		test1.setEnunciado("dime la respuesta correcta");
		test1.presenta();
		System.out.println("");
		respuesta=sc.nextLine();
		
		
		test1.resultado();
	}

}
