package boletin1.EjerciciosParte2;

public class Empleado {
	String nombre;
	int edad;
	int numeroempleado;
	int salario;
	double retencion = 0.21;
	int plus = 200;
	double formula = plus * retencion / 100;

	public Empleado(String nom, int ed, int nume, int sl) {
		nom = nombre;
		ed = edad;
		nume = numeroempleado;
		sl = salario;

	}

	public double getSalario() {
		return salario;
	}

	public double plus() {
		if (edad > 40) {
			double aumento = salario + plus - (formula);
			salario += aumento;
		}
		return salario;
	}
}
