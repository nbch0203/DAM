package Parte3Ejercicio4;

public class CuentaClass {
	String titular;
	double cantidad;

	public CuentaClass(String titular) {
		this.titular = titular;
	}

	public CuentaClass(String titular, double cantidad) {
		this(titular);
		this.cantidad = cantidad;
	}

	public CuentaClass(CuentaClass otraCuenta) {
		this(otraCuenta.getTitular(), otraCuenta.getCantidad());
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if(getClass()==obj.getClass()) {
			CuentaClass other= (CuentaClass) obj;
			return(this.titular.equals(other.getTitular()) && this.cantidad==other.getCantidad());
		}
		return false;
	}

	public void ingresar(double cantidad) {
		if (cantidad > 0) {
			this.cantidad = getCantidad() + cantidad;
		}
	}

	public void retirar(double cantidad) {
		if (cantidad <= this.cantidad) {
			this.cantidad -= cantidad;
			if (cantidad == this.cantidad) {
				setCantidad(0);
			}
		}
	}

}
