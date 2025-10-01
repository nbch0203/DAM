
public class Jugador {
	//atributos 
	String nombre;
	int puntuacion;
	
	//constructor1
	

	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntuacion =0;
		
	}
     //constructor 2
	public Jugador(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}
     //metodo para llamar a todos los parametros o settear 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	//metodo para incrementar la puntuacion de un jugador
	public void incrementarPuntuacion() {
		puntuacion++;
		
	}
	
	
	
}
