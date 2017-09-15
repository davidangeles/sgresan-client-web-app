package pe.com.sgresan.entidad;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class GraficoReserva {
	
	//Datos Resultado
	private BigInteger cantidad;
	private String fecha;
	private String estadoReserva;
	private String tipoHabitacion;
	
	private List<GraficoReserva> lstDataReserva;
	private List<GraficoReserva> lstDataHabitación;
	
	/**
	 * Returns attribute cantidad
	 * @return cantidad <code>BigInteger</code>
	 */
	public BigInteger getCantidad() {
		return cantidad;
	}
	
	/**
	 * Sets attribute cantidad
	 * @param cantidad <code>BigInteger</code>
	 */
	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Returns attribute fecha
	 * @return fecha <code>String</code>
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets attribute fecha
	 * @param fecha <code>String</code>
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Returns attribute estadoReserva
	 * @return estadoReserva <code>String</code>
	 */
	public String getEstadoReserva() {
		return estadoReserva;
	}
	
	/**
	 * Sets attribute estadoReserva
	 * @param estadoReserva <code>String</code>
	 */
	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	/**
	 * Returns attribute tipoHabitacion
	 * @return tipoHabitacion <code>String</code>
	 */
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}
	
	/**
	 * Sets attribute tipoHabitacion
	 * @param tipoHabitacion <code>String</code>
	 */
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	/**
	 * Returns attribute lstEstadisticaReserva
	 * @return lstEstadisticaReserva <code>List<EstadisticaReserva></code>
	 */
	public List<GraficoReserva> getLstDataReserva() {
		return lstDataReserva;
	}

	/**
	 * Sets attribute lstEstadisticaReserva
	 * @param lstEstadisticaReserva <code>List<EstadisticaReserva></code>
	 */
	public void setLstDataReserva(List<GraficoReserva> lstEstadisticaReserva) {
		this.lstDataReserva = lstEstadisticaReserva;
	}

	/**
	 * Returns attribute lstEstadisticaHabitación
	 * @return lstEstadisticaHabitación <code>List<EstadisticaReserva></code>
	 */
	public List<GraficoReserva> getLstDataHabitación() {
		return lstDataHabitación;
	}

	/**
	 * Sets attribute lstEstadisticaHabitación
	 * @param lstEstadisticaHabitación <code>List<EstadisticaReserva></code>
	 */
	public void setLstDataHabitación(List<GraficoReserva> lstEstadisticaHabitación) {
		this.lstDataHabitación = lstEstadisticaHabitación;
	}

}
