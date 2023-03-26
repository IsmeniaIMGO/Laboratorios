package model;

import java.util.ArrayList;
import exceptions.*;
import services.*;

public class Banco implements ICrudCuenta, ITransaccionesCuenta {
	private String nombre;
	private ArrayList<Cuenta>listaCuentas = new ArrayList<Cuenta>();
	
	
	public Banco(String nombre, ArrayList<Cuenta> listaCuentas) {
		this.nombre = nombre;
		this.listaCuentas = listaCuentas;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ArrayList<Cuenta> getListaCuentas() {
		return listaCuentas;
	}


	public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banco other = (Banco) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Banco [nombre=" + nombre  + ", listaCuentas=" + listaCuentas + "]";
	}
	
	
	
	@Override
	public Cuenta crearCuenta(String id, int saldo, String clave, String nombreUsuario, String apellidoUsuario, String cedula) throws Exception{
		if(existeCuenta(id))
			throw new IdYaExisteException("Esta numero de cuenta ya se encuentra registrada");
		if (clave.length()>4 || clave.length()<4)
			throw new ClaveException("La clave debe tener 4 digitos");
		
		if (nombreUsuario.equals("")||apellidoUsuario.equals("")||cedula.equals("")||clave.equals(""))
			throw new ParametroVacioException("Alguno de los par�metros indicados es est� vac�o");

		Cuenta cuenta = new Cuenta(id, saldo, clave, nombreUsuario, apellidoUsuario, cedula);
		this.listaCuentas.add(cuenta);
		
		return cuenta;
		
	}
	
	@Override
	public String informacionCuenta(String id) throws Exception{
		for (Cuenta c : listaCuentas) {
			if(c.getId().equals(id))
				return c.toString();	
		}
		return "No existe";
		
	}
	
	
	public int SaldoCuenta(String id) throws Exception{
		for (Cuenta c : listaCuentas) {
			if(c.getId().equals(id))
				return c.getSaldo();	
		}
		return 0;
		
	}
	
	
	@Override
	public boolean existeCuenta(String id)  throws NullPointerException {
		for (Cuenta c : listaCuentas) {
			if(c.getId().equals(id)) return true;
			
		}			
		return false;
	}
	
	@Override
	public void actualizarCuenta(String id, String clave, String nuevoNombre, String nuevoApellido){
		if(!id.equals("")){
		
			for(Cuenta c : listaCuentas){
				if(c != null && c.getId() != null && c.getId().equals(id) && c.getClave().equals(clave)){
					if(!nuevoNombre.equals("")) c.setNombreUsuario(nuevoNombre);;
					if(!nuevoApellido.equals("")) c.setApellidoUsuario(nuevoApellido);
				}
			}
		}
	}
	
	@Override
	public void eliminarCuenta(String id, String motivo, String clave) throws Exception{
		
		if(!existeCuenta(id))
			throw new CuentaException("no existe ninguna cuenta con id "+id+" registrado en el Banco");
		if (clave.length()>4 || clave.length()<4)
			throw new ClaveException("La clave debe tener 4 digitos");
		
		if (motivo.equals(""))
			throw new ParametroVacioException("Alguno de los par�metros indicados es est� vac�o");
		
		for(Cuenta c: listaCuentas) {
			if(c.getId().equals(id) && c.getClave().equals(clave))
				listaCuentas.remove(c);
			break;
		}
	}


	@Override
	public void ConsignarDinero(String id, String cedula, int cantidad) throws Exception {
		if(!id.equals("")){
			
			if(!existeCuenta(id))
				throw new CuentaException("no existe ninguna cuenta con id "+id+" registrado en el Banco");
			
			if (cedula.equals("") || cantidad == 0)
				throw new ParametroVacioException("Alguno de los par�metros indicados es est� vac�o");

			for(Cuenta c : listaCuentas){
				if(c != null && c.getId() != null && c.getId().equals(id) && c.getCedula().equals(cedula)){
					int nuevoSaldo = c.getSaldo()+cantidad;
					c.setSaldo(nuevoSaldo);
					break;
				}
			}
		}
		
	}


	@Override
	public void TransferirDinero(String idDestino, int cantidad, String idOrigen, String clave) throws Exception {
		if(!idDestino.equals("") && !idOrigen.equals("") ){
			if(!existeCuenta(idDestino))
				throw new CuentaException("no existe ninguna cuenta con id de destino "+idDestino+" registrado en el Banco");
			if(!existeCuenta(idOrigen))
				throw new CuentaException("no existe ninguna cuenta con id de origen "+idOrigen+" registrado en el Banco");
			if (cantidad == 0)
				throw new ParametroVacioException("Alguno de los par�metros indicados es est� vac�o");
			if (clave.length()>4 || clave.length()<4)
				throw new ClaveException("La clave debe tener 4 digitos");
			
			for(Cuenta c: listaCuentas) {
				if(c.getId().equals(idOrigen) && c.getClave().equals(clave)){
					if(cantidad > c.getSaldo()){
						throw new CantidadException("la cantidad que quiere transferir no puede ser mayor a su saldo");
					}else {
						int nuevoSaldoOrigen = c.getSaldo()-cantidad;
						c.setSaldo(nuevoSaldoOrigen);
						break;
					}
				}
			}
			
			for(Cuenta c: listaCuentas) {
				if(c.getId().equals(idDestino)){
					int nuevoSaldoDestino = c.getSaldo()+cantidad;
					c.setSaldo(nuevoSaldoDestino);
					break;
				}
			}
		}
		
	}


	@Override
	public void RetirarDinero(String cedula, String id, int cantidad, String clave) throws Exception {
		if(!id.equals("")){
			if(!existeCuenta(id))
				throw new CuentaException("no existe ninguna cuenta con id "+id+" registrado en el Banco");
			if (cantidad == 0 || cedula.equals(""))
				throw new ParametroVacioException("Alguno de los par�metros indicados es est� vac�o");
			if (clave.length()>4 || clave.length()<4)
				throw new ClaveException("La clave debe tener 4 digitos");

			for(Cuenta c: listaCuentas) {
				if(c.getId().equals(id) && c.getClave().equals(clave)){
					if(cantidad > c.getSaldo()){
						throw new CantidadException("la cantidad que quiere transferir no puede ser mayor a su saldo");
					}else {
						int nuevoSaldo = c.getSaldo()-cantidad;
						c.setSaldo(nuevoSaldo);
						break;
					}
				}
			}
		}
		
	}
	
	

	
}
