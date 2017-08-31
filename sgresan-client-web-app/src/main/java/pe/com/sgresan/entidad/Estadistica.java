package pe.com.sgresan.entidad;

import java.io.Serializable;
import java.math.BigInteger;

public class Estadistica implements Serializable{
	
	private static final long serialVersionUID = -8178321187261412652L;
	
	private BigInteger cantidad;
	private String fecha;        
    private String texto;
	
	public Estadistica()
	{
		
	}
	
	public Estadistica(BigInteger CANTIDAD,String FECHA,String TEXTO)
	{
		this.cantidad=CANTIDAD;
		this.fecha=FECHA;
                this.texto=TEXTO;
	}

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
	 * Returns attribute texto
	 * @return texto <code>String</code>
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Sets attribute texto
	 * @param texto <code>String</code>
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}	
	
}
