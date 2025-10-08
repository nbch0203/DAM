package boletin1_Ejercicio1;

public class Satelites extends Astros {
	private Double distancia_al_planeta;
	private Double orbita_planetaria;
	private String planeta_al_q_pertenece;

	public Satelites(String nombre, Double masa, Double diametro_medio, Double rotacion, Double traslacion,
			Double distancia_media, Double distancia_al_planeta, Double orbita_planetaria,
			String planeta_al_q_pertenece) {
		super(nombre, masa, diametro_medio, rotacion, traslacion, distancia_media);
		this.distancia_al_planeta = distancia_al_planeta;
		this.orbita_planetaria = orbita_planetaria;
		this.planeta_al_q_pertenece = planeta_al_q_pertenece;
	}

	public Double getDistancia_al_planeta() {
		return distancia_al_planeta;
	}

	public void setDistancia_al_planeta(Double distancia_al_planeta) {
		this.distancia_al_planeta = distancia_al_planeta;
	}

	public Double getOrbita_planetaria() {
		return orbita_planetaria;
	}

	public void setOrbita_planetaria(Double orbita_planetaria) {
		this.orbita_planetaria = orbita_planetaria;
	}

	public String getPlaneta_al_q_pertenece() {
		return planeta_al_q_pertenece;
	}

	public void setPlaneta_al_q_pertenece(String planeta_al_q_pertenece) {
		this.planeta_al_q_pertenece = planeta_al_q_pertenece;
	}

	public String muestra() {
		return "Satelites distancia al planeta=" + getDistancia_al_planeta() + ", orbita planetaria="
				+ getOrbita_planetaria() + ", planeta al que pertenece=" + getPlaneta_al_q_pertenece();
	}

}
