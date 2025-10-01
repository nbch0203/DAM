package boletin1_Ejercicio6;

public class Rectangulo implements IFigura2D{
	private double ancho=11;
	private double alto=12;
	@Override
	public double perimetro() {
		double peri=(alto*2)+(ancho*2);
		return peri;
	}
	@Override
	public double area() {
		double area=alto*ancho;
		return area;
	}
	@Override
	public void escalar(double escala) {
		if(escala>0) {
			
		}
		
	}
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		
	}
	
	
}
