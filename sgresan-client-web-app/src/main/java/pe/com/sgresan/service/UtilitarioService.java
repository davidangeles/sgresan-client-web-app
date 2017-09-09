package pe.com.sgresan.service;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.common.Email;
import pe.com.sgresan.mapper.ConsultaDao;
import pe.com.sgresan.model.ParametroDetalle;

@Service(UtilitarioService.BEAN_NAME)
public class UtilitarioService {

	public static final String BEAN_NAME = "utilitarioService";

	public static final String EL_NAME = "#{utilitarioService}";

	@Autowired
	private ConsultaDao consultaDao;

	@SuppressWarnings("deprecation")
	public void enviarCorreoHTML(Integer idCorreo, Map<String, String> map) throws Exception {
		ParametroDetalle obj = consultaDao.getParametroDetailPk(idCorreo);

		StrSubstitutor objStrTemplate = new StrSubstitutor(map);

		Email objEmail = new Email();
		objEmail.sendHtml(map.get(CommonConstants.STR_KEY_MAP_EMAIL), obj.getValor(),
				objStrTemplate.replace(obj.getValor2()));
	}

}
