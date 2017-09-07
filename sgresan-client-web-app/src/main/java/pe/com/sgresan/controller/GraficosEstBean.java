package pe.com.sgresan.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pe.com.sgresan.service.ConsultaService;

@ManagedBean
@SessionScoped
public class GraficosEstBean {
	
	@ManagedProperty(value = ConsultaService.EL_NAME)
	private ConsultaService consultaService;

	public void onPageLoad() {
		System.out.println("Carga una vez");
	}

}
