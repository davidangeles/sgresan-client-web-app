/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TCategoria;
import model.TProducto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class ProductoDao {
    
    public List<TProducto> listarProductos()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TProducto ").list();
    }
    
    public List<TCategoria> listarCategoria()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TCategoria ").list();
    }
    
    public List<TProducto> listarProductosByCategoria(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TProducto where TCategoria.idCategoria="+id).list();
    }
    
    public void InsetartProducto(TProducto producto)
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
    
    public void ActualizarProducto(TProducto producto)
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
    
    public void EliminarProducto(TProducto producto)
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
    
    public void InsetarCategoria(TCategoria categoria)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.save(categoria);
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
    
    public void ActualizarCategoria(TCategoria categoria)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.update(categoria);
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
    
    public void EliminarCategoria(TCategoria categoria)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.delete(categoria);
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
    
    public TProducto BuscaporIdProducto(int id) {
        
        TProducto reserva = null;
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            reserva = (TProducto) sesion.load(TProducto.class, id);
            System.out.println("categoria : "+reserva.getTCategoria().getNombreCategoria());
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
    
    public TCategoria BuscaporIdCategoria(int id) {
        
        TCategoria reserva = null;
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            reserva = (TCategoria) sesion.load(TCategoria.class, id);
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
}
