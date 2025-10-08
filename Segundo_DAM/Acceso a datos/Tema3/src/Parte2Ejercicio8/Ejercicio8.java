package Parte2Ejercicio8;

public class Ejercicio8 {

	public static void main(String[] args) {
		Ejercicio8Class billete1 = new Ejercicio8Class("Nixon", "9494949");
		Ejercicio8Class billete2=new Ejercicio8Class("Maria","asd55");
		System.out.println(billete1.toString());

		billete1.setfacturar("a455");
		billete1.setClase("turista");
		billete1.tipoClase();
		
		billete2.setfacturar("asd");
		billete2.setClase("vip");
		billete2.tipoClase();
		
	}

}
