/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PersonaDao;
import dao.UsuarioDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import model.TCliente;
import model.TPersona;
import model.TUbigeo;
import model.TUsuario;

/**
 *
 * @author Monica
 */
@ManagedBean
@SessionScoped

public class UsuarioBean {
    private TUsuario usuario;
    
    UsuarioDao dao  = new UsuarioDao();
    
    @PostConstruct
    public void init() {
        usuario = new TUsuario();
    }

    public void irAgregar() {

        usuario.setEstado("Activo");
        usuario.setTipoUsuario("cliente");
        dao.ingresarUsuario(usuario);
    }

    public TUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }
    
}
