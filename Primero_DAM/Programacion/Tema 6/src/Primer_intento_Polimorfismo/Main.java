package Primer_intentoPoli;

public class Main {
	public static void main(String[] args) {
		madre madre = new madre();
		Hija1 h1 = new Hija1();
		Hija2  h2 = new Hija2();
		
		//Otra variable madre
		madre madre2;
		
		// Asignamos a madre 2 el obj madre
		madre2 = madre;
		madre2.llamame();
		
		//AAsignamos a madre2 el obj h1
		madre2=h1;
		h1.llamame();
		
		//Asignamos a madre2 el obj h2
		madre2 =h2;
		h2.llamame();
		madre2.llamame();
		
	}
}
