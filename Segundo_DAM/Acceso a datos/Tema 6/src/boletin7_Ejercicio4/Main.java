package boletin7_Ejercicio4;

public class Main {
	public static int devulveNumero(int num) {
		try {
			if (num % 2 == 0) {
				throw new Exception("Lanzando excepcion");
			}
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(devulveNumero(1));
	}
}
