/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.THabitacion;
import model.TTipohabitacion;

import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class HabitacionDao {
    public List<THabitacion> listareserva()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from THabitacion").list();
    }
    
    public List<THabitacion> listarhabitaciones(String fecEnt,String fecSal)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from THabitacion  where idHabitacion not in (\n" +
                                   "select hab.idHabitacion from TReservadetalle  reservdet\n" +
                                   "inner join reservdet.TReserva rerserv\n" +
                                   "inner join reservdet.THabitacion hab\n" +
                                   " where (fechaEntrada <  '"+fecEnt+"' and fechaSalida >  '"+fecEnt+"')\n" +
"or (fechaEntrada < '"+fecSal+"' and fechaSalida > '"+fecSal+"') \n" +
"or (fechaEntrada between  '"+fecEnt+"' and '"+fecSal+"' and fechaSalida between '"+fecEnt+"' and '"+fecSal+"') \n" +
"or (fechaEntrada < '"+fecEnt+"' and fechaSalida > '"+fecSal+"') )").list();
    }
    
    public int Precio(int Hab) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int pk = (Integer.parseInt(session.createQuery("select precio from THabitacion where NroHabitacion="+Hab).uniqueResult().toString()))+1;
        return  pk;
    }
    
    public List<TTipohabitacion> listarTipoHabitacion()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TTipohabitacion").list();
    }
}
