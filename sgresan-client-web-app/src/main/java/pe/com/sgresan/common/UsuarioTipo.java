package pe.com.sgresan.common;

public enum UsuarioTipo {
	
	U_RECEP("recepcionista", "R"),U_CLIENTE("cliente", "C"),U_GERENTE("gerente", "A");
	
	private UsuarioTipo(String nombre, String nemonico){
		this.nombre = nombre;
		this.nemonico = nemonico;
	}

	private String nombre;
	private String nemonico;
	
	/**
	 * Returns attribute nombre
	 * @return nombre <code>String</code>
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets attribute nombre
	 * @param nombre <code>String</code>
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Returns attribute nemonico
	 * @return nemonico <code>String</code>
	 */
	public String getNemonico() {
		return nemonico;
	}
	
	/**
	 * Sets attribute nemonico
	 * @param nemonico <code>String</code>
	 */
	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}
}
