/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PromocionDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.TPromocion;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class PromocionBean {

     private List<TPromocion> lista;
    private TPromocion selectedProm;
    public PromocionDao metodos = new PromocionDao();
    /**
     * Creates a new instance of PromocionBean
     */
    public PromocionBean() {
        selectedProm = new TPromocion();
        this.lista = new ArrayList<TPromocion>();
    }
    public List<TPromocion> getLista() {

        lista = metodos.listar();
        return lista;
    }

    public TPromocion getSelectedProm() {
        return selectedProm;
    }

    public void setSelectedProm(TPromocion selectedProm) {
        this.selectedProm = selectedProm;
    }

    public void btnCreate(ActionEvent actionEvent) {
        PromocionDao aa = new PromocionDao();
        String msg;
        
      
        if (aa.create(selectedProm)) {
            msg = "Se creo correctamente el registro";
               FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);//para enviar un mensaje despues de intentar registrar
        FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "error al crear el registro";
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);//para enviar un mensaje despues de intentar registrar
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
      
    }

    public void btnUpdate(ActionEvent actionEvent) {
        PromocionDao aa = new PromocionDao();
        String msg;
        if (aa.update(this.selectedProm)) {
            msg = "Se Modifico correctamente el registro";
        } else {
            msg = "error al modificar el registro";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);//para enviar un mensaje despues de intentar registrar
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void btnDelete(ActionEvent actionEvent) {
      /*  PromocionDao aa = new PromocionDao();
        String msg;
        if (aa.delete(this.selectedProm.getIdPromociones())) {
            msg = "Se elimino correctamente el registro";
        } else {
            msg = "error al eliminar el registro";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);//para enviar un mensaje despues de intentar registrar
        FacesContext.getCurrentInstance().addMessage(null, message);*/
    }
}
