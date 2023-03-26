package services;

public interface ITransaccionesCuenta {

	public void ConsignarDinero(String id, String cedula, int cantidad) throws Exception;
	
	public void TransferirDinero(String idDestino, int cantidad, String idOrigen, String clave) throws Exception;
	
	public void RetirarDinero(String cedula, String id, int cantidad, String clave) throws Exception;
}
