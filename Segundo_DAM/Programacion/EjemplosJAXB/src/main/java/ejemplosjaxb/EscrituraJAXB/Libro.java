package ejemplosjaxb.EscrituraJAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Patricia
 */
//Elemento raíz de mi xml
@XmlRootElement(name="Libro")
@XmlType(propOrder={"publicado_en","titulo","autor"}) //Orden en el que aparece en el xml
public class Libro {
    
    //Atributos de la clase Libro, pueden ser atributos del xml o elementos
    private String publicado_en;
    private String titulo;
    private String autor;

    //Constructor vacio
    public Libro() {
    }

    //Indico los que son para atributos y los que son para elementos
    //Get y set de los atributos de la clase
    @XmlAttribute(name="publicado_en")
    public String getPublicado_en() {
        return publicado_en;
    }

    public void setPublicado_en(String publicado_en) {
        this.publicado_en = publicado_en;
    }

    @XmlElement(name="Titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(name="Autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    //Metodo modifica titulo del libro
    public void modificaTitulo (String AnteriorTitulo, String NuevoTitulo){
        if (this.titulo.equals(AnteriorTitulo)){
            this.titulo=NuevoTitulo;
           }
    }
    
}
