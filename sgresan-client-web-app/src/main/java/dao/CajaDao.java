/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.TCaja;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class CajaDao {
    Date fec = new Date();
        String fecha =(fec.getYear()+1900)+"-"+(fec.getMonth()+1)+"-"+fec.getDate();
        String hora=fec.getHours()+":"+fec.getMinutes()+":"+fec.getSeconds();
        String cadena =fecha+" "+hora;
        
    public List<TCaja> listar()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TCaja").list();
    }
    
     public List<TCaja> listarHoy()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TCaja where fecha='"+ fecha+"'").list();
    }
     public int  PK(String id,String horaactual) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         System.out.println(cadena);
        int pk = (Integer.parseInt(session.createQuery("select count(*)from TCaja where descripcion like '%"+id+"%' and hora between '"+fecha+" "+horaactual+"' and '"+cadena+"'").uniqueResult().toString()));
        return  pk;
    }
     
    //select saldo from TCaja order  by hora desc
    //select hora from TCaja where descripcion like '%Ci%' and fecha='2014-11-01' order by hora desc
    public double Saldo()
    {double sa=0;
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query= session.createQuery("from TCaja order  by hora desc");
          query.setFirstResult(0);
          query.setMaxResults(1);
            //query.getFirstResult();
            //System.out.println(query.list().size());
           sa = ((TCaja )query.list().get(0)).getSaldo();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            sa=0;
        }
        return sa;
    }
    
    public Date fech()
    {Date da = new Date();
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query= session.createQuery("from TCaja where descripcion like '%Ap%' and fecha='"+fecha+"' order by hora desc");
          query.setFirstResult(0);
          query.setMaxResults(1);
            //query.getFirstResult();
            //System.out.println(query.list().size());
           da = ((TCaja )query.list().get(0)).getHora();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return da;
    }
    
    public TCaja Caja(TCaja c)
    {
        Date fecha = new Date();
        try
        {
            if(c.getSalida() == null && c.getEntrada() == null)
        {
            c.setEntrada(0.0);
            c.setSalida(0.0);
            c.setSaldo(c.getSaldo());
        }else
            {
                if(c.getEntrada()==null || c.getEntrada() == 0)
        {
            c.setEntrada(0.0);
             c.setSaldo(Saldo()-c.getSalida());
        }
        if(c.getSalida()==null || c.getSalida()== 0)
        {
            c.setSalida(0.0);
             c.setSaldo(c.getEntrada()+Saldo());
        }
            }
            
        
        c.setFecha(fecha);
        c.setHora(fecha);
        c.setMoneda("Soles");
        }
        catch(Exception e)
        {
            System.out.println("1"+e.getMessage());
        }
        return c;
    }
    
        public void InsetartReserva(TCaja caja)
    { 
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
       
        try {
            tx =sesion.beginTransaction();
            sesion.save(Caja(caja));
            tx.commit();
        } catch  (Exception e) {
            if(tx!=null)
            {
                tx.rollback();System.out.println("2"+e.getMessage());
            }System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
    
    }
    
}
