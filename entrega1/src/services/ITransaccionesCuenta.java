package services;

public interface ITransaccionesCuenta {

	public void consignarDinero(String id, String cedula, int cantidad) throws Exception;
	
	public void transferirDinero(String idDestino, int cantidad, String idOrigen, String clave) throws Exception;
	
	public void retirarDinero(String cedula, String id, int cantidad, String clave) throws Exception;
	
	public int consultarSaldo(String id) throws Exception;
	
	public String informacionCuenta(String id) throws Exception;
}
