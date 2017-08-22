/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Entidad.Estadistica;
import dao.EstadisticaDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class EstadisticaBean {
    
private List<Estadistica> lista;
private List<Estadistica> listaMeses;
private List<Estadistica> listaAnual;


private BarChartModel barra;
private BarChartModel barraMeses;
private BarChartModel barraAnual;

    EstadisticaDao eDao;
    private int valor;

    public EstadisticaBean() {
        eDao = new EstadisticaDao();
        valor=1;
        createAnimatedModels(); 
        createAnimatedModelsMeses();
        createAnimatedModelsAnual();
    }
 
    
    public void ELECCION(){
        switch(valor) {
            case 1:
                createAnimatedModels(); break;
            case 2: 
                createAnimatedModelsMeses(); break;
            case 3:
                createAnimatedModelsAnual(); break;
        }   
    }
    
    private void createAnimatedModels() {
        
         
        setBarra(initBarModel());
        getBarra().setTitle("Reservas del Mes");
        getBarra().setAnimate(true);
        getBarra().setLegendPosition("ne");
       // getBarra().setBarMargin(80);
        //getBarra().setBarPadding(100);
    }
    
    private void createAnimatedModelsMeses() {
        
         
        setBarraMeses(initBarModelMeses());
        getBarraMeses().setTitle("Reservas de los Meses del Año");
        getBarraMeses().setAnimate(true);
        getBarraMeses().setLegendPosition("ne");
       // getBarra().setBarMargin(80);
        //getBarra().setBarPadding(100);
    }
    
        private void createAnimatedModelsAnual() {
        
         
        setBarraAnual(initBarModelAnual());
        getBarraAnual().setTitle("Reservas por Años");
        getBarraAnual().setAnimate(true);
        getBarraAnual().setLegendPosition("ne");
       // getBarra().setBarMargin(80);
        //getBarraAnual().setBarPadding(100);
        getBarraAnual().setBarWidth(20);
    }

    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries est = new ChartSeries();
        est.setLabel("Reservas");
     Axis xAxis = model.getAxis(AxisType.X);
     xAxis.setTickAngle(-60);
        lista=new ArrayList<Estadistica>();
        lista.addAll(eDao.visitaMesActual());

        for(int i=0;i<lista.size();i++)
        {
        	est.set(lista.get(i).getFECHA(), lista.get(i).getCANTIDAD());
        	//est.set(x, y);
        }
        model.addSeries(est);
         
        return model;
    }
    
    private BarChartModel initBarModelMeses() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries est = new ChartSeries();
        est.setLabel("Reservas");
     /*Axis xAxis = model.getAxis(AxisType.X);
     xAxis.setTickAngle(-60);*/
        listaMeses=new ArrayList<Estadistica>();
        listaMeses.addAll(eDao.visitaMeses());

        for(int i=0;i<listaMeses.size();i++)
        {
        	est.set(listaMeses.get(i).getFECHA(), listaMeses.get(i).getCANTIDAD());
        	//est.set(x, y);
        }
        model.addSeries(est);
         
        return model;
    }
    
    private BarChartModel initBarModelAnual() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries est = new ChartSeries();
        est.setLabel("Reservas");
     /*Axis xAxis = model.getAxis(AxisType.X);
     xAxis.setTickAngle(-60);*/
        listaAnual=new ArrayList<Estadistica>();
        listaAnual.addAll(eDao.visitaAnual());

        for(int i=0;i<listaAnual.size();i++)
        {
        	est.set(listaAnual.get(i).getFECHA(), listaAnual.get(i).getCANTIDAD());
        	//est.set(x, y);
        }
        model.addSeries(est);
         
        return model;
    }

     public List<Estadistica> getLista() {
        return lista;
    }

    public void setLista(List<Estadistica> lista) {
        this.lista = lista;
    }
    public List<Estadistica> getListaMeses() {
        return listaMeses;
    }

    public void setListaMeses(List<Estadistica> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public List<Estadistica> getListaAnual() {
        return listaAnual;
    }

    public void setListaAnual(List<Estadistica> listaAnual) {
        this.listaAnual = listaAnual;
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
    
    
}
