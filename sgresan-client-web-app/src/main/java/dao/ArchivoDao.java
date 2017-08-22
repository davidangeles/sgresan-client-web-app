/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TCliente;
import model.TReserva;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class ArchivoDao {
    public List<TReserva> listareserva(String id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReserva where TCliente='"+id+"' order by fecharegistro desc").list();
    }//from TCliente where TPersona='abc124'
    
    public TCliente buscarcliente(String id)
    {
         Session sesion =HibernateUtil.getSessionFactory().openSession();
        String sql="select u from TCliente u where TPersona=:user"; 
       
        Query query = sesion.createQuery(sql);
        query.setString("user", id);
        return (TCliente) query.uniqueResult();
        
    }
    
    public TReserva BuscaporId(String id) {
        
        TReserva reserva = null;
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            reserva = (TReserva) sesion.load(TReserva.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx!=null)
            {
                tx.rollback();
            }
            throw  new RuntimeException(e);
        } finally {
            sesion.close();
        }
        return reserva;
    }
    public void ModificarReserva(TReserva reserva)
    { 
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.merge(reserva);
            tx.commit();
        } catch  (Exception e) {
            if(tx!=null)
            {
                tx.rollback();System.out.println(e.getMessage());
            }System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
    }
}
