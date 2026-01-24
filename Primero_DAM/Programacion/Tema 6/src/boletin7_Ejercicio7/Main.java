package boletin7_Ejercicio7;

public class Main {

	public static void main(String[] args) {
		try {
			throw new Prueba_exception("entrada");
		} catch (Prueba_exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			System.out.println("finally");
		}

	}

}
