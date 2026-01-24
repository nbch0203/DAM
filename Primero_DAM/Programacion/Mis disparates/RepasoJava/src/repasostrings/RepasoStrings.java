package repasostrings;

//import java.util.Scanner;

public class RepasoStrings {

	public static void main(String[] args) {

		// Ejercicio 1

//		Crea un programa que compare dos cadenas de texto, ignorando las diferencias entre
//		mayúsculas y minúsculas. Luego, realiza varias manipulaciones como reemplazar palabras,
//		convertir a mayúsculas y minúsculas, y buscar posiciones específicas de caracteres. También,
//		utiliza diferentes formas de obtener subcadenas a partir de un texto.
//		System.out.println("Entrada datos: ");
//
//		Scanner entrada = new Scanner(System.in);
//		String dato = entrada.nextLine();
//		Scanner entrada2 = new Scanner(System.in);
//		String dato2 = entrada.nextLine();
//
//		if (dato.equals(dato2)) {
//			System.out.println("True");
//		} else {
//			System.out.println("False");
//		}
//		if (dato.equalsIgnoreCase(dato2)) {
//			System.out.println("True");
//		} else {
//			System.out.println("False");
//		}
//		
//		System.out.println(dato.toLowerCase());
//		System.out.println(dato2.toUpperCase());
//		
//		String reemplazo="Hola Java";
//		System.out.println(dato.replaceAll(dato, reemplazo));
//		System.out.println(dato.indexOf("MUNDO"));
//		System.out.println(dato.indexOf("O",6));
//		System.out.println(dato.lastIndexOf("O"));
//		System.out.println(dato.startsWith("HOLA"));
//		System.out.println(dato.endsWith("MUNDO"));
//		
//		
//		
//		entrada2.close();
//		entrada.close();

		// Ejercicio 2

//		Crea un programa que manipule una cadena de texto larga, extrayendo subcadenas, buscando
//		caracteres en posiciones específicas, y verificando si comienza o termina con ciertos valores.
//		Además, convierte valores de tipos primitivos a cadenas y de cadenas a tipos primitivos.
//
		String cadena = "Bienvenido a la progrmación en Java";

		// Dime toda la cadena a partir de la posición 11
		System.out.println(cadena.substring(11));

		// Dime la cadena a partir de la posicion 11 hasta la 23
		System.out.println(cadena.subSequence(11, 23));

		// Dime el caracter en la posición 3
		System.out.println(cadena.charAt(3));

		// Como convertir de Int a String
		int numero = 100;
		String numero2 = String.valueOf(numero);
		System.out.println(numero2);

		// Como covertir de String a Int
		String cadeanentero = "123";
		int entero = Integer.parseInt(cadeanentero);
		System.out.println(entero);

		// Como convertir de String a booleano
		String valor = "true";
		boolean valorbooleano = Boolean.parseBoolean(valor);
		System.out.println(valorbooleano);

		// Como convertir de un double a String
		double decimal = Double.parseDouble("3.2");
		System.out.println(decimal);

		// Empieza con Bienvenido?
		System.out.println(cadena.startsWith("Bienvenido"));

		// Termina con Java?
		System.out.println(cadena.endsWith("Java"));

//		// Ejercicio 3
//		Crea un programa en Java que pida al usuario introducir un texto. El programa debe contar el
//		número de vocales mayúsculas y minúsculas que contiene el texto. El proceso se repetirá hasta
//		que el usuario introduzca la palabra "Fin" (sin distinción entre mayúsculas y minúsculas).
//
//		Scanner en = new Scanner(System.in);
//		System.out.println("Introduce el texto:");
//		String texto = en.nextLine();
//
//		int contador = 0;
//		int longitud = texto.length();
//		for (int i = 0; i < longitud; i++) {
//			if (i == longitud - 1 || texto.charAt(i + 1) == ' ') {
//				char ultimoCaracter = texto.charAt(i);
//				if (esVocalMayuscula(ultimoCaracter)) {
//					contador++;
//				}
//			}
//		}
//
//		System.out.println("Número de palabras que terminan con una vocal mayúscula: " + contador);
//		en.close();
//	}
//
//	// Función auxiliar para verificar si un carácter es una vocal mayúscula
//	public static boolean esVocalMayuscula(char c) {
//		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
//	}

		// Ejercicio4
//		    Crea un programa en Java que solicite al usuario un texto y cuente cuántas vocales mayúsculas
//		    y minúsculas aparecen al final de cada palabra en el texto. El proceso se repetirá hasta que el
//		    usuario introduzca la palabra "Fin" (ignorando mayúsculas y minúsculas). No se permite usar el
//		    método split().
//		int contador = 0;
//		Scanner entrada = new Scanner(System.in);
//		String palabra = entrada.nextLine();
//		System.out.println("Introduce un texto (escribe 'Fin' para terminar):");
//		boolean siesmayuscula
//		for (int i = 0; i < palabra.length(); i++) {
//			if (palabra.equals("Fin")) {
//				System.out.println("Programa finalizado.");
//				break;
//			} else if (palabra.equals(siesmayuscula)) {
//				contador++;
//				System.out.println("Numero de E: " + contador);
//
//			}
//			entrada.next();
//
//		}
//
//		entrada.close();
		// Ejercicio 5

//		// 1. INMUTABILIDAD: Las cadenas en Java son inmutables
//		String texto = "Hola"; // Se crea una cadena con el valor "Hola"
//		texto = texto + " Mundo"; // Se crea una nueva cadena con el valor "Hola Mundo", el original "Hola"
//		System.out.println(texto); // sigue igual
//
//		// 2. CREACIÓN DE CADENAS: Se puede crear usando literales o el constructor de
//		// String
//		String str1 = "Hola"; // Literal
//		System.out.println(str1);
//		String str2 = new String("Mundo"); // Usando el constructor
//		System.out.println(str2);
//
//		// 3. MÉTODOS MÁS COMUNES
//
//		// length(): Devuelve la longitud de la cadena
//		int len = texto.length(); // Longitud de "Hola Mundo" es 10
//		System.out.println(len);
//
//		// charAt(): Devuelve el carácter en una posición específica
//		char letra = texto.charAt(0); // Retorna 'H', el primer carácter
//		System.out.println(letra);
//
//		// substring(): Extrae una subcadena
//		String sub = texto.substring(1, 4); // Extrae "ola"
//		System.out.println(sub);
//
//		// equals(): Compara el contenido de las cadenas (distingue mayúsculas y
//		// minúsculas)
//		boolean esIgual = texto.equals("Hola Mundo"); // true
//		System.out.println(esIgual);
//
//		// equalsIgnoreCase(): Compara cadenas ignorando mayúsculas y minúsculas
//		boolean esIgualSinMayus = texto.equalsIgnoreCase("hola mundo"); // true
//		System.out.println(esIgualSinMayus);
//
//		// toUpperCase() y toLowerCase(): Convierte a mayúsculas o minúsculas
//		String mayus = texto.toUpperCase(); // "HOLA MUNDO"
//		String minus = texto.toLowerCase(); // "hola mundo"
//		System.out.println(mayus);
//		System.out.println(minus);
//
//		// contains(): Verifica si la cadena contiene una secuencia de caracteres
//		boolean contiene = texto.contains("Mundo"); // true
//		System.out.println(contiene);
//
//		// startsWith() y endsWith(): Verifica si la cadena comienza o termina con una
//		// secuencia específica
//		boolean empieza = texto.startsWith("Hola"); // true
//		boolean termina = texto.endsWith("Mundo"); // true
//		System.out.println(empieza);
//		System.out.println(termina);
//		// 4. CONCATENACIÓN DE CADENAS
//		// Usando el operador +
//		String saludo = "Hola" + " Mundo"; // "Hola Mundo"
//		System.out.println(saludo);
//		// Usando concat()
//		String saludo2 = "Hola".concat(" Mundo"); // "Hola Mundo"
//		System.out.println(saludo2);
//
//		// 5. COMPARACIÓN DE CADENAS
//		String str3 = "Hola";
//		String str4 = new String("Hola");
//
//		// Comparación con ==
//		System.out.println(str3 == str4); // false, diferentes objetos
//
//		// Comparación con equals()
//		System.out.println(str3.equals(str4)); // true, el contenido es igual
//
//		// 6. CONVERTIR OTROS TIPOS A CADENAS: String.valueOf()
//		int numero = 10;
//		String numeroStr = String.valueOf(numero); // "10"
//		System.out.println(numeroStr);
//
//		// 7. POOL DE CADENAS: Literal String reutiliza el mismo objeto en memoria
//		String a = "Hola";
//		String b = "Hola";
//		System.out.println(a == b); // true, apuntan al mismo objeto en el pool de cadenas
//
//		// 8. CONVERTIR CADENAS A OTROS TIPOS: Integer.parseInt(), Double.parseDouble()
//		String numeroTexto = "123";
//		int numeroInt = Integer.parseInt(numeroTexto); // 123
//		double numeroDbl = Double.parseDouble(numeroTexto); // 123.0
//		System.out.println(numeroInt);
//		System.out.println(numeroDbl);
//
//		System.out.println(" " + " " + " " + " ");
//
//		// Ejercicio 1: Muestra la longitud del texto: “This is Java!”
//		String texto1 = "This is Java!";
//		System.out.println("Ejercicio 1: Longitud del texto: " + texto1.length()); // Resultado: 12
//
//		// Ejercicio 2: Desmenuza el String “Hola Mundo” mostrando carácter a carácter
//		String texto2 = "Hola Mundo";
//		System.out.println("Ejercicio 2: Caracteres de 'Hola Mundo':");
//		for (int i = 0; i < texto2.length(); i++) {
//			System.out.println(texto2.charAt(i));
//		}
//
//		// Ejercicio 3: Invertir el texto: “Hola Mundo”
//		String texto3 = "Hola Mundo";
//		String textoInvertido = new StringBuilder(texto3).reverse().toString();
//		System.out.println("Ejercicio 3: Texto invertido: " + textoInvertido); // Resultado: "odnuM aloH"
//
//		// Ejercicio 4: Elimina los espacios del texto: “James Gosling Created Java”
//		String textoOriginal = "James Gosling Created Java";
//
//		// Eliminar los espacios usando replace
//		String resultadoReplace = textoOriginal.replace(" ", "");
//
//		// Eliminar los espacios usando replaceAll
//		String resultadoReplaceAll = textoOriginal.replaceAll("\\s", "");
//
//		// Mostrar los resultados con formato
//		System.out.println("----------------------------------------------------------------");
//		System.out.println("                        -- CON REPLACE --");
//		System.out.printf("Texto original: %-30s Texto resultado: %s\n", textoOriginal, resultadoReplace);
//		System.out.println("                        -- CON REPLACE ALL --");
//		System.out.printf("Texto original: %-30s Texto resultado: %s\n", textoOriginal, resultadoReplaceAll);
//		System.out.println("----------------------------------------------------------------");
//
////				        String texto5 = "Java Java Java";
//		int vocales = 0, consonantes = 0;
//		System.out.println("Ejercicio 5: Vocales: " + vocales + ", Consonantes: " + consonantes); // Vocales: 6,
//																									// Consonantes: 6
//
//		// Ejercicio 6: Divide la cadena de texto “Hola Mundo” en dos partes y vuelve a
//		// concatenarlas
//		String texto6 = "Hola Mundo";
//		String parte1 = texto6.substring(0, 5); // "Hola "
//		String parte2 = texto6.substring(5); // "Mundo"
//		String concatenado = parte1.concat(parte2);
//		System.out.println("Ejercicio 6: Concatenado de nuevo: " + concatenado); // Resultado: "Hola Mundo"
//
//		// Ejercicio 7: Transformar “Hola Mundo” a mayúsculas y luego a minúsculas
//		String texto7 = "Hola Mundo";
//		String mayus2 = texto7.toUpperCase();
//		System.out.println("Ejercicio 7: Texto en mayúsculas: " + mayus2); // "HOLA MUNDO"
//		String minus2 = mayus2.toLowerCase();
//		System.out.println("Ejercicio 7: Texto en minúsculas: " + minus2); // "hola mundo"
//
//		// Ejercicio 8: Compara si el String “Java” es igual que el String “JavaScript”
//		String strJava = "Java";
//		String strJavaScript = "JavaScript";
//		boolean sonIguales = strJava.equals(strJavaScript);
//		System.out.println("Ejercicio 8: ¿Java es igual a JavaScript? " + sonIguales); // Resultado: false
//
//		// Ejercicio 9: Transformar los caracteres del String: “ABCD” a ASCII
//		String texto9 = "ABCD";
//		System.out.println("Ejercicio 9: Caracteres en ASCII:");
//		for (char c : texto9.toCharArray()) {
//			System.out.println((int) c); // Resultado: 65 66 67 68}
	}
}