package boletin1_Ejercicio6;

public class Cuadrado implements IFigura2D {
	private double a = 11;

	@Override
	public double perimetro() {
		double peri = a * 4;
		return peri;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void escalar(double escala) {
		if (escala > 0) {
			escala *= a;
		} else
			escala /= a;

	}

	@Override
	public void imprimir() {
		System.out.println("El lado del cuadrado es de : " + getA());

	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

}
