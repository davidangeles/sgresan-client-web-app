package pe.com.sgresan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.entidad.Estadistica;
import pe.com.sgresan.entidad.Imagen;
import pe.com.sgresan.mapper.ConsultaDao;
import pe.com.sgresan.model.ParametroDetalle;

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
	
	public List<Imagen> mostrarImagenesHabitaciones() throws Exception{
		List<Imagen> lstImagen = null;
		try {
			lstImagen = consultaDao.imagenHabitaciones();
			for (Imagen objImagen : lstImagen) {
				objImagen.setImagenBase64(Base64.encodeBase64String(objImagen.getImagen()));
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstImagen;
	}
	
	public List<Imagen> mostrarImagenesHabitacionDetalle(String idHabitacion) throws Exception{
		List<Imagen> lstImagen = null;
		try {
			lstImagen = consultaDao.imagenDetalleHabitacion(idHabitacion);
			for (Imagen objImagen : lstImagen) {
				objImagen.setImagenBase64(Base64.encodeBase64String(objImagen.getImagen()));
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstImagen;
	}
	
	public List<ParametroDetalle> obtenerTipoHabitaciones() throws Exception{
		List<ParametroDetalle> lstParametro = null;
		try {
			lstParametro = consultaDao.obtenerTipoHabitaciones();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstParametro;
	}
	
	public List<ParametroDetalle> obtenerEstadoReserva() throws Exception{
		List<ParametroDetalle> lstEstReserva = null;
		try {
			lstEstReserva = consultaDao.obtenerEstadoReserva();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstReserva;
	}
	
	public List<Estadistica> visitaFinancieroMesActual(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataFinancieroMesActual(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}
	
	public List<Estadistica> visitaFinancieroMeses(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataFinancieroMeses(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}
	
	public List<Estadistica> visitaFinancieroAnual(int accion) throws Exception {
		List<Estadistica> lstEstadistica = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ACCION, accion);
			lstEstadistica = consultaDao.obtenerDataFinancieroAnual(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstEstadistica;
	}


}
