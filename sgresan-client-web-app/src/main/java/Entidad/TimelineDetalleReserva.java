/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.util.Date;

/**
 *
 * @author Joel
 */
public class TimelineDetalleReserva {
      TimelineReserva  timelinereserva;
      Date fecha_entrada;
      Date fecha_salida;
      boolean booleano;
      String habitacion;
      String estado;

    public TimelineDetalleReserva() {
    }

    public TimelineDetalleReserva(TimelineReserva  timelinereserva,Date fecha_entrada, Date fecha_salida, boolean booleano, String habitacion, String estado) {
        this.timelinereserva=timelinereserva;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.booleano = booleano;
        this.habitacion = habitacion;
        this.estado = estado;
    }

    public TimelineReserva getTimelinereserva() {
        return timelinereserva;
    }

    public void setTimelinereserva(TimelineReserva timelinereserva) {
        this.timelinereserva = timelinereserva;
    }

 
    
    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public boolean isBooleano() {
        return booleano;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
      
      
}
