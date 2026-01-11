
public class Partido {
	Jugador jugador1 = new Jugador("Federre", 6000);
	Jugador jugador2 = new Jugador("Anto", 7000);

	// Atributos
	String Jugador1;
	String Jugador2;
	String partido;
	int Jugador1puntuacion;
	int Jugador2puntuacion;
	String ganador;

	//constructor 
	public Partido(String jugador1, String jugador2) {
		this.Jugador1 = jugador1;
		this.Jugador2 = jugador2;
	}
    //metodo para mostrar la informacion de los jugadores
	public void mostrarInformacionJugadores() {
		System.out.println("Jugador 1: " + Jugador1 + " - " + jugador1.getPuntuacion());
		System.out.println("Jugador 2: " + Jugador2 + " - " + jugador2.getPuntuacion());
	}
	//metodo para determinar el ganador mediante un numero aleatorio mas su puntuacion

	public String determinarGanador() {

		Jugador1puntuacion = (int) ((Math.random() * 100) + 1);
		Jugador1puntuacion *= jugador1.getPuntuacion();
		Jugador2puntuacion = (int) ((Math.random() * 100) + 1);
		Jugador2puntuacion *= jugador2.getPuntuacion();

		
		if (Jugador1puntuacion == Jugador2puntuacion) {
			do {
				Jugador1puntuacion = (int) ((Math.random() * 100) + 1);
				Jugador1puntuacion *= jugador1.getPuntuacion();
				Jugador2puntuacion = (int) ((Math.random() * 100) + 1);
				Jugador2puntuacion *= jugador2.getPuntuacion();

			} while (Jugador1puntuacion == Jugador2puntuacion);
		}
		else if (Jugador1puntuacion > Jugador2puntuacion) {
			ganador = (jugador1.getNombre());
			System.out.println("EL ganador es : " + ganador);
		}

		else if (Jugador2puntuacion > Jugador1puntuacion) {
			ganador = jugador2.getNombre();
		}
		return ganador;

	}//metodo para generar una nueva puntuacion 

	public void jugar() {
		System.out.println("Nueva puntucación de " + jugador1.getNombre() + " : " + jugador1.puntuacion);
		jugador2.incrementarPuntuacion();
		System.out.println("Nueva puntucación de " + jugador2.getNombre() + " : " + jugador2.puntuacion);
	}

}
