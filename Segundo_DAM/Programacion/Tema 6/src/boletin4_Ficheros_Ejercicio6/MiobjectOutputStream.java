package boletin4_Ficheros_Ejercicio6;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiobjectOutputStream extends ObjectOutputStream {

	protected MiobjectOutputStream(OutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}

	public MiobjectOutputStream() throws IOException, SecurityException {
		super();
	}

	protected void writeStreamHeader() throws IOException {
	}

}
