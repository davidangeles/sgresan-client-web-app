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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import model.TCliente;
import model.TPersona;
import model.TUbigeo;
import model.TUsuario;
import pe.com.sgresan.common.Utilidades;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.model.Usuario;

/**
 *
 * @author Monica
 */
@ManagedBean
@SessionScoped

public class UsuarioBean {
    private TUsuario usuario;
    
    private String pwdActual;
    private String pwdNueva;
    private String pwdNuevaBi;
    
    UsuarioDao dao  = new UsuarioDao();
    
    @PostConstruct
    public void init() {
        usuario = new TUsuario();
        pwdActual="";pwdNueva="";pwdNuevaBi="";
    }

    public void irAgregar() {

        usuario.setEstado("Activo");
        usuario.setTipoUsuario("cliente");
        dao.ingresarUsuario(usuario);
    }

    public void VALIDAR() throws Exception{
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        //Modificar ...
        TUsuario u = new TUsuario();
        u.setNombreUsuario(usuario.getNombreUsuario());
        u.setContrasena(pwdActual);
        u = dao.buscarporusuario(u);
         
        String encriptado = Utilidades.desencriptar(u.getContrasena());
        u.setContrasena(encriptado);
        
        System.out.println("SESSION : "+u.getContrasena() +"||"+pwdActual);
        if(Utils.isNotNull(u) && u.getContrasena().equals(pwdActual)){
          u.setContrasena(pwdNueva);
          if(dao.modificarUsuario(u)){
                context.addMessage(null, new FacesMessage("Exito", "Se modificó su contraseña"));
                System.out.println("Se modificó su contraseña");
          }else{
              context.addMessage(null, new FacesMessage("Advertencia", "Hubo un error"));
               System.out.println("Hubo un error");
          }
        }else{         
            context.addMessage(null, new FacesMessage("Advertencia", "Ingrese su Contraseña Actual"));
             System.out.println("Ingrese su Contraseña Actual");
        }
        pwdActual="";
        pwdNueva="";
        pwdNuevaBi="";
    }
    
    public TUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public String getPwdActual() {
        return pwdActual;
    }

    public void setPwdActual(String pwdActual) {
        this.pwdActual = pwdActual;
    }

    public String getPwdNueva() {
        return pwdNueva;
    }

    public void setPwdNueva(String pwdNueva) {
        this.pwdNueva = pwdNueva;
    }

    public String getPwdNuevaBi() {
        return pwdNuevaBi;
    }

    public void setPwdNuevaBi(String pwdNuevaBi) {
        this.pwdNuevaBi = pwdNuevaBi;
    }
    
    
}
