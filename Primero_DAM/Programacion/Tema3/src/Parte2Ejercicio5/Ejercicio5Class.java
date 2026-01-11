package Parte2Ejercicio5;

public class Ejercicio5Class {
	String matriculas;
	double asigmedia;
	double asignatura1, asignatura2, asignatura3;

	public Ejercicio5Class(String matriculas,double asignatura1,double asignatura2,double asignatura3) {
		this.matriculas = matriculas;
		this.asignatura1=asignatura1;
		this.asignatura2=asignatura2;
		this.asignatura3=asignatura3;
	}

	public double media() {
		asigmedia = asignatura1 + asignatura2 + asignatura3;
		asigmedia /= 3;
		return asigmedia;
	}

	public double getMedia() {
		return asigmedia;
	}

	public void ordAlumno() {
		for (int i = 0; i <= 10; i++) {
			if(i==asigmedia) {
				System.out.println(matriculas+" "+asigmedia);

			}
			if(i==asigmedia) {
				System.out.println(matriculas+" "+asigmedia);

			}
			if(i==asigmedia) {
				System.out.println(matriculas+" "+asigmedia);

			}
		}
	}

	@Override
	public String toString() {
		return (matriculas != null ? "matriculas=" + matriculas + ", " : "") + "asigmedia=" + asigmedia;
	}

}