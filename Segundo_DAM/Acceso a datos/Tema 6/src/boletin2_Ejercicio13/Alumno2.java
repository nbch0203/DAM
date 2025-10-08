package boletin2_Ejercicio13;

public class Alumno2 extends Alumno implements Comparar {

	public Alumno2(String nombre, int edad) {
		super(nombre, edad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMenor(Object objeto) {
		Alumno al = (Alumno) objeto;
		if (getEdad() < al.getEdad()) {

			System.out.println("El alumno 2 es menor que el alumno 1");
			return true;
		} else
			System.out.println("El alumno 2 es mayor que el alumno 1");
		return false;
	}

	@Override
	public boolean esMayor(Object objeto) {
		Alumno al = (Alumno) objeto;
		if (getEdad() > al.getEdad()) {
			System.out.println("El alumno 2 es mayor que el alumno 1");
			return true;
		} else {
			System.out.println("El alumno 2 es menor que el alumno 1");
			return false;
		}
	}

	@Override
	public boolean esIgual(Object objeto) {
		Alumno al = (Alumno) objeto;
		if (al.getNombre().equals(getNombre()) && al.getEdad() == (getEdad())) {
			System.out.println("Son iguales");
			return true;
		} else {
			System.out.println("No son iguales");
			return false;
		}
	}

}
