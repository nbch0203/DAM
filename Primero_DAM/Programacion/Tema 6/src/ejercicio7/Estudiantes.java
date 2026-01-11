package ejercicio7;

public class Estudiantes extends Persona {
	String curso_matriculado;

	public Estudiantes(String nombre, String apellidos, String dNI, String estado_civil, String curso_matriculado) {
		super(nombre, apellidos, dNI, estado_civil);
		this.curso_matriculado = curso_matriculado;
	}

	public void sabercurso() {
		System.out.println("Este estudiante esta matriculado en :" + curso_matriculado);
	}

	public void matriculacionNuevoCurso(String curso_matriculado) {
		this.curso_matriculado = curso_matriculado;
	}

	@Override
	public String toString() {
		return "Curso_matriculado=" + curso_matriculado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", DNI="
				+ DNI + ", estado_civil=" + estado_civil;
	}

}
