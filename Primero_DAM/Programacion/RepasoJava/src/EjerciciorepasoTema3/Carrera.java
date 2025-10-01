package EjerciciorepasoTema3;

public class Carrera {
	private double pista = 0.1;
	private String ganador;

	Corredor corredor1, corredor2;

	public Carrera(Corredor corredor1, Corredor corredor2) {
		this.corredor1 = corredor1;
		this.corredor2 = corredor2;
	}

	public double getPista() {
		return pista;
	}

	public String calcularGanador() {
		double total;
		double total2;
		double variacion1=corredor1.getVelocidad()*(Math.random() * 0.1) - 0.05;
		double variacion2=corredor1.getVelocidad()*(Math.random() * 0.1) - 0.05;
		total=corredor1.getVelocidad()*(1+variacion1)+pista;
		total2=corredor1.getVelocidad()*(1+variacion2)+pista;
		System.out.println(total);
		System.out.println(total2);
		System.out.println();
		if (total > total2) {
			ganador = corredor1.getNombre();
			return ganador;
		} else {
			ganador = corredor2.getNombre();
			return ganador;
		}
	}
//	public Corredor calcularGanador() {
//
//		double rendimiento1 = c1.getVelocidad()*Math.random();		
//		double rendimiento2 = c1.getVelocidad()*Math.random();
//
//		if (rendimiento1>rendimiento2) {
//			return c1;
//		}else {
//			return c2;
//		}
//	}



	public void competir() {
		if (ganador==corredor1.getNombre()) {
			corredor1.mejorarVelocidad();
		} else {
			corredor2.mejorarVelocidad();
		}
	}
	
//	public class SimulacionRendimiento {
//	    public static void main(String[] args) {
//	        // Velocidad promedio en unidades por segundo (por ejemplo, km/h)
//	        double velocidadPromedio = 100.0; // Cambiar según el caso
//	        
//	        // Generar una variación aleatoria entre -0.05 y 0.05
//	        double variacionPorcentual = (Math.random() * 0.1) - 0.05; // ±5%
//	        
//	        // Calcular la velocidad ajustada
//	        double velocidadAjustada = velocidadPromedio * (1 + variacionPorcentual);
//	        
//	        // Imprimir resultados
//	        System.out.printf("Velocidad promedio: %.2f\n", velocidadPromedio);
//	        System.out.printf("Variación porcentual: %.2f%%\n", variacionPorcentual * 100);
//	        System.out.printf("Velocidad ajustada: %.2f\n", velocidadAjustada);
//	    }
//	}
	


}
