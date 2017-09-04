package pe.com.sgresan.entidad;

import java.io.Serializable;
import java.util.Date;

public class TimelineReserva implements Serializable{
	
	private static final long serialVersionUID = -5205168803377188005L;
	
	String idReserva;
	String cliente;
	Date fecha_entrada;
	Date fecha_salida;
	String descripcion;
	double subtotal;
	double igv;
	double total;
	String estado;
	int cantTotal;
        
	public TimelineReserva() {
	}

	public TimelineReserva(String idReserva, String cliente, Date fecha_entrada, Date fecha_salida, String descripcion,
			double subtotal, double igv, double total, String estado, int canTotal) {
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.descripcion = descripcion;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.estado = estado;
                this.cantTotal= canTotal;
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
	 * Returns attribute cliente
	 * @return cliente <code>String</code>
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * Sets attribute cliente
	 * @param cliente <code>String</code>
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	 * Returns attribute descripcion
	 * @return descripcion <code>String</code>
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
	 * Returns attribute subtotal
	 * @return subtotal <code>double</code>
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * Sets attribute subtotal
	 * @param subtotal <code>double</code>
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * Returns attribute igv
	 * @return igv <code>double</code>
	 */
	public double getIgv() {
		return igv;
	}

	/**
	 * Sets attribute igv
	 * @param igv <code>double</code>
	 */
	public void setIgv(double igv) {
		this.igv = igv;
	}

	/**
	 * Returns attribute total
	 * @return total <code>double</code>
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Sets attribute total
	 * @param total <code>double</code>
	 */
	public void setTotal(double total) {
		this.total = total;
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

    public int getCantTotal() {
        return cantTotal;
    }

    public void setCantTotal(int cantTotal) {
        this.cantTotal = cantTotal;
    }
        
        
}
