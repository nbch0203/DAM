package boletin4_Ficheros_Ejercicio8;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Configuracion implements Serializable {
    private Map<String, String> propiedades = new HashMap<>();

    public void agregarPropiedad(String clave, String valor) {
        propiedades.put(clave, valor);
    }

    public Map<String, String> getPropiedades() {
        return propiedades;
    }
}