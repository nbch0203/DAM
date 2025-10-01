package Parte2Ejercicio7;

public class Ejercicio7 {

	public static void main(String[] args) {
		Ejercicio7Class billete1=new Ejercicio7Class("Nixon","9494949");

		billete1.setMaletas(2);
		billete1.setfacturar("a455");
		billete1.setClase("VIP");
		billete1.setBano(false);
		System.out.println(billete1.toString());
	}

}
