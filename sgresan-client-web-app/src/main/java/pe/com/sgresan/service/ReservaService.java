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
import pe.com.sgresan.common.EstadoReservaTipo;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.entidad.TimelineDetalleReserva;
import pe.com.sgresan.mapper.ReservaDao;
import pe.com.sgresan.model.Habitacion;
import pe.com.sgresan.model.Persona;
import pe.com.sgresan.model.Reserva;
import pe.com.sgresan.model.ReservaDetalle;

@Service(ReservaService.BEAN_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReservaService {

	public static final String BEAN_NAME = "reservaService";

	public static final String EL_NAME = "#{reservaService}";

	@Autowired
	private ReservaDao reservaDao;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UtilitarioService utilService;

	private final Logger logger = Logger.getLogger(this.getClass());

	public Reserva buscarReservaId(String id) throws Exception{
		Reserva objReserva = null;
		try {
			Map<String, Object> objParams = new HashMap<String, Object>();
			objParams.put(CommonConstants.STR_KEY_MAP_ID, id);
			objReserva = reservaDao.buscaporId(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return objReserva;
	}

	public void registrarReserva(Reserva objReserva) throws Exception {
		try {
			reservaDao.registroReserva(objReserva);

			long dia = (objReserva.getFechaSalida().getTime() - objReserva.getFechaEntrada().getTime()) / CommonConstants.MILLSECS_PER_DAY;

			String strDescHabitacion = CommonConstants.STR_EMPTY;
			for (Habitacion objHabitacion : objReserva.getLstHabitacion()) {
				ReservaDetalle objDetalle = new ReservaDetalle();
				objDetalle.setCosto(objHabitacion.getPrecio() * dia);
				objDetalle.setIdHabitacion(objHabitacion.getIdHabitacion());
				objDetalle.setIdReserva(objReserva.getIdReserva());
				reservaDao.registroReservaDetalle(objDetalle);

				strDescHabitacion = objHabitacion.getTipoHabNombre().concat(CommonConstants.STR_SEPARATOR);
			}

			if(EstadoReservaTipo.PRE_RESERVA.getNombre().equals(objReserva.getEstado())){
				Persona objPersona = objReserva.getObjCliente().getObjPersona();

				Map<String, String> objParams = new HashMap<String, String>();
				objParams.put(CommonConstants.STR_KEY_MAP_EMAIL, objPersona.getEmail());
				objParams.put(CommonConstants.STR_KEY_MAP_CLIENTE,
						Utils.concatWithDelimiter(CommonConstants.STR_SPACE, objPersona.getNombres(), objPersona.getApellidoP(), objPersona.getApellidoM()));
				objParams.put(CommonConstants.STR_KEY_MAP_HABITACION, strDescHabitacion);
				objParams.put(CommonConstants.STR_KEY_MAP_CANTIDAD, Utils.getString(objReserva.getCantA()));
				objParams.put(CommonConstants.STR_KEY_MAP_PRECIO, Utils.getString(objReserva.getPrecio()));
				objParams.put(CommonConstants.STR_KEY_MAP_FECHAINICIO, Utils.getString(objReserva.getFechaEntrada()));
				objParams.put(CommonConstants.STR_KEY_MAP_FECHAFIN, Utils.getString(objReserva.getFechaEntrada()));

				utilService.enviarCorreoHTML(CommonConstants.INT_CORREO_PRERESERVA, objParams);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
	}

	public void actualizarReserva(Reserva objReserva) throws Exception{
		try {
			reservaDao.actualizarReserva(objReserva);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
	}

	public List<Reserva> reservasPorPersona_Cliente(String idPersona, String idCliente) throws Exception{
		List<Reserva> lstReservas = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_PERSONA, idPersona);
			objParams.put(CommonConstants.STR_KEY_MAP_CLIENTE, idCliente);
			lstReservas = reservaDao.filtroReservas(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstReservas;
	}

	public List<Reserva> reservasPorEstado(String idEstado) throws Exception{
		List<Reserva> lstReservas = new ArrayList<>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_ESTADO, idEstado);
			lstReservas = reservaDao.filtroReservas(objParams);
			for (Reserva objReserva : lstReservas) {
				objReserva.setObjCliente(usuarioService.buscarClienteId(objReserva.getIdCliente()));
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return lstReservas;
	}

	public List<TimelineDetalleReserva> SP_listareservafiltrosF_EST(String tipohab, String estado) throws Exception {
		List<TimelineDetalleReserva> listTDetalleReserva = new ArrayList<TimelineDetalleReserva>();
		try {
			Map<String, Object> objParams = new HashMap<>();
			objParams.put(CommonConstants.STR_KEY_MAP_HABITACION, tipohab);
			objParams.put(CommonConstants.STR_KEY_MAP_ESTADO, estado);

			listTDetalleReserva = reservaDao.SP_listareservafiltrosF_EST(objParams);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception();
		}
		return listTDetalleReserva;
	}

}

