package Ejercicio_repaso_Jaxb.Ejercicio_repaso;

import javax.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
public class Concesionario {
    @XmlAttribute
    private String nombre;
	@XmlElementWrapper(name="vehiculos")
    @XmlElement(name = "vehiculo")
    private List<Vehiculo> vehiculos;
    @XmlElement
    private String ubicacion;

    public Concesionario() {}

    public Concesionario(String nombre, List<Vehiculo> vehiculos, String ubicacion) {
        this.nombre = nombre;
        this.vehiculos = vehiculos;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }


    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
