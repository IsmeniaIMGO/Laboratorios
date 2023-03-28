package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	
	/**
	 * atributos
	 */
	private static final Scanner SCANNER = new Scanner(System.in);//definimos entrada por teclado
	public static final String SERVER = "4.tcp.ngrok.io"; //definimos la direccion ip del servidor
	public static final int PORT = 11324; //definimos el puerto por donde se van a comunicar el cliente y el servidor
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	private Socket clientSideSocket;
	
	
	public Cliente() {
		System.out.println("Echo TCP client is running ...");
	}
	
	/**
	 * metodos que se realizan antes de la ejecucion de la clase cliente
	 * @throws Exception
	 */
	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);
		createStreams(clientSideSocket);
		protocol(clientSideSocket);
		clientSideSocket.close();
	}
	
	
	/**
	 * metodo que hace las acciones correspondientes y
	 * �pide los datos necesarios para luego enviar en
	 * un formato especificio al servidor una solicitud
	 * @param socket
	 * @throws Exception
	 */
	public void protocol(Socket socket) throws Exception {
		System.out.println("\n-------------CAJERO AUTOMATICO---------------------");
		System.out.println("\n Escriba el numero de la accion que desea realizar: "
				+ "\n 1. Crear cuenta"
				+ "\n 2. actualizar cuenta"
				+ "\n 3. eliminar cuenta"
				+ "\n 4. consignar dinero en una cuenta"
				+ "\n 5. transferir dinero de mi cuenta a otra"
				+ "\n 6. retirar dinero de mi cuenta \n");
		
		String opcion = SCANNER.nextLine();
		
		if (opcion.equals("1")) {
			System.out.println("\n----------CREAR CUENTA----------");
			System.out.println("\n ingrese su nombre:");
			String  nombreUsuario = SCANNER.nextLine();
			System.out.println("ingrese su apellido:");
			String  apellidoUsuario = SCANNER.nextLine();
			System.out.println("ingrese su cedula:");
			String  cedula = SCANNER.nextLine();
			System.out.println("ingrese la cantidad de dinero que va consignar ");
			int cantidad = SCANNER.nextInt();
			SCANNER.nextLine();
			System.out.println("Ingrese la clave de 4 digitos");
			String clave = SCANNER.nextLine();
			
			
			String answer = opcion+"/"+nombreUsuario+"/"+apellidoUsuario+"/"+cedula+"/"+cantidad+"/"+clave;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

		}else if (opcion.equals("2")) {
			System.out.println("\n----------ACTUALIZAR CUENTA----------\n");
			System.out.println("Ingrese el numero de cuenta a modificar: ");
			String id = SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			System.out.println("Ingrese el nuevo nombre de usuario");
			String nuevoNombre = SCANNER.nextLine();
			System.out.println("Ingrese el nuevo apellido del usuario");
			String nuevoApellido = SCANNER.nextLine();
			
			String answer = opcion+"/"+id+"/"+clave+"/"+nuevoNombre+"/"+nuevoApellido;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

		}else if (opcion.equals("3")) {
			System.out.println("\n----------ELIMINAR CUENTA----------\n");
			System.out.println("Ingrese el numero de cuenta a eliminar: ");
			String id = SCANNER.nextLine();
			System.out.println("Ingrese el motivo de eliminacion de la cuenta: ");
			String motivo = SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			
			String answer = opcion+"/"+id+"/"+motivo+"/"+clave;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

			
		}else if (opcion.equals("4")) {
			System.out.println("\n----------CONSIGNAR EN UN CUENTA----------\n");
			System.out.println("Ingrese el numero de la cuenta");
			String id = SCANNER.nextLine();
			System.out.println("ingrese su cedula:");
			String  cedula = SCANNER.nextLine();
			System.out.println("ingrese la cantidad de dinero que va consignar ");
			int cantidad = SCANNER.nextInt();
			SCANNER.nextLine();
			
			String answer = opcion+"/"+id+"/"+cedula+"/"+cantidad;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

		}else if (opcion.equals("5")) {
			System.out.println("\n----------TRANSFERIR DINERO----------\n");
			System.out.println("Ingrese el numero de la cuenta a la que va tranferir (destino)");
			String idDestino = SCANNER.nextLine();
			System.out.println("ingrese la cantidad de dinero que va transferir ");
			int cantidad = SCANNER.nextInt();
			SCANNER.nextLine();
			System.out.println("Ingrese el numero de su cuenta (origen)");
			String idOrigen = SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			
			String answer = opcion+"/"+idDestino+"/"+cantidad+"/"+idOrigen+"/"+clave;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

			
		}else if (opcion.equals("6")) {
			System.out.println("\n----------RETIRAR DINERO----------\n");
			System.out.println("ingrese su cedula:");
			String  cedula = SCANNER.nextLine();
			System.out.println("Ingrese el numero de la cuenta");
			String id = SCANNER.nextLine();
			System.out.println("ingrese la cantidad de dinero que va retirar ");
			int cantidad = SCANNER.nextInt();
			SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			
			String answer = opcion+"/"+cedula+"/"+id+"/"+cantidad+"/"+clave;
	    	toNetwork.println(answer);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

			
		}else{
	    	toNetwork.println(opcion);
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);

		}
		
		

    		
	}
	/**
	 * metodo que envia y recibe un mensaje
	 * @param socket
	 * @throws Exception
	 */
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static void main(String args[]) throws Exception {
		Cliente ec = new Cliente();
		ec.init(); 
	}
	
}
