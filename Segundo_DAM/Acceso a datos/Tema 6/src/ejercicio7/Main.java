package ejercicio7;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

//		Amplíe el ejercicio anterior creando una clase Universidad cuyo atributo
//		será un array de 8 posiciones donde se almacenarán objetos de tipo
//		Persona. Almacena en las posiciones 0, 1 y 6 objetos Profesor, en las
//		posiciones 2, 3 y 7 objetos Estudiante y en el resto de posiciones objetos
//		PersonalServicios.
//		A continuación, implementa los siguientes métodos en la clase
//		Universidad:
//				● cambiarCurso (dni, nuevoCurso): cambiará el curso a un Estudiante.
//				Si el dni no existe o no pertenece a un estudiante, se devuelve false
//				(true en caso contrario).
//				● cambiarDepartamento(dni, departamento): cambiará el
//				departamento a un Profesor. Si el dni no existe o no pertenece a un
//				profesor, se devuelve false (true en caso contrario).
//				● cambiarSeccion(dni, seccion): cambiará la seccion a un Personal de
//				Servicios. Si el dni no existe o no pertenece a un personal de
//				servicios, se devuelve false (true en caso contrario).
//				● listarEmpleados(): mostrará los empleados que trabajan en la
//				Universidad.
//				● listarProfesoresDepartamento(departamento): mostrará los
//				profesores que pertenezcan al departamento pasado como
//				parámetro.
//				
//				Implementa una clase de prueba para probar la clase Universidad y sus
//				métodos.
		
		Universidad uni = new Universidad();
		
		uni.listarEmpleados();

	}
}
