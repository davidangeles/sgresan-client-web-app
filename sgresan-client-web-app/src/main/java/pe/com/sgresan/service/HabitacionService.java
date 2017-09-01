package pe.com.sgresan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.mapper.HabitacionDao;
import pe.com.sgresan.model.Habitacion;

@Service(HabitacionService.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HabitacionService {
	
	public static final String BEAN_NAME = "habitacionService";

	public static final String EL_NAME = "#{habitacionService}";
	
	@Autowired
	private HabitacionDao habitacionDao;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public List<Habitacion> obtenerHabitacionesDisponibles(String fechaInicio, String fechaFin) throws Exception {
		List<Habitacion> lstHabitacion = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<String, Object>();
			objParams.put(CommonConstants.STR_KEY_MAP_FECHAINICIO, fechaInicio);
			objParams.put(CommonConstants.STR_KEY_MAP_FECHAFIN, fechaFin);
			lstHabitacion = habitacionDao.habitacionDisponible(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstHabitacion;
	}

}
