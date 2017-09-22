package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.entidad.Estadistica;
import pe.com.sgresan.entidad.GraficoReserva;
import pe.com.sgresan.entidad.Imagen;
import pe.com.sgresan.model.ParametroDetalle;
import pe.com.sgresan.model.Habitacion;

public interface ConsultaDao {
	
	List<Estadistica> obtenerDataMesActual(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataMesesNuevo(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataAnual(Map<String, Object> objParams);	
	
	List<Imagen> imagenHabitaciones();
	
	List<Imagen> imagenDetalleHabitacion(String idHabitacion);

	ParametroDetalle getParametroDetailPk(Integer id);
	
	List<ParametroDetalle> obtenerTipoHabitaciones();
	
	List<ParametroDetalle> obtenerEstadoReserva();
	
	List<Estadistica> obtenerDataFinancieroMesActual(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataFinancieroMeses(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataFinancieroAnual(Map<String, Object> objParams);
	
	List<GraficoReserva> obtenerReporteReserva1(Map<String, Object> objParams);
	
	List<GraficoReserva> obtenerReporteReserva2(Map<String, Object> objParams);
	
	List<Estadistica> obtenerDataTopClientes(Map<String, Object> objParams);

}

