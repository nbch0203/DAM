package ejericiorepaso1;

public class Coche {
	String Propietario;
	String Matricula;
	String Marca;
	boolean reparado = false;

	public Coche(String propietario, String matricula, String marca, boolean reparado) {
		super();
		Propietario = propietario;
		Matricula = matricula;
		Marca = marca;
		this.reparado = reparado;

	}

	public String getPropietario() {
		return Propietario;
	}

	public void setPropietario(String propietario) {
		Propietario = propietario;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public boolean isReparado() {
		return reparado;
	}

	public void setReparado(boolean reparado) {
		this.reparado = reparado;
	}

	@Override
	public String toString() {
		return "\n"+"Propietario=" + Propietario + "\n" + "Matricula=" + Matricula + "\n"
				+ "Marca=" + Marca +"\n" + "reparado=" + reparado + "\n";
	}

}