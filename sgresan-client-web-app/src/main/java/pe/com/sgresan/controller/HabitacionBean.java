/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.sgresan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.DualListModel;

import pe.com.sgresan.common.Utils;
import pe.com.sgresan.entidad.Imagen;
import pe.com.sgresan.model.Habitacion;
import pe.com.sgresan.service.ConsultaService;
import pe.com.sgresan.service.HabitacionService;
/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class HabitacionBean {

    private List<Habitacion> habitacionesdisponibles;

    private Date fecInicio;
    private Date fecSalida;
    private String fecIn;
    private String fecSal;

    private DualListModel<Habitacion> cities;
    private Date max;
    
    /*** Depurar ***/
    
    @ManagedProperty(value = ConsultaService.EL_NAME)
    private ConsultaService consultaService;
    
    @ManagedProperty(value = HabitacionService.EL_NAME)
    private HabitacionService habitacionService;
    
    private List<Imagen> lstImagenesHabitaciones;
    private List<Imagen> lstImagenesDetalleHabitaciones;
    private Imagen objImagen;
    private String divMostrar;

    /**
     * Creates a new instance of HabitacionBean
     */
    public HabitacionBean() {
        max = new Date();
    }
    
	@PostConstruct
    public void init() {
		divMostrar = "habitaciones";
    	try {    		
    		//Imagenes de Habitaciones
    		if(Utils.isNull(lstImagenesHabitaciones) || lstImagenesHabitaciones.isEmpty()){
    			lstImagenesHabitaciones = consultaService.mostrarImagenesHabitaciones();
    		}
    		
    		//Datos de Habitaciones Disponibles
    		fecIn = "2016-01-01";
            fecSal = "2017-01-01";
            
            habitacionesdisponibles = habitacionService.obtenerHabitacionesDisponibles(fecIn, fecSal);
            
            List<Habitacion> citiesSource = new ArrayList<>(habitacionesdisponibles);
            List<Habitacion> citiesTarget = new ArrayList<>();
            
            cities = new DualListModel<Habitacion>(citiesSource, citiesTarget);               
		} catch (Exception e) {
			e.getMessage();
		}    	     
    }
	
	public void mostrarDetalle(String id) throws Exception{
		divMostrar = "detalle"; 
		for (Imagen imagen : lstImagenesHabitaciones) {
			if(imagen.getIdImagen().equals(id)){
				this.objImagen =  imagen;
				if(Utils.isNull(objImagen.getLstImagen()) || objImagen.getLstImagen().isEmpty()){
					objImagen.setLstImagen(consultaService.mostrarImagenesHabitacionDetalle(id));
				}
				lstImagenesDetalleHabitaciones = objImagen.getLstImagen();
				break;
			}
		}
	}
	
	public void atras() {
		divMostrar = "habitaciones";
	}

    public void Actualizar() throws Exception {
        habitacionesdisponibles = habitacionService.obtenerHabitacionesDisponibles(fecIn, fecSal);
        
        List<Habitacion> citiesSource = new ArrayList<>(habitacionesdisponibles);
        List<Habitacion> citiesTarget = new ArrayList<>();
        
        cities = new DualListModel<Habitacion>(citiesSource, citiesTarget); 
    }
    
    public void BUSQUEDA() throws Exception {
        fecIn = (fecInicio.getYear() + 1900) + "/" + (fecInicio.getMonth() + 1) + "/" + fecInicio.getDate();
        fecSal = (fecSalida.getYear() + 1900) + "/" + (fecSalida.getMonth() + 1) + "/" + fecSalida.getDate();        
        Actualizar();
    }

    public List<Habitacion> getHabitacionesdisponibles() throws Exception {
    	if(Utils.isNotNull(habitacionService)){
    		habitacionesdisponibles = habitacionService.obtenerHabitacionesDisponibles(fecIn, fecSal);
    	}
        return habitacionesdisponibles;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecSalida() {
        return fecSalida;
    }

    public void setFecSalida(Date fecSalida) {
        this.fecSalida = fecSalida;
    }

    public String getFecIn() {
        return fecIn;
    }

    public void setFecIn(String fecIn) {
        this.fecIn = fecIn;
    }

    public String getFecSal() {
        return fecSal;
    }

    public void setFecSal(String fecSal) {
        this.fecSal = fecSal;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

	/**
	 * Returns attribute cities
	 * @return cities <code>DualListModel<Habitacion></code>
	 */
	public DualListModel<Habitacion> getCities() {
		return cities;
	}

	/**
	 * Sets attribute cities
	 * @param cities <code>DualListModel<Habitacion></code>
	 */
	public void setCities(DualListModel<Habitacion> cities) {
		this.cities = cities;
	}

	/**
	 * Returns attribute divMostrar
	 * @return divMostrar <code>String</code>
	 */
	public String getDivMostrar() {
		return divMostrar;
	}

	/**
	 * Sets attribute divMostrar
	 * @param divMostrar <code>String</code>
	 */
	public void setDivMostrar(String divMostrar) {
		this.divMostrar = divMostrar;
	}

	/**
	 * Returns attribute objImagen
	 * @return objImagen <code>Imagen</code>
	 */
	public Imagen getObjImagen() {
		return objImagen;
	}

	/**
	 * Sets attribute objImagen
	 * @param objImagen <code>Imagen</code>
	 */
	public void setObjImagen(Imagen objImagen) {
		this.objImagen = objImagen;
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
	 * Returns attribute lstImagenesHabitaciones
	 * @return lstImagenesHabitaciones <code>List<Imagen></code>
	 */
	public List<Imagen> getLstImagenesHabitaciones() {
		return lstImagenesHabitaciones;
	}

	/**
	 * Returns attribute lstImagenesDetalleHabitaciones
	 * @return lstImagenesDetalleHabitaciones <code>List<Imagen></code>
	 */
	public List<Imagen> getLstImagenesDetalleHabitaciones() {
		return lstImagenesDetalleHabitaciones;
	}

	/**
	 * Returns attribute habitacionService
	 * @return habitacionService <code>HabitacionService</code>
	 */
	public HabitacionService getHabitacionService() {
		return habitacionService;
	}

	/**
	 * Sets attribute habitacionService
	 * @param habitacionService <code>HabitacionService</code>
	 */
	public void setHabitacionService(HabitacionService habitacionService) {
		this.habitacionService = habitacionService;
	}

}
