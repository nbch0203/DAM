package Parte3Ejercicio2;

public class Ejercicio2 {

	public static void main(String[] args) {
		Ejercicio2Class persona1 = new Ejercicio2Class("bkf","Nixon", 21, " ", 1.80, 68.8);
		Ejercicio2Class persona2=new Ejercicio2Class("bkf","Nixon",21," ",1.80,68.8);
		System.out.println(persona1.esMayorDeEdad());
		
		persona1.calcularIMC();
		System.out.println(persona1.toString());
		System.out.println(persona2.toString());
		System.out.println(Ejercicio2Class.getSexo());
		System.out.println(Ejercicio2Class.getSexo());
		System.out.println(persona2.equals(persona1));
		
	}

}
