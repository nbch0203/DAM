package modelo;

public class Cliente extends Persona {

	private String fecha_ent, fecha_sali, metodo_pago;
	private boolean pagado;

	public Cliente(String nombre, String apellido, String dni, String genero, boolean mayor_edad, String fecha_ent,
			String fecha_sali, String metodo_pago, boolean pagado) {
		super(nombre, apellido, dni, genero, mayor_edad);
		this.fecha_ent = fecha_ent;
		this.fecha_sali = fecha_sali;
		this.metodo_pago = metodo_pago;
		this.pagado = pagado;
	}

	public String getFecha_ent() {
		return fecha_ent;
	}

	public void setFecha_ent(String fecha_ent) {
		this.fecha_ent = fecha_ent;
	}

	public String getFecha_sali() {
		return fecha_sali;
	}

	public void setFecha_sali(String fecha_sali) {
		this.fecha_sali = fecha_sali;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	@Override
	public String toString() {
		return super.toString() + " " + "Cliente [getFecha_ent()=" + getFecha_ent() + ", getFecha_sali()="
				+ getFecha_sali() + ", getMetodo_pago()=" + getMetodo_pago() + ", isPagado()=" + isPagado() + "]";
	}

}
