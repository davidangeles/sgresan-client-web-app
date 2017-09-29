package pe.com.sgresan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sgresan.mapper.EncuestaDao;

 

@Service(EncuestaService.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EncuestaService {
	public static final String BEAN_NAME = "encuestaService";

	public static final String EL_NAME = "#{encuestaService}" ;
	
	@Autowired
	private EncuestaDao encuestaDao;
	
	
	
	public int CrearCabecera(String cli,String com,String comp,String comn){
		Map<String, Object> objParams = new HashMap<String, Object>();
		objParams.put("CLIENTE", cli);
		objParams.put("COM", com);
		objParams.put("COM_P", comp);
		objParams.put("COM_N", comn);
	 
		return encuestaDao.SP_CabeceraEncuesta(objParams);
	}
	
	public void CrearDetalleEncuesta(int enc,int pro){
		Map<String, Object> objParams = new HashMap<String, Object>();
		objParams.put("ENC", enc);
		objParams.put("PRO", pro);
 
		 encuestaDao.SP_DetalleEncuesta(objParams);
	}
}
