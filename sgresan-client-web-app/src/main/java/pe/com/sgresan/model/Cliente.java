package pe.com.sgresan.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1556012369406375774L;
	
	private String idCliente;
    private String tipoCliente;
    private Integer ruc;
    private String razonSocial;
    private String representanteLegal;
    private String descripcion;
    private String idPersona;
    private Persona objPersona;
    
	/**
	 * Returns attribute idClienteidPersona
	 * @return idClienteidPersona <code>String</code>
	 */
	public String getIdCliente() {
		return idCliente;
	}
	
	/**
	 * Sets attribute idCliente
	 * @param idCliente <code>String</code>
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	/**
	 * Returns attribute tipoClienteidPersona
	 * @return tipoClienteidPersona <code>String</code>
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	
	/**
	 * Sets attribute tipoCliente
	 * @param tipoCliente <code>String</code>
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	/**
	 * Returns attribute rucidPersona
	 * @return rucidPersona <code>Integer</code>
	 */
	public Integer getRuc() {
		return ruc;
	}
	
	/**
	 * Sets attribute ruc
	 * @param ruc <code>Integer</code>
	 */
	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}
	
	/**
	 * Returns attribute razonSocialidPersona
	 * @return razonSocialidPersona <code>String</code>
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	
	/**
	 * Sets attribute razonSocial
	 * @param razonSocial <code>String</code>
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	/**
	 * Returns attribute representanteLegalidPersona
	 * @return representanteLegalidPersona <code>String</code>
	 */
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	
	/**
	 * Sets attribute representanteLegal
	 * @param representanteLegal <code>String</code>
	 */
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	
	/**
	 * Returns attribute descripcionidPersona
	 * @return descripcionidPersona <code>String</code>
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets attribute descripcion
	 * @param descripcion <code>String</code>
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Returns attribute idPersona
	 * @return idPersona <code>String</code>
	 */
	public String getIdPersona() {
		return idPersona;
	}

	/**
	 * Sets attribute idPersona
	 * @param idPersona <code>String</code>
	 */
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * Returns attribute objPersonaidPersona
	 * @return objPersonaidPersona <code>Persona</code>
	 */
	public Persona getObjPersona() {
		return objPersona;
	}

	/**
	 * Sets attribute objPersona
	 * @param objPersona <code>Persona</code>
	 */
	public void setObjPersona(Persona objPersona) {
		this.objPersona = objPersona;
	}	
}
