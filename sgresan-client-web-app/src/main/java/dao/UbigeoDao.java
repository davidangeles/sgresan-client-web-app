/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TCliente;
import model.TUbigeo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Monica
 */
public class UbigeoDao {
    Session sesion;
    Transaction trans;
    Query qry;
    
    public List<TUbigeo> listarubigeo()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from TUbigeo").list();
    }
    
}
