package modelo;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOutputStreamSinCabecera extends ObjectOutputStream {

	public ObjectOutputStreamSinCabecera(OutputStream out) throws IOException {
		super(out);
	}

	public ObjectOutputStreamSinCabecera() throws IOException, SecurityException {
		super();
	}

	@Override
	protected void writeStreamHeader() throws IOException {

	}
}
