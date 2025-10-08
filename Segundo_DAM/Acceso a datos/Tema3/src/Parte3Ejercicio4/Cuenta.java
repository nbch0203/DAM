package Parte3Ejercicio4;

public class Cuenta {

	public static void main(String[] args) {
		CuentaClass persona1 =new CuentaClass("antonio",10.11);
		CuentaClass persona2=new CuentaClass("antonio",10.11);
		persona1.ingresar(10);
		System.out.println(persona1.getCantidad());
		persona1.retirar(10);
		System.out.println(persona1.getCantidad());
		System.out.println(persona2.equals(persona1));
	}

}
