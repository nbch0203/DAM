package Ejercicio_7.Ejercicio_7;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import jakarta.xml.bind.Marshaller;

public class Ejercicio4JAXB {
	public static void main(String[] args) throws Exception {
		File file = new File("album.xml");

		// 1. Crear objeto Album
		Album album = new Album(2010, "Queen", "Lo mejor de Queen");
		album.addCancion(new Cancion("Bohemian Rhapsody", new Duracion(354, "no")));
		album.addCancion(new Cancion("Don't Stop Me Now", new Duracion(354, "si")));
		album.addCancion(new Cancion("We Will Rock You", new Duracion(354, "no")));

		// 2. Guardar en XML con JAXB
		JAXBContext context = JAXBContext.newInstance(Album.class);
		Marshaller marshaller = (Marshaller) context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(album, file);

		System.out.println("Archivo XML generado: " + file.getName());

		// 3. Leer desde XML con JAXB
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Album albumLeido = (Album) unmarshaller.unmarshal(file);

		System.out.println("\nÁlbum leído desde XML:");
		System.out.println("Artista: " + albumLeido.getArtista() + " año álbum: " + albumLeido.getAnio() + " nombre: "
				+ albumLeido.getNombre());
		for (Cancion c : albumLeido.getCanciones()) {
			System.out.println(c);
		}
	}
}