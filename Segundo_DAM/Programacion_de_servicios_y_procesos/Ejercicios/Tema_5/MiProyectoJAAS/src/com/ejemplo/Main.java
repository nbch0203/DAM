package com.ejemplo;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import java.security.Principal;

public class Main {
    public static void main(String[] args) {
        
        // PASO 1: Configuración
        // Le decimos a la JVM dónde está el archivo .config que dice qué LoginModule usar.
        // Sin esto, Java no sabe qué clase cargar.
        System.setProperty("java.security.auth.login.config", "jaas.config");

        try {
            // PASO 2: Crear el contexto de Login
            // "EjemploLogin": Es el nombre que pusimos dentro del archivo jaas.config
            // new MiCallbackHandler(): Le damos nuestro "Camarero" para que lo use.
            LoginContext lc = new LoginContext("EjemploLogin", new MiCallbackHandler());

            System.out.println("Iniciando sistema de seguridad...");
            
            // PASO 3: Intentar Loguearse
            // Esta línea salta al TxtLoginModule -> método login()
            // Si falla la contraseña, aquí saltará una Excepción y el programa irá al 'catch'.
            lc.login(); 

            // Si llegamos a esta línea, es que la contraseña era correcta
            System.out.println("¡Login Correcto!");

            // PASO 4: Autorización (¿Qué permisos tengo?)
            // Obtenemos al Sujeto ya autenticado (con sus etiquetas pegadas)
            Subject subject = lc.getSubject();

            // Imprimimos todas sus etiquetas para verlas
            System.out.println("Usuario autenticado con: " + subject.getPrincipals());

            // --- EJEMPLO DE CONTROL DE ACCESO (RBAC) ---
            
            // Verificamos si es ADMINISTRADOR
            if (tieneRol(subject, "ADMINISTRADOR")) {
                System.out.println(">>> Tienes acceso TOTAL al sistema.");
            } else {
                System.out.println(">>> Bloqueado: No eres administrador.");
            }

            // Verificamos si es ESTUDIANTE
            if (tieneRol(subject, "ESTUDIANTE")) {
                System.out.println(">>> Puedes ver tus calificaciones.");
            }

        } catch (Exception e) {
            // Si la contraseña falla o el fichero no existe, caemos aquí
            System.err.println("ACCESO DENEGADO: " + e.getMessage());
        }
    }

    // Método auxiliar (hecho por nosotros) para buscar dentro de las etiquetas del usuario
    public static boolean tieneRol(Subject subject, String rolBuscado) {
        // Recorremos todas las etiquetas (Principals) que tiene el usuario
        for (Principal p : subject.getPrincipals()) {
            // Si la etiqueta es de tipo Rol y el nombre coincide...
            if (p instanceof RolePrincipal && p.getName().equals(rolBuscado)) {
                return true; // ¡Lo tiene!
            }
        }
        return false; // No lo tiene
    }
}