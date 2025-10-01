package Tema4Ejercicio3;

public class Test {

//	Atributos de la clase

	private int numero_pregunta;
	private String enunciado;
	private String[] opciones = { "Hola", "Adios", "feliz", "triste" };
	private String correcta = "triste";

//	Getters y setters

	public int getNumero_pregunta() {
		return numero_pregunta;
	}

	public void setNumero_pregunta(int numero_pregunta) {
		this.numero_pregunta = numero_pregunta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public void presenta() {
		System.out.println(getEnunciado() + ":");
		System.out.println("**************************************************");
		for (int i = 0; i < opciones.length; i++) {
			if (i == 0) {
				System.out.print("A)");
				setNumero_pregunta(i);
			} else if (i == 1) {
				System.out.print("B)");
				setNumero_pregunta(i);
			} else if (i == 2) {
				System.out.print("C)");
				setNumero_pregunta(i);
			} else if (i == 3) {
				System.out.print("D)");
				setNumero_pregunta(i);
			}

			String opcion = opciones[i];
			System.out.print(opcion + "  ");
		}

	}

	public void resultado() {

		for (int j = 0; j < opciones.length; j++) {
			if (PruebaTest.respuesta.equalsIgnoreCase(correcta)) {
				switch (getNumero_pregunta()) {
				case 0:
					System.out.println("La respuesta correcta es la : A por lo que es correcto");
					break;

				case 1:
					System.out.println("La respuesta correcta es la : B por lo que es correcto");
					break;
				case 2:
					System.out.println("La respuesta correcta es la : C por lo que es correcto");
					break;
				case 3:
					System.out.println("La respuesta correcta es la : D por lo que es correcto");
					break;
				}
				break;

			}

		}
	}
}