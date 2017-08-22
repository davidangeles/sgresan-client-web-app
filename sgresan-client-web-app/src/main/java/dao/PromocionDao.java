/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TPromocion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class PromocionDao {
     public List<TPromocion> listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TPromocion").list();

    }

    public boolean create(TPromocion a) {
        boolean flag=false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      
        try {
            tx = sesion.beginTransaction();
            sesion.save(a);
            tx.commit();
            flag= true;
        } catch (Exception e) {
            if(tx!=null){
              tx.rollback();System.out.println(e.getMessage());
            }System.out.println(e.getMessage());
           
        } finally{
            sesion.close();
        }
     return flag;  
     
    }

    public boolean update(TPromocion a) {
         boolean flag=false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

    try{
        tx = sesion.beginTransaction();
        sesion.update(a);
        tx.commit();
        flag = true;
    }catch(Exception e){
        if(tx!=null){
              tx.rollback();System.out.println(e.getMessage());
            }System.out.println(e.getMessage());
           
        } finally{
            sesion.close();
        }
    return flag;

    }
}
