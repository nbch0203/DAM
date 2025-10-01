package Tema4Ejercicio10;

public class Tema4Ejercicio10 {
	int[][] matriz = new int[10][10];

	public void mostrarMatriz() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int colum = 0; colum < matriz.length; colum++) {
				if (fila == colum) {
					matriz[fila][colum] = 1;
				} else {
					matriz[fila][colum] = 0;
				}
				System.out.println("matriz unitaria de orden 10: ");
				for (int i = 0; i < 10; i++) {
					for (int j=0; j < 10; j++) {
						System.out.println(matriz[fila][colum] + " ");
					}
					System.out.println();
				}
			}
		}
	}

	public static void main(String[] args) {
		Tema4Ejercicio10 int1 = new Tema4Ejercicio10();

//		int1.mostrarMatriz();
		  int[][] matriz = new int[10][10];

	        // Rellenar la matriz unitaria
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                if (i == j) {
	                    matriz[i][j] = 1; // Asignar 1 en la diagonal principal
	                } else {
	                    matriz[i][j] = 0; // Asignar 0 en las demás posiciones
	                }
	            }
	        }

	        // Imprimir la matriz
	        System.out.println("Matriz unitaria de orden 10:");
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                System.out.print(matriz[i][j] + " ");
	            }
	            System.out.println(); // Salto de línea para cada fila
	        }
	    }
	}
