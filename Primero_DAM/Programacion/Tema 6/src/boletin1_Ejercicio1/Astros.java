package boletin1_Ejercicio1;

public abstract class Astros {
	String nombre;
	Double masa;
	Double diametro_medio;
	Double rotacion;
	Double traslacion;
	Double distancia_media;

	public Astros(String nombre,Double masa, Double diametro_medio, Double rotacion, Double traslacion, Double distancia_media) {
		super();
		this.nombre=nombre;
		this.masa = masa;
		this.diametro_medio = diametro_medio;
		this.rotacion = rotacion;
		this.traslacion = traslacion;
		this.distancia_media = distancia_media;
	}

	public Double getMasa() {
		return masa;
	}

	public void setMasa(Double masa) {
		this.masa = masa;
	}

	public Double getDiametro_medio() {
		return diametro_medio;
	}

	public void setDiametro_medio(Double diametro_medio) {
		this.diametro_medio = diametro_medio;
	}

	public Double getRotacion() {
		return rotacion;
	}

	public void setRotacion(Double rotacion) {
		this.rotacion = rotacion;
	}

	public Double getTraslacion() {
		return traslacion;
	}

	public void setTraslacion(Double traslacion) {
		this.traslacion = traslacion;
	}

	public Double getDistancia_media() {
		return distancia_media;
	}

	public void setDistancia_media(Double distancia_media) {
		this.distancia_media = distancia_media;
	}

	abstract public String muestra();

}
