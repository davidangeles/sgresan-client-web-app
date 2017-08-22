/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.HabitacionDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.THabitacion;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class HabitacionBean {

    private List<THabitacion> habitaciones;
    private List<THabitacion> habitacionesdisponibles;
    private List<THabitacion> images;

    private Date fecInicio;
    private Date fecSalida;
    private String fecIn;
    private String fecSal;

    private DualListModel<THabitacion> cities;
    private Date max;

    /**
     * Creates a new instance of HabitacionBean
     */
    public HabitacionBean() {
        List<THabitacion> citiesSource = new ArrayList<THabitacion>();
        List<THabitacion> citiesTarget = new ArrayList<THabitacion>();
        max = new Date();
        fecIn = "2016-01-01";
        fecSal = "2017-01-01";
        HabitacionDao dao = new HabitacionDao();
        habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        for (int i = 0; i < habitacionesdisponibles.size(); i++) {
            citiesSource.add(habitacionesdisponibles.get(i));
        }
        cities = new DualListModel<THabitacion>(citiesSource, citiesTarget);

        /* -----------------------------------------------------------------------  */ 
        // Se debe cambiar este metodo posteriormente esto es solo para una prueba -- Saludos David del Futuro
        images = new ArrayList<THabitacion>();
        
        THabitacion habitacion1 = new THabitacion();
        habitacion1.setIdHabitacion("cama_simple.jpg");
        habitacion1.setNroHabitacion("Habitación Individual Simple");
        habitacion1.setDescripcion("Estas habitaciones disponen de aire acondicionado y baño compartido."
                + "\nEquipamiento audiovisual y tecnológico: WIFI gratuito.");
        images.add(habitacion1);
        
        THabitacion habitacion2 = new THabitacion();
        habitacion2.setIdHabitacion("cama_doble_camarote.jpg");
        habitacion2.setNroHabitacion("Habitación Doble Simple - Camarote");
        habitacion2.setDescripcion("Estas habitaciones disponen de aire acondicionado, baño compartido y TV."
                + "\nEquipamiento audiovisual y tecnológico: Tv y WIFI gratuito.");
        images.add(habitacion2);
        
        THabitacion habitacion4 = new THabitacion();
        habitacion4.setIdHabitacion("cama_triple.jpg");
        habitacion4.setNroHabitacion("Habitación Triple Simple");
        habitacion4.setDescripcion("Estas habitaciones disponen de aire acondicionado, baño compartido y TV."
                + "\nEquipamiento audiovisual y tecnológico: Tv y WIFI gratuito.");
        images.add(habitacion4);
        
        THabitacion habitacion5 = new THabitacion();
        habitacion5.setIdHabitacion("cama_triple.jpg");
        habitacion5.setNroHabitacion("Habitación Triple");
        habitacion5.setDescripcion("Estas habitaciones disponen de aire acondicionado, baño propio y TV."
                + "\nEquipamiento audiovisual y tecnológico: Tv y WIFI gratuito.");
        images.add(habitacion5);
        
        THabitacion habitacion3 = new THabitacion();
        habitacion3.setIdHabitacion("cama_cuatruple_1.jpg");
        habitacion3.setNroHabitacion("Habitación Cuatruple - Camarote y Cama Doble");
        habitacion3.setDescripcion("Estas habitaciones disponen de aire acondicionado, baño compartido y TV."
                + "\nEquipamiento audiovisual y tecnológico: Tv y WIFI gratuito.");
        images.add(habitacion3);
        
        THabitacion habitacion9 = new THabitacion();
        habitacion9.setIdHabitacion("servicio_desayuno.jpg");
        habitacion9.setNroHabitacion("Servicio de Desayuno");
        habitacion9.setDescripcion("Servicio adicional de Desayuno.\nDisponible de 6 am a 8 am");
        images.add(habitacion9);
        
        THabitacion habitacion10 = new THabitacion();
        habitacion10.setIdHabitacion("servicio_piscina.jpg");
        habitacion10.setNroHabitacion("Piscina");
        habitacion10.setDescripcion("Piscina al aire libre.\nDisponible de Martes a Domingo");
        images.add(habitacion10);
    }

    public void Actualizar() {
        List<THabitacion> citiesSource = new ArrayList<THabitacion>();
        List<THabitacion> citiesTarget = new ArrayList<THabitacion>();

        HabitacionDao dao = new HabitacionDao();
        habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        for (int i = 0; i < habitacionesdisponibles.size(); i++) {
            citiesSource.add(habitacionesdisponibles.get(i));
        }
        cities = new DualListModel<THabitacion>(citiesSource, citiesTarget);

    }

    public List<THabitacion> getHabitaciones() {
        HabitacionDao obj = new HabitacionDao();
        habitaciones = obj.listareserva();
        return habitaciones;
    }

    public List<THabitacion> getHabitacionesdisponibles() {
        HabitacionDao dao = new HabitacionDao();
        habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        return habitacionesdisponibles;
    }

    public List<THabitacion> getImages() {
        return images;
    }

    public void BUSQUEDA() {
        HabitacionDao dao = new HabitacionDao();
        fecIn = (fecInicio.getYear() + 1900) + "/" + (fecInicio.getMonth() + 1) + "/" + fecInicio.getDate();
        fecSal = (fecSalida.getYear() + 1900) + "/" + (fecSalida.getMonth() + 1) + "/" + fecSalida.getDate();
        habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        Actualizar();
        System.out.println("Fecha Entrada : " + fecInicio.getDate() + "/" + (fecInicio.getMonth() + 1) + "/" + (fecInicio.getYear() + 1900));
        System.out.println("Fecha Salida : " + fecSalida.getDate() + "/" + (fecSalida.getMonth() + 1) + "/" + (fecSalida.getYear() + 1900));
        System.out.println(habitacionesdisponibles.size());
    }

    public void BUSQUEDA2(Date fecE, Date fecS) {
        HabitacionDao dao = new HabitacionDao();
        fecIn = (fecE.getYear() + 1900) + "/" + (fecE.getMonth() + 1) + "/" + fecE.getDate();
        fecSal = (fecS.getYear() + 1900) + "/" + (fecS.getMonth() + 1) + "/" + fecS.getDate();
        habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        Actualizar();
        System.out.println("Fecha Entrada : " + fecE.getDate() + "/" + (fecE.getMonth() + 1) + "/" + (fecE.getYear() + 1900));
        System.out.println("Fecha Salida : " + fecS.getDate() + "/" + (fecS.getMonth() + 1) + "/" + (fecS.getYear() + 1900));
        System.out.println(habitacionesdisponibles.size());
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

    public DualListModel<THabitacion> getCities() {
        return cities;
    }

    public void setCities(DualListModel<THabitacion> cities) {
        this.cities = cities;
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

}
