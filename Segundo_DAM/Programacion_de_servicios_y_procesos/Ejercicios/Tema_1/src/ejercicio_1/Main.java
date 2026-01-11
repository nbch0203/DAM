package ejercicio_1;

public class Main {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Debes pasar dos numeros enteros");
			return;
		}
		try {
			int num1 = Integer.parseInt(args[0]);
			int num2 = Integer.parseInt(args[1]);

			int inicio = Math.min(num1, num2);
			int fin = Math.max(num1, num2);

			int suma = 0;
			for (int i = inicio; i <= fin; i++) {
				suma += i;
			}
			System.out.println(suma); // 🔹 Solo imprimimos el resultado
		} catch (NumberFormatException e) {
			System.out.println("Los argumentos deben ser enteros.");
		}

	}

}
