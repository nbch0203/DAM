package boletin1;

public class Boletin1 {

	public static void main(String[] args) {
		
		//double precio = 25.4;
		//System.out.printf("$");
	//	System.out.printf("%6.2f",precio);
//		System.out.printf(" unidad");

	//	double x = 10000.0/3.0;
	//	System.out.printf("%9.3f",x);
		
		double costes=269.4635;
		System.out.printf("%10.3f%n",costes);
		//f significa los espacios a la derecha y la n es un salto de linea para el siguiente dato
		double precio=23;
		System.out.printf("%-10s%n","Total:"+precio);
		//S significa crear es numero de espacios a la izquierda

double n = 1.25036;
int x = 10;
System.out.printf("n = %.2f x = %d %n", n, x);
		//te continua creando datos en base al formato dado
double n1 = 1.25;
double x2 = 10;
String mitexto = "mi cadena";

System.out.printf("Mi decimal es: %.2f Mi entero es: %.0f Mi cadena es: %15s%n", n1, x2, mitexto);
	}

}
