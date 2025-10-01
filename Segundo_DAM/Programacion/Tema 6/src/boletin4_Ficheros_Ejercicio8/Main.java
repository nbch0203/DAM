package boletin4_Ficheros_Ejercicio8;

import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Leer y parsear config.txt
        Configuracion config = leerConfig("config.txt");
        
        // 2. Serializar a binario
        guardarConfigBinaria(config, "config.dat");
        
        // 3. (Opcional) Deserializar y mostrar
        Configuracion configCargada = cargarConfigBinaria("config.dat");
        configCargada.getPropiedades().forEach((k, v) -> 
            System.out.println(k + " = " + v));
    }

    // Lee config.txt usando BufferedReader
    public static Configuracion leerConfig(String ruta) {
        Configuracion config = new Configuracion();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains("=")) {
                    String[] partes = linea.split("=", 2);
                    config.agregarPropiedad(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
        }
        return config;
    }

    // Serializa a archivo binario
    public static void guardarConfigBinaria(Configuracion config, String ruta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream(ruta))) {
            oos.writeObject(config);
        } catch (IOException e) {
            System.err.println("Error serializando: " + e.getMessage());
        }
    }

    // Deserializa desde archivo binario
    public static Configuracion cargarConfigBinaria(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream(ruta))) {
            return (Configuracion) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializando: " + e.getMessage());
            return null;
        }
    }
}