package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import model.*;

public class Servidor {
	/**
	 * atributos
	 */
	public static final int PORT = 3400;
	private ServerSocket listener;
	private Socket serverSideSocket;
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	
	/**
	 * inicializacion de atributos necesarios
	 */
	ArrayList<Cuenta>listaCuentas = new ArrayList<Cuenta>();
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	Banco banco = new Banco("x", listaCuentas, listaUsuarios);
	Random random = new Random();
	String[]palabras = new String[]{};

	public Servidor() {
		System.out.println("Echo TCP server is running on port: " + PORT);
	}
	
	/**
	 * metodo que realiza acciones antes de la ejecucion de la clase
	 * @throws Exception
	 */
	public void init() throws Exception {
		listener = new ServerSocket(PORT);
		while (true) {
			serverSideSocket = listener.accept();
			createStreams(serverSideSocket);
			protocol(serverSideSocket);
		}
	}
	
	
	/**
	 * metodo que interpreta el mensaje que recibio del cliente
	 * para luego realizar las acciones necesarias y finalmente
	 * enviar una respuesta de acuerdo a la solicitud
	 * @param socket
	 * @throws Exception
	 */
	public void protocol(Socket socket) throws Exception {
		String mensaje = fromNetwork.readLine();
		System.out.println("[Server] From client: " + mensaje);
		
		palabras = mensaje.split("/");
		
		if (palabras[0].equals("1")) {

			int numero = random.nextInt(2000-1000+1)+1000;
			String id = Integer.toString(numero);
			
			String nombreUsuario = palabras[1];
			String apellidoUsuario = palabras[2];
			String cedula = palabras[3];
			int cantidad = Integer.parseInt(palabras[4]);
			String clave = palabras[5];
			
			Usuario usuario = banco.crearUsuario(nombreUsuario, apellidoUsuario, cedula);
			banco.crearCuenta(id,cantidad, clave, usuario );
			String answer = "Cuenta creada: "+banco.informacionCuenta(id);
			toNetwork.println(answer);
			
		}else if (palabras[0].equals("2")) {
			String id = palabras[1];
			String clave = palabras[2];
			String nuevoNombre = palabras[3];
			String nuevoApellido = palabras[4];
			
			banco.actualizarCuenta(id, clave, nuevoNombre, nuevoApellido);
			String answer = "Cuenta actualizada: "+banco.informacionCuenta(id);
			toNetwork.println(answer);

		
		}else if (palabras[0].equals("3")) {
			String id = palabras[1];
			String motivo = palabras[2];
			String clave = palabras[3];
			
			banco.eliminarCuenta(id, motivo, clave);
			String answer = "Cuenta: "+id +" eliminada por: "+motivo;
			toNetwork.println(answer);
			
		}else if (palabras[0].equals("4")) {
			String id = palabras[1];
			String cedula = palabras[2];
			int cantidad = Integer.parseInt(palabras[3]);
			
			banco.consignarDinero(id, cedula, cantidad);
			String answer = "se ha consignado"+cantidad +"en la cuenta numero: "+id ;
			toNetwork.println(answer);

			
		}else if (palabras[0].equals("5")) {
			String idDestino = palabras[1];
			int cantidad = Integer.parseInt(palabras[2]);
			String idOrigen = palabras[3];
			String clave = palabras[4];
			
			
			banco.transferirDinero(idDestino, cantidad, idOrigen, clave);
			String answer = "se ha transferido "+cantidad+" a la cuenta numero: "+idDestino + 
							"\n el nuevo saldo de su cuenta: "+ idOrigen+" es: " +banco.consultarSaldo(idOrigen) ;
			toNetwork.println(answer);

			
		}else if (palabras[0].equals("6")) {
			String cedula = palabras[1];
			String id = palabras[2];
			int cantidad = Integer.parseInt(palabras[3]);
			String clave = palabras[4];

			banco.retirarDinero(cedula, id, cantidad, clave);
			String answer = "se ha retirado "+cantidad+" de la cuenta numero: "+id + 
							"\n su nuevo saldo es: " +banco.consultarSaldo(id) ;
			toNetwork.println(answer);

			
		}else if (!palabras[0].equals("1")||!palabras[0].equals("2")||!palabras[0].equals("3")
					||!palabras[0].equals("4")||!palabras[0].equals("5")||!palabras[0].equals("6")){
			
			String answer = "error 400: opcion no encontrada";
			toNetwork.println(answer);
		}
	}
	
	/**
	 * metodo que permite enviar o recibir mensajes
	 * @param socket
	 * @throws Exception
	 */
	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	
	public static void main(String args[]) throws Exception {
		Servidor es = new Servidor();
		es.init();
	}
}
