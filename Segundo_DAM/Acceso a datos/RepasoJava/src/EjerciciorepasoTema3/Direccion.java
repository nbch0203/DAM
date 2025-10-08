package EjerciciorepasoTema3;

public class Direccion {
	private String calle;
	private String nombre;
	private String numero;
	private String portal;
	private String piso;
	private String puerta;
	private String cp;
	private String provincia;
	
	

	public Direccion(String calle, String nombre, String numero, String portal, String piso, String puerta, String cp,
			String provincia) {
		super();
		this.calle = calle;
		this.nombre = nombre;
		this.numero = numero;
		this.portal = portal;
		this.piso = piso;
		this.puerta = puerta;
		this.cp = cp;
		this.provincia = provincia;
	}

	public String getDireccion() {
		return calle;
	}

	public void setDireccion(String calle) {
		this.calle = calle;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}
