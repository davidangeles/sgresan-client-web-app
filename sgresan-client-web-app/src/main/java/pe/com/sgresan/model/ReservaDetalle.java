package pe.com.sgresan.model;

import java.io.Serializable;

public class ReservaDetalle implements Serializable{
	
	private static final long serialVersionUID = -1529278213791623695L;
	
	private Integer idTReservaDetalle;
    private Double costo;
    private String idReserva;
    private String idHabitacion;
    
	/**
	 * Returns attribute idTReservaDetalle
	 * @return idTReservaDetalle <code>Integer</code>
	 */
	public Integer getIdTReservaDetalle() {
		return idTReservaDetalle;
	}
	
	/**
	 * Sets attribute idTReservaDetalle
	 * @param idTReservaDetalle <code>Integer</code>
	 */
	public void setIdTReservaDetalle(Integer idTReservaDetalle) {
		this.idTReservaDetalle = idTReservaDetalle;
	}
	
	/**
	 * Returns attribute costo
	 * @return costo <code>Double</code>
	 */
	public Double getCosto() {
		return costo;
	}
	
	/**
	 * Sets attribute costo
	 * @param costo <code>Double</code>
	 */
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	/**
	 * Returns attribute idReserva
	 * @return idReserva <code>String</code>
	 */
	public String getIdReserva() {
		return idReserva;
	}
	
	/**
	 * Sets attribute idReserva
	 * @param idReserva <code>String</code>
	 */
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	
	/**
	 * Returns attribute idHabitacion
	 * @return idHabitacion <code>String</code>
	 */
	public String getIdHabitacion() {
		return idHabitacion;
	}
	
	/**
	 * Sets attribute idHabitacion
	 * @param idHabitacion <code>String</code>
	 */
	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

}
