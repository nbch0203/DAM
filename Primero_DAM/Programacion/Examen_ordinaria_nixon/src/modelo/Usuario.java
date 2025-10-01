package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient File fichero_txt = new File("usuarios.txt");
	private transient File fichero_dat = new File("usuarios.dat");
	private String usuario;
	private transient String password;
	private String rol;

	public Usuario(String usuario, String password, String rol) {
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public File getFichero_txt() {
		return fichero_txt;
	}

	public void setFichero_txt(File fichero_txt) {
		this.fichero_txt = fichero_txt;
	}

	public File getFichero_dat() {
		return fichero_dat;
	}

	public void setFichero_dat(File fichero_dat) {
		this.fichero_dat = fichero_dat;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", password=" + password + ", rol=" + rol + "]";
	}

	public void guardarTxT(Usuario usuario) throws IOException {
		FileWriter fw = null;
		BufferedWriter buffer = null;
		if (!getFichero_txt().exists()) {

			fw = new FileWriter(fichero_txt);

			buffer = new BufferedWriter(fw);

		} else {
			fw = new FileWriter(fichero_txt, true);
			buffer = new BufferedWriter(fw);
		}

		buffer.write(usuario.toString() + "\n");

		buffer.close();
		fw.close();

	}

	public void guardarObj(Usuario usuario) throws IOException {
		FileOutputStream fs = null;
		ObjectOutputStream obst = null;

		if (!getFichero_dat().exists()) {
			getFichero_dat().createNewFile();
			fs = new FileOutputStream(getFichero_dat());
			obst = new ObjectOutputStream(fs);
		} else {
			fs = new FileOutputStream(getFichero_dat(), true);
			obst = new ObjectOutputStreamSinCabecera(fs);
		}

		obst.writeObject(usuario);

		fs.close();
		obst.close();
	}

	public boolean leerTxt(Usuario usuario) throws IOException {

		String linea = "";

		try (FileReader fr = new FileReader(getFichero_txt()); BufferedReader buffer = new BufferedReader(fr)) {
			while ((linea = buffer.readLine()) != null) {
				if (linea.contains(usuario.getUsuario()) && linea.contains(usuario.getPassword())
						&& linea.contains(usuario.getRol())) {
					return true;
				}

			}

			return false;
		}
	}

	public ArrayList<String> leeUsuarios() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<String> lista = new ArrayList<String>();

		try (FileInputStream fr = new FileInputStream(getFichero_dat());
				ObjectInputStream buffer = new ObjectInputStream(fr)) {
			while (true) {
				Usuario usuario = (Usuario) buffer.readObject();
				lista.add(usuario.toString());
			}
		} catch (EOFException e) {
			// TODO: handle exception
		}
		return lista;

	}
}
