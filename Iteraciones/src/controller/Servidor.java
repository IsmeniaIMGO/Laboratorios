package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.*;


public class Servidor {
	
	public static final int PORT = 3400;
	private ServerSocket listener;
	private Socket serverSideSocket;
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	private static final Scanner SCANNER = new Scanner(System.in);
	
	ArrayList<Cuenta>listaCuentas = new ArrayList<Cuenta>();
	Banco banco = new Banco("x", listaCuentas);
	Random random = new Random();


	public Servidor() {
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
		String comando = fromNetwork.readLine();
		System.out.println("[Server] From client: " + comando);
					
		if (comando.equals("1")) {

			System.out.println("\n Bienvenido al banco \n ingrese su nombre:");
			String  nombreUsuario = SCANNER.nextLine();
			System.out.println("ingrese su apellido:");
			String  apellidoUsuario = SCANNER.nextLine();
			System.out.println("ingrese su cedula:");
			String  cedula = SCANNER.nextLine();
			System.out.println("ingrese la cantidad de dinero que va consignar ");
			int saldo = SCANNER.nextInt();
			SCANNER.nextLine();
			System.out.println("Ingrese la clave de 4 digitos");
			String clave = SCANNER.nextLine();
			
			int numero = random.nextInt(2000-1000+1)+1000;
			String id = Integer.toString(numero);
			
			banco.crearCuenta(id, saldo, clave, nombreUsuario, apellidoUsuario, cedula);
			
			String answer = "Cuenta creada: "+banco.buscarCuenta(id);
			toNetwork.println(answer);
			
		}else if (comando.equals("2")) {
			System.out.println("Ingrese el numero de cuenta a modificar: ");
			String id = SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			System.out.println("Ingrese el nuevo nombre de usuario");
			String nuevoNombre = SCANNER.nextLine();
			System.out.println("Ingrese el nuevo apellido del usuario");
			String nuevoApellido = SCANNER.nextLine();
			
			banco.actualizarCuenta(id, clave, nuevoNombre, nuevoApellido);
			String answer = "Cuenta actualizada: "+banco.buscarCuenta(id);
			toNetwork.println(answer);

		
		}else if (comando.equals("3")) {
			System.out.println("Ingrese el numero de cuenta a eliminar: ");
			String id = SCANNER.nextLine();
			System.out.println("Ingrese el motivo de eliminacion de la cuenta: ");
			String motivo = SCANNER.nextLine();
			System.out.println("Ingrese la clave: ");
			String clave = SCANNER.nextLine();
			
			banco.eliminarCuenta(id, motivo, clave);
			String answer = "Cuenta: "+id +" eliminada por: "+motivo;
			toNetwork.println(answer);
			
		}else if (!comando.equals("1")||!comando.equals("2")||!comando.equals("3")){
			String answer = "error 400: opcion no encontrada";
			toNetwork.println(answer);
		}
	}
	
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public static void main(String args[]) throws Exception {
		Servidor es = new Servidor();
		es.init();
	}
}
