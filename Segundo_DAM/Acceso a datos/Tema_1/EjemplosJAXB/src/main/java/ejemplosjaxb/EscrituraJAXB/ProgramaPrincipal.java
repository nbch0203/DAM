package ejemplosjaxb.EscrituraJAXB;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Patricia
 */
public class ProgramaPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException, IOException {
        
        //Creo los objetos que necesito para crear el XML en objetos
        JAXBContext contexto= JAXBContext.newInstance(Libros.class);
        
        Marshaller m=contexto.createMarshaller();
        
        Libros misLibros=new Libros();
        
        //Creo un array de dos libros
        ArrayList<Libro> libros= new ArrayList();
        Libro libro=new Libro();
        libro.setPublicado_en("1985-11-26");
        libro.setAutor("Autor JAXB");
        libro.setTitulo("Titulo JAXB");
        //Aniadimos el primer libro
        libros.add(libro);
        
        libro=new Libro();
        libro.setPublicado_en("2020-10-03");
        libro.setAutor("Autor JAXB 2");
        libro.setTitulo("Titulo JAXB 2");
        //Aniadimos el segundo libro
        libros.add(libro);
        
        //Aniadimos el array de libros a mi objeto XML
        misLibros.setLibros(libros);
        
        //Doy formato al XML
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //Para ver por pantalla lo que escribiremos en el fichero
        m.marshal(misLibros, System.out);
        
        //Escribimos la información en un fichero XML fisico
        FileWriter fw = new FileWriter("SalidaJAXB.xml");
        m.marshal(misLibros,fw);
        
    }
    
}
