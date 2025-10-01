package boletin1.EjerciciosParte2;

public class Empleado2 {
	    // Atributos de la clase
	    private String nombre;
	    private int edad;
	    private int numeroEmpleado;
	    private double salario;

	    // Constructor con parámetros para inicializar los atributos
	    public Empleado2(String nombre, int edad, int numeroEmpleado, double salario) {
	        this.nombre = nombre;
	        this.edad = edad;
	        this.numeroEmpleado = numeroEmpleado;
	        this.salario = salario;
	    }

	    // Constructor sin parámetros (inicialización vacía)
	    public Empleado2() {
	    }

	    // Métodos getter y setter
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public int getEdad() {
	        return edad;
	    }

	    public void setEdad(int edad) {
	        this.edad = edad;
	    }

	    public int getNumeroEmpleado() {
	        return numeroEmpleado;
	    }

	    public void setNumeroEmpleado(int numeroEmpleado) {
	        this.numeroEmpleado = numeroEmpleado;
	    }

	    public double getSalario() {
	        return salario;
	    }

	    public void setSalario(double salario) {
	        this.salario = salario;
	    }

	    // Método plus que incrementa el salario si el empleado tiene más de 40 años
	    public void plus(double incremento) {
	        if (edad > 40) {
	            salario += incremento;
	        }
	    }

	    // Método plus que incrementa el salario aplicando retención si el empleado tiene más de 40 años
	    public void plus(double incremento, double retencion) {
	        if (edad > 40) {
	            salario += incremento - (incremento * retencion / 100);
	        }
	    }
	}


