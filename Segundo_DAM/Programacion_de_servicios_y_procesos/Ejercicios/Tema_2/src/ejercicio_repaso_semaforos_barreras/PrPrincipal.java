package ejercicio_repaso_semaforos_barreras;

public class PrPrincipal {
	public static void main(String[] args) {

		Tienda tienda = new Tienda(); // UNA SOLA tienda para todos
		tienda.rellenar(tienda.getProbadores());

		for (int i = 0; i < 10; i++) {
			Clientes c = new Clientes("cliente" + i, tienda);
			Thread h = new Thread(c);
			h.start();
			// NO uses h.join() aquí para permitir concurrencia
		}
	}

}
