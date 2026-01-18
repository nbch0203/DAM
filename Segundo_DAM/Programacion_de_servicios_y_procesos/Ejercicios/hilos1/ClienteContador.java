package ejer1;

import java.io.*;
import java.net.Socket;

class ClienteContador extends Thread {
	private String host;
	private int puerto;
	private int idCliente;

	// Variables de recursos (serán cerradas en desconectar())
	private Socket socket;
	private BufferedReader lector;
	private BufferedWriter escritor;

	public ClienteContador(String host, int puerto, int id) {
		this.host = host;
		this.puerto = puerto;
		this.idCliente = id;
	}

	@Override
	public void run() {
		// Ejecución modular
		if (conectar()) {
			leerMensajeInicial();
			ejecutarLogica(); // En este ejercicio, solo se lee y se envía el 'bye'
		}
		desconectar();
	}

	/** Establece la conexión e inicializa los streams. */
	private boolean conectar() {
		try {
			this.socket = new Socket(this.host, this.puerto);
			this.lector = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			this.escritor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			System.out.println("[C" + this.idCliente + "] Conexión establecida con " + this.puerto);
			return true;
		} catch (IOException e) {
			System.err.println("[C" + this.idCliente + "] Error al conectar: " + e.getMessage());
			return false;
		}
	}

	/** Lee y muestra el mensaje de bienvenida del servidor. */
	private void leerMensajeInicial() {
		try {
			String mensajeServidor = lector.readLine();
			if (mensajeServidor != null) {
				System.out.println("[C" + this.idCliente + "] Servidor: " + mensajeServidor);
			}
		} catch (IOException e) {
			System.err.println("[C" + this.idCliente + "] Error leyendo bienvenida.");
		}
	}

	/** Lógica de la prueba (sólo enviar el bye). */
	private void ejecutarLogica() {
		// En este ejemplo simple, no se envía nada más que el comando de desconexión.
		// Envío del comando 'bye'
		if (escritor != null) {
			try {
				escritor.write("bye_"+this.getIdCliente()+"\n");
				escritor.flush();
				System.out.println("[C" + this.idCliente + "] Desconexión enviada.");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** Envía el comando final y cierra el socket. */
	private void desconectar() {

		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	
	
}