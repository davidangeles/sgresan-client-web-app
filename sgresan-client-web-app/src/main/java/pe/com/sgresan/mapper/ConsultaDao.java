package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.entidad.Estadistica;
import pe.com.sgresan.entidad.Imagen;
import pe.com.sgresan.model.Habitacion;

public interface ConsultaDao {
	
	List<Estadistica> obtenerDataMesActual(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataMesesNuevo(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataAnual(Map<String, Object> objParams);	
	
	List<Imagen> imagenHabitaciones();
	
	List<Imagen> imagenDetalleHabitacion(String idHabitacion);

}

