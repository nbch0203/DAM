package boletin2_Ejercicio13;

public class Vehiculo2 extends Vehiculo implements Comparar {

	public Vehiculo2(double peso, String matricula) {
		super(peso, matricula);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean esMenor(Object objeto) {
		Vehiculo ve = (Vehiculo) objeto;
		if (getPeso() < ve.getPeso()) {
			System.out.println("El vehiculo 2 pesa menos que el vehiculo 1");
			return true;
		} else {
			System.out.println("El vehiculo 1 pesa menos que el vehiculo 2");
			return false;
		}

	}

	@Override
	public boolean esMayor(Object objeto) {
		Vehiculo ve = (Vehiculo) objeto;
		if (getPeso() > ve.getPeso()) {
			System.out.println("El vehiculo 2 pesa mas que el vehiculo 1");
			return true;
		} else {
			System.out.println("El vehiculo 1 pesa mas que el vehiculo 2");
			return false;
		}
	}

	@Override
	public boolean esIgual(Object objeto) {
		Vehiculo ve = (Vehiculo) objeto;
		if (ve.getMatricula().equals(getMatricula()) & ve.getPeso() == getPeso()) {
			System.out.println("Son iguales");
			return true;
		} else {
			System.out.println("No son iguales");
			return false;
		}
	}

}
