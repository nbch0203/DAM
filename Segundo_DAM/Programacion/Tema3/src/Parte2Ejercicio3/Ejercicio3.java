package Parte2Ejercicio3;

public class Ejercicio3 {

	public static void main(String[] args) {
		Ejercicio3class c1 =new Ejercicio3class("Pera","España", 200, 0.89, 1.56);
		Ejercicio3class c2 =new Ejercicio3class("Manzana", "España", 2050, 2.50, 1.0);
		Ejercicio3class c3 =new Ejercicio3class("Coco", "Hawai", 20, 0.50, 1);
		
	
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		System.out.println("");
		System.out.println(c1.mismaprocedencia(c1.procedencia, c2.procedencia));
		System.out.println("");
		System.out.println(c3.vender(10));
	}

}
