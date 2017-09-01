package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.model.Habitacion;

public interface HabitacionDao {
	
	List<Habitacion> habitacionDisponible(Map<String, Object> objMap);

}
