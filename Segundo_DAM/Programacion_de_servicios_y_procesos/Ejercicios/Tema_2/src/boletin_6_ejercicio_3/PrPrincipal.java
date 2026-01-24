package boletin_6_ejercicio_3;

public class PrPrincipal {
	public static void main(String[] args) {

		String Tic = "Tic";
		String Tac = "Tac";

		Thread tic = new Thread(new Tic(), Tic);
		Thread tac = new Thread(new Tac(), Tac);

		tic.start();
		tac.start();

		try {
			tic.join();
			tac.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
