package prueba_xml.prueba_xml;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "empresa")
@XmlType(propOrder = { "cif", "empleados", "nombre" })
public class Empresa {

	private String cif;
	private String nombre;
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public Empresa() {
	}

	@XmlAttribute(value= "asdasda")
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElementWrapper(name = "empleados")
	@XmlElement
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void agregarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
	}

	@Override
	public String toString() {
		return "Empresa: " + nombre + " CIF: " + cif;
	}
}