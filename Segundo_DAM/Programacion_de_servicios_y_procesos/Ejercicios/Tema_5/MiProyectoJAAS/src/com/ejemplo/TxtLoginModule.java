package com.ejemplo;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

//Aquí está la lógica pura. Verifica si el usuario existe en el fichero de texto.
// "implements LoginModule": Firmamos el contrato de "Yo sé verificar credenciales"
public class TxtLoginModule implements LoginModule {

    // VARIABLES DE LA CLASE
    private Subject subject;            // El "Sujeto": el usuario al que vamos a identificar
    private CallbackHandler callbackHandler; // El "Camarero": el que pedirá los datos
    private boolean loginSucceeded = false;  // Bandera: ¿La contraseña fue correcta?
    
    // Variables temporales para guardar los datos si el login es correcto antes de confirmar
    private String tempUser;
    private String tempRole;

    // MÉTODO 1: INITIALIZE (Configuración inicial)
    // Se ejecuta al arrancar. JAAS nos pasa las herramientas necesarias.
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, 
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject; // Guardamos el usuario vacío para llenarlo luego
        this.callbackHandler = callbackHandler; // Guardamos al camarero para llamarlo luego
    }

    // MÉTODO 2: LOGIN (La verificación)
    // Aquí es donde comprobamos la contraseña.
    @Override
    public boolean login() throws LoginException {
        // Si no hay camarero (nadie para pedir datos), cancelamos.
        if (callbackHandler == null) {
            throw new LoginException("Error: No hay CallbackHandler para pedir datos");
        }

        // Preparamos las "notas" vacías para el camarero
        NameCallback nameCb = new NameCallback("Escribe tu Usuario: ");
        PasswordCallback passCb = new PasswordCallback("Escribe tu Contraseña: ", false);

        try {
            // ¡ACCIÓN! Le decimos al camarero: "Vete a la consola y rellena esto"
            // El programa se detendrá aquí hasta que el usuario escriba en la consola
            callbackHandler.handle(new Callback[]{nameCb, passCb});
        } catch (Exception e) {
            throw new LoginException("Falló la lectura de datos: " + e.getMessage());
        }

        // Recuperamos lo que el usuario escribió en la consola
        String usuarioIngresado = nameCb.getName();
        String passwordIngresado = new String(passCb.getPassword()); // Convertimos char[] a String

        // AHORA LEEMOS EL FICHERO DE TEXTO (Nuestra "Base de Datos")
        // Usamos try-with-resources para que el fichero se cierre solo al terminar
        try (BufferedReader br = new BufferedReader(new FileReader("password.txt"))) {
            String linea;
            // Leemos línea por línea hasta el final del fichero
            while ((linea = br.readLine()) != null) {
                // Cortamos la línea usando el separador "/"
                // Ejemplo: entra "admin/1234/ADMIN" -> sale array ["admin", "1234", "ADMIN"]
                String[] partes = linea.split("/");
                
                // Verificamos que la línea tenga las 3 partes necesarias
                if (partes.length == 3) {
                    String uFichero = partes[0].trim(); // Usuario del fichero
                    String pFichero = partes[1].trim(); // Password del fichero
                    String rFichero = partes[2].trim(); // Rol del fichero

                    // COMPARACIÓN: ¿Coincide lo que escribió el usuario con lo que hay en el fichero?
                    if (uFichero.equals(usuarioIngresado) && pFichero.equals(passwordIngresado)) {
                        // ¡ÉXITO!
                        loginSucceeded = true;
                        this.tempUser = uFichero; // Guardamos datos temporalmente
                        this.tempRole = rFichero;
                        return true; // Retornamos true porque el login fue bien
                    }
                }
            }
        } catch (IOException e) {
            throw new LoginException("No se pudo leer el fichero password.txt");
        }

        // Si el bucle termina y no hemos retornado true, es que no encontramos al usuario
        loginSucceeded = false;
        throw new LoginException("Usuario o contraseña incorrectos");
    }

    // MÉTODO 3: COMMIT (Confirmación y Asignación de Roles)
    // Se ejecuta SOLO si login() devolvió true. Aquí pegamos las etiquetas (Principals) al usuario.
    @Override
    public boolean commit() throws LoginException {
        if (!loginSucceeded) return false; // Por seguridad, doble chequeo

        // 1. Creamos una etiqueta (Principal) con el NOMBRE del usuario
        //    (Clase anónima rápida para no crear otro fichero java solo para esto)
        Principal userPrincipal = new Principal() {
            public String getName() { return tempUser; }
        };
        // Pegamos la etiqueta al sujeto
        subject.getPrincipals().add(userPrincipal);

        // 2. Creamos una etiqueta con el ROL del usuario
        //    Usamos nuestra clase RolePrincipal
        RolePrincipal rolePrincipal = new RolePrincipal(tempRole);
        // Pegamos la etiqueta de rol al sujeto
        subject.getPrincipals().add(rolePrincipal);

        return true; // Todo correcto
    }

    // Métodos obligatorios pero que no usaremos en este ejemplo simple
    @Override
    public boolean abort() throws LoginException { return false; } // Se llama si el login falla para limpiar

    @Override
    public boolean logout() throws LoginException { 
        // Aquí deberíamos borrar los principals del subject para desloguear
        subject.getPrincipals().clear();
        return true; 
    }
}