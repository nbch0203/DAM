package Parte3Ejercicio3;

public class Ejercicio3 {

	public static void main(String[] args) {
		Ejercicio3Class empleado1=new Ejercicio3Class("Felipe","73747");
		Ejercicio3Class empleado2=new Ejercicio3Class("Felipe","73737");
		System.out.println(empleado2.getNombre());
		empleado1.equals(empleado2);
		System.out.println(Ejercicio3Class.getNumempleados());
	}

}
