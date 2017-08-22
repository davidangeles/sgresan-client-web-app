/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.TReserva;
import model.TResxprod;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class ReservaProductoDao {
    
     public List<TResxprod> listarProductos()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TResxprod ").list();
    }
     
     public List<TResxprod> listarProductosByReserva(String id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TResxprod where TReserva.idReserva='"+id+"'").list();
    }
     
     public void InsetartProducto(TResxprod producto)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.save(producto);
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
    
    public void ActualizarProducto(TResxprod producto)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.update(producto);
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
    
    public void EliminarProducto(TResxprod producto)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.delete(producto);
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
    
    public TResxprod BuscaporIdProducto(int id) {
        
        TResxprod reserva = null;
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            reserva = (TResxprod) sesion.load(TResxprod.class, id);
            System.out.println("categoria : "+reserva.getTProducto().getNombreProducto() + "||"+
                    reserva.getTReserva().getIdReserva());
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
    
     public TReserva SP_BUSCAR_RESERVA(int accion,String campo1,String campo2,String campo3)
    {
          TReserva res = new TReserva();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_BuscarReserva(:accion,:campo1,:campo2,:campo3) }");
             q.setParameter("accion", accion);
             q.setParameter("campo1", campo1);
             q.setParameter("campo2", campo2);
             q.setParameter("campo3", campo3);
              
          
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
                        String idReserva = (String)result[0];
                        Date fecha = (Date)result[1];
                        String descripcion =(String)result[2];
                    
res.setIdReserva(idReserva);
res.setFechaEntrada(fecha);
res.setDescripcion(descripcion);
                        
                       
			}
        } catch (Exception e) {
            System.out.println("Error SP_BUSCAR_RESERVA : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return res;
    }   
}
