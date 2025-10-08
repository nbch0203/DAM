package boletin1.EjerciciosParte2;

public class EjerciciosParte2 {

	public static void main(String[] args) {
//		Ejercicio1

//		Crea la clase empleado con los siguientes atributos: nombre, edad, número empleado, salario.
//		Utiliza tipos de datos apropiados para cada atributo.
//		Crea un constructor donde se inicialicen los atributos y otro constructor donde no se
//		inicialicen.
//		Después crea los métodos get y set para poder modificar los atributos utilizando estos
//		métodos.
//		Por último crea dos métodos que se llamen plus con los siguientes argumentos:
//		 El primer método plus tendrá como argumento la cantidad de dinero a incrementar
//		sobre el salario que tiene. Si el empleado tiene más de 40 años entonces se realiza la
//		operación de subida de salario.
//		 El segundo método tendrá como argumento la cantidad de dinero a incrementar y la
//		retención que le hacen. Si el empleado tiene más de 40 años entonces se realiza la
//		operación de subida de salario. La fórmula para el nuevo salario será:
//		o Salario actual = salario actual + plus – (plus*retención/100)
//		Donde el salario actual es el que tiene asignado actualmente el empleado, el
//		plus es la cantidad de dinero que se le quiere incrementar al salario actual y
//		retención es el % de retención que se le quiere hacer sobre ese extra.

		// Creación de un empleado usando el constructor con parámetros
		Empleado2 empleado1 = new Empleado2("Juan Pérez", 45, 12345, 3000.0);

		// Uso de los métodos getter
		System.out.println("Nombre: " + empleado1.getNombre());
		System.out.println("Edad: " + empleado1.getEdad());
		System.out.println("Número de empleado: " + empleado1.getNumeroEmpleado());
		System.out.println("Salario inicial: " + empleado1.getSalario());

		// Uso del método plus para incrementar salario sin retención
		empleado1.plus(500);
		System.out.println("Salario después del primer plus: " + empleado1.getSalario());

		// Uso del método plus para incrementar salario con retención
		empleado1.plus(500, 10); // Incremento de 500 con 10% de retención
		System.out.println("Salario después del segundo plus: " + empleado1.getSalario());

		// Modificación de atributos usando los métodos setter
		empleado1.setNombre("Carlos Ramírez");
		empleado1.setEdad(30);
		empleado1.setNumeroEmpleado(54321);
		empleado1.setSalario(2500.0);

		// Impresión de los atributos modificados
		System.out.println("\nDespués de modificar los atributos:");
		System.out.println("Nombre: " + empleado1.getNombre());
		System.out.println("Edad: " + empleado1.getEdad());
		System.out.println("Número de empleado: " + empleado1.getNumeroEmpleado());
		System.out.println("Salario: " + empleado1.getSalario());

		// Creación de un segundo empleado usando el constructor sin parámetros
		Empleado2 empleado2 = new Empleado2();

		// Establecimiento de atributos usando set
		empleado2.setNombre("Laura Gómez");
		empleado2.setEdad(42);
		empleado2.setNumeroEmpleado(67890);
		empleado2.setSalario(2800.0);

		// Uso de los métodos getter para el segundo empleado
		System.out.println("\nDatos del segundo empleado:");
		System.out.println("Nombre: " + empleado2.getNombre());
		System.out.println("Edad: " + empleado2.getEdad());
		System.out.println("Número de empleado: " + empleado2.getNumeroEmpleado());
		System.out.println("Salario inicial: " + empleado2.getSalario());

		// Uso de los métodos plus en el segundo empleado
		empleado2.plus(300);
		System.out.println("Salario después del primer plus: " + empleado2.getSalario());

		empleado2.plus(200, 5);
		System.out.println("Salario después del segundo plus: " + empleado2.getSalario());
	}

}
