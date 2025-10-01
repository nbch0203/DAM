package boletin1_Ejercicio3;

public class Main {

	public static void main(String[] args) {
		CuentaBancaria cuenta= new CuentaBancaria("1111",22.01);
		
		cuenta.consultarSaldo();
		cuenta.ingresar(22.01);
		cuenta.retirar(11.01);
		cuenta.consultarSaldo();
	}

}
