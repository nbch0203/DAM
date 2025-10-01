/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosjaxb.LecturaJAXB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Patricia
 */
public class ProgramaPrincipal {

    /**
     * @param args the command line arguments
     * @throws javax.xml.bind.JAXBException
     */
    public static void main(String[] args) throws JAXBException, IOException {
        
        //Creo los objetos necesarios para leer el documento
        JAXBContext contexto = JAXBContext.newInstance(Libros.class);
        Unmarshaller u=contexto.createUnmarshaller();
        
        //Deserializa el fichero y lo carga en un objeto Libros
        File f=new File ("res/libros.xml");
        Libros misLibros= (Libros) u.unmarshal(f);
        
        //Recorremos el array de libros y lo escribimos por pantalla
        //Cargo el arrayList de libros
        ArrayList<Libro> libros= misLibros.getLibros();
        
        //Por cada objeto de libros que muestre por pantalla sus valores (atributo y elementos)
        for(Libro l: libros){
            System.out.println("Datos libro:");
            System.out.println("\t "+"Publicado en: "+ l.getPublicado_en());
            System.out.println("\t "+"Titulo: "+ l.getTitulo());
            System.out.println("\t "+"Autor: "+ l.getAutor());
            System.out.println("Fin libro\n");
            //cuando encuentra el libro que tiene el titulo del primer parámetro lo cambia por el titulo del segundo parámetro
            l.modificaTitulo("El Sanador de Caballos", "El Sanador de Caballos B");
        }
        
        System.out.println("*****************Fin lista libros recuperada****************");
        
//          //Creo los objetos que necesito para crear el XML en objetos
//        JAXBContext contexto2= JAXBContext.newInstance(Libros.class);
//        Marshaller m=contexto2.createMarshaller();
//        
//         //Doy formato al XML
//        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        //Para ver por pantalla lo que escribiremos en el fichero
//        m.marshal(misLibros, System.out);
//        
//        //Escribimos la información en un fichero XML fisico
//        FileWriter fw = new FileWriter("librosModificado.xml");
//        m.marshal(misLibros,fw);
    }
    
}
