package model;

public class Cuenta {
	
	/**
	 * atributos
	 */
	private String id;
	private int saldo;
	private String clave;
	private Usuario usuario;
	
	

	/**
	 * constructor
	 * @param id
	 * @param saldo
	 * @param clave
	 * @param nombreUsuario
	 * @param apellidoUsuario
	 * @param cedula
	 * @param usuario
	 */
	public Cuenta(String id, int saldo, String clave,Usuario usuario) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.clave = clave;
		this.usuario = usuario;
	}

	/**
	 * Metodos set y get de cada atributo
	 * @return
	 */
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * metodo hashcode con atributo id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * metodo equals con atributo id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	/**
	 * metodo to string
	 */
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", saldo=" + saldo + ", clave=" + clave + ", usuario=" + usuario.toString() + "]";
	}

	

	

}