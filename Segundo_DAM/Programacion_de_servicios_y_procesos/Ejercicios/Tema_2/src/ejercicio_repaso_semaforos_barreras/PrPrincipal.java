package ejercicio_repaso_semaforos_barreras;

public class PrPrincipal {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			Clientes c = new Clientes("cliente" + i);
			Thread h = new Thread(c);
			h.start();
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
