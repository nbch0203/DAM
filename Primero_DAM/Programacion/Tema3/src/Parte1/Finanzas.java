package Parte1;

public class Finanzas {
	double euro;
	double dolares;
	double dinero;
	double conversion = 0.92;

	double getDinero() {
		return dinero;
	}

	void dolaresToEuros(double dolares) {
		 
		dinero = dolares * conversion;
	}

	void eurosToDolares(double euros) {
		 
		dinero = euros / conversion;
	}

}
