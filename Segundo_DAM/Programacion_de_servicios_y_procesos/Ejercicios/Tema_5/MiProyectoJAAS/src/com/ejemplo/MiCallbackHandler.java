package com.ejemplo;

// Importamos las herramientas necesarias de JAAS y de entrada/salida
import javax.security.auth.callback.*;
import java.io.IOException;
import java.util.Scanner;

// "implements CallbackHandler": Firmamos un contrato diciendo "Yo sé hablar con el usuario"
public class MiCallbackHandler implements CallbackHandler {

    // Este es el método obligatorio. Recibe un array de "preguntas" (callbacks) que envía el LoginModule.
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        
        // Preparamos el Scanner para leer lo que el alumno escriba en la consola de Eclipse
        Scanner scanner = new Scanner(System.in);

        // Recorremos las preguntas una por una. 
        // Normalmente JAAS envía dos: una para el Nombre y otra para la Contraseña.
        for (Callback callback : callbacks) {
            
            // CASO 1: Si la pregunta es "¿Cómo te llamas?" (NameCallback)
            if (callback instanceof NameCallback) {
                // Convertimos el callback genérico a uno específico de Nombre para poder usar sus métodos
                NameCallback nameCallback = (NameCallback) callback;
                
                // Imprimimos el mensaje que definió el LoginModule (ej: "Usuario: ")
                System.out.print(nameCallback.getPrompt());
                
                // LEEMOS DEL TECLADO y guardamos el texto dentro del objeto callback
                // Es como si el camarero apuntara el nombre en la nota.
                nameCallback.setName(scanner.next()); 
                
            // CASO 2: Si la pregunta es "¿Cuál es tu contraseña?" (PasswordCallback)
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                
                // Imprimimos el mensaje (ej: "Contraseña: ")
                System.out.print(passwordCallback.getPrompt());
                
                // LEEMOS DEL TECLADO la contraseña
                String pass = scanner.next();
                
                // Guardamos la contraseña en el objeto callback. 
                // OJO: JAAS pide que la contraseña sea un array de caracteres (char[]), no un String, por seguridad.
                passwordCallback.setPassword(pass.toCharArray());
                
            } else {
                // Si nos llega un tipo de pregunta que no conocemos, lanzamos error.
                throw new UnsupportedCallbackException(callback);
            }
        }
        // Fin del bucle. Los objetos "callbacks" ahora están llenos con los datos del usuario.
    }
}