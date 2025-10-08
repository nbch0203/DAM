package Parte2Ejercicio1;

public class Ejercicio1 {

	public static void main(String[] args) {
		Ejercicio1Class empleado=new Ejercicio1Class("hbd",7338);
		Ejercicio1Class empleado2=new Ejercicio1Class("as",73783);
		System.out.println(empleado.getNombre());
		System.out.println(empleado2.getNombre());
		empleado.setTelefono(1213123);
		empleado2.setTelefono(23234);
		System.out.println(empleado.getTelefono());
		System.out.println(empleado2.getTelefono());
		System.out.println(Ejercicio1Class.getnumepleado());
	}

}
