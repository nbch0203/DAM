package Jaxb.PruebaUT1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

// Clase especializada para añadir objetos a un fichero existente 
public class ObjectStreamAppender extends ObjectOutputStream {

    public ObjectStreamAppender(OutputStream out) throws IOException {
        super(out);
    }
  
    @Override
    protected void writeStreamHeader() throws IOException {
    }
}