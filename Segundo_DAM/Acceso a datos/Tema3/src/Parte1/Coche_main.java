package Parte1;

public class Coche_main {

	public static void main(String[] args) {
		Coche minivan = new Coche();
		Coche furgoneta = new Coche("Ferrari", "SC02");
		System.out.println("La marca de la minivan es: " + minivan.marca + " y el modelo es : " + minivan.modelo);
		System.out.println("La marca de la minivan es: " + furgoneta.marca + " y el modelo es : " + furgoneta.modelo);
		System.out.println("" + "" + "");
		Coche c1 = new Coche();
		System.out.println(c1.Velocidad);
		c1.acelera(100);
		System.out.println(c1.Velocidad);
		c1.frena(5);
		System.out.println(c1.Velocidad);

		Finanzas euro = new Finanzas();
		euro.dolaresToEuros(1.0);
		System.out.println(euro.dinero);
		euro.eurosToDolares(4.0);
		System.out.printf("%.2f",euro.dinero);
		
		
		minumero num1=new minumero(5);
		System.out.println("");
		System.out.println(num1.getDoble());
		System.out.println(num1.getTriple());
		System.out.println(num1.getCuadruple());
		
		
		System.out.println("");
		Numero num2=new Numero(2);
		System.out.println(num2.numero);
		num2.getValor(1);
		System.out.println(num2.numero);
		num2.getResta();
		System.out.println(num2.numero);
		
		System.out.println(num2.getDoble());
		System.out.println(num2.getTriple());
		num2.setNumero(3);
		System.out.println(num2.numero);
	}

}
