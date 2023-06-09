package c_users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class UsersTCPServer {
	
	public static final int PORT = 3400;
	private ServerSocket listener;
	private Socket serverSideSocket;
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	
	private HashMap<String, Integer> usuarios = new HashMap<String, Integer>();
	private int contador = 1;
	
	public UsersTCPServer() {
		System.out.println("Echo TCP server is running on port: " + PORT);
	}
	
	public void init() throws Exception {
		listener = new ServerSocket(PORT);
		while (true) {
			serverSideSocket = listener.accept();
			createStreams(serverSideSocket);
			protocol(serverSideSocket);
		}
	}
	
	public void protocol(Socket socket) throws Exception {
		String message = fromNetwork.readLine();
		System.out.println("[Server] From client: " + message);
		
		
		if (message.charAt(0)=='L'&message.charAt(1)=='O'&
			message.charAt(2)=='G'&message.charAt(3)=='I'& 
			message.charAt(4)=='N') {
			
			String nombre = message.substring(6);
			
			if (usuarios.containsKey(nombre)) {
				int ingresos = usuarios.get(nombre);
				ingresos++;
				usuarios.put(nombre, ingresos);
				String answer = nombre+ ": Usted ha ingresado " + ingresos+ " veces al sistema";
				toNetwork.println(answer);
			}else {
				usuarios.put(nombre, 0);
				String answer = "BIENVENIDO "+nombre+ " usted es el usuario: "+ contador;
				contador++;
				toNetwork.println(answer);
			}
			
		}else {
			String answer = "error 400";
			toNetwork.println(answer);

		}
	}
	
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public static void main(String args[]) throws Exception {
		UsersTCPServer es = new UsersTCPServer();
		es.init();
	}
}
