package pe.com.sgresan.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.common.Utilidades;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.mapper.ClienteDao;
import pe.com.sgresan.mapper.PersonaDao;
import pe.com.sgresan.mapper.UsuarioDao;
import pe.com.sgresan.model.Cliente;
import pe.com.sgresan.model.Persona;
import pe.com.sgresan.model.Usuario;

@Service(UsuarioService.BEAN_NAME)
public class UsuarioService {

	public static final String BEAN_NAME = "usuarioService";

	public static final String EL_NAME = "#{usuarioService}";
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PersonaDao personaDao;
	
	@Autowired
	private ClienteDao clienteDao; 

	public Usuario buscarporusuario(Usuario user) {
		Usuario objUsuario = null;
		try {
			if(Utils.isNullOrEmpty(user.getNombreUsuario()) && Utils.isNullOrEmpty(user.getContrasena())){
				return objUsuario;
			}
			user.setContrasena(Utilidades.encriptar(user.getContrasena()));
			objUsuario = usuarioDao.buscarUsuario(user);
			
			Persona objPersona = personaDao.buscaporId(objUsuario.getNombreUsuario());
			objUsuario.setObjPersona(objPersona);
		} catch (Exception e) {
			logger.error(e);
		}
		return objUsuario;
	}
	
	public Cliente buscarClienteId(String id){
		Cliente objCliente = null;
		try {
			objCliente = clienteDao.buscaporId(id);
			objCliente.setObjPersona(personaDao.buscaporId(objCliente.getIdPersona()));
		} catch (Exception e) {
			logger.error(e);
		}
		return objCliente;
	}
	
	public Cliente buscarClienteIdPersona(String id){
		Cliente objCliente = null;
		try {
			objCliente = clienteDao.buscaporIdPersona(id);
			objCliente.setObjPersona(personaDao.buscaporId(objCliente.getIdPersona()));
		} catch (Exception e) {
			logger.error(e);
		}
		return objCliente;
	}
	
	public List<Cliente> listarClientes(){
		List<Cliente> lstCliente = null;
		try {
			lstCliente = clienteDao.buscarTodos();
			for (Cliente objCliente : lstCliente) {
				objCliente.setObjPersona(personaDao.buscaporId(objCliente.getIdPersona()));
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return lstCliente;
	}

}
