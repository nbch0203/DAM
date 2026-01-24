package EjerciciorepasoTema3;

public class TestCarrera {

	public static void main(String[] args) {
		Corredor corredor1 = new Corredor("Antonio", 100);
		Corredor corredor2 = new Corredor("Felipe", 100);
		Carrera carrera1 = new Carrera(corredor1, corredor2);
		System.out.println("Corredor 1: " + corredor1.getNombre() + " " + corredor1.getVelocidad());
		System.out.println("Corredor 2: " + corredor2.getNombre() + " " + corredor2.getVelocidad());

		System.out.println("******************************************************************");
		System.out.println(carrera1.calcularGanador());
		
	}

}
