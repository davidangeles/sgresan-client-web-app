/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.HabitacionDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.TCliente;
import model.THabitacion;
import model.TReserva;
import model.TReservadetalle;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Joel
 */
@ManagedBean(name="UserWizard")
@SessionScoped
public class UserWizard {
 
      
    private boolean skip;  
      
    private static Logger logger = Logger.getLogger(UserWizard.class.getSimpleName());  
    
    private DualListModel<THabitacion> cities;
    private TReserva reserv;
    private TReservadetalle reserva;
    private List<THabitacion> habitacionesdisponibles;
    private String fecIn;
    private String fecSal;
    /**
     * Creates a new instance of UserWizard
     */
    public UserWizard() {
        reserva = new TReservadetalle();
        reserva.setTHabitacion(new THabitacion());
        reserva.setTReserva(new TReserva());
        
        reserv = new TReserva();
        reserv.setTCliente(new TCliente());
        
        List<THabitacion> citiesSource = new ArrayList<THabitacion>();
        List<THabitacion> citiesTarget = new ArrayList<THabitacion>();
        
            fecIn="2016-01-01";
            fecSal="2017-01-01";
             HabitacionDao dao = new HabitacionDao();
       habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        for(int i = 0;i<habitacionesdisponibles.size();i++)
        {
            citiesSource.add(habitacionesdisponibles.get(i));
        }
        cities = new DualListModel<THabitacion>(citiesSource, citiesTarget);
        
    }
    
    public String onFlowProcess(FlowEvent event) {  
        logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());  
        logger.log(Level.INFO, "Next step:{0}", event.getNewStep());  
       
        if(event.getNewStep().equals("dormitoriotab"))
        {
           // BUSQUEDA();
            System.out.println("Aca empieza la busqueda :V");
        }
        
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }
        
    }
    public void Actualizar()
    {
        List<THabitacion> citiesSource = new ArrayList<THabitacion>();
        List<THabitacion> citiesTarget = new ArrayList<THabitacion>();
 
       HabitacionDao dao = new HabitacionDao();
       habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        for(int i = 0;i<habitacionesdisponibles.size();i++)
        {
            citiesSource.add(habitacionesdisponibles.get(i));
        }
        cities = new DualListModel<THabitacion>(citiesSource, citiesTarget);
        
    }

    public  void BUSQUEDA()
   {System.out.println(reserv.getFechaEntrada());
       //System.out.println((reserv.getFechaEntrada().getYear()+1900) +"/"+(reserv.getFechaEntrada().getMonth()+1)+"/"+reserv.getFechaEntrada().getDate());
//       HabitacionDao dao = new HabitacionDao();
//       fecIn=(reserv.getFechaEntrada().getYear()+1900) +"/"+(reserv.getFechaEntrada().getMonth()+1)+"/"+reserv.getFechaEntrada().getDate();
//       fecSal=(reserv.getFechaSalida().getYear()+1900) +"/"+(reserv.getFechaSalida().getMonth()+1)+"/"+reserv.getFechaSalida().getDate();
//       habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
//       Actualizar();
//             System.out.println(habitacionesdisponibles.size());
   }
    
    public List<THabitacion> getHabitacionesdisponibles() {
         HabitacionDao dao = new HabitacionDao();
       habitacionesdisponibles = dao.listarhabitaciones(fecIn, fecSal);
        return habitacionesdisponibles;
    }
    
    public TReserva getReserv() {
        return reserv;
    }

    public void setReserv(TReserva reserv) {
        this.reserv = reserv;
    }

    public TReservadetalle getReserva() {
        return reserva;
    }

    public void setReserva(TReservadetalle reserva) {
        this.reserva = reserva;
    }

    public String getFecIn() {
        return fecIn;
    }

    public void setFecIn(String fecIn) {
        this.fecIn = fecIn;
    }

    public String getFecSal() {
        return fecSal;
    }

    public void setFecSal(String fecSal) {
        this.fecSal = fecSal;
    }

    public DualListModel<THabitacion> getCities() {
        return cities;
    }

    public void setCities(DualListModel<THabitacion> cities) {
        this.cities = cities;
    }
    
    
    
    
    
}
