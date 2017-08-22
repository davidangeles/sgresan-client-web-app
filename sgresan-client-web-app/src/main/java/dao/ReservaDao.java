/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Entidad.TimelineDetalleReserva;
import Entidad.TimelineReserva;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.THabitacion;
import model.TReserva;
import model.TReservadetalle;
import model.TReservalog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Joel
 */
public class ReservaDao {
    public List<TReservadetalle> listareserva()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReservadetalle ").list();
    }
    
    public List<TReservadetalle> listareservafiltros(String id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("select r from TReservadetalle  r\n"
                + "inner join r.THabitacion  j\n"
                + "inner join j.TTipohabitacion t\n"
                + "where t.nombre like '%" + id + "%'").list();
    }
    
    public List<TimelineDetalleReserva> SP_listareservafiltros()
    {
          List<TimelineDetalleReserva> lista= new ArrayList<TimelineDetalleReserva>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_Timeline() }");
         
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
                        String idReserva = (String)result[0];
                        String cliente = (String)result[1];
                        String descripcion =(String)result[2];
                         double subtotal =Double.parseDouble(result[3].toString());
                         double igv =Double.parseDouble(result[4].toString());
                         double monto=Double.parseDouble(result[5].toString());
                          Date fecha_entrada = (Date) result[6];
                          Date fecha_salida = (Date) result[7];
                          boolean booleano=  Boolean.parseBoolean(result[8].toString());
                          String habitacion= ((String) result[9]) ;
                          String estado= ((String) result[10] );

                        
                        lista.add(new TimelineDetalleReserva(
                                new TimelineReserva(idReserva, cliente, fecha_entrada, fecha_salida, descripcion, subtotal, igv, monto,estado),
                                fecha_entrada, fecha_salida, booleano, habitacion, estado));
                       // lista.add(new PacientePresencial(posicion, paciente,fecha, cod_cli, cod_vis, cod_ter));
			}
        } catch (Exception e) {
            System.out.println("Error SP_ListarPacienteEnEspera : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;
    }   
    
    public List<TimelineDetalleReserva> SP_listareservafiltrosF(String valor)
    {
          List<TimelineDetalleReserva> lista= new ArrayList<TimelineDetalleReserva>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_TimelineFiltro(:filtro) }");
             q.setParameter("filtro", valor);
         
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
                        String idReserva = (String)result[0];
                        String cliente = (String)result[1];
                        String descripcion =(String)result[2];
                         double subtotal =Double.parseDouble(result[3].toString());
                         double igv =Double.parseDouble(result[4].toString());
                         double monto=Double.parseDouble(result[5].toString());
                          Date fecha_entrada = (Date) result[6];
                          Date fecha_salida = (Date) result[7];
                          boolean booleano=  Boolean.parseBoolean(result[8].toString());
                          String habitacion= ((String) result[9]) ;
                          String estado= ((String) result[10] );

                        
                        lista.add(new TimelineDetalleReserva(
                                new TimelineReserva(idReserva, cliente, fecha_entrada, fecha_salida, descripcion, subtotal, igv, monto,estado),
                                fecha_entrada, fecha_salida, booleano, habitacion, estado));
                       // lista.add(new PacientePresencial(posicion, paciente,fecha, cod_cli, cod_vis, cod_ter));
			}
        } catch (Exception e) {
            System.out.println("Error SP_ListarPacienteEnEspera : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;
    }   
    
        public List<TimelineDetalleReserva> SP_listareservafiltrosF_EST(String valor,String est)
    {
          List<TimelineDetalleReserva> lista= new ArrayList<TimelineDetalleReserva>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_TimelineFiltro_Estado(:filtro,:est) }");
             q.setParameter("filtro", valor);
             q.setParameter("est", est);
         
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
                        String idReserva = (String)result[0];
                        String cliente = (String)result[1];
                        String descripcion =(String)result[2];
                         double subtotal =Double.parseDouble(result[3].toString());
                         double igv =Double.parseDouble(result[4].toString());
                         double monto=Double.parseDouble(result[5].toString());
                          Date fecha_entrada = (Date) result[6];
                          Date fecha_salida = (Date) result[7];
                          boolean booleano=  Boolean.parseBoolean(result[8].toString());
                          String habitacion= ((String) result[9]) ;
                          String estado= ((String) result[10] );

                        
                        lista.add(new TimelineDetalleReserva(
                                new TimelineReserva(idReserva, cliente, fecha_entrada, fecha_salida, descripcion, subtotal, igv, monto,estado),
                                fecha_entrada, fecha_salida, booleano, habitacion, estado));
                       // lista.add(new PacientePresencial(posicion, paciente,fecha, cod_cli, cod_vis, cod_ter));
			}
        } catch (Exception e) {
            System.out.println("Error SP_listareservafiltrosF_EST : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;
    } 

     public List<THabitacion> listarhabitacionesconfiltros(String id,String fecEnt,String fecSal)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("select th from THabitacion th \n" +
                                    " inner join th.TTipohabitacion tt"
                                    + "  where idHabitacion not in (\n" +
                                   "select hab.idHabitacion from TReservadetalle  reservdet\n" +
                                   "inner join reservdet.TReserva rerserv\n" +
                                   "inner join reservdet.THabitacion hab\n" +
                                   " where (fechaEntrada <  '"+fecEnt+"' and fechaSalida >  '"+fecEnt+"')\n" +
"or (fechaEntrada < '"+fecSal+"' and fechaSalida > '"+fecSal+"') \n" +
"or (fechaEntrada between  '"+fecEnt+"' and '"+fecSal+"' and fechaSalida between '"+fecEnt+"' and '"+fecSal+"') \n" +
"or (fechaEntrada < '"+fecEnt+"' and fechaSalida > '"+fecSal+"') )  and tt.nombre like '%"+id+"%'").list();
    }
    
    public List<TReserva> listarestadoreserva()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReserva where Estado='pre-reserva'").list();
    }
    
    public void InsetartReserva(TReserva reserva)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.save(reserva);
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
    
    public void MoficiarReserva(TReserva reserva)
    { 
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.merge(reserva);
            tx.commit();
        } catch  (Exception e) {
            System.out.println("ERROR : "+e.getMessage());
            if(tx!=null)
            {
                tx.rollback();
            }
        } finally {
            sesion.close();
        }
    }
    public boolean SP_MoficiarReserva(int accion,TimelineReserva reserva)
    { 
        boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_ModificarReserva(:accion,:reserva,:flag,:motivo,:inicio,:fin) }");
             q.setParameter("accion", accion);
             q.setParameter("reserva", reserva.getIdReserva());
             q.setParameter("flag", reserva.getEstado());
             q.setParameter("motivo", reserva.getDescripcion());
             q.setParameter("inicio", reserva.getFecha_entrada());
             q.setParameter("fin", reserva.getFecha_salida());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_MoficiarReserva : "+e.getMessage());
             resultado=false;
         } finally {
            session.flush();
            session.close();
        }
         return resultado;
    }
    
     public String PK() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int pk = (Integer.parseInt(session.createQuery("select count(*) from TReserva ").uniqueResult().toString()))+1;
        return  "abc"+pk;
    }
    
    public void InsetartReservaDetalle(TReservadetalle reserva)
    { 
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            sesion.save(reserva);
            tx.commit();
        } catch  (Exception e) {
            if(tx!=null)
            {
                tx.rollback();
            }
        } finally {
            sesion.close();
        }
    
    }
    
    public TReservadetalle BuscaporId(int id) {
        
        TReservadetalle reserva = null;
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            reserva = (TReservadetalle) sesion.load(TReservadetalle.class, id);
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
    
    public List<TReserva> listareserva(String id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReserva where ModalidadPago like '%"+id+"%'").list();
    } 
     public List<TReservadetalle> listarNumeroCuartos(String id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReservadetalle where TReserva='"+id+"'").list();
    }
     public List<TReservalog> listareservalog()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReservalog order by id desc").list();
    } 
     
    public List<TReserva> listarsoloreserva()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TReserva order by fechaRegistro desc").list();
    } 
}
