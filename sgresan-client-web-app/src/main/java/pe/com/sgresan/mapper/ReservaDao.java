package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.entidad.TimelineDetalleReserva;
import pe.com.sgresan.model.Reserva;
import pe.com.sgresan.model.ReservaDetalle;

public interface ReservaDao {
	
	int registroReserva(Reserva objReserva);
	
	int actualizarReserva(Reserva objReserva);
	
	int registroReservaDetalle(ReservaDetalle objReservaDetalle);
	
	Reserva buscaporId (Map<String, Object> objParams);
	
	List<Reserva> filtroReservas(Map<String, Object> objParams);	
	
	List<TimelineDetalleReserva> SP_listareservafiltrosF_EST(Map<String, Object> objParams);
	
	

}
