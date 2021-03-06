package pe.com.sgresan.model;

import java.util.HashSet;
import java.util.Set;

public class Habitacion {

	private String idHabitacion;
	private Hotel objHotel;	
	private String nroHabitacion;
	private String descripcion;
	private double precio;	
	private int idTTipoHabitacion;
	private String tipoHabNombre;
	private byte[] imagen;	

	/**
	 * Returns attribute idHabitacion
	 * 
	 * @return idHabitacion <code>String</code>
	 */
	public String getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * Sets attribute idHabitacion
	 * 
	 * @param idHabitacion
	 *            <code>String</code>
	 */
	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/**
	 * Returns attribute objHotel
	 * 
	 * @return objHotel <code>Hotel</code>
	 */
	public Hotel getObjHotel() {
		return objHotel;
	}

	/**
	 * Sets attribute objHotel
	 * 
	 * @param objHotel
	 *            <code>Hotel</code>
	 */
	public void setObjHotel(Hotel objHotel) {
		this.objHotel = objHotel;
	}

	/**
	 * Returns attribute idTTipoHabitacion
	 * 
	 * @return idTTipoHabitacion <code>int</code>
	 */
	public int getIdTTipoHabitacion() {
		return idTTipoHabitacion;
	}

	/**
	 * Sets attribute idTTipoHabitacion
	 * 
	 * @param idTTipoHabitacion
	 *            <code>int</code>
	 */
	public void setIdTTipoHabitacion(int idTTipoHabitacion) {
		this.idTTipoHabitacion = idTTipoHabitacion;
	}

	/**
	 * Returns attribute nroHabitacion
	 * 
	 * @return nroHabitacion <code>String</code>
	 */
	public String getNroHabitacion() {
		return nroHabitacion;
	}

	/**
	 * Sets attribute nroHabitacion
	 * 
	 * @param nroHabitacion
	 *            <code>String</code>
	 */
	public void setNroHabitacion(String nroHabitacion) {
		this.nroHabitacion = nroHabitacion;
	}

	/**
	 * Returns attribute descripcion
	 * 
	 * @return descripcion <code>String</code>
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets attribute descripcion
	 * 
	 * @param descripcion
	 *            <code>String</code>
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Returns attribute precio
	 * 
	 * @return precio <code>double</code>
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Sets attribute precio
	 * 
	 * @param precio
	 *            <code>double</code>
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Returns attribute tipoHabNombre
	 * @return tipoHabNombre <code>String</code>
	 */
	public String getTipoHabNombre() {
		return tipoHabNombre;
	}

	/**
	 * Sets attribute tipoHabNombre
	 * @param tipoHabNombre <code>String</code>
	 */
	public void setTipoHabNombre(String tipoHabNombre) {
		this.tipoHabNombre = tipoHabNombre;
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
	
}
