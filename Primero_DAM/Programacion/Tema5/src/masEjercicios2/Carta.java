package masEjercicios2;

import java.util.Objects;

public class Carta {
	private Integer numero;
	private String palo;

	private static String[] n = { "as", "dos", "tres", "cuatro", "cinco", "seis", "siete", "sota", "caballo", "rey" };

	private static String[] p = { "bastos", "copas", "espadas", "oros" };

	public Carta() {
		this.numero = (int) (Math.random() * 10);
		this.palo = p[(int) (Math.random() * 4)];
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	@Override
	public String toString() {
		return "numero=" + numero + " palo= " + palo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, palo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (!this.numero.equals(other.getNumero()) || !this.palo.equals(other.getPalo())) {
			return false;
		} else {
			return true;
		}
	}
	
	public int compareTo(Carta c) {
		
		if (palo.equals(c.getPalo())) {
			return numero.compareTo(c.getNumero());
		} else {
			return palo.compareTo(c.getPalo());
		}
	}

}
