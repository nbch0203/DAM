package EjerciciorepasoTema3;

public class Contacto {
	private String nombre;

	public Contacto(String nombre, Telefono numero, String direccion) {
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean validartelefono(Telefono numero) {
		if (numero.getNumero().length() == 9) {
			return true;
		} else {
			numero.setNumero("999999999");
			return false;
		}
	}

}
