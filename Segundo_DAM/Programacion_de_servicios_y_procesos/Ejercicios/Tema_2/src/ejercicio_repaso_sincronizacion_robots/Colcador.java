package ejercicio_repaso_sincronizacion_robots;

public class Colcador extends Robot {
	private static int numero_radom = (int) (Math.random() * 3) + 1;

	@Override
	public void run() {
		colocar(getNumeros());

	}

	public static void colocar(int[] num) {

		for (int i = 0; i < num.length; i++) {
			if (num[i] != 0) {
				try {

					Thread.sleep(2000);
					num[i] = numero_radom;
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			} else
				System.out.println("La posicion :" + num[i] + " esta ocupada");
		}

	}

}
