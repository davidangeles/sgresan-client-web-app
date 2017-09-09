package pe.com.sgresan.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pe.com.sgresan.model.ParametroDetalle;
import pe.com.sgresan.service.ConsultaService;

@ManagedBean
@SessionScoped
public class GraficosEstBean {
	
	private List<ParametroDetalle> lstTipoHabitacion;
	private List<ParametroDetalle> lstEstadoReserva;
	
	private Integer[] selectedHabitacion;
	private String[] selectedEstReserva;
		
	@ManagedProperty(value = ConsultaService.EL_NAME)
	private ConsultaService consultaService;

	@PostConstruct
	public void init() {
		try {
			System.out.println("Carga una vez");
			lstTipoHabitacion = consultaService.obtenerTipoHabitaciones();//selectedCars = new String[5];
			lstEstadoReserva = consultaService.obtenerEstadoReserva();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prueba() throws Exception {
		System.out.println("Carga una vez");
		lstTipoHabitacion = consultaService.obtenerTipoHabitaciones();//selectedCars = new String[5];
		lstEstadoReserva = consultaService.obtenerEstadoReserva();
	}
	
	public void quepasa(){
		System.out.println("111");
		System.out.println("2222");
	}

	/**
	 * Returns attribute consultaService
	 * @return consultaService <code>ConsultaService</code>
	 */
	public ConsultaService getConsultaService() {
		return consultaService;
	}

	/**
	 * Sets attribute consultaService
	 * @param consultaService <code>ConsultaService</code>
	 */
	public void setConsultaService(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}

	/**
	 * Returns attribute lstTipoHabitacion
	 * @return lstTipoHabitacion <code>List<ParametroDetalle></code>
	 */
	public List<ParametroDetalle> getLstTipoHabitacion() {
		return lstTipoHabitacion;
	}	

	/**
	 * Returns attribute lstEstadoReserva
	 * @return lstEstadoReserva <code>List<ParametroDetalle></code>
	 */
	public List<ParametroDetalle> getLstEstadoReserva() {
		return lstEstadoReserva;
	}	
	
	/**
	 * Returns attribute selectedEstReserva
	 * @return selectedEstReserva <code>String[]</code>
	 */
	public String[] getSelectedEstReserva() {
		return selectedEstReserva;
	}

	/**
	 * Sets attribute selectedEstReserva
	 * @param selectedEstReserva <code>String[]</code>
	 */
	public void setSelectedEstReserva(String[] selectedEstReserva) {
		this.selectedEstReserva = selectedEstReserva;
	}

	/**
	 * Returns attribute selectedHabitacion
	 * @return selectedHabitacion <code>Integer[]</code>
	 */
	public Integer[] getSelectedHabitacion() {
		return selectedHabitacion;
	}

	/**
	 * Sets attribute selectedHabitacion
	 * @param selectedHabitacion <code>Integer[]</code>
	 */
	public void setSelectedHabitacion(Integer[] selectedHabitacion) {
		this.selectedHabitacion = selectedHabitacion;
	}

}
