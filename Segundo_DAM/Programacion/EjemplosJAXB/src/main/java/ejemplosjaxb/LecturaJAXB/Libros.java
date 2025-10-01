package ejemplosjaxb.LecturaJAXB;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Patricia
 */
@XmlRootElement(name="Libros")
@XmlType(propOrder={"libros"})
public class Libros {
    
    //Atributos de la clase Libros. Contiene varios objetos libro
    private ArrayList<Libro> libros = new ArrayList();
    
    //Constructor vacio
    public Libros() {
    }

    @XmlElement(name="Libro")
    //Get y set de los atributos
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    

    
}
