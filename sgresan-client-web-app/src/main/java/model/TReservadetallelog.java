package model;
// Generated 07/06/2015 10:37:49 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TReservadetallelog generated by hbm2java
 */
public class TReservadetallelog  implements java.io.Serializable {


     private Integer id;
     private int idTReservaDetalle;
     private Double costo;
     private String idReserva;
     private String idHabitacion;
     private String accion;
     private Date fecha;
     private String pc;

    public TReservadetallelog() {
    }

	
    public TReservadetallelog(int idTReservaDetalle) {
        this.idTReservaDetalle = idTReservaDetalle;
    }
    public TReservadetallelog(int idTReservaDetalle, Double costo, String idReserva, String idHabitacion, String accion, Date fecha, String pc) {
       this.idTReservaDetalle = idTReservaDetalle;
       this.costo = costo;
       this.idReserva = idReserva;
       this.idHabitacion = idHabitacion;
       this.accion = accion;
       this.fecha = fecha;
       this.pc = pc;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdTReservaDetalle() {
        return this.idTReservaDetalle;
    }
    
    public void setIdTReservaDetalle(int idTReservaDetalle) {
        this.idTReservaDetalle = idTReservaDetalle;
    }
    public Double getCosto() {
        return this.costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    public String getIdReserva() {
        return this.idReserva;
    }
    
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    public String getIdHabitacion() {
        return this.idHabitacion;
    }
    
    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
    public String getAccion() {
        return this.accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getPc() {
        return this.pc;
    }
    
    public void setPc(String pc) {
        this.pc = pc;
    }




}


