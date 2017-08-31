package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.entidad.Estadistica;

public interface ConsultaDao {
	
	List<Estadistica> obtenerDataMesActual(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataMesesNuevo(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataAnual(Map<String, Object> objParams);

}

