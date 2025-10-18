package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		/*
		 * 5 practica: proceso de un fichero .xml
		 * 
		 * ENUNCIADO: Se trata de hacer un programa que lea el fichero oposición.xml y
		 * guardar en un fichero oposición_resultado.txt los datos de salnifa, que
		 * serán: Nif*pruebafisica*psicotécnico*media nota exámenes*apto o no apto Un
		 * alumno será apto si tienes las tres notas mayor o igual a 5, en el fichero
		 * que os mando el resultado seria: 55555m*6.7*5.3*6*APTO 44444m*4.7*6.3*5*NO
		 * APTO
		 * 
		 * extra: pedir los ficheros mediante scanner y añadir un mensaje informativo a
		 * las excepciones. Subir solo la clase Main.
		 */

		File xmlFile = new File("oposicion.xml");
		File txtFile = new File("oposicion_resultaod.txt");

		// Lectura de fichero xml
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			// estos métodos podemos usarlos combinados para normalizar el archivo XML
			doc.getDocumentElement().normalize();

			// almacenamos los nodos
			NodeList nList = doc.getElementsByTagName("alumno");

			String salidaTxt, apto;

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					int media = 0;
					apto = "";
					Element eElement = (Element) nNode;

					// Guardamos las notas
					for (int i = 0; i < eElement.getElementsByTagName("examen").getLength(); i++) {
						media += Integer.parseInt(eElement.getElementsByTagName("examen").item(i).getTextContent());
					}

					media = media / eElement.getElementsByTagName("examen").getLength();
					// Calculamos media
					if (media >= 5) {
						apto = "APTO";
					} else {
						apto = "NO APTO";
					}

					// Volcamos todo el contenido en un string
					salidaTxt = eElement.getAttribute("nif") + "*"
							+ eElement.getElementsByTagName("pruebafisica").item(0).getTextContent() + "*"
							+ eElement.getElementsByTagName("psicotecnico").item(0).getTextContent() + "*" + media + "*"
							+ apto;

					// Guardamos en el fichero txt
					System.out.println("Guardando informacion en el fichero txt... \n");
					writeDataInTxt(txtFile, salidaTxt);

				}
			}

			// Imprimimos el contenido del txt
			System.out.println("Leyendo información del fichero txt: \n");
			readTxtFile(txtFile);
		} catch (FileNotFoundException e) {
			System.out.println("El fichero xml no existe o no se encuentra");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error de entrada/salida");
			;
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error");
		}
	}

	private static void readTxtFile(File txtFile) throws FileNotFoundException, IOException {
		String linea;
		try (FileReader reader = new FileReader(txtFile); BufferedReader buffer = new BufferedReader(reader)) {
			while ((linea = buffer.readLine()) != null) {
				System.out.println(linea);
			}
		}
	}

	private static void writeDataInTxt(File txtFile, String textToSave) throws IOException {
		FileWriter writer = null;
		// Si no existe lo creamos, si existe añadimos
		if (!txtFile.exists()) {
			writer = new FileWriter(txtFile);
		} else {
			writer = new FileWriter(txtFile, true);
		}

		writer.write(textToSave + "\n");
		writer.close();
	}
}