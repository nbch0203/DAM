package ejercicio_repaso_sincronizacion;

public class Empaquetador extends Robot {
	@Override
	public void run() {

	}

	public static void empaquetar(int[] num) {
		try {
			for (int i = 0; i < num.length; i++) {
				switch (num[i]) {
				case 1:

					num[i] = 0;
					break;

				case 2:
					num[i] = 0;

					break;
				case 3:

					Thread.sleep(15000);

					num[i] = 0;

					break;
				default:
					Thread.currentThread().wait();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
