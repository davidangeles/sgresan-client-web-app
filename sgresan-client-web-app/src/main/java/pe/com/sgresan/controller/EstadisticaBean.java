package pe.com.sgresan.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

import dao.HabitacionDao;
import dao.ProductoDao;
import model.TProducto;
import model.TTipohabitacion;
import pe.com.sgresan.entidad.Estadistica;
import pe.com.sgresan.entidad.TipoPago;
import pe.com.sgresan.service.ConsultaService;

@ManagedBean
@SessionScoped
public class EstadisticaBean {
	
	@ManagedProperty(value = ConsultaService.EL_NAME)
	private ConsultaService consultaService;
    
	private List<Estadistica> lista;
	private List<Estadistica> listaAnual;

	private List<TTipohabitacion> listaTipoHab;

	private BarChartModel barra;
	private BarChartModel barraMeses;
	private BarChartModel barraAnual;

    HabitacionDao hDao;ProductoDao pDao;
    private int valor;
    private int accion;
    
    private List<Estadistica> listaFinanciero;
    private List<TProducto> listaProducto;
    
    private int valorFinanciero;
    private int accionFinanciero;
    private BarChartModel barraFinanciero;
    
    
    private List<Estadistica> listaClientes;
    private BarChartModel barraClientes;
    private int accionC;
    private int valorC;
    private Date fechaI,fechaF;

    public EstadisticaBean() {
        valor=1;accion=1; accionC=1;valorC=1;
        valorFinanciero=2;accionFinanciero=1;
    }
    
    @PostConstruct
    public void init() {
    	try {
            hDao = new HabitacionDao();
            pDao = new ProductoDao();
            fechaI= new Date();
            Calendar cal = Calendar.getInstance();
    		cal.setTime(fechaI);  
    		cal.add(Calendar.YEAR, cal.YEAR-3);
            fechaI= cal.getTime();
            fechaF= new Date();
            createAnimatedModels(); 
            createAnimatedModelsMeses();
            createAnimatedModelsAnual();
            createAnimatedModelsFinMesActual();
            createAnimatedModelsClientesTop();
		} catch (Exception e) {
			e.getMessage();
		}
    }
    
    public void onPageLoad(){
        System.out.println("Carga una vez");
     }
 
    
    public void ELECCION() throws Exception{
        switch(valor) {
            case 1:
                createAnimatedModels(); break;
            case 2: 
                createAnimatedModelsMeses(); break;
            case 3:
                createAnimatedModelsAnual(); break;
        }   
    }
    
    public void ELECCION_FIN() throws Exception{
        switch(valorFinanciero) {
            case 1:
                createAnimatedModelsFinMesActual(); break;
            case 2: 
                createAnimatedModelsFinMeses(); break;
            case 3:
                createAnimatedModelsFinAnual(); break;
        }   
    }
    
    public void ELECCION_CLI() throws Exception{
    	System.out.println(fechaI+" "+fechaF);
        switch(accionC) {
            case 1:
                createAnimatedModelsClientesTop(); break;
            case 2: 
            	createAnimatedModelsClientesTop(); break;
            case 3:
            	createAnimatedModelsClientesTop(); break;
        }   
    }
    
	private void createAnimatedModels() throws Exception {
		setBarra(initBarModel());
		getBarra().setTitle("Reservas del Mes");
		getBarra().setAnimate(true);
		getBarra().setLegendPosition("s");
		getBarra().setLegendRows(1);
		getBarra().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}
    
	private void createAnimatedModelsMeses() throws Exception {
		setBarraMeses(initBarModelMeses());
		getBarraMeses().setTitle("Reservas de los Meses del Año");
		getBarraMeses().setAnimate(true);
		getBarraMeses().setLegendPosition("s");
		getBarraMeses().setLegendRows(1);
		getBarraMeses().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}
    
	private void createAnimatedModelsAnual() throws Exception {
		setBarraAnual(initBarModelAnual());
		getBarraAnual().setTitle("Reservas por Años");
		getBarraAnual().setAnimate(true);
		getBarraAnual().setLegendPosition("s");
		getBarraAnual().setLegendRows(1);
		getBarraAnual().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		getBarraAnual().setBarWidth(20);
	}
	
	private void createAnimatedModelsFinMesActual() throws Exception {
		setBarraFinanciero(initBarModelFinancieroMesActual());
		getBarraFinanciero().setTitle("Monto en S/. del Mes");
		getBarraFinanciero().setAnimate(true);
		getBarraFinanciero().setLegendPosition("s");
		getBarraFinanciero().setLegendRows(1);
		getBarraFinanciero().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}
	
	private void createAnimatedModelsFinMeses() throws Exception {
		setBarraFinanciero(initBarModelFinancieroMeses());
		getBarraFinanciero().setTitle("Monto en S/. de los Meses");
		getBarraFinanciero().setAnimate(true);
		getBarraFinanciero().setLegendPosition("s");
		getBarraFinanciero().setLegendRows(1);
		getBarraFinanciero().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}
	
	private void createAnimatedModelsFinAnual() throws Exception {
		setBarraFinanciero(initBarModelFinancieroAnual());
		getBarraFinanciero().setTitle("Monto en S/. por Años");
		getBarraFinanciero().setAnimate(true);
		getBarraFinanciero().setLegendPosition("s");
		getBarraFinanciero().setLegendRows(1);
		getBarraFinanciero().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		getBarraFinanciero().setBarWidth(20);
	}
	
	private void createAnimatedModelsClientesTop() throws Exception {
		setBarraClientes(initBarModelClientes());
		getBarraClientes().setTitle("Top Clientes");
		getBarraClientes().setAnimate(true);
		getBarraClientes().setLegendPosition("s");
		getBarraClientes().setLegendRows(1);
		getBarraClientes().setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		getBarraClientes().setBarWidth(20);
	}

	private BarChartModel initBarModelClientes() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setTickAngle(-60);
		listaClientes = new ArrayList<Estadistica>();
		listaClientes.addAll(consultaService.getTopClientes(accionC, valorC,fechaI,fechaF));

		ChartSeries est = new ChartSeries();
		est.setLabel("Clientes");

		for (int i = 0; i < listaClientes.size(); i++) {
			System.out.println(listaClientes.get(i).getFecha()+" "+ listaClientes.get(i).getCantidad());
			est.set(listaClientes.get(i).getFecha(), listaClientes.get(i).getCantidad());
		}
		model.addSeries(est);
	/*	if (accion != 3) {
			ChartSeries est = new ChartSeries();
			est.setLabel("Clientes");

			for (int i = 0; i < lista.size(); i++) {
				est.set(lista.get(i).getFecha(), lista.get(i).getCantidad());
			}
			model.addSeries(est);
		} else {
			List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
			listaTipoHab = hDao.listarTipoHabitacion();
			for (int i = 0; i < listaTipoHab.size(); i++) {
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaTipoHab.get(i).getNombre());
				for (int j = 0; j < lista.size(); j++) {
					if (lista.get(j).getTexto().equals(listaTipoHab.get(i).getNombre())) {
						listaBarra.get(i).set(lista.get(j).getFecha(), lista.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
			}

		}*/

		return model;
	}
    
	private BarChartModel initBarModel() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setTickAngle(-60);
		lista = new ArrayList<Estadistica>();
		lista.addAll(consultaService.visitaMesActual(accion));

		if (accion != 3) {
			ChartSeries est = new ChartSeries();
			est.setLabel("Reservas");

			for (int i = 0; i < lista.size(); i++) {
				est.set(lista.get(i).getFecha(), lista.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
		} else {
			List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
			listaTipoHab = hDao.listarTipoHabitacion();
			for (int i = 0; i < listaTipoHab.size(); i++) {
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaTipoHab.get(i).getNombre());
				for (int j = 0; j < lista.size(); j++) {
					if (lista.get(j).getTexto().equals(listaTipoHab.get(i).getNombre())) {
						listaBarra.get(i).set(lista.get(j).getFecha(), lista.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
			}

		}

		return model;
	}

	private BarChartModel initBarModelMeses() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setTickAngle(-60);
		lista = new ArrayList<Estadistica>();
		lista.addAll(consultaService.visitaMeses(accion));

		if (accion != 3) {
			ChartSeries est = new ChartSeries();
			est.setLabel("Reservas");

			for (int i = 0; i < lista.size(); i++) {
				est.set(lista.get(i).getFecha(), lista.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
		} else {
			List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
			listaTipoHab = hDao.listarTipoHabitacion();
			for (int i = 0; i < listaTipoHab.size(); i++) {
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaTipoHab.get(i).getNombre());
				for (int j = 0; j < lista.size(); j++) {
					if (lista.get(j).getTexto().equals(listaTipoHab.get(i).getNombre())) {
						listaBarra.get(i).set(lista.get(j).getFecha(), lista.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
			}

		}

		return model;
	}
    
	private BarChartModel initBarModelAnual() throws Exception {
		BarChartModel model = new BarChartModel();

		listaAnual = new ArrayList<Estadistica>();
		listaAnual.addAll(consultaService.visitaAnual(accion));

		if (accion != 3) {
			ChartSeries est = new ChartSeries();
			est.setLabel("Reservas");

			for (int i = 0; i < listaAnual.size(); i++) {
				est.set(listaAnual.get(i).getFecha(), listaAnual.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
		} else {
			List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
			listaTipoHab = hDao.listarTipoHabitacion();
			for (int i = 0; i < listaTipoHab.size(); i++) {
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaTipoHab.get(i).getNombre());
				for (int j = 0; j < listaAnual.size(); j++) {
					if (listaAnual.get(j).getTexto().equals(listaTipoHab.get(i).getNombre())) {
						listaBarra.get(i).set(listaAnual.get(j).getFecha(), listaAnual.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
			}

		}
		return model;
	}

	
	/*************************Estadistica Financiero********************************************/
	private BarChartModel initBarModelFinancieroMesActual() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setTickAngle(-60);
		listaFinanciero = new ArrayList<Estadistica>();
		listaFinanciero.addAll(consultaService.visitaFinancieroMesActual(accionFinanciero));

		List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
		switch(accionFinanciero){
		case 1:
			
			ChartSeries est = new ChartSeries();
			est.setLabel("Monto en S/");

			for (int i = 0; i < listaFinanciero.size(); i++) {
				est.set(listaFinanciero.get(i).getFecha(), listaFinanciero.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
			
			break;
			
		case 2 :
			
			
			
			List<TipoPago> listaPagos = new ArrayList<>();
			listaPagos.add(new TipoPago(1,"Efectivo"));
			listaPagos.add(new TipoPago(1,"Deposito"));
			listaPagos.add(new TipoPago(1,"Venta P/S"));
			for (int i = 0; i < listaPagos.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaPagos.get(i).getNombre());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaPagos.get(i).getNombre())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}
			break;
		case 3 :
			 
			listaProducto = pDao.listarProductos();
		 
			for (int i = 0; i < listaProducto.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaProducto.get(i).getNombreProducto());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaProducto.get(i).getNombreProducto())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}break;
			}
   	return model;
	}

	
	private BarChartModel initBarModelFinancieroMeses() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setTickAngle(-60);
		listaFinanciero = new ArrayList<Estadistica>();
		listaFinanciero.addAll(consultaService.visitaFinancieroMeses(accionFinanciero));

		List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
		switch(accionFinanciero){
		case 1:
			
			ChartSeries est = new ChartSeries();
			est.setLabel("Monto en S/");

			for (int i = 0; i < listaFinanciero.size(); i++) {
				est.set(listaFinanciero.get(i).getFecha(), listaFinanciero.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
			
			break;
			
		case 2 :
			
			
			
			List<TipoPago> listaPagos = new ArrayList<>();
			listaPagos.add(new TipoPago(1,"Efectivo"));
			listaPagos.add(new TipoPago(1,"Deposito"));
			listaPagos.add(new TipoPago(1,"Venta P/S"));
			for (int i = 0; i < listaPagos.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaPagos.get(i).getNombre());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaPagos.get(i).getNombre())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}
			break;
			
	
		default:
			 
			listaProducto = pDao.listarProductos();
		 
			for (int i = 0; i < listaProducto.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaProducto.get(i).getNombreProducto());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaProducto.get(i).getNombreProducto())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}break;
			}
   	return model;
	}
	
	private BarChartModel initBarModelFinancieroAnual() throws Exception {
		BarChartModel model = new BarChartModel();

		Axis xAxis = model.getAxis(AxisType.X);
		listaFinanciero = new ArrayList<Estadistica>();
		listaFinanciero.addAll(consultaService.visitaFinancieroAnual(accionFinanciero));

		List<ChartSeries> listaBarra = new ArrayList<ChartSeries>();
		switch(accionFinanciero){
		case 1:
			
			ChartSeries est = new ChartSeries();
			est.setLabel("Monto en S/");

			for (int i = 0; i < listaFinanciero.size(); i++) {
				est.set(listaFinanciero.get(i).getFecha(), listaFinanciero.get(i).getCantidad());
				// est.set(x, y);
			}
			model.addSeries(est);
			
			break;
			
		case 2 :
			
			
			
			List<TipoPago> listaPagos = new ArrayList<>();
			listaPagos.add(new TipoPago(1,"Efectivo"));
			listaPagos.add(new TipoPago(1,"Deposito"));
			listaPagos.add(new TipoPago(1,"Venta P/S"));
			for (int i = 0; i < listaPagos.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaPagos.get(i).getNombre());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaPagos.get(i).getNombre())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}
			break;
		case 3 :
			 
			listaProducto = pDao.listarProductos();
		 
			for (int i = 0; i < listaProducto.size(); i++) {
				 
				listaBarra.add(new ChartSeries());
				listaBarra.get(i).setLabel(listaProducto.get(i).getNombreProducto());
				for (int j = 0; j < listaFinanciero.size(); j++) {
					 
					if (listaFinanciero.get(j).getTexto().equals(listaProducto.get(i).getNombreProducto())) {
						listaBarra.get(i).set(listaFinanciero.get(j).getFecha(), listaFinanciero.get(j).getCantidad());
					}
				}
				model.addSeries(listaBarra.get(i));
		
		}break;
			}
   	return model;
	}
	
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
     public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

    public BarChartModel getBarraMeses() {
        return barraMeses;
    }

    public void setBarraMeses(BarChartModel barraMeses) {
        this.barraMeses = barraMeses;
    }

    public BarChartModel getBarraAnual() {
        return barraAnual;
    }

    public void setBarraAnual(BarChartModel barraAnual) {
        this.barraAnual = barraAnual;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    

    public List<TTipohabitacion> getListaTipoHab() {
        return listaTipoHab;
    }
    

	public List<TProducto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<TProducto> listaProducto) {
		this.listaProducto = listaProducto;
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

	public int getValorFinanciero() {
		return valorFinanciero;
	}

	public void setValorFinanciero(int valorFinanciero) {
		this.valorFinanciero = valorFinanciero;
	}

	public int getAccionFinanciero() {
		return accionFinanciero;
	}

	public void setAccionFinanciero(int accionFinanciero) {
		this.accionFinanciero = accionFinanciero;
	}

	public BarChartModel getBarraFinanciero() {
		return barraFinanciero;
	}

	public void setBarraFinanciero(BarChartModel barraFinanciero) {
		this.barraFinanciero = barraFinanciero;
	}

	public List<Estadistica> getListaClientes() {
		return listaClientes;
	}

	public BarChartModel getBarraClientes() {
		return barraClientes;
	}

	public void setBarraClientes(BarChartModel barraClientes) {
		this.barraClientes = barraClientes;
	}

	public int getAccionC() {
		return accionC;
	}

	public void setAccionC(int accionC) {
		this.accionC = accionC;
	}

	public int getValorC() {
		return valorC;
	}

	public void setValorC(int valorC) {
		this.valorC = valorC;
	}

	public Date getFechaI() {
		return fechaI;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public Date getFechaF() {
		return fechaF;
	}

	public void setFechaF(Date fechaF) {
		this.fechaF = fechaF;
	}


	 
                               
}
