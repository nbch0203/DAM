package boletin_7_ejercicio_2;


public class Consultorio  {
	private static Thread h;
	
	public void entrarConsulta(int idClliente) {
		long tiempoNecesitado = (long) (Math.random() * 10000);
		try {
			System.out.println("El paciente " + idClliente + " acaba de entrar a una consulta");
			
			
			Thread.sleep(tiempoNecesitado); // El cliente se tomara su tiempo para ser atendido por el m�dico.
			System.out.println("El paciente " + idClliente + " ha terminado en un tiempo " + tiempoNecesitado);
		} catch (InterruptedException E) {
			System.out.println("Se genero una excepcion entrando a consulta");
		}
	}
}
