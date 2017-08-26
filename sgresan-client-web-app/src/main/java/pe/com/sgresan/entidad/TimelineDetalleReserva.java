package pe.com.sgresan.entidad;

import java.util.Date;

public class TimelineDetalleReserva {
	
	TimelineReserva timelinereserva;
	Date fecha_entrada;
	Date fecha_salida;
	boolean booleano;
	String habitacion;
	String estado;

	public TimelineDetalleReserva() {
	}

	public TimelineDetalleReserva(TimelineReserva timelinereserva, Date fecha_entrada, Date fecha_salida,
			boolean booleano, String habitacion, String estado) {
		this.timelinereserva = timelinereserva;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.booleano = booleano;
		this.habitacion = habitacion;
		this.estado = estado;
	}

	/**
	 * Returns attribute timelinereserva
	 * @return timelinereserva <code>TimelineReserva</code>
	 */
	public TimelineReserva getTimelinereserva() {
		return timelinereserva;
	}

	/**
	 * Sets attribute timelinereserva
	 * @param timelinereserva <code>TimelineReserva</code>
	 */
	public void setTimelinereserva(TimelineReserva timelinereserva) {
		this.timelinereserva = timelinereserva;
	}

	/**
	 * Returns attribute fecha_entrada
	 * @return fecha_entrada <code>Date</code>
	 */
	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	/**
	 * Sets attribute fecha_entrada
	 * @param fecha_entrada <code>Date</code>
	 */
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	/**
	 * Returns attribute fecha_salida
	 * @return fecha_salida <code>Date</code>
	 */
	public Date getFecha_salida() {
		return fecha_salida;
	}

	/**
	 * Sets attribute fecha_salida
	 * @param fecha_salida <code>Date</code>
	 */
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	/**
	 * Returns attribute booleano
	 * @return booleano <code>boolean</code>
	 */
	public boolean isBooleano() {
		return booleano;
	}

	/**
	 * Sets attribute booleano
	 * @param booleano <code>boolean</code>
	 */
	public void setBooleano(boolean booleano) {
		this.booleano = booleano;
	}

	/**
	 * Returns attribute habitacion
	 * @return habitacion <code>String</code>
	 */
	public String getHabitacion() {
		return habitacion;
	}

	/**
	 * Sets attribute habitacion
	 * @param habitacion <code>String</code>
	 */
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
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

}
