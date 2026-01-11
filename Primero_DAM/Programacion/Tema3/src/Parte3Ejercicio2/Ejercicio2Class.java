package Parte3Ejercicio2;

public class Ejercicio2Class {
//  Atributos de la clase
	private String DNI;
	private static String sexo = "";
	private String nombre = "";
	private int edad = 0;
	private double peso = 0;
	private double altura = 0;

	// Constructor inicializador vacio
	public Ejercicio2Class() {
	}

//	Constructor inicializador de la clase con sus parametros
	public Ejercicio2Class(String DNI, String nombre, int edad, String sexo) {
		sexo = "Hombre";
		this.DNI = DNI;
		this.nombre = nombre;
		this.edad = edad;
		Ejercicio2Class.sexo = comprobarSexo();
	}

//	Segundo constructor para posibles parametros extra
	public Ejercicio2Class(String DNI, String nombre, int edad, String sexo, double peso, double altura) {
		this(DNI, nombre, edad, sexo);
		this.peso = peso;
		this.altura = altura;
	}

//	1º método para calcular el IMC de la persona y saber si esta saludable 
	public void calcularIMC() {
		if (altura > 0 && peso > 0) {
			double imc = getPeso() / (getAltura() * getAltura());
			if (imc < 20) {
				System.out.println("-1");
			} else if (imc >= 20 || imc <= 25) {
				System.out.println("0");
			} else {
				System.out.println("1");
			}
		}
	}

	public boolean esMayorDeEdad() {
		boolean mayor = false;
		if (getEdad() >= 18) {
			mayor = true;
		}

		return mayor;
	}

	private String comprobarSexo() {
		if (sexo.equalsIgnoreCase("Hombre") || sexo.equalsIgnoreCase("Mujer")) {
		} else {
			setSexo("Hombre");
		}
		return sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public static String getSexo() {
		return Ejercicio2Class.sexo;
	}

	public static void setSexo(String sexo) {
		Ejercicio2Class.sexo = sexo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return (DNI != null ? "DNI=" + DNI + ", " : "") + (nombre != null ? "nombre=" + nombre + ", " : "") + "edad="
				+ edad + ", peso=" + peso + ", altura=" + altura;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Ejercicio2Class) {
			Ejercicio2Class d = (Ejercicio2Class) obj;
			if (this.nombre.equals(d.getNombre()) && this.DNI.equals(getDNI())) {
				return (true);
			}

		}
		return false;
	}
}
