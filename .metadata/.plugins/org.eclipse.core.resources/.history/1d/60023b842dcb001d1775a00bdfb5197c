package a_echotcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoTCPClient {
	private static final Scanner SCANNER = new Scanner(System.in);
	public static final String SERVER = "0.tcp.ngrok.io"; //localhost o mi propia ip
	public static final int PORT = 18904; //3400
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	private Socket clientSideSocket;
	
	public EchoTCPClient() {
		System.out.println("Echo TCP client is running ...");
	}
	
	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);
		createStreams(clientSideSocket);
		protocol(clientSideSocket);
		clientSideSocket.close();
	}
	
	public void protocol(Socket socket) throws Exception {
		System.out.print("Ingrese un mensaje: ");
		String fromUser = SCANNER.nextLine();
		toNetwork.println(fromUser);
		String fromServer = fromNetwork.readLine();
		System.out.println("[Client] From server: " + fromServer);
	}
	
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static void main(String args[]) throws Exception {
		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
	}
}


//El sistema utiliza un HashMap para almacenar los nombres de los usuarios que han sido registrados
//y la cantidad de veces que han ingresado luego de su registro inicial.
// El sistema inicia inicia esperando la llegada de un cliente.
// Cuando se conecta un cliente, el sistema queda esperando la llegada de un mensaje.
// Cuando el servidor recibe un mensaje, si inicia con la palabra "LOGIN", extrae el nombre que viene
//en el mensaje. Si el nombre corresponde a un usuario nuevo, lo registra en el HashMap y responde al
//cliente con el mensaje "BIENVENIDO fulano, usted es el usuario #n", donde n aumenta a medida
//que se registran usuarios en el sistema. Si el nombre ya está registrado, se incrementa la cantidad de
//ingresos al sistema y responde al cliente con el mensaje "Fulano: Usted ha ingresado #k veces al
//sistema", donde k es la cantidad de veces que ha ingresado al sistema, sin tener en cuenta el registro
//en el sistema.