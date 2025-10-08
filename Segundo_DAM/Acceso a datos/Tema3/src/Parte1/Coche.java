package Parte1;

public class Coche {
	String marca;
	String modelo;
	int Velocidad;
	int Km;
	// Constructores
	public Coche() {
	}

	// Constructores
	public Coche(String m, String mod) {
		marca = m;
		modelo = mod;
	}

	// Constructor de velocidad

	int getVelocidad() {
		return Velocidad;
	}
	//Velocidad=Velocidad+incremento
	void acelera(int incremento) {
		Velocidad+=incremento;
	}
	//Velocidad=Velocidad-decremento
	void frena(int decremento) {
		Velocidad-=decremento;
	}
}
