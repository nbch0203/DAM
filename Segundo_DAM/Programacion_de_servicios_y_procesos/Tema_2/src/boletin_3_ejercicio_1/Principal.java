package boletin_3_ejercicio_1;

public class Principal {

	public static void main(String[] args) {
		Usuario usu = new Usuario();
		usu.start();
		
		Thread hilog= new Thread(null, usu, "grupo ejemplo");
		System.out.println(hilog.getName());

	}

}
