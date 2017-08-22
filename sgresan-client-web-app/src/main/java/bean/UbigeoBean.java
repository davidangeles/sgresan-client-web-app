/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PersonaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.TUbigeo;

/**
 *
 * @author Monica
 */
@ManagedBean
@SessionScoped

public class UbigeoBean {
     private List<TUbigeo> listarUbigeoSel;

    public UbigeoBean() {
        
    }
     
    public List<TUbigeo> getListarUbigeo() {
        PersonaDao objTrb = new PersonaDao();
        listarUbigeoSel = objTrb.listarUbigeo();
        return listarUbigeoSel;
    }
    
    public void setListarUbigeoSel(List<TUbigeo> listarUbigeoSel) {
        this.listarUbigeoSel = listarUbigeoSel;
    }
}
