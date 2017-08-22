/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entidad.Estadistica;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class EstadisticaDao {
    
    
     public List<Estadistica> visitaMesActual() {
        List<Estadistica> lista= new ArrayList<Estadistica>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
       
             Query q = session.createSQLQuery("{ CALL SP_EstadisticaMesActual() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
				String NOMBRE = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, NOMBRE));
			}
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;      
    } 
     
     public List<Estadistica> visitaMeses() {
        List<Estadistica> lista= new ArrayList<Estadistica>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
       
             Query q = session.createSQLQuery("{ CALL SP_EstadisticaMesesNuevo() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
				String NOMBRE = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, NOMBRE));
			}
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;      
    }  
     
     public List<Estadistica> visitaAnual() {
        List<Estadistica> lista= new ArrayList<Estadistica>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
       
             Query q = session.createSQLQuery("{ CALL SP_EstadisticaAnual() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
				String NOMBRE = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, NOMBRE));
			}
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;      
    }
}
