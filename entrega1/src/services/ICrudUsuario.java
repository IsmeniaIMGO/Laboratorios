package services;

import model.*;

public interface ICrudUsuario {
	
	public Usuario crearUsuario(String nombre, String apellido, String cedula ) throws Exception;
	public boolean existeUsuario(String cedula);
	
	
}
