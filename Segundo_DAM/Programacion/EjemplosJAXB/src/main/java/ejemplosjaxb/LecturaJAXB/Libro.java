package ejemplosjaxb.LecturaJAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Patricia
 */
@XmlRootElement(name="Libro")
@XmlType(propOrder={"publicado_en","titulo","autor"})
public class Libro {
    
    //Atributos de la clase Libro
    private String publicado_en;
    private String titulo;
    private String autor;

    //Constructor vacio
    public Libro() {
    }

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
