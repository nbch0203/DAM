package Parte2Ejercicio6;

public class Ejercicio6 {
	public static void main(String[] args) {
		Ejercicio6Class persona1= new Ejercicio6Class("Nixon","ALBERTO" , 65465);
		
		persona1.realizaPedido(true,2,"pera");
		persona1.realizaPedido(true, 1, "tomate");
		persona1.realizaPedido(true, 5, "Manzana");
		persona1.realizaPedido(false, 2, "pera");
		persona1.realizaPedido(false, 1, "pera");
		persona1.mostrarLista(true);
	
	}

}
