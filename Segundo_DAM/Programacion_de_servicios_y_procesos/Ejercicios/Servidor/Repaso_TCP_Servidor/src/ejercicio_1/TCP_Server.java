package ejercicio_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
	private ServerSocket server;
	private Socket socket;

	private InputStream input;
	private OutputStream output;

	private DataInputStream dataInput;
	private DataOutputStream dataOutput;

	public TCP_Server() {
	}

}
