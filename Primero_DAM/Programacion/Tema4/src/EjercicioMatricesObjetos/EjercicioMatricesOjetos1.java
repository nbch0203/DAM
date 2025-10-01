package EjercicioMatricesObjetos;

public class EjercicioMatricesOjetos1 {
	static Polvorones[][] matriz = new Polvorones[2][3];

	public static void borrar(Polvorones p2) {
//		for (int f = 0; f < matriz.length; f++) {
//			for (int c = 0; c < matriz.length; c++) {
//				matriz[f][c] = 0;
//			}
//		}
//		int[] pos = buscar(p2.getSabor());
//		if (pos[0] != -1) {
//			matriz[pos[0]][pos[1]] = null;
//		}
	}

	public static void mostrarCesta() {
		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz[f].length; c++) {
				Polvorones pol = matriz[f][c];
				if (!pol.getSabor().equals(null)) {
					System.out.println(pol.getSabor() + " " + pol.getCantidadad());
					System.out.println("***************************");

				} else
					System.out.println("No hay polvoron");
			}
		}
	}

	public static void insertar(Polvorones pol) {
		for (int f = 0; f < matriz.length; f++) {
			for (int c = 0; c < matriz[f].length; c++) {
				if (pol != null) {
					if (matriz[f][c] == null) {
						matriz[f][c] = pol;
						System.out.println("Insertado :" + pol.getSabor());
						break;
					}

				}
			}
		}
	}

	public static boolean buscar(String sabor) {
		int[] posiciones = { -1, -1 };
		int f = 0, c;
		boolean encontrado = false;

		while (f < matriz.length && !encontrado) {
			c = 0;
			while (c < matriz[f].length && !encontrado) {
				Polvorones p = matriz[f][c];
				if (p != null) {
					if (p.getSabor().equals(sabor)) {
						posiciones[0] = f;
						posiciones[1] = c;
						encontrado = true;
					} else
						encontrado = false;
				}
			}
			c++;
		}
		f++;
		return encontrado;
	}

	public static void main(String[] args) {
		Polvorones fresa = new Polvorones("Fresa", 1);
		Polvorones choco = new Polvorones("Chocolate", 3);
		Polvorones queso = new Polvorones("Queso", 2);
		Polvorones vainilla = new Polvorones("Vainilla", 4);
		Polvorones menta = new Polvorones("Menta", 5);
		Polvorones dulce = new Polvorones("Dulce", 6);



		EjercicioMatricesOjetos1.insertar(queso);
		EjercicioMatricesOjetos1.insertar(vainilla);
		EjercicioMatricesOjetos1.insertar(fresa);
		EjercicioMatricesOjetos1.insertar(choco);
		EjercicioMatricesOjetos1.insertar(menta);
		EjercicioMatricesOjetos1.insertar(dulce);


//		EjercicioMatricesOjetos1.buscar("Fresa");
		EjercicioMatricesOjetos1.mostrarCesta();
	}

}
