/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ReservaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.TReserva;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ReservaVoucherBean {

    private List<TReserva> reservasALL;
    private List<TReserva> reservasByID;
    
    /**
     * Creates a new instance of ReservaVoucherBean
     */
    public ReservaVoucherBean() {
    }

    public List<TReserva> getReservasALL() {
        ReservaDao dao = new ReservaDao();
        reservasALL = dao.listarestadoreservaVouchers();
        return reservasALL;
    }
    
    public void CargarTabla(){
        ReservaDao dao = new ReservaDao();
        reservasALL = dao.listarestadoreservaVouchers();
    }

    public void BUSCAR(String id){
        ReservaDao dao = new ReservaDao();
        System.out.println("COD : "+id);
        reservasByID=dao.SP_listareservaVouchers(id);
        
    }
    
    public void setReservasALL(List<TReserva> reservasALL) {
        this.reservasALL = reservasALL;
    }

    public List<TReserva> getReservasByID() {
        return reservasByID;
    }

    public void setReservasByID(List<TReserva> reservasByID) {
        this.reservasByID = reservasByID;
    }
    
    
    
}
