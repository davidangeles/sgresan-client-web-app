package pe.com.sgresan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.entidad.Estadistica;
import pe.com.sgresan.mapper.ConsultaDao;

@Service(ConsultaService.BEAN_NAME)
public class ConsultaService {
	
	public static final String BEAN_NAME = "consultaService";

	public static final String EL_NAME = "#{consultaService}";
	
	@Autowired
	private ConsultaDao consultaDao;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public List<Estadistica> visitaMesActual(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataMesActual(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}
	
	public List<Estadistica> visitaMeses(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataMesesNuevo(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}	
	
	public List<Estadistica> visitaAnual(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataAnual(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}

}
