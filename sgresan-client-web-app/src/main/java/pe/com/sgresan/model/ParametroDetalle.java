package pe.com.sgresan.model;

import java.io.Serializable;

public class ParametroDetalle implements Serializable{
	
	private static final long serialVersionUID = 4831795790402217352L;
	
	private Integer id;
	private Integer parametroFk;
	private String nombre;
	private String valor;
	private String valor2;
	private String nemonico;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParametroFk() {
		return parametroFk;
	}
	public void setParametroFk(Integer parametroFk) {
		this.parametroFk = parametroFk;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValor2() {
		return valor2;
	}
	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}
	public String getNemonico() {
		return nemonico;
	}
	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}
}
