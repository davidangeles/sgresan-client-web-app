package pe.com.sgresan.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

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
	private BarChartModel barModel;
	private PieChartModel pieModel;
	
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
			filtroBusqueda.setParameterInteger1(CommonConstants.I_UNO);
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
			llenarDataGraficos(objGrafReserva);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void llenarDataGraficos(GraficoReserva objGrafReserva) throws Exception{
		//DataLineChart
		Map<Object, Object> objLineChart = new TreeMap<>();
		Map<String, BigInteger> objChartEstado = new TreeMap<>();
		for (GraficoReserva graficoReserva : objGrafReserva.getLstDataReserva()) {
			formatoDataLineChart(objLineChart, graficoReserva.getCantidad(), graficoReserva.getFecha(), graficoReserva.getEstadoReserva());
			formatoDataChart(objChartEstado, graficoReserva.getEstadoReserva(), graficoReserva.getCantidad());
		}
		
		Map<String, BigInteger> objChartHabitacion = new TreeMap<>();
		for (GraficoReserva graficoReserva : objGrafReserva.getLstDataHabitación()) {
			formatoDataChart(objChartHabitacion, graficoReserva.getTipoHabitacion(), graficoReserva.getCantidad());
		}
		
		llenarDataLineChart(objLineChart);
		llenarDataBarChart(objChartEstado);
		llenarDataPie(objChartHabitacion, lstTipoHabitacion);
	}
	
	private void llenarDataPie(Map<String, BigInteger> objChartEstado, List<ParametroDetalle> lstTipoHabitacion){
		pieModel = new PieChartModel();
		for (String objValue : objChartEstado.keySet()) {
			for(ParametroDetalle objParametro : lstTipoHabitacion){
				if(objParametro.getId().toString().equals(objValue)){
					pieModel.set(objParametro.getNombre().toUpperCase(), objChartEstado.get(objValue));
				}
			}			
		}		
		pieModel.setTitle("Tipo de Habitación");
		pieModel.setLegendPosition("w");
		pieModel.setShowDataLabels(true);
	}
	
	private void llenarDataBarChart(Map<String, BigInteger> objChartEstado){
		barModel = new BarChartModel();
		int intMaxValue = 0;
		for (String objValue : objChartEstado.keySet()) {
			ChartSeries series = new ChartSeries();
			BigInteger intValue = objChartEstado.get(objValue);
			if(intMaxValue < intValue.intValue()){
				intMaxValue = intValue.intValue();
			}
			series.setLabel(objValue);
			series.set("Estado", intValue);
			barModel.addSeries(series);
		}
		
		barModel.setTitle("Cantidad de Reservas por Estado");
        barModel.setLegendPosition("ne");
        barModel.setLegendRows(1);
        barModel.setAnimate(true);
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(intMaxValue+2);//No se xq dos
        yAxis.setLabel("Cantidad");
	}
	
	@SuppressWarnings("unchecked")
	private void llenarDataLineChart(Map<Object, Object> objParams) {
		// Llenar LineChart
		dateModel = new LineChartModel();
		//Lista para calcular la fecha maxima y minima
		Set<String> setFecha = new TreeSet<String>();
		for (Object objValue : objParams.keySet()) {
			ChartSeries series = new ChartSeries();
			if (CommonConstants.I_UNO == filtroBusqueda.getParameterInteger1().intValue()) {
				series = new LineChartSeries();
			}
			series.setLabel(Utils.getString(objValue).toUpperCase());

			Map<String, BigInteger> objData = (TreeMap<String, BigInteger>) objParams.get(objValue);
			setFecha.addAll(objData.keySet());
			for (String objString : objData.keySet()) {
				series.set(objString, objData.get(objString));
			}
			dateModel.addSeries(series);
		}

		dateModel.setTitle("Cantidad de Reservas");
		dateModel.getAxis(AxisType.Y).setLabel("Cantidad");
		
		if (CommonConstants.I_UNO == filtroBusqueda.getParameterInteger1().intValue()) {
			DateAxis axis = new DateAxis("Fechas");
			axis.setTickAngle(-50);
			List<String> lstFecha = new ArrayList<>(setFecha);
			if(!lstFecha.isEmpty()){
				Date dateMin = Utils.convertStringtoDate(lstFecha.get(0), CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD);
				Date dateMax = Utils.convertStringtoDate(lstFecha.get(lstFecha.size()-1), CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD);
				axis.setMin(Utils.convertDatetoString(DateUtils.addDays(dateMin, -1), CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD));
				axis.setMax(Utils.convertDatetoString(DateUtils.addDays(dateMax, 1), CommonConstants.STR_DATE_FORMAT_YYYY_MM_DD));
				dateMin = DateUtils.addDays(new Date(),-30);
			}
			axis.setTickFormat("%d/%m/%y");
			dateModel.getAxes().put(AxisType.X, axis);
		} else {
			dateModel.setShowPointLabels(true);
			dateModel.getAxes().put(AxisType.X, new CategoryAxis("Fechas"));
		}
		dateModel.setAnimate(true);
		dateModel.setLegendPosition("s");
		dateModel.setLegendRows(1);
		dateModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}
	
	@SuppressWarnings("unchecked")
	private void formatoDataLineChart(Map<Object, Object> objParams, BigInteger cantidad, String fecha, Object valor){
		if(!objParams.containsKey(valor)){
			Map<String, BigInteger> objData = new TreeMap<>();
			objParams.put(valor, objData);
		}
		
		Map<String, BigInteger> objData = (Map<String, BigInteger>) objParams.get(valor);
		if(Utils.isNull(objData.get(fecha))){
			objData.put(fecha, cantidad);
		}else{
			BigInteger total = objData.get(fecha);
			objData.put(fecha, total.add(cantidad));
		}		
	}
	
	@SuppressWarnings("unused")
	private void formatoDataChart(Map<String, BigInteger> objParams, String valor, BigInteger cantidad){
		if(!objParams.containsKey(valor)){
			objParams.put(valor, cantidad);
		}else{
			BigInteger total = (BigInteger) objParams.get(valor);
			objParams.put(valor, total.add(cantidad));
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

	/**
	 * Returns attribute barModel
	 * @return barModel <code>BarChartModel</code>
	 */
	public BarChartModel getBarModel() {
		return barModel;
	}

	/**
	 * Returns attribute pieModel
	 * @return pieModel <code>PieChartModel</code>
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}

}
