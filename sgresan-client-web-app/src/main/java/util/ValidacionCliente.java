/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.TPersona;
import model.TUsuario;
import pe.com.sgresan.common.Utils;

/**
 *
 * @author Joel
 */
public class ValidacionCliente {
    
    public boolean esNombreValido(TUsuario usuario,TPersona persona)
    {
        boolean resultado = true;
        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el nombre del usuario"));
            resultado = false;
        }
        
        if(persona.getNombres() == null || persona.getNombres().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el nombre de la persona"));
            resultado = false;
        }
        
        if(persona.getApellidoP()== null || persona.getApellidoP().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el apellido paterno de la persona"));
            resultado = false;
        }
        
        if(persona.getApellidoM()== null || persona.getApellidoM().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el apellido materno de la persona"));
            resultado = false;
        }
        
        if(persona.getDni() == null || persona.getDni().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el DNI de la persona"));
            resultado = false;
        }
        
       // resultado = ValidacionUtil.esSoloNumero(persona.getDni());
        if(!Utils.esSoloNumero(persona.getDni()))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","El campo DNI debe ser numerico"));
            resultado = false;
        }
        
        if(persona.getDireccion() == null || persona.getDireccion().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar la direcciÃ³n de la persona"));
            resultado = false;
        }
        
        if(persona.getTelefono()== null || persona.getTelefono().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el telÃ©fono de la persona"));
            resultado = false;
        }
        
        if(persona.getCelular()== null || persona.getCelular().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el celular de la persona"));
            resultado = false;
        }
        
        if(persona.getEmail()== null || persona.getEmail().trim().length() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar el correo de la persona"));
            resultado = false;
        }
        
      if("[^A-Za-zÃ±Ã‘Ã¡Ã©Ã­Ã³ÃºÃ¼Ã�Ã‰Ã�Ã“Ãš \\- ]".equals(persona.getNombres())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Debe ingresar sÃ³lo letras"));
            resultado = false;
      }
        return resultado;
    }

     
      public boolean esVistaValida(TUsuario usuario,TPersona persona){
        boolean resultado = true;
        if(!esNombreValido(usuario,persona)){
            resultado = false;
        }
        return resultado;
    }
}
