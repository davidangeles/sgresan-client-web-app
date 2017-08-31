/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import pe.com.sgresan.entidad.Estadistica;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class EstadisticaDao {
    
    
//     public List<Estadistica> visitaMesActual(int accion) {
//        List<Estadistica> lista= new ArrayList<Estadistica>();
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                 try {
//       
//             Query q = session.createSQLQuery("{ CALL SP_EstadisticaMesActual(:accion) }");
//             q.setParameter("accion", accion);
//			List<Object[]> d=q.list();
//			for (Object[] result : d) {
//				
//				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
//				String NOMBRE = (String) result[1];
//                                String TEXTO = (String) result[2];
//				lista.add(new Estadistica(CANTIDAD, NOMBRE,TEXTO));
//			}
//        } catch (Exception e) {
//            System.out.println("Error : "+e.getMessage());
//        } finally {
//            session.flush();
//            session.close();
//        }
//          return lista;      
//    } 
//     
//     public List<Estadistica> visitaMeses(int accion) {
//        List<Estadistica> lista= new ArrayList<Estadistica>();
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                 try {
//       
//             Query q = session.createSQLQuery("{ CALL SP_EstadisticaMesesNuevo(:accion) }");
//             q.setParameter("accion", accion);
//			List<Object[]> d=q.list();
//			for (Object[] result : d) {
//				
//				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
//				String NOMBRE = (String) result[1];
//                                String TEXTO = (String) result[2];
//				lista.add(new Estadistica(CANTIDAD, NOMBRE,TEXTO));
//			}
//        } catch (Exception e) {
//            System.out.println("Error : "+e.getMessage());
//        } finally {
//            session.flush();
//            session.close();
//        }
//          return lista;      
//    }  
//     
//     public List<Estadistica> visitaAnual(int accion) {
//        List<Estadistica> lista= new ArrayList<Estadistica>();
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                 try {
//       
//             Query q = session.createSQLQuery("{ CALL SP_EstadisticaAnual(:accion) }");
//             q.setParameter("accion", accion);
//			List<Object[]> d=q.list();
//			for (Object[] result : d) {
//				
//				BigInteger CANTIDAD = BigInteger.valueOf(((BigDecimal) result[0]).intValue());
//				String NOMBRE = (String) result[1];
//                                String TEXTO = (String) result[2];
//				lista.add(new Estadistica(CANTIDAD, NOMBRE,TEXTO));
//			}
//        } catch (Exception e) {
//            System.out.println("Error : "+e.getMessage());
//        } finally {
//            session.flush();
//            session.close();
//        }
//          return lista;      
//    }
}
