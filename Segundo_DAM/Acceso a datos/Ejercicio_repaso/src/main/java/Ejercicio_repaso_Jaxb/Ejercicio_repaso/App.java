package Ejercicio_repaso_Jaxb.Ejercicio_repaso;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws JAXBException, IOException {

		JAXBContext contexto = JAXBContext.newInstance(Concesionario.class);
		Marshaller m = contexto.createMarshaller();

		Concesionario conce = new Concesionario();
		conce.setNombre("asda");
		conce.setUbicacion("asdasdasd");

		ArrayList<Vehiculo> coches = new ArrayList<Vehiculo>();

		Vehiculo c1 = new Vehiculo();
		c1.setAno(1999);
		c1.setMarca("Mercedes");
		c1.setModelo("patata");
		c1.setPrecio(15000.00);
		c1.setVendedor("Antonio");

		coches.add(c1);

		conce.setvehiculos(coches);

		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(conce, System.out);

		FileWriter fw = new FileWriter("salida_coches.xml");

		m.marshal(conce, fw);

	}
}
