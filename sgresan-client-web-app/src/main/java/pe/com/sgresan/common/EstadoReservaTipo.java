package pe.com.sgresan.common;

public enum EstadoReservaTipo {
	
	PRE_RESERVA("pre-reserva"),PRE_RESERVA_CV("pre-reserva-cv"),RESERVADO("reservado"),HOSPEDADO("hospedado"),CANCELADO("cancelado");
	
	private EstadoReservaTipo(String nombre){
		this.nombre = nombre;
	}
	
	private String nombre;

	/**
	 * Returns attribute nombre
	 * @return nombre <code>String</code>
	 */
	public String getNombre() {
		return nombre;
	}

}
