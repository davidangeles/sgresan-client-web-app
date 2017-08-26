/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.TCliente;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.model.Cliente;
import pe.com.sgresan.service.UsuarioService;

/**
 * pureba carlos
 * 
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ClienteBean {

	@ManagedProperty(value = UsuarioService.EL_NAME)
	private UsuarioService usuarioService;

	private List<Cliente> clientes;

	/**
	 * Creates a new instance of ClienteBean
	 */
	public ClienteBean() {
	}

	/**
	 * Returns attribute clientes
	 * 
	 * @return clientes <code>List<Cliente></code>
	 */
	public List<Cliente> getClientes() {
		if (Utils.isNotNull(usuarioService)) {
			clientes = usuarioService.listarClientes();
		}
		return clientes;
	}

	/**
	 * Returns attribute usuarioService
	 * 
	 * @return usuarioService <code>UsuarioService</code>
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * Sets attribute usuarioService
	 * 
	 * @param usuarioService
	 *            <code>UsuarioService</code>
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
