package pe.com.sgresan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import dao.ProductoDao;
import model.TCategoria;
import model.TProducto;
import pe.com.sgresan.model.Usuario;
import pe.com.sgresan.service.EncuestaService;

@ManagedBean
@SessionScoped
public class EncuestaBean {
	
	 @ManagedProperty(value = EncuestaService.EL_NAME )
	 private EncuestaService encuestaService;
	
	ProductoDao pDao = new ProductoDao();
	private List<TProducto> listaP;
	private List<TCategoria> listaC;
	private int valor;
	
	private int[] listaServ;
	private List<Integer> listaS;
	
	private List<SelectItem> cars;
	
	
	private String com1,com2,com3;
	
	public EncuestaBean(){
		valor=1;
		
cars = new ArrayList<SelectItem>();
listaC=pDao.listarCategoria();
		for(int i=0;i<listaC.size();i++){
			 SelectItemGroup germanCars = new SelectItemGroup(listaC.get(i).getNombreCategoria());
			 
			 listaP= pDao.listarProductosByCategoria(listaC.get(i).getIdCategoria());
			 SelectItem[] valores= new SelectItem[listaP.size()];
			 
			for(int j=0;j<listaP.size();j++){
				  valores[j]=new SelectItem(listaP.get(j).getIdProducto(),listaP.get(j).getNombreProducto());
			 		}
			germanCars.setSelectItems(valores);
			cars.add(germanCars);
		}

	 
	}

	
 

	public void ELECCION(){
		System.out.println("Valor :"  +valor);
		listaP= pDao.listarProductosByCategoria(valor);
	}
	
	
	public void GUARDAR_ENCUESTA(){
		System.out.println("::::VALORES::::");
		String c= ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getNombreUsuario();
		int re= encuestaService.CrearCabecera(c, com1, com2, com3);
		
		
		System.out.println("TAMANIO : "+ listaServ.length);
 
		for(int i=0;i<listaServ.length;i++){
		 
	
			encuestaService.CrearDetalleEncuesta(re, listaServ[i]);
		}
	}


	public List<TProducto> getListaP() {
		listaP= pDao.listarProductosByCategoria(valor);
		return listaP;
	}




	public List<TCategoria> getListaC() {
		return listaC;
	}


 

	public int getValor() {
		return valor;
	}




	public void setValor(int valor) {
		this.valor = valor;
	}


	public int[] getListaServ() {
		return listaServ;
	}


	public void setListaServ(int[] listaServ) {
		this.listaServ = listaServ;
	}


	public List<Integer> getListaS() {
		return listaS;
	}


	public void setListaS(List<Integer> listaS) {
		this.listaS = listaS;
	}


	public List<SelectItem> getCars() {
		return cars;
	}


	public void setCars(List<SelectItem> cars) {
		this.cars = cars;
	}




	public String getCom1() {
		return com1;
	}




	public void setCom1(String com1) {
		this.com1 = com1;
	}




	public String getCom2() {
		return com2;
	}




	public void setCom2(String com2) {
		this.com2 = com2;
	}




	public String getCom3() {
		return com3;
	}




	public void setCom3(String com3) {
		this.com3 = com3;
	}




	public EncuestaService getEncuestaService() {
		return encuestaService;
	}




	public void setEncuestaService(EncuestaService encuestaService) {
		this.encuestaService = encuestaService;
	}

 
	
	
	
}
