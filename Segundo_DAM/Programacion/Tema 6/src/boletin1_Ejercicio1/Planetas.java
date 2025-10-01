package boletin1_Ejercicio1;

public class Planetas extends Astros {
	private Double distancia_sol;
	private Double orbita_al_sol;
	private String tiene_satelites;

	public Planetas(String nombre,Double masa, Double diametro_medio, Double rotacion, Double traslacion, Double distancia_media,
			Double distancia_sol, Double orbita_al_sol, String tiene_satelite) {
		super(nombre,masa, diametro_medio, rotacion, traslacion, distancia_media);
		this.distancia_sol = distancia_sol;
		this.orbita_al_sol = orbita_al_sol;
		this.tiene_satelites = tiene_satelite;
	}

	public Double getDistancia_sol() {
		return distancia_sol;
	}

	public void setDistancia_sol(Double distancia_sol) {
		this.distancia_sol = distancia_sol;
	}

	public Double getOrbita_al_sol() {
		return orbita_al_sol;
	}

	public void setOrbita_al_sol(Double orbita_al_sol) {
		this.orbita_al_sol = orbita_al_sol;
	}

	public String getTiene_satelites() {
		return tiene_satelites;
	}

	public void setTiene_satelites(String tiene_satelites) {
		this.tiene_satelites = tiene_satelites;
	}

	public String muestra() {
		return "Planetas masa=" + masa + ", diametro medio=" + diametro_medio + ", velocidad rotacion=" + rotacion
				+ ", velocidad de traslacion=" + traslacion + ", distancia media=" + distancia_media + ", distancia al sol="
				+ getDistancia_sol() + ", velocidad a la que orbita al sol=" + getOrbita_al_sol() + ", tiene satelites="
				+ getTiene_satelites() + ", masa=" + getMasa() + ", diametro medio=" + getDiametro_medio()
				+ ", velocidad de rotacion" + getRotacion() + ", velocidad de traslacion=" + getTraslacion()
				+ ", distancia media=" + getDistancia_media();
	}

	

	
}
