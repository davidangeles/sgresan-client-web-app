package pe.com.sgresan.model;

import java.util.Date;
import java.util.List;

public class Reserva {
	
    private String idReserva;    
    private String estado;
    private Date fechaRegistro;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String descripcion;
    private String modalidadPago;
    private byte[] voucher;
    private Double subtotal;
    private Double igv;
    private Double precio;
    private String usuario;
    private String idCliente;
    private int cantN;
    private int cantA;
    private int cantTotal;
    
    private Cliente objCliente;
    private List<Habitacion> lstHabitacion;

	/**
	 * Returns attribute idReservaidPersona
	 * @return idReservaidPersona <code>String</code>
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
	 * Returns attribute estadoidPersona
	 * @return estadoidPersona <code>String</code>
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
	 * Returns attribute fechaRegistroidPersona
	 * @return fechaRegistroidPersona <code>Date</code>
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Sets attribute fechaRegistro
	 * @param fechaRegistro <code>Date</code>
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

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
	 * Returns attribute fechaEntradaidPersona
	 * @return fechaEntradaidPersona <code>Date</code>
	 */
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	/**
	 * Sets attribute fechaEntrada
	 * @param fechaEntrada <code>Date</code>
	 */
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	/**
	 * Returns attribute fechaSalidaidPersona
	 * @return fechaSalidaidPersona <code>Date</code>
	 */
	public Date getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * Sets attribute fechaSalida
	 * @param fechaSalida <code>Date</code>
	 */
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
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
	 * Returns attribute modalidadPagoidPersona
	 * @return modalidadPagoidPersona <code>String</code>
	 */
	public String getModalidadPago() {
		return modalidadPago;
	}

	/**
	 * Sets attribute modalidadPago
	 * @param modalidadPago <code>String</code>
	 */
	public void setModalidadPago(String modalidadPago) {
		this.modalidadPago = modalidadPago;
	}

	/**
	 * Returns attribute voucheridPersona
	 * @return voucheridPersona <code>byte[]</code>
	 */
	public byte[] getVoucher() {
		return voucher;
	}

	/**
	 * Sets attribute voucher
	 * @param voucher <code>byte[]</code>
	 */
	public void setVoucher(byte[] voucher) {
		this.voucher = voucher;
	}

	/**
	 * Returns attribute subtotalidPersona
	 * @return subtotalidPersona <code>Double</code>
	 */
	public Double getSubtotal() {
		return subtotal;
	}

	/**
	 * Sets attribute subtotal
	 * @param subtotal <code>Double</code>
	 */
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * Returns attribute igvidPersona
	 * @return igvidPersona <code>Double</code>
	 */
	public Double getIgv() {
		return igv;
	}

	/**
	 * Sets attribute igv
	 * @param igv <code>Double</code>
	 */
	public void setIgv(Double igv) {
		this.igv = igv;
	}

	/**
	 * Returns attribute precioidPersona
	 * @return precioidPersona <code>Double</code>
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Sets attribute precio
	 * @param precio <code>Double</code>
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * Returns attribute usuarioidPersona
	 * @return usuarioidPersona <code>String</code>
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets attribute usuario
	 * @param usuario <code>String</code>
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Returns attribute cantNidPersona
	 * @return cantNidPersona <code>int</code>
	 */
	public int getCantN() {
		return cantN;
	}

	/**
	 * Sets attribute cantN
	 * @param cantN <code>int</code>
	 */
	public void setCantN(int cantN) {
		this.cantN = cantN;
	}

	/**
	 * Returns attribute cantAidPersona
	 * @return cantAidPersona <code>int</code>
	 */
	public int getCantA() {
		return cantA;
	}

	/**
	 * Sets attribute cantA
	 * @param cantA <code>int</code>
	 */
	public void setCantA(int cantA) {
		this.cantA = cantA;
	}

	/**
	 * Returns attribute cantTotalidPersona
	 * @return cantTotalidPersona <code>int</code>
	 */
	public int getCantTotal() {
		return cantTotal;
	}

	/**
	 * Sets attribute cantTotal
	 * @param cantTotal <code>int</code>
	 */
	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}

	/**
	 * Returns attribute objClienteidPersona
	 * @return objClienteidPersona <code>Cliente</code>
	 */
	public Cliente getObjCliente() {
		return objCliente;
	}

	/**
	 * Sets attribute objCliente
	 * @param objCliente <code>Cliente</code>
	 */
	public void setObjCliente(Cliente objCliente) {
		this.objCliente = objCliente;
	}

	/**
	 * Returns attribute lstHabitacion
	 * @return lstHabitacion <code>List<Habitacion></code>
	 */
	public List<Habitacion> getLstHabitacion() {
		return lstHabitacion;
	}

	/**
	 * Sets attribute lstHabitacion
	 * @param lstHabitacion <code>List<Habitacion></code>
	 */
	public void setLstHabitacion(List<Habitacion> lstHabitacion) {
		this.lstHabitacion = lstHabitacion;
	}

}
