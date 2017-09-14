package pe.com.sgresan.controller;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import pe.com.sgresan.common.CommonConstants;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.entidad.FiltroBusqueda;
import pe.com.sgresan.entidad.GraficoReserva;
import pe.com.sgresan.model.ParametroDetalle;
import pe.com.sgresan.service.ConsultaService;

@ManagedBean
@SessionScoped
public class GraficosEstBean {
	
	private List<ParametroDetalle> lstTipoHabitacion;
	private List<ParametroDetalle> lstEstadoReserva;
		
	private LineChartModel dateModel;
	
	private FiltroBusqueda filtroBusqueda;
		
	@ManagedProperty(value = ConsultaService.EL_NAME)
	private ConsultaService consultaService;

	@PostConstruct
	public void init() {
		try {
			lstTipoHabitacion = consultaService.obtenerTipoHabitaciones();
			lstEstadoReserva = consultaService.obtenerEstadoReserva();
			/** Se carga todos los combos **/
			String[] selectedEstReserva = new String[lstEstadoReserva.size()];
			for (int i = 0; i < lstEstadoReserva.size(); i++) {
				selectedEstReserva[i] = lstEstadoReserva.get(i).getNombre();
			}
			Integer[] selectedHabitacion = new Integer[lstTipoHabitacion.size()];
			for (int i = 0; i < lstTipoHabitacion.size(); i++) {
				selectedHabitacion[i] = lstTipoHabitacion.get(i).getId();
			}
			
			/** Inicializar los Filtros de busqueda **/
			filtroBusqueda = new FiltroBusqueda();
			
			filtroBusqueda.setSelectedString1(selectedEstReserva);
			filtroBusqueda.setSelectedInteger1(selectedHabitacion);
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, CommonConstants.I_NEG_UNO);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));			
			filtroBusqueda.setFechaInicio(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			filtroBusqueda.setFechaFin(calendar.getTime());			
			
			buscar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prueba() throws Exception {
		System.out.println("Carga una vez");
		lstTipoHabitacion = consultaService.obtenerTipoHabitaciones();//selectedCars = new String[5];
		lstEstadoReserva = consultaService.obtenerEstadoReserva();
	}
	
	public void buscar(){
		try {
			GraficoReserva objGrafReserva = consultaService.obtenerEstadisticaReserva(filtroBusqueda);
			llenarDataGraficos(objGrafReserva.getLstDataReserva());
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void llenarDataGraficos(List<GraficoReserva> lstDataReserva) throws Exception{
		//DataLineChart
		Map<Object, Object> objParams = new HashMap<>();
		for (GraficoReserva graficoReserva : lstDataReserva) {
			armarDataLineChart(objParams, graficoReserva.getCantidad(), graficoReserva.getFecha(), graficoReserva.getEstadoReserva());
		}
		
		//Llenar LineChart
		dateModel = new LineChartModel();
		for(Object objValue : objParams.keySet()){
			LineChartSeries series = new LineChartSeries();
			series.setLabel(Utils.getString(objValue).toUpperCase());
			
			Map<String, BigInteger> objData = (Map<String, BigInteger>) objParams.get(objValue);
			for (String objString : objData.keySet()) {
				series.set(objString, objData.get(objString));
			}
			dateModel.addSeries(series);
		}
		
		dateModel.setTitle("Zoom for Details");
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
//        axis.setMax("2017-09-13");
        axis.setTickFormat("%d/%m/%y");
        dateModel.setAnimate(true);
        dateModel.setLegendPosition("s");
        dateModel.setLegendRows(1);
        dateModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        dateModel.getAxes().put(AxisType.X, axis);
	}
	
	@SuppressWarnings("unchecked")
	private void armarDataLineChart(Map<Object, Object> objParams, BigInteger cantidad, Date fecha, Object valor){
		if(Utils.isNull(objParams.get(valor))){
			Map<String, BigInteger> objData = new HashMap<>();
			objParams.put(valor, objData);
		}
		
		Map<String, BigInteger> objData = (Map<String, BigInteger>) objParams.get(valor);
		if(Utils.isNull(objData.get(fecha))){
			objData.put(Utils.convertDatetoString(fecha, CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD), cantidad);
		}else{
			BigInteger total = objData.get(fecha);
			objData.put(Utils.convertDatetoString(fecha, CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD), total.add(cantidad));
		}		
	}

	/**
	 * Returns attribute consultaService
	 * @return consultaService <code>ConsultaService</code>
	 */
	public ConsultaService getConsultaService() {
		return consultaService;
	}

	/**
	 * Sets attribute consultaService
	 * @param consultaService <code>ConsultaService</code>
	 */
	public void setConsultaService(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}

	/**
	 * Returns attribute lstTipoHabitacion
	 * @return lstTipoHabitacion <code>List<ParametroDetalle></code>
	 */
	public List<ParametroDetalle> getLstTipoHabitacion() {
		return lstTipoHabitacion;
	}	

	/**
	 * Returns attribute lstEstadoReserva
	 * @return lstEstadoReserva <code>List<ParametroDetalle></code>
	 */
	public List<ParametroDetalle> getLstEstadoReserva() {
		return lstEstadoReserva;
	}

	/**
	 * Returns attribute dateModel
	 * @return dateModel <code>LineChartModel</code>
	 */
	public LineChartModel getDateModel() {
		return dateModel;
	}

	/**
	 * Sets attribute dateModel
	 * @param dateModel <code>LineChartModel</code>
	 */
	public void setDateModel(LineChartModel dateModel) {
		this.dateModel = dateModel;
	}

	/**
	 * Returns attribute objFiltroBusqueda
	 * @return objFiltroBusqueda <code>FiltroBusqueda</code>
	 */
	public FiltroBusqueda getFiltroBusqueda() {
		return filtroBusqueda;
	}

}
