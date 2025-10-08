package ejercicio7;

public class Universidad {

	Persona[] array = new Persona[8];

	public void iniciarArray() {
		for (int i = 0; i < array.length; i++) {
			switch (i) {
			case 1: {
				
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				break;
			}
			case 5: {
				break;
			}
			case 6: {
				break;
			}
			case 7: {
				break;
			}
			case 8: {
				break;
			}

			}
		}
	}

	public boolean cambiarCurso(String dni, String nuevoCurso) {
		for (int i = 0; i < array.length; i++) {
			if (dni.equals(array[i].getDNI())) {
				if (array[i].getClass().equals(Estudiantes.class)) {
					Estudiantes aux = (Estudiantes) array[i];
					aux.matriculacionNuevoCurso(nuevoCurso);
					return true;
				}
			}

		}

		return false;

	}

	public boolean cambiarDepartamento(String dni, String departamento) {
		for (int i = 0; i < array.length; i++) {
			if (dni.equals(array[i].DNI)) {
				if (array[i].getClass().equals(Profesores.class)) {
					Profesores aux = (Profesores) array[i];
					aux.cambioDepartamento(departamento);
					return true;
				}

			}

		}
		return false;

	}

	public boolean cambiarSeccion(String dni, String seccion) {
		for (int i = 0; i < array.length; i++) {
			if (dni.equals(array[i].DNI)) {
				if (array[i].getClass().equals(Personal_de_servicio.class)) {
					Personal_de_servicio aux = (Personal_de_servicio) array[i];
					aux.trasladoSeccion_asigna(seccion);
					return true;
				}

			}

		}
		return false;
	}

	public void listarEmpleados() {
		for (int i = 0; i < array.length; i++) {
			if (array[i].getClass().equals(Empleado.class)) {
				System.out.println(array[i].toString());
			}
		}
	}

	public void listarProfesoresDepartamento(String departamento) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].getClass().equals(Profesores.class)) {
				System.out.println(array[i].toString());
			}
		}
	}

}
