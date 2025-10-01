package boletin1_Ejercico4;

public class Empleados {
	private String nombre;
	private String DNI;
	private Double sueldo_bruto;
	private int edad;
	private String telefono;
	private String direccion;

	public Empleados(String nombre, String DNI, Double sueldo_burto) {
		this.nombre = nombre;
		this.DNI = DNI;
		this.sueldo_bruto = sueldo_burto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Double getSueldo_bruto() {
		return sueldo_bruto;
	}

	public void setSueldo_bruto(Double sueldo_bruto) {
		this.sueldo_bruto = sueldo_bruto;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
