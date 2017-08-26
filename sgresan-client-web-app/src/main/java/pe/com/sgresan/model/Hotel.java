package pe.com.sgresan.model;

import java.io.Serializable;

public class Hotel implements Serializable{
	
	private static final long serialVersionUID = 8266790674423459398L;
	
	private int ruc;
    private String razonSocial;
    private String fechaInicio;
    private String direccion;
    private String telefono;
    private byte[] logo;
    
	/**
	 * Returns attribute rucidPersona
	 * @return rucidPersona <code>int</code>
	 */
	public int getRuc() {
		return ruc;
	}
	
	/**
	 * Sets attribute ruc
	 * @param ruc <code>int</code>
	 */
	public void setRuc(int ruc) {
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
	 * Returns attribute fechaInicioidPersona
	 * @return fechaInicioidPersona <code>String</code>
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Sets attribute fechaInicio
	 * @param fechaInicio <code>String</code>
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Returns attribute direccionidPersona
	 * @return direccionidPersona <code>String</code>
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Sets attribute direccion
	 * @param direccion <code>String</code>
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Returns attribute telefonoidPersona
	 * @return telefonoidPersona <code>String</code>
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Sets attribute telefono
	 * @param telefono <code>String</code>
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Returns attribute logoidPersona
	 * @return logoidPersona <code>byte[]</code>
	 */
	public byte[] getLogo() {
		return logo;
	}
	
	/**
	 * Sets attribute logo
	 * @param logo <code>byte[]</code>
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}    

}
