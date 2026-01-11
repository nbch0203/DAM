package pcIP;

public class Aula {
	int[][] clase = new int[5][6];

	public void mostrar() {
		for(int [] fila :clase) {
			for(int pc :fila) {
				System.out.println(pc);
			}
		}
	}
	public void añadir(PC pcañadido) {
		for(int [] fila: clase) {
			for(int pc:fila) {
				if(pcañadido!=null) {
//					pcañadido=clase [][];
				}
			}
		}
	}
}
