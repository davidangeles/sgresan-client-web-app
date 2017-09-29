package pe.com.sgresan.mapper;

 
import java.util.Map;

 

public interface EncuestaDao {
	Integer SP_CabeceraEncuesta(Map<String, Object> objMap);
	void SP_DetalleEncuesta(Map<String, Object> objMap);
}
