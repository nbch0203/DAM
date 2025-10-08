package boletin4_Ficheros_Ejercicio6;

import java.io.Serializable;

public class CuentaBancaria implements Serializable {
	private String titular;
	private double saldo;

	public CuentaBancaria(String titular, double saldo) {
		this.titular = titular;
		this.saldo = saldo;
	}

	public CuentaBancaria() {
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double consultarSaldo() {
		return saldo;
	}

	public double depositar(double saldo) {

		if (saldo < 0) {
			System.out.println("Tienes que depositar algo");
		}

		saldo += consultarSaldo();

		return saldo;
	}

	public double retirar(double saldo) {
		if (saldo > consultarSaldo()) {
			System.out.println("No se puede retirar mas dinero del que tienes");
		} else {

			setSaldo(consultarSaldo() - saldo);
		}
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return titular + " - " + saldo;
	}

}
