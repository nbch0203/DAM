package ejercicio6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

//		Se pretende realizar una aplicación para esta facultad que gestione la
//		información sobre las personas vinculadas con la misma, que se pueden
//		clasificar en tres tipos: estudiantes, profesores y personal de servicio. A
//		continuación, se detalla qué tipo de información debe gestionar esta
//		aplicación:
//		● Por cada persona, se debe conocer, al menos, su nombre y apellidos,
//		su número de identificación y su estado civil.
//		● Con respecto a los empleados, sean del tipo que sean, hay que
//		saber su año de incorporación a la facultad y qué número de
//		despacho tienen asignado.
//		● En cuanto a los estudiantes, se requiere almacenar el curso en el
//		que están matriculados.
//		● Por lo que se refiere a los profesores, es necesario gestionar a qué
//		departamento pertenecen (lenguajes, matemáticas, arquitectura,
//		...).
//		● Sobre el personal de servicio, hay que conocer a qué sección están
//		asignados (biblioteca, decanato, secretaría, ...).
//		El ejercicio consiste, en primer lugar, en definir la jerarquía de clases de
//		esta aplicación. A continuación, debe programar las clases definidas en las
//		que, además de los constructores, hay que desarrollar los métodos
//		correspondientes a las siguientes acciones:
//		
//		Cambio del estado civil de una persona.
//		● Reasignación de despacho a un empleado.
//		● Matriculación de un estudiante en un nuevo curso.
//		● Cambio de departamento de un profesor.
//		● Traslado de sección de un empleado del personal de servicio.
//		● Imprimir toda la información de cada tipo de individuo.
//		Incluya un programa de prueba que instancie objetos de los distintos tipos
//		y pruebe los métodos desarrollados.

		Estudiantes estu1 = new Estudiantes("Nixon", "Cruz", "626262", "Soltero", "Ingenieria");
		Profesores prof1 = new Profesores("Felipe", "Sanz", "4554", "Soltero", "10/12/1985", "155", "Matematicas");
		Personal_de_servicio per1 = new Personal_de_servicio("Antonio", "Jimenez", "555", "Soltero", "10/11/1999",
				"122", "Norte");

		System.out.println(estu1);
		estu1.cambioEstado_civil("casado");

		System.out.println("-------------------------------");

		System.out.println(prof1.getNumero_desp());

		prof1.reasignacionNumero_desp("233");

		System.out.println(prof1.getNumero_desp());

		System.out.println("-------------------------------");

		System.out.println(prof1.getDepartamento());

		prof1.cambioDepartamento("Lengua");

		System.out.println(prof1.getDepartamento());

		System.out.println("-------------------------------");

		System.out.println(per1.getSeccion_asigna());

		per1.trasladoSeccion_asigna("Sur");

		System.out.println(per1.getSeccion_asigna());

		System.out.println("-------------------------------");

		System.out.println(estu1);
		System.out.println("-------------------------------");
		System.out.println(prof1);
		System.out.println("-------------------------------");
		System.out.println(per1);

	}
}