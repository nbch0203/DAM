package ejercicio_8;

// PalindromoChecker.java
public class Verificador {
    public static void main(String[] args) {
        // Verificar si se han introducido argumentos
        if (args.length < 1) {
            System.out.println("Error: No se han introducido parámetros");
            System.exit(1);
        }
        
        String cadena = args[0];
        
        // Verificar si la cadena está vacía
        if (cadena.length() == 0) {
            System.out.println("La cadena está vacía");
            System.exit(0);
        }
        
        // Verificar si es palíndromo
        if (esPalindromo(cadena)) {
            System.out.println("'" + cadena + "' ES un palíndromo");
        } else {
            System.out.println("'" + cadena + "' NO es un palíndromo");
        }
        
        System.exit(0);
    }
    
    // Método para verificar si una cadena es palíndromo
    private static boolean esPalindromo(String cadena) {
        // Eliminar espacios y convertir a minúsculas para mejor comparación
        String cadenaLimpia = cadena.replaceAll("\\s+", "").toLowerCase();
        int longitud = cadenaLimpia.length();
        
        for (int i = 0; i < longitud / 2; i++) {
            if (cadenaLimpia.charAt(i) != cadenaLimpia.charAt(longitud - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}