package com.ejemplo;

import java.security.Principal;

//Esta clase es simplemente un objeto para transportar el texto del Rol (ej: "ADMINISTRADOR"). JAAS exige que implemente la interfaz Principal
public class RolePrincipal implements Principal {
    private String name; // Aquí guardaremos "ADMINISTRADOR" o "ESTUDIANTE"

    // Constructor: Al crear la etiqueta, le ponemos el nombre
    public RolePrincipal(String name) {
        this.name = name;
    }

    // Método obligatorio: devolver el nombre de la etiqueta
    @Override
    public String getName() {
        return name;
    }
    
    // Método toString: para que al imprimirlo en la consola se vea bonito
    @Override
    public String toString() {
        return "Rol: " + name;
    }
}