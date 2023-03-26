package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	
	private static final Scanner SCANNER = new Scanner(System.in);
	public static final String SERVER = "localhost"; //localhost o mi propia ip
	public static final int PORT = 3400; //3400
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	private Socket clientSideSocket;
	
	public Cliente() {
		System.out.println("Echo TCP client is running ...");
	}
	
	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);
		createStreams(clientSideSocket);
		protocol(clientSideSocket);
		clientSideSocket.close();
	}
	
	public void protocol(Socket socket) throws Exception {
		System.out.println("Escriba el numero de la accion que desea realizar en el banco"
				+ "\n 1. Crear cuenta"
				+ "\n 2. actualizar cuenta"
				+ "\n 3. eliminar cuenta"
				+ "\n 4. consignar dinero en una cuenta"
				+ "\n 5. transferir dinero de mi cuenta a otra"
				+ "\n 6. retirar dinero de mi cuenta");
		
		String comando = SCANNER.nextLine();

    	toNetwork.println(comando);
		String fromServer = fromNetwork.readLine();
		System.out.println("[Client] From server: " + fromServer);
    		
	}
	
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static void main(String args[]) throws Exception {
		Cliente ec = new Cliente();
		ec.init(); 
	}
	
}
