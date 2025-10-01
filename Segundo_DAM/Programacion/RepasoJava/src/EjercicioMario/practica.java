package EjercicioMario;
import java.util.Scanner;


public class practica {

	public static void main(String[] args) {
		Scanner en = new Scanner(System.in);
		int num = 10;
		int contador = 0;
		int km = 0;
		int contador2 = 0;
		int contadornega = 0;
		System.out.println("Mario necesita recorrer 5 km para rescatar a la princesa.");
		System.out.println(
				"Introduce hasta 10 numeros. Si el numero es positivo, Mario avanzará 1km. Si es negativo, retrocera 10 km(máximo 2veces de retroceso)");
		for (int i = 1; i <= num; i++) {

			System.out.print("Introduce un numero ( " + i + " de 10): ");
			// entrada de distancia

			int dismario = en.nextInt();
			// Valores positivos y negativos
			if (dismario > 0) {
				contador2++;
				km++;
				System.out.println("Mario avanza 1 Km. Ha recorrido " + km + " de 5Km.");
				// Mostrar valores si ha llegado a 5km
				if (km == 5) {
					System.out.print("Los pasos de Mario han sido: ");
					for (int o = 1; o <= contador2; o++) {
						System.out.print(" 1km ");
						
					}
					for(int p=1;p<=contadornega;p++) {
						System.out.print(" -2km ");
					}
					break;
				}
				// Valores negativos
			} else if (dismario == 0) {
				en.nextInt();
			} else if (dismario < 0) {
				contadornega++;
				contador++;
				dismario -= 2;
				if (contador == 2) {
					System.out.println(
							"Mario ya ha retrocedido el maximo de 2 veces. No puede retroceder mas. Introduce un nuevo numero. Mario esta en: "
									+ km + " de 5 Km");
				}

			}
			// Si llega a 10 intentos muestra valores finales
			

			if (i == num) {
				System.out.println("Mario no ha podido rescatar a la princesa. Ha recorrido " + km + " km de 5 km");

				System.out.print("Los pasos de Mario han sido: ");
				for (int o = 1; o <= contador2; o++) {
					System.out.print(" 1km ");
					
				}
				for(int p=1;p<=contadornega;p++) {
					System.out.print(" -2km ");
				}
			}

		}

		en.close();
	}

}
