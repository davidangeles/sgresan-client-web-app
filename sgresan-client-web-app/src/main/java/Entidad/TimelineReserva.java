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
public class TimelineReserva {
    String idReserva;
    String cliente;
    Date fecha_entrada;
    Date fecha_salida;
    String descripcion;
    double subtotal;
    double igv;
    double total;
    String estado;

    public TimelineReserva() {
    }

    public TimelineReserva(String idReserva, String cliente, Date fecha_entrada, Date fecha_salida, String descripcion, double subtotal, double igv, double total,String estado) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.descripcion = descripcion;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.estado = estado;
    }



    
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
