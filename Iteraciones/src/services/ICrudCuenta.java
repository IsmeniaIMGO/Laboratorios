package services;

import model.Cuenta;

public interface ICrudCuenta {
	public Cuenta crearCuenta(String id, int saldo, String clave, String nombreUsuario, String apellidoUsuario, String cedula) throws Exception;
	
	public String informacionCuenta(String id) throws Exception;
	
	public boolean existeCuenta(String id)  throws NullPointerException;
	
	public void actualizarCuenta(String id, String clave, String nuevoNombre, String nuevoApellido);
	
	public void eliminarCuenta(String id, String motivo, String clave) throws Exception;
}
