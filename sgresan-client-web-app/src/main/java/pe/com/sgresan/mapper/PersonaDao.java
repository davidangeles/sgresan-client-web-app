package pe.com.sgresan.mapper;

import java.util.Map;

import pe.com.sgresan.model.Persona;

public interface PersonaDao {
	
	Persona buscaporId(Map<String, Object> objMap);

}
