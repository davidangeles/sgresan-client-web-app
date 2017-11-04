package prueba;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.sgresan.common.EstadoReservaTipo;
import pe.com.sgresan.model.Cliente;
import pe.com.sgresan.model.Habitacion;
import pe.com.sgresan.model.Reserva;
import pe.com.sgresan.model.Usuario;
import pe.com.sgresan.service.HabitacionService;
import pe.com.sgresan.service.ReservaService;
import pe.com.sgresan.service.UsuarioService;

public class PruebaConfig {	
	
	@Test
	@SuppressWarnings("resource")
	public void pruebaMapeo(){
		try {
			//Prueba de registro de reservas
			ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
			
			//Se declaran los servicios
			UsuarioService usuarioService = (UsuarioService) cxt.getBean("usuarioService");
			HabitacionService habitacionService = (HabitacionService) cxt.getBean("habitacionService");
			ReservaService reservaService = (ReservaService) cxt.getBean("reservaService");
			
			//Datos Fijos
			List<String> lstModalidadPago = new ArrayList<>();
			lstModalidadPago.add("Efectivo");
			lstModalidadPago.add("Deposito");
			
			//Estados de la Reserva
			List<EstadoReservaTipo> lstEstado = new ArrayList<>();
			lstEstado.add(EstadoReservaTipo.HOSPEDADO);
			lstEstado.add(EstadoReservaTipo.HOSPEDADO);
			lstEstado.add(EstadoReservaTipo.HOSPEDADO);
			lstEstado.add(EstadoReservaTipo.HOSPEDADO);
			lstEstado.add(EstadoReservaTipo.CANCELADO);
			
			//Se trae la lista de clientes
			List<Cliente> lstClientes = usuarioService.listarClientes();
			Date fecInicial = new Date("11/01/2017");
			
									
			Date fecIn = new Date();
			Date fecSal = new Date();
			
			//cantidad de dias que vamos a sumar
			int idias = 3;
			//cantidad de dias que se va hospedar
			int idiaHospedaje = 2;
			
			//Ingresamos el numeor de registros a crear
			for (int i = 0; i < 10; i++) {
				//Fecha que se da inicio la reserva
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fecInicial);
				calendar.add(Calendar.DATE, (int) (Math.random() * idias));
				fecIn = calendar.getTime();
				
				//Por defecto es un dia
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(fecIn);
				calendar2.add(Calendar.DATE, (int) (Math.random() * idiaHospedaje) + 1);
				fecSal = calendar2.getTime();
				
				//una vez elejhido las fechas se continua con todo el proceso
				
				//Se toma un CLiente al Azar
				int iCliente = (int) (Math.random()*lstClientes.size());
				Cliente objClienteSelec = lstClientes.get(iCliente);
				
				// Lista de Habitaciones Disponibles
				List<Habitacion> lsthabitacionesdisponibles = habitacionService.obtenerHabitacionesDisponibles(devolverFechaToString(fecIn), devolverFechaToString(fecSal));
				if(lsthabitacionesdisponibles.size() == 0){
					continue;
				}
				//Se toma una Habitación por al Azar
				int iHabitación = (int) (Math.random()*lsthabitacionesdisponibles.size());
				Habitacion objHabitacionSelec = lsthabitacionesdisponibles.get(iHabitación);
				List<Habitacion> lstHabitacionSelec = new ArrayList<>();
				lstHabitacionSelec.add(objHabitacionSelec);
				
				int icantA = (int) (Math.random()*4)+1;			
				long  dia = (fecSal.getTime() - fecIn.getTime()) / MILLSECS_PER_DAY;
				double costo = objHabitacionSelec.getPrecio() * dia * icantA;
				double igv = costo * 0.18;
				double costoTotal = costo + igv;
				
				String modalidadPago = lstModalidadPago.get( (int) (Math.random()*lstModalidadPago.size()) );
				
				String estado = lstEstado.get((int) (Math.random()*lstEstado.size())).getNombre();
				
				//Se setan los datos
				Reserva reserva = new Reserva();
				reserva.setCantA(icantA);
				reserva.setCantTotal(icantA);
				reserva.setFechaEntrada(devolverFechaAct(fecIn));
				reserva.setFechaSalida(devolverFechaAct(fecSal));
				reserva.setSubtotal(costo);
				reserva.setIgv(igv);
				reserva.setPrecio(costoTotal);
				reserva.setModalidadPago(modalidadPago);
				reserva.setUsuario("recep");
				reserva.setIdCliente(objClienteSelec.getIdCliente());
				reserva.setObjCliente(objClienteSelec);
				reserva.setEstado(estado);
				reserva.setFechaRegistro(fecIn);
				reserva.setLstHabitacion(lstHabitacionSelec);
				reserva.setDescripcion("Data de Prueba");
		        reservaService.registrarReserva(reserva);				
			}					
//			//Se toma un CLiente al Azar
//			int iCliente = (int) (Math.random()*lstClientes.size());
//			Cliente objClienteSelec = lstClientes.get(iCliente);
//			
//			// Lista de Habitaciones Disponibles
//			List<Habitacion> lsthabitacionesdisponibles = habitacionService.obtenerHabitacionesDisponibles(devolverFechaToString(fecIn), devolverFechaToString(fecSal));			
//			//Se toma una Habitación por al Azar
//			int iHabitación = (int) (Math.random()*lsthabitacionesdisponibles.size());
//			Habitacion objHabitacionSelec = lsthabitacionesdisponibles.get(iHabitación);
//			List<Habitacion> lstHabitacionSelec = new ArrayList<>();
//			lstHabitacionSelec.add(objHabitacionSelec);
//			
//			
//			int icantA = (int) (Math.random()*4)+1;			
//			long  dia = (fecSal.getTime() - fecIn.getTime()) / MILLSECS_PER_DAY;
//			double costo = objHabitacionSelec.getPrecio() * dia * icantA;
//			double igv = costo * 0.18;
//			double costoTotal = costo + igv;
//			
//			String modalidadPago = lstModalidadPago.get( (int) (Math.random()*lstModalidadPago.size()) );
//			
//			String estado = lstEstado.get((int) (Math.random()*lstEstado.size())).getNombre();
//			
//			//Se setan los datos
//			Reserva reserva = new Reserva();
//			reserva.setCantA(icantA);
//			reserva.setCantTotal(icantA);
//			reserva.setFechaEntrada(devolverFechaAct(fecIn));
//			reserva.setFechaSalida(devolverFechaAct(fecSal));
//			reserva.setSubtotal(costo);
//			reserva.setIgv(igv);
//			reserva.setPrecio(costoTotal);
//			reserva.setModalidadPago(modalidadPago);
//			reserva.setUsuario("recep");
//			reserva.setIdCliente(objClienteSelec.getIdCliente());
//			reserva.setObjCliente(objClienteSelec);
//			reserva.setEstado(estado);
//			reserva.setFechaRegistro(fecIn);
//			reserva.setLstHabitacion(lstHabitacionSelec);
//			reserva.setDescripcion("Data de Prueba");
//	        reservaService.registrarReserva(reserva);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	
	static String devolverFechaToString(Date fec){
		fec.setHours(0);
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
		return formatoDeFecha.format(fec)+ "12:00:00";
	}
	
	static Date devolverFechaAct(Date fec){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fec);
		cal.add(Calendar.HOUR, 12);
		return cal.getTime();
	}

}
