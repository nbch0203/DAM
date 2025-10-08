package boletin1_Ejercicio3;

public class CuentaBancaria {
	private String iban;
	private double saldo;

	public CuentaBancaria(String iban, double saldo) {
		super();
		this.iban = iban;
		this.saldo = saldo;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void ingresar(Double cantidad) {
		System.out.println("Ha ingresado :" + cantidad);
		cantidad += getSaldo();
		setSaldo(cantidad);
		System.out.println("\nTiene un total de :" + cantidad + "\n");
	}

	public void retirar(Double cantidad) {
		double aux = 0.0;
		aux = getSaldo() - cantidad;
		setSaldo(aux);

		System.out.printf("Te quedan en el banco un total de  : %.2f%n", aux);
		System.out.println("");
	}

	public void consultarSaldo() {
		System.out.printf("Tiene un saldo de : %.2f%n", getSaldo());
		System.out.println("");

	}
}
