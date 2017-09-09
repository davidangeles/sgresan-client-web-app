package pe.com.sgresan.model;

import java.io.Serializable;

public class ParametroDetalle implements Serializable{
	
	private static final long serialVersionUID = 4831795790402217352L;
	
	private Integer id;
	private Integer parametroFk;
	private String nombre;
	private String valor;
	private String valor2;
	private String nemonico;
	
	/**
	 * Returns attribute id
	 * @return id <code>Integer</code>
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets attribute id
	 * @param id <code>Integer</code>
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns attribute parametroFk
	 * @return parametroFk <code>Integer</code>
	 */
	public Integer getParametroFk() {
		return parametroFk;
	}

	/**
	 * Sets attribute parametroFk
	 * @param parametroFk <code>Integer</code>
	 */
	public void setParametroFk(Integer parametroFk) {
		this.parametroFk = parametroFk;
	}

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
	 * Returns attribute valor
	 * @return valor <code>String</code>
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets attribute valor
	 * @param valor <code>String</code>
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Returns attribute valor2
	 * @return valor2 <code>String</code>
	 */
	public String getValor2() {
		return valor2;
	}

	/**
	 * Sets attribute valor2
	 * @param valor2 <code>String</code>
	 */
	public void setValor2(String valor2) {
		this.valor2 = valor2;
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
