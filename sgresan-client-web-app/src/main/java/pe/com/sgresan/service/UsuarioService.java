package pe.com.sgresan.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.common.Utilidades;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.mapper.PersonaDao;
import pe.com.sgresan.mapper.UsuarioDao;
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

	public Usuario buscarporusuario(Usuario user) {
		Usuario objUsuario = null;
		try {
			if(Utils.isNullOrEmpty(user.getNombreUsuario()) && Utils.isNullOrEmpty(user.getContrasena())){
				return objUsuario;
			}
			user.setContrasena(Utilidades.encriptar(user.getContrasena()));
			objUsuario = usuarioDao.buscarUsuario(user);
			
			Map<String, Object> objParams = new HashMap<String, Object>();
			objParams.put(CommonConstants.STR_KEY_MAP_ID, objUsuario.getNombreUsuario());
			
			Persona objPersona = personaDao.buscaporId(objParams);
			objUsuario.setObjPersona(objPersona);
		} catch (Exception e) {
			logger.error(e);
		}
		return objUsuario;
	}

}
