package model;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Gesto_clientes {
	private Cliente cl;
	private static File file = new File("fichero.txt");


	public void guardarTxt(String texto) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		if (!file.exists()) {
			file.createNewFile();
		}
		bw.write(texto + "\n");
		bw.close();
		fw.close();

	}
	
	public void guardarObj() throws IOException {
		FileOutputStream fw = new  FileOutputStream(file,true);
		ObjectOutputStream os= new ObjectOutputStream(fw);
		
		
	}

}
