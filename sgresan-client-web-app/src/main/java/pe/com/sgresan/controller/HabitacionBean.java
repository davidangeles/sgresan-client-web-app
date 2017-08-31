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
import pe.com.sgresan.model.Habitacion;
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
    
    @ManagedProperty(value = HabitacionService.EL_NAME)
    private HabitacionService habitacionService;
    
    private List<Habitacion> lstImagenesHabitaciones;
    private List<Habitacion> lstImagenesDetalleHabitaciones;
    private Habitacion objHabitacion;
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
    		lstImagenesHabitaciones = habitacionService.mostrarImagenesHabitaciones();
    		for (Habitacion objHabitacion : lstImagenesHabitaciones) {
    			objHabitacion.setImagenBase64(Base64.encodeBase64String(objHabitacion.getImagen()));    			
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
	
	public void mostrarDetalle(String idHabitacion) throws Exception{
		divMostrar = "detalle"; 
		for (Habitacion habitacion : lstImagenesHabitaciones) {
			if(habitacion.getIdHabitacion().equals(idHabitacion)){
				objHabitacion =  habitacion;
			}
		}//objHabitacion.setDescripcion("<ul><li style='text-align: left;'>Tiene Televisi&oacute;n.</li></ul>");
		lstImagenesDetalleHabitaciones = habitacionService.mostrarImagenesHabitacionDetalle(objHabitacion.getIdHabitacion());
		for (Habitacion objHabitacion : lstImagenesDetalleHabitaciones) {
			objHabitacion.setImagenBase64(Base64.encodeBase64String(objHabitacion.getImagen()));    			
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
	 * Returns attribute lstImagenesHabitaciones
	 * @return lstImagenesHabitaciones <code>List<Habitacion></code>
	 */
	public List<Habitacion> getLstImagenesHabitaciones() {
		return lstImagenesHabitaciones;
	}

	/**
	 * Sets attribute lstImagenesHabitaciones
	 * @param lstImagenesHabitaciones <code>List<Habitacion></code>
	 */
	public void setLstImagenesHabitaciones(List<Habitacion> lstImagenesHabitaciones) {
		this.lstImagenesHabitaciones = lstImagenesHabitaciones;
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

	public List<Habitacion> getLstImagenesDetalleHabitaciones() {
		return lstImagenesDetalleHabitaciones;
	}

	public void setLstImagenesDetalleHabitaciones(List<Habitacion> lstImagenesDetalleHabitaciones) {
		this.lstImagenesDetalleHabitaciones = lstImagenesDetalleHabitaciones;
	}

	public String getDivMostrar() {
		return divMostrar;
	}

	public void setDivMostrar(String divMostrar) {
		this.divMostrar = divMostrar;
	}

	public Habitacion getObjHabitacion() {
		return objHabitacion;
	}

	public void setObjHabitacion(Habitacion objHabitacion) {
		this.objHabitacion = objHabitacion;
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
