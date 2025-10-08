package Parte3Ejercicio1;

public class Ejercicio1 {

	public static void main(String[] args) {
		Ejercicio1Class empleado1 = new Ejercicio1Class("Nixon", "8788");
		Ejercicio1Class empleado2=new Ejercicio1Class("Antonio", "asd");
		System.out.println("El nombre del empleado 1 es: "+empleado1.getNombre());
		System.out.println("El nombre del empleado 2 es: "+empleado2.getNombre());
		System.out.println("Existen un total de : "+Ejercicio1Class.getNumempleados()+" empleados");
	}

}
