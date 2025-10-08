package boletin2;

public class Boletin2 {

	public static void main(String[] args) {
		
		//Apartaqdo A
		
		int a=6;
		int b=3;
		int c=0;
		
		int suma=a+b;
		System.out.println("Suma: c = a + b = " +suma);
		
		//Apoartado B
		
		int resta=a-b;
		System.out.println("Resta: c = a- b = " + resta);
		
		//Apartado C
		
		int mult=a*b;
		System.out.println("Multiplicación: c = a * b = "+ mult);
		
		//Apartado D
		
		int div=a/b;
		System.out.println("División: c = b / a = "+ div);
		
		//Apartado E
		
		c=6%3;
		System.out.println("Módulo: c = 6 % 3 = "+c);
		
		//Apartado F
		a++;
		System.out.println("Incremento: a++ = "+a);
		
		//Apartado G
		b--;
		System.out.println("Decremento: b-- = " + b);
		
		//Apartado H
		a+=b;
		System.out.println("a += b: a = "+a+", b ="+b);
		
		//Apartado I
		b-=a;
		System.out.println("b - = a: a = "+a+", b="+b);
	}

}
