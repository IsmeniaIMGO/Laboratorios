package model;

public class Usuario {
	
	/**
	 * atributos
	 */
	private String nombre;
	private String apellido;
	private String cedula;
	
	
	/**
	 * constructor vacio
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * constructos con atributos
	 * @param nombre
	 * @param apellido
	 * @param cedula
	 */
	public Usuario(String nombre, String apellido, String cedula) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
	}

	/**
	 * metodos set y get
	 * @return
	 */
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	/**
	 * metodo hashcode con atributo cedula
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	
	/**
	 * metodo equals con atributo cedula
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

	
	/**
	 * metodo to string
	 */
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + "]";
	}
	
	
}
