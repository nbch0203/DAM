package datos_necesarios;

public class DNI {
	private String number;

	public DNI(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "DNI [getNumber()=" + getNumber() + "]";
	}

}
