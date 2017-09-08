package pe.com.sgresan.entidad;

import java.io.Serializable;

public class TipoPago implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -8485550502129482870L;
		private int idTipoPago;
		private String nombre;
		
		
		public TipoPago(){
			
		}
		
		public TipoPago(int idTipoPago, String nombre) {
			super();
			this.idTipoPago = idTipoPago;
			this.nombre = nombre;
		}
		public int getIdTipoPago() {
			return idTipoPago;
		}
		public void setIdTipoPago(int idTipoPago) {
			this.idTipoPago = idTipoPago;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		
		
}
