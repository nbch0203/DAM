
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Equipo;
import modelo.ObjectOutputStreamSinCabecera;
import vista.Vista;

public class Controlador {
	private File file = new File("equipos.txt");
	private static File file2 = new File("equipos.txt");
	private File file_obj = new File("equipos.dat");
	private Vista vista;
	private Equipo modelo;

	public Controlador(Vista vista, Equipo modelo) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!file_obj.exists()) {
			try {
				file_obj.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.vista = vista;
		this.modelo = modelo;
		iniciarBotones();
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public Equipo getModelo() {
		return modelo;
	}

	public void setModelo(Equipo modelo) {
		this.modelo = modelo;
	}

	public void iniciarBotones() {

		getVista().getBtn_obj().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				guardarOBJ();
			}
		});
		getVista().getBtn_mostrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mostrarOBJ1();
			}
		});
		getVista().getBtn_buscar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscarTXT();
			}
		});
		getVista().getBtn_txt().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				guardarTxT();
			}
		});

	}

	public void guardarTxT() {
		Equipo eq;

		String numero = getVista().getNumero_serie();
		String marca = getVista().getMarca();
		String ano_compra = getVista().getAno_compra();
		String tipo = getVista().getTipo();
		boolean ssd = false;
		boolean ram = false;
		boolean gpu = false;
		boolean wifi = false;
		if (getVista().getSsd().isSelected()) {
			ssd = true;
		}
		if (getVista().getRAM().isSelected()) {
			ram = true;
		}
		if (getVista().getGPU_dedicada().isSelected()) {
			gpu = true;
		}
		if (getVista().getWifi().isSelected()) {
			wifi = true;
		}

		try {
			eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(numero);

			fw.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	/**
	 * 
	 */
	public void guardarTxT1() {
		// Verificar campos obligatorios
		if (getVista().getNumero_serie().isEmpty() || getVista().getMarca().isEmpty()
				|| getVista().getAno_compra().isEmpty() || getVista().getTipo() == null
				|| getVista().getTipo().isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos obligatorios",
					"Campos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			// Crear el equipo
			Equipo eq = new Equipo(getVista().getNumero_serie(), getVista().getMarca(), getVista().getAno_compra(),
					getVista().getTipo(), getVista().getSsd().isSelected(), getVista().getRAM().isSelected(),
					getVista().getGPU_dedicada().isSelected(), getVista().getWifi().isSelected());

			// Verificar si el archivo existe y es escribible
			if (!file.exists()) {
				file.createNewFile();
			}

			if (!file.canWrite()) {
				JOptionPane.showMessageDialog(vista, "No se puede escribir en el archivo", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Escribir en el archivo
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				writer.write(eq.toString());
				writer.newLine();
				JOptionPane.showMessageDialog(vista, "Equipo guardado correctamente", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

//	public void guardarTxT() {
//		Equipo eq;
//
//		String numero = getVista().getNumero_serie();
//		String marca = getVista().getMarca();
//		String ano_compra = getVista().getAno_compra();
//		String tipo = getVista().getTipo();
//		boolean ssd = false;
//		boolean ram = false;
//		boolean gpu = false;
//		boolean wifi = false;
//		if (getVista().getSsd().isSelected()) {
//			ssd = true;
//		}
//		if (getVista().getRAM().isSelected()) {
//			ram = true;
//		}
//		if (getVista().getGPU_dedicada().isSelected()) {
//			gpu = true;
//		}
//		if (getVista().getWifi().isSelected()) {
//			wifi = true;
//		}
//		try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
//
//			eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);
//			bw.write(eq.toString() + "\n");
//		} catch (IOException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(vista, "Error al guardar en el archivo: " + e.getMessage());
//		}
//
////		try {
////			eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);
////
////			FileWriter fw = new FileWriter(file, true);
////			BufferedWriter bw = new BufferedWriter(fw);
////
////			bw.write(eq.toString() + "\n");
////
////			fw.close();
////			bw.close();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////		}
//
//	}

	public void buscarTXT() {

		try {
			String numero = getVista().getNumero_serie();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String aux = "";
			while ((aux = br.readLine()) != null) {
				if (aux.contains(numero)) {
					getVista().setTextArea(aux);
					br.close();
					fr.close();
					ArrayList<String> lista = new ArrayList<>();
					lista.add(aux);
					for (String string : lista) {
						if (string.equals(numero)) {
							getVista().setTextArea(string);
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane jp = new JOptionPane(null, 0, 0, null, null, "Error en la lectura del fichero");
		}

	}

	public void guardarOBJ() {

		String numero = getVista().getNumero_serie();
		String marca = getVista().getMarca();
		String ano_compra = getVista().getAno_compra();
		String tipo = getVista().getTipo();
		boolean ssd = false;
		boolean ram = false;
		boolean gpu = false;
		boolean wifi = false;
		if (getVista().getSsd().isSelected()) {
			ssd = true;
		}
		if (getVista().getRAM().isSelected()) {
			ram = true;
		}
		if (getVista().getGPU_dedicada().isSelected()) {
			gpu = true;
		}
		if (getVista().getWifi().isSelected()) {
			wifi = true;
		}
		Equipo eq = new Equipo(numero, marca, ano_compra, tipo, ssd, ram, gpu, wifi);

		try {

			FileOutputStream fos = new FileOutputStream(file_obj, true);
			ObjectOutputStreamSinCabecera ois = new ObjectOutputStreamSinCabecera(fos);

			ois.writeObject(eq);
			ois.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	public void mostrarOBJ1() {
		try {
			FileInputStream fis = new FileInputStream(file_obj);
			ObjectInputStream oos = new ObjectInputStream(fis);

			while (true) {

				Object eq = oos.readObject();

				getVista().getTextArea().setText(eq.toString());

			}

		} catch (IOException | ClassNotFoundException e) {
		}
		// TODO: handle exception
	}
//	public void mostrarOBJ() {
//		try {
//			// Limpiar el área de texto primero
//			getVista().getTextArea().setText("");
//
//			// Verificar si el archivo existe y no está vacío
//			if (!file_obj.exists() || file_obj.length() == 0) {
//				JOptionPane.showMessageDialog(vista, "No hay datos guardados o el archivo no existe", "Información",
//						JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
//
//			try {
//				FileInputStream fis = new FileInputStream("equipos.dat");
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				StringBuilder contenido = new StringBuilder();
//
//				while (true) {
//					try {
//						Object eq = ois.readObject();
//						contenido.append(eq.toString()).append("\n");
//					} catch (EOFException e) {
//						// Fin del archivo alcanzado
//						break;
//					}
//				}
//
//				if (contenido.length() > 0) {
//					getVista().getTextArea().setText(contenido.toString());
//				} else {
//					getVista().getTextArea().setText("No se encontraron equipos en el archivo");
//				}
//
//			} catch (ClassNotFoundException e) {
//				JOptionPane.showMessageDialog(vista, "Error en el formato del archivo: " + e.getMessage(), "Error",
//						JOptionPane.ERROR_MESSAGE);
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(vista, "Error al leer el archivo: " + e.getMessage(), "Error",
//					JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//		}
//	}

	public void mostrarOBJ() {
		try {
			FileInputStream fis = new FileInputStream(file_obj);
			try (ObjectInputStream oos = new ObjectInputStream(fis)) {
				while (true) {
					ArrayList<Equipo> lista = new ArrayList<>();
					lista.add((Equipo) oos.readObject());
					for (Equipo equipo : lista) {
						getVista().getTextArea().setText(equipo.toString());
					}

				}
			}

		} catch (IOException | ClassNotFoundException e) {
		}

	}
}
