package IntentodeArray;

public class intento1 {
	public static void main(String[] args) {

//		int mayor, menor, suma;
		int[] nums = { 3, 4, 8, 2 };

		System.out.println(suma(nums));
		System.out.println(calculaMayor(nums));
		System.out.println(calculaMenor(nums));
//		suma = 0;
//		menor = nums[0];
//		mayor = nums[0];
//
//		for (int i = 0; i < nums.length; i++) {
//			if (nums[i] > mayor) {
//				mayor = nums[i];
//			}
//			if (nums[i] < menor) {
//				menor = nums[i];
//			}
//			suma += nums[i];
//
//		}
//		System.out.println(mayor);
//		System.out.println(menor);
//		System.out.println(suma);

	}

	public static int calculaMayor(int nums[]) {
		int mayor=nums[0];

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > mayor) {
				mayor = nums[i];
			}
		}
		return mayor;

	}

	public static int calculaMenor(int nums[]) {
		int menor=nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < menor) {
				menor = nums[i];
			}
		}
		
		return menor;
	}

	public static int suma(int nums[]) {
		int suma=0;
		for (int i = 0; i < nums.length; i++) {
			suma += nums[i];
		}
		return suma;

	}

}
