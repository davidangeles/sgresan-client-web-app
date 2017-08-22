/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.sgresan.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import pe.com.sgresan.common.UsuarioTipo;
import pe.com.sgresan.model.Persona;
import pe.com.sgresan.model.Usuario;
import pe.com.sgresan.service.ParametroService;
import pe.com.sgresan.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginBean {

	@ManagedProperty(value = ParametroService.EL_NAME)
	private ParametroService parametroService;
	
	@ManagedProperty(value = UsuarioService.EL_NAME)
	private UsuarioService usuarioService;

	public Usuario usuario;
	public Persona persona;

	private String ruta;
	private boolean sesionI;

	/**
	 * Creates a new instance of LoginBean
	 */
	public LoginBean() {
		usuario = new Usuario();
		persona = new Persona();
		sesionI = true;	
	}

	public String logueo() throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean loggedIn = false;
		usuario = usuarioService.buscarporusuario(usuario);
		if (usuario != null) {
			loggedIn = true;			
			persona = usuario.getObjPersona();			
			sesionI = false;
			if (usuario.getTipoUsuario().equals(UsuarioTipo.U_RECEP.getNombre())) {
				ruta = "ReservaRecepcionista.xhtml";
			} else if (usuario.getTipoUsuario().equals(UsuarioTipo.U_CLIENTE.getNombre())) {
				ruta = "index.xhtml";
			} else if (usuario.getTipoUsuario().equals(UsuarioTipo.U_GERENTE.getNombre())) {
				ruta = "index.xhtml";
			} else {
				throw new Exception();
			}
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario.getNombreUsuario());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("persona", persona);
		} else {
			usuario = new Usuario();
			loggedIn = false;
			new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");			
			ruta = "#";
		}
		context.addCallbackParam("loggedIn", loggedIn);
		return ruta;
	}

	public void logoud() {

		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession) fc.getExternalContext().getSession(false);
		sesion.invalidate();
		usuario = new Usuario();
		this.sesionI = true;
		System.out.println(usuario.getNombreUsuario().length());
		context.addCallbackParam("loggedIn", true);
	}

	/**
	 * Returns attribute ruta
	 * @return ruta <code>String</code>
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Sets attribute ruta
	 * @param ruta <code>String</code>
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Returns attribute sesionI
	 * @return sesionI <code>boolean</code>
	 */
	public boolean isSesion() {
		return sesionI;
	}

	/**
	 * Sets attribute sesionI
	 * @param sesionI <code>boolean</code>
	 */
	public void setSesion(boolean sesionI) {
		this.sesionI = sesionI;
	}

	/**
	 * Returns attribute persona
	 * @return persona <code>Persona</code>
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * Sets attribute persona
	 * @param persona <code>Persona</code>
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * Returns attribute usuario
	 * @return usuario <code>Usuario</code>
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Sets attribute usuario
	 * @param usuario <code>Usuario</code>
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Returns attribute parametroService
	 * @return parametroService <code>ParametroService</code>
	 */
	public ParametroService getParametroService() {
		return parametroService;
	}

	/**
	 * Sets attribute parametroService
	 * @param parametroService <code>ParametroService</code>
	 */
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	/**
	 * Returns attribute usuarioService
	 * @return usuarioService <code>UsuarioService</code>
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * Sets attribute usuarioService
	 * @param usuarioService <code>UsuarioService</code>
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
}
