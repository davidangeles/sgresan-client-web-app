package pe.com.sgresan.model;

import java.io.Serializable;
import java.util.Date;

import model.TUbigeo;
import model.TUsuario;

public class Persona implements Serializable{
	
	private static final long serialVersionUID = -9006999867088047222L;
	
	private String idPersona;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private String dni;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private String estado;
    private byte[] imagen;
    private String observacion;
	
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
	 * Returns attribute nombres
	 * @return nombres <code>String</code>
	 */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * Sets attribute nombres
	 * @param nombres <code>String</code>
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * Returns attribute apellidoP
	 * @return apellidoP <code>String</code>
	 */
	public String getApellidoP() {
		return apellidoP;
	}
	
	/**
	 * Sets attribute apellidoP
	 * @param apellidoP <code>String</code>
	 */
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}
	
	/**
	 * Returns attribute apellidoM
	 * @return apellidoM <code>String</code>
	 */
	public String getApellidoM() {
		return apellidoM;
	}
	
	/**
	 * Sets attribute apellidoM
	 * @param apellidoM <code>String</code>
	 */
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}
	
	/**
	 * Returns attribute dni
	 * @return dni <code>String</code>
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Sets attribute dni
	 * @param dni <code>String</code>
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Returns attribute fechaNacimiento
	 * @return fechaNacimiento <code>Date</code>
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Sets attribute fechaNacimiento
	 * @param fechaNacimiento <code>Date</code>
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Returns attribute direccion
	 * @return direccion <code>String</code>
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
	 * Returns attribute telefono
	 * @return telefono <code>String</code>
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
	 * Returns attribute celular
	 * @return celular <code>String</code>
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Sets attribute celular
	 * @param celular <code>String</code>
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	/**
	 * Returns attribute email
	 * @return email <code>String</code>
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets attribute email
	 * @param email <code>String</code>
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Returns attribute imagen
	 * @return imagen <code>byte[]</code>
	 */
	public byte[] getImagen() {
		return imagen;
	}
	
	/**
	 * Sets attribute imagen
	 * @param imagen <code>byte[]</code>
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Returns attribute observacion
	 * @return observacion <code>String</code>
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * Sets attribute observacion
	 * @param observacion <code>String</code>
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
