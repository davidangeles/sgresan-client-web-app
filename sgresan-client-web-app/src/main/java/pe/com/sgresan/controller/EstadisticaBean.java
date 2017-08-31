package pe.com.sgresan.controller;

import java.util.ArrayList;
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
import model.TTipohabitacion;
import pe.com.sgresan.entidad.Estadistica;
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

    HabitacionDao hDao;
    private int valor;
    private int accion;

    public EstadisticaBean() {
        valor=1;accion=1;
    }
    
    @PostConstruct
    public void init() {
    	try {
            hDao = new HabitacionDao();
            
            createAnimatedModels(); 
            createAnimatedModelsMeses();
            createAnimatedModelsAnual();
		} catch (Exception e) {
			e.getMessage();
		}
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
        listaTipoHab = hDao.listarTipoHabitacion();
        return listaTipoHab;
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
    
    
}
