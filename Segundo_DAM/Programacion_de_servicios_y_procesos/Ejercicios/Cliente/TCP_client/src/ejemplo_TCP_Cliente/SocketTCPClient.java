package ejemplo_TCP_Cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCPClient {
	private String serverIP;
	private int serverPort;
	private Socket socket;
	private InputStream is;
	private OutputStream os;

	public SocketTCPClient(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	public void start() throws UnknownHostException, IOException {
		System.out.println(" (Cliente) Estableciendo conexión...");
		socket = new Socket(serverIP, serverPort);
		os = socket.getOutputStream();
		is = socket.getInputStream();
		System.out.println(" (Cliente) Conexión establecida.");
	}

	public void stop() throws IOException {
		System.out.println("(Cliente) Cerrando conexiones...");
		is.close();
		os.close();
		socket.close();
		System.out.println("(Cliente) Conexiones cerradas.");
	}

	public static void main(String[] args) {
		SocketTCPClient cliente = new SocketTCPClient("127.0.2.1", 49171);
		try {
			cliente.start();
			cliente.os.write(100);
			System.out.println("Mensaje del servidor:" + cliente.is.read());
			cliente.stop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}