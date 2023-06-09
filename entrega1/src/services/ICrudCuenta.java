package services;

import model.Cuenta;
import model.Usuario;

public interface ICrudCuenta {

	public Cuenta crearCuenta(String id, int saldo, String clave, Usuario usuario ) throws Exception;
	
	public boolean existeCuenta(String id)  throws NullPointerException;
	
	public void actualizarCuenta(String id, String clave, String nuevoNombre, String nuevoApellido);
	
	public void eliminarCuenta(String id, String motivo, String clave) throws Exception;
}
