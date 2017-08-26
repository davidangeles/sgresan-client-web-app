/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.sgresan.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.timeline.TimelineAddEvent;
import org.primefaces.event.timeline.TimelineDragDropEvent;
import org.primefaces.event.timeline.TimelineModificationEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import dao.ClienteDao;
import dao.HabitacionDao;
import dao.ReservaDao;
import model.TCliente;
import model.THabitacion;
import model.THotel;
import model.TPersona;
import model.TReserva;
import model.TReservadetalle;
import pe.com.sgresan.model.Reserva;
import pe.com.sgresan.service.ReservaService;
import pe.com.sgresan.service.UsuarioService;
import pe.com.sgresan.common.EstadoReservaTipo;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.entidad.TimelineDetalleReserva;
import pe.com.sgresan.entidad.TimelineReserva;
import pe.com.sgresan.model.Cliente;
import pe.com.sgresan.model.Habitacion;
import pe.com.sgresan.model.Persona;
import pe.com.sgresan.model.Usuario;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ReservaDetalleBean {
	
	@ManagedProperty(value = ReservaService.EL_NAME)
	private ReservaService reservaService;
	
	@ManagedProperty(value = UsuarioService.EL_NAME)
	private UsuarioService usuarioService;
	
	private Reserva reserv;

    /**
     * *** ACTUALIZACION ****
     */
    private List<TimelineDetalleReserva> listareservas;
    private TimelineReserva reserva1;
    //private List<TReservadetalle> listareservas;
    private TReservadetalle reserva;
    /**
     * ********************************************
     */
    private String rutaImagen;
    private THabitacion hab;
    private List<TReservadetalle> list;
    private Cliente cli;
    private String motivo;
    public int nrohabitacion;
    private double igv;
    private double costoTotal;
    private TimelineModel model;
    private TimelineEvent event; // current event to be changed, edited, deleted or added  
    private long zoomMax;
    private Date start;
    private String startm;
    private Date end;
    private String endm;
    private TimeZone timeZone = TimeZone.getTimeZone("America/Lima");
    private boolean timeChangeable = true;

    ReservaDao dao = new ReservaDao();

    private DualListModel<Habitacion> cities;
    private String fecIn;
    private String fecSal;
    private List<Habitacion> habitacionesdisponibles;

    private double costo;
    private boolean editable;
    long dia;
    private Date max;
    //private boolean editable=true;
    /**
     * Creates a new instance of ReservaDetalleBean
     */
    private String tipohab;
    private String estado;
    private String nombreC;

    public ReservaDetalleBean() {
        zoomMax = 600000001;
        event = new TimelineEvent();
        tipohab = "";estado="";
        costo = 0;
        editable = false;
        igv = 0.0;
        costoTotal = 0.0;
        motivo = "";

        reserva = new TReservadetalle();
        reserva.setTHabitacion(new THabitacion());
        reserva.setTReserva(new TReserva());

        reserv = new Reserva();
        reserv.setObjCliente(new Cliente());
        hab = new THabitacion();
        hab.setTHotel(new THotel());
        rutaImagen="/images/bed/blanco.png";
    }
    
    @PostConstruct
    public void init() {
    	try {
    		llenar();       

            fecIn = "2016-01-01";
            fecSal = "2017-01-01";
            
            habitacionesdisponibles = reservaService.obtenerHabitacionesDisponibles(fecIn, fecSal);
            
            List<Habitacion> citiesSource = new ArrayList<>(habitacionesdisponibles);
            List<Habitacion> citiesTarget = new ArrayList<>();
            
            cities = new DualListModel<Habitacion>(citiesSource, citiesTarget);   
		} catch (Exception e) {
			e.getMessage();
		}    	     
    }

    public void INICIALIZACION() {
        costo = 0.0;
        motivo = "";
        startm = "";
        endm = "";
        reserva = new TReservadetalle();
        reserva.setTHabitacion(new THabitacion());
        reserva.setTReserva(new TReserva());

        reserv = new Reserva();
        reserv.setObjCliente(new Cliente());
        cli = new Cliente();
        cli.setObjPersona(new Persona());
        rutaImagen="/images/bed/blanco.png";
    }

    public void CAMBIO() throws Exception {
        max = reserv.getFechaEntrada();
        Calendar cal = new GregorianCalendar();
        cal.setTime(max);
        cal.add(Calendar.DATE, 1);

        System.out.println(cal.getTime());
        reserv.setFechaSalida(cal.getTime());
        BUSQUEDA2(reserv.getFechaEntrada(), reserv.getFechaSalida());
    }

    public void createTimeline() {
        model = new TimelineModel();
    }

    public void HOSPEDAR() throws Exception {
        reserva1.setEstado(EstadoReservaTipo.HOSPEDADO.getNombre());
        if (dao.SP_MoficiarReserva(1, reserva1)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se hospedo "));

            llenar();
            INICIALIZACION();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ocurrio un Error"));
        }
    }

    public void onAdd(TimelineAddEvent e) {

//        if(e.getStartDate().before(new Date()))
//        {
//            llenar();
//            System.out.println("Ex menor");
//        }else
//        {
//            
        reserv.setFechaEntrada(e.getStartDate());
        //model.add(event); 
        System.out.println("Es mayor");
        // }

    }

    public void onEdit(TimelineSelectEvent e) {
        System.out.println("Editando...");
        event = e.getTimelineEvent();
        reserva1 = (TimelineReserva) event.getData();
        if(reserva1.getEstado().equals("pre-reserva-cv")){
            reserva1.setEstado("pre-reserva");
        }
        /*reserva =((TReservadetalle)event.getData());
           startm=reserva.getTReserva().getFechaEntrada().getDate()+"/"+(reserva.getTReserva().getFechaEntrada().getMonth()+1)+"/"+(reserva.getTReserva().getFechaEntrada().getYear()+1900);
           endm=reserva.getTReserva().getFechaSalida().getDate()+"/"+(reserva.getTReserva().getFechaSalida().getMonth()+1)+"/"+(reserva.getTReserva().getFechaSalida().getYear()+1900);
         */
        ReservaDao rdao = new ReservaDao();
        //list= rdao.listarNumeroCuartos(reserva.getTReserva().getIdReserva());
        list = rdao.listarNumeroCuartos(reserva1.getIdReserva());
    }

    public void onDelete(TimelineModificationEvent e) {
        // get clone of the TimelineEvent to be deleted  
        event = e.getTimelineEvent();

        /* reserva =((TReservadetalle)event.getData());
        reserv = reserva.getTReserva();*/
        reserva1 = (TimelineReserva) event.getData();
    }

    public void onChange(TimelineModificationEvent e) {
        System.out.println("Modificacion");
        event = e.getTimelineEvent();
        /* reserva =((TReservadetalle)event.getData());
        reserv = reserva.getTReserva();
         */
        reserva1 = (TimelineReserva) event.getData();
        start = event.getStartDate();
        end = event.getEndDate();
//        FacesMessage msg =  
//            new FacesMessage(FacesMessage.SEVERITY_INFO, "The booking dates " + cuarto.getCuarto() + " have been updated", null);  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  

    }

    public void onDrop(TimelineDragDropEvent e) {
        System.out.println("PRUEBA...");

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Probando drop", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void REPROGRAMAR() throws Exception {
        reserva1.setDescripcion(motivo);
        reserva1.setFecha_entrada(start);
        reserva1.setFecha_salida(end);
        if (dao.SP_MoficiarReserva(2, reserva1)) {
            model = new TimelineModel();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se modifico la reserva "));
            llenar();
            INICIALIZACION();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia",  "Hubo un error en la modificación"));
        }

    }

    public void CANCEL() throws Exception {
        reserva1.setDescripcion(motivo);
        reserva1.setEstado("cancelado");
        if (dao.SP_MoficiarReserva(3, reserva1)) {
            model = new TimelineModel();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se canceló la reserva "));
            model = new TimelineModel();
            llenar();
            INICIALIZACION();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia",  "Hubo un error en la cancelación"));
        }
    }

    public void registrarprereserva() throws Exception {
		Persona objPersona = ((Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("persona"));    	
    	Cliente objCliente = usuarioService.buscarClienteIdPersona(objPersona.getIdPersona());
    	objCliente.setObjPersona(objPersona);
    	        
        reserv.setEstado("pre-reserva");
        reserv.setFechaRegistro(new Date());
        reserv.setModalidadPago("Deposito");
        reserv.setUsuario(((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getNombreUsuario());
        reserv.setIdCliente(objCliente.getIdCliente());
        reserv.setPrecio(costoTotal);
        reserv.setLstHabitacion(cities.getTarget());
        reserv.setObjCliente(objCliente);
        reservaService.registrarReserva(reserv);
        INICIALIZACION();
    }

    public void registrarreserva() throws Exception {
//        ClienteDao clidao = new ClienteDao();
//        String valor = clidao.buscarCliente(((Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona")).getIdPersona()).getIdCliente();
//
//        reserv.setEstado("reservado");
//        reserv.setFechaRegistro(new Date());
//        reserv.setModalidadPago("Deposito");
//        reserv.setUsuario(((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getNombreUsuario());
//        reserv.setIdCliente(valor);
//        reserv.setPrecio(costoTotal);
//        
//        reservaService.registrarReserva(reserv);
    }

    public void Actualizar() throws Exception {
        habitacionesdisponibles = reservaService.obtenerHabitacionesDisponibles(fecIn, fecSal);
        
        List<Habitacion> citiesSource = new ArrayList<>(habitacionesdisponibles);
        List<Habitacion> citiesTarget = new ArrayList<>();
        
        cities = new DualListModel<Habitacion>(citiesSource, citiesTarget);
    }

    public void BUSQUEDA2(Date fecE, Date fecS) throws Exception {
        fecIn = (fecE.getYear() + 1900) + "/" + (fecE.getMonth() + 1) + "/" + fecE.getDate();
        fecSal = (fecS.getYear() + 1900) + "/" + (fecS.getMonth() + 1) + "/" + fecS.getDate();
        Actualizar();
        System.out.println("Fecha Entrada : " + fecE.getDate() + "/" + (fecE.getMonth() + 1) + "/" + (fecE.getYear() + 1900));
        System.out.println("Fecha Salida : " + fecS.getDate() + "/" + (fecS.getMonth() + 1) + "/" + (fecS.getYear() + 1900));
        System.out.println(habitacionesdisponibles.size());
        nombre();
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Habitacion) item).getNroHabitacion()).append("<br />");
        }
        costo = 0.0;
        reserv.setSubtotal(costo);
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        dia = (reserv.getFechaSalida().getTime() - reserv.getFechaEntrada().getTime()) / MILLSECS_PER_DAY;
        for (int i = 0; i < cities.getTarget().size(); i++) {
            costo = costo + cities.getTarget().get(i).getPrecio() * dia * reserv.getCantTotal();

        }
        costo = costo / cities.getTarget().size();
        igv = costo * 0.18;

        costoTotal = costo + igv;
        reserv.setSubtotal(costo);
        reserv.setIgv(igv);
        reserv.setPrecio(costoTotal);
    }
    
    public void onSelect(SelectEvent event) {
        Habitacion t = (Habitacion) event.getObject();
        if("simple".equals(t.getTipoHabNombre())){
            rutaImagen="/images/bed/cama_simple.jpg";
        } else if ("doble".equals(t.getTipoHabNombre())){
            rutaImagen="/images/bed/cama_doble.jpg";
        }else{
            rutaImagen="/images/bed/cama_triple.jpg";
        }
    }
    
    public void cambioImg(){
        rutaImagen="/images/bed/blanco.png";
        System.out.println("RUTA : "+rutaImagen);
    }

    public void nombre() {
    	if(Utils.isNotNull(reserv.getIdCliente())){
    		cli = usuarioService.buscarClienteId(reserv.getIdCliente());
    	}
    }

    public void GUARDARDETALLES() throws Exception {
        reserv.setUsuario(((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getNombreUsuario());
        reserv.setIdCliente(cli.getIdCliente());
        reserv.setObjCliente(cli);
        reserv.setFechaRegistro(new Date());
        
        String fec_reg = (reserv.getFechaRegistro().getYear() + 1900) + "/" + (reserv.getFechaRegistro().getMonth() + 1) + "/" + reserv.getFechaRegistro().getDate();
        String fec_ent = (reserv.getFechaEntrada().getYear() + 1900) + "/" + (reserv.getFechaEntrada().getMonth() + 1) + "/" + reserv.getFechaEntrada().getDate();
        if (fec_reg.equals(fec_ent)) {
            reserv.setEstado(EstadoReservaTipo.HOSPEDADO.getNombre());
        } else if (reserv.getModalidadPago().equals("Efectivo")) {
            reserv.setEstado(EstadoReservaTipo.RESERVADO.getNombre());
        } else {
            reserv.setEstado(EstadoReservaTipo.PRE_RESERVA.getNombre());
        }
        
        reserv.setLstHabitacion(cities.getTarget());
        reservaService.registrarReserva(reserv);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se Registro 1 reserva a nombre de  " + cli.getObjPersona().getNombres()));
        llenar();
        INICIALIZACION();
    }

    public void llenar() throws Exception {// set initial start / end dates for the axis of the timeline  
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date now = new Date();

        // create timeline model  
        model = new TimelineModel();

        cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);
        start = cal.getTime();

        cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 40000);
        end = cal.getTime();
        
        listareservas = reservaService.SP_listareservafiltrosF_EST(tipohab,estado);
        
        for (TimelineDetalleReserva objTDetalleReserva : listareservas) {
        	if(!EstadoReservaTipo.CANCELADO.getNombre().equals(objTDetalleReserva.getEstado())){
        		model.add(new TimelineEvent(objTDetalleReserva.getTimelinereserva(), 
        				objTDetalleReserva.getFecha_entrada(),
        				objTDetalleReserva.getFecha_salida(), 
        				true,
        				objTDetalleReserva.getHabitacion(),
        				objTDetalleReserva.getEstado()));
        	}
			
		}
    }

    public List<TimelineDetalleReserva> getListareservas() {
        return listareservas;
    }

    public void setListareservas(List<TimelineDetalleReserva> listareservas) {
        this.listareservas = listareservas;
    }

    public TimelineModel getModel() {
        return model;
    }

    public void setModel(TimelineModel model) {
        this.model = model;
    }

    public TimelineEvent getEvent() {
        return event;
    }

    public void setEvent(TimelineEvent event) {
        this.event = event;
    }

    public long getZoomMax() {
        return zoomMax;
    }

    public void setZoomMax(long zoomMax) {
        this.zoomMax = zoomMax;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isTimeChangeable() {
        return timeChangeable;
    }

    public void setTimeChangeable(boolean timeChangeable) {
        this.timeChangeable = timeChangeable;
    }

    public TReservadetalle getReserva() {
        return reserva;
    }

    public void setReserva(TReservadetalle reserva) {
        this.reserva = reserva;
    }

    public THabitacion getHab() {
        return hab;
    }

    public void setHab(THabitacion hab) {
        this.hab = hab;
    }

    public int getNrohabitacion() {
        return nrohabitacion;
    }

    public void setNrohabitacion(int nrohabitacion) {
        this.nrohabitacion = nrohabitacion;
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
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public String getTipohab() {
        return tipohab;
    }

    public void setTipohab(String tipohab) {
        this.tipohab = tipohab;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<TReservadetalle> getList() {
        return list;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getStartm() {
        return startm;
    }

    public void setStartm(String startm) {
        this.startm = startm;
    }

    public String getEndm() {
        return endm;
    }

    public void setEndm(String endm) {
        this.endm = endm;
    }

    public TimelineReserva getReserva1() {
        return reserva1;
    }

    public void setReserva1(TimelineReserva reserva1) {
        this.reserva1 = reserva1;
    }

    public void calcularPersonar() {
        reserv.setCantTotal(reserv.getCantA() + reserv.getCantN());
        System.out.println("Cantidad de Personas : " + reserv.getCantTotal());
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	/**
	 * Returns attribute reservaService
	 * @return reservaService <code>ReservaService</code>
	 */
	public ReservaService getReservaService() {
		return reservaService;
	}

	/**
	 * Sets attribute reservaService
	 * @param reservaService <code>ReservaService</code>
	 */
	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	/**
	 * Returns attribute reserv
	 * @return reserv <code>Reserva</code>
	 */
	public Reserva getReserv() {
		return reserv;
	}

	/**
	 * Sets attribute reserv
	 * @param reserv <code>Reserva</code>
	 */
	public void setReserv(Reserva reserv) {
		this.reserv = reserv;
	}

	/**
	 * Returns attribute usuarioService
	 * @return usuarioService <code>UsuarioService</code>
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * Sets attribute usuarioService
	 * @param usuarioService <code>UsuarioService</code>
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * Returns attribute habitacionesdisponibles
	 * @return habitacionesdisponibles <code>List<Habitacion></code>
	 */
	public List<Habitacion> getHabitacionesdisponibles() {
		return habitacionesdisponibles;
	}

	/**
	 * Returns attribute cli
	 * @return cli <code>Cliente</code>
	 */
	public Cliente getCli() {
		return cli;
	}

	/**
	 * Sets attribute cli
	 * @param cli <code>Cliente</code>
	 */
	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	/**
	 * Returns attribute cities
	 * @return cities <code>DualListModel<Habitacion></code>
	 */
	public DualListModel<Habitacion> getCities() {
		return cities;
	}

	/**
	 * Sets attribute cities
	 * @param cities <code>DualListModel<Habitacion></code>
	 */
	public void setCities(DualListModel<Habitacion> cities) {
		this.cities = cities;
	}
	
}
