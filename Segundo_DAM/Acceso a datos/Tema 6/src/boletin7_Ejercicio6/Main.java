package boletin7_Ejercicio6;

public class Main {
	
	
	
	public static void main(String[] args) {
		try {
		throw new Exception("mensaje");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}finally {
			System.out.println("Ha pasado por aqui");
		}
	}
}
