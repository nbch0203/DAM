package pcIP;

public class PC {
	String Name;
	String IP;
	public PC(String name, String iP) {
		super();
		Name = name;
		IP = iP;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	
}
