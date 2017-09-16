package pe.com.sgresan.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ProductoDao;
import model.TCategoria;
import model.TProducto;

@ManagedBean
@SessionScoped
public class EncuestaBean {
	
	
	ProductoDao pDao = new ProductoDao();
	private List<TProducto> listaP;
	private List<TCategoria> listaC;
	private int valor;
	
	
	
	
	public EncuestaBean(){
		valor=1;
	 
	}


	public void ELECCION(){
		System.out.println("Valor :"  +valor);
		listaP= pDao.listarProductosByCategoria(valor);
	}


	public List<TProducto> getListaP() {
		listaP= pDao.listarProductosByCategoria(valor);
		return listaP;
	}




	public List<TCategoria> getListaC() {
		listaC=pDao.listarCategoria();
		return listaC;
	}


 

	public int getValor() {
		return valor;
	}




	public void setValor(int valor) {
		this.valor = valor;
	}

 

	
	
}
