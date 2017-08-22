package pe.com.sgresan.model;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 7046616533860563717L;
	
	private String idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String estado;
    private String tipoUsuario;
    private Persona objPersona;
    
	/**
	 * Returns attribute idUsuario
	 * @return idUsuario <code>String</code>
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Sets attribute idUsuario
	 * @param idUsuario <code>String</code>
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Returns attribute nombreUsuario
	 * @return nombreUsuario <code>String</code>
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	/**
	 * Sets attribute nombreUsuario
	 * @param nombreUsuario <code>String</code>
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	/**
	 * Returns attribute contrasena
	 * @return contrasena <code>String</code>
	 */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * Sets attribute contrasena
	 * @param contrasena <code>String</code>
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * Returns attribute estado
	 * @return estado <code>String</code>
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Sets attribute estado
	 * @param estado <code>String</code>
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Returns attribute tipoUsuario
	 * @return tipoUsuario <code>String</code>
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	/**
	 * Sets attribute tipoUsuario
	 * @param tipoUsuario <code>String</code>
	 */
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * Returns attribute objPersona
	 * @return objPersona <code>Persona</code>
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
