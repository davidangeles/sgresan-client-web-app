/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.sgresan.controller;

import bean.ReservaVoucherBean;
import dao.ReservaDao;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import pe.com.sgresan.common.EstadoReservaTipo;
import pe.com.sgresan.common.Utils;
import pe.com.sgresan.model.Cliente;
import pe.com.sgresan.model.Persona;
import pe.com.sgresan.model.Reserva;
import pe.com.sgresan.service.ReservaService;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ArchivoBean {
	
	@ManagedProperty(value = ReservaService.EL_NAME)
	private ReservaService reservaService;
	
	@ManagedProperty(value = "#{reservaDetalleBean}")
	private ReservaDetalleBean reservaDetalleBean;
	
	private UploadedFile file;
	private Reserva reserva;
	private List<Reserva> reservas;
	private List<Reserva> reservasALL;
	private StreamedContent imagem;
        
        private int accion;

	/**
	 * Creates a new instance of ArchivoBean
	 */
	public ArchivoBean() {
		reserva = new Reserva();
		reserva.setObjCliente(new Cliente());
                accion=1;
	}
	
    @PostConstruct
    public void init() {
    	try {
    		CAMBIARTABLA();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void AGREGAR_BOLETA() {
		try {
			if (file.getContentType().substring(0, 1).equals("i")) {
				Reserva objReserva = new Reserva();
				objReserva.setIdReserva(reserva.getIdReserva());
				byte[] bytes = IOUtils.toByteArray(file.getInputstream());
				objReserva.setVoucher(bytes);
				 
						if(reserva.getEstado().equals("pre-reserva") || reserva.getEstado().equals("pre-reserva-cv")){
							objReserva.setEstado(EstadoReservaTipo.PRE_RESERVA_CV.getNombre());
		               }
				
				reservaService.actualizarReserva(objReserva);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();

				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia",
						"El Archivo que subio no es una imagen"));
			}
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
	}

	public String devolverEstado(String str) {
		if (str.equals("pre-reserva-cv")) {
			return "pre-reserva";
		} else {
			return str;
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			byte[] bytes = IOUtils.toByteArray(event.getFile().getInputstream());
			Reserva objReserva = new Reserva();
			objReserva.setIdReserva(reserva.getIdReserva());
			objReserva.setVoucher(bytes);
			objReserva.setDescripcion(event.getFile().getFileName());
			reservaService.actualizarReserva(objReserva);
		} catch (Exception e) {

		}
	}

	public void buscar(String id) throws Exception {
		reserva = reservaService.buscarReservaId(id);		
		System.out.println(reserva.getIdReserva());
	}

	public void ver(String id) throws Exception {
		reserva = reservaService.buscarReservaId(id);
		imagem = new DefaultStreamedContent(new ByteArrayInputStream(reserva.getVoucher()));
		System.out.println(reserva.getIdReserva());
	}

	public void ACTUALIZAR(String idReserva) throws Exception {
		Reserva objReserva = new Reserva();
		objReserva.setIdReserva(idReserva);
		objReserva.setEstado(EstadoReservaTipo.RESERVADO.getNombre());
		reservaService.actualizarReserva(objReserva);

		reservaDetalleBean.llenar();
		RequestContext.getCurrentInstance().update(":formRecep:timeline");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se Aprobo la reserva " + reserva.getIdReserva()));
		
		CAMBIARTABLA();
	}

	private void HOSPEDAR(String idReserva) throws Exception {
		Reserva objReserva = new Reserva();
		objReserva.setIdReserva(idReserva);
		objReserva.setEstado(EstadoReservaTipo.HOSPEDADO.getNombre());
		reservaService.actualizarReserva(objReserva);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Proceso Exitoso", "Se hospedo "));
	}
        
        public void VisualizarImagen(byte[] img){
        imagem =new DefaultStreamedContent(new ByteArrayInputStream(img));
    }
    
    public void CAMBIARTABLA() throws Exception{
       	
        if(accion==1){
            ReservaDao dao = new ReservaDao();
            reservasALL = reservaService.reservasPorEstado(EstadoReservaTipo.PRE_RESERVA.getNombre());
            reservasALL.addAll(reservaService.reservasPorEstado(EstadoReservaTipo.PRE_RESERVA_CV.getNombre()));
        } else{
            ReservaVoucherBean managedBean = new ReservaVoucherBean();
            managedBean.CargarTabla();
        }
        
    }

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	/**
	 * Returns attribute reservasALL
	 * @return reservasALL <code>List<Reserva></code>
	 * @throws Exception 
	 */
	public List<Reserva> getReservasALL() throws Exception {
		return reservasALL;
	}

	/**
	 * Returns attribute reservas
	 * @return reservas <code>List<Reserva></code>
	 * @throws Exception 
	 */
	public List<Reserva> getReservas() throws Exception {
		if(Utils.isNotNull(reservaService)){
			Persona pers = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona");
			reservas = reservaService.reservasPorPersona_Cliente(pers.getIdPersona(), null);
		}
		return reservas;
	}

	/**
	 * Returns attribute reserva
	 * @return reserva <code>Reserva</code>
	 */
	public Reserva getReserva() {
		return reserva;
	}

	/**
	 * Sets attribute reserva
	 * @param reserva <code>Reserva</code>
	 */
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

	public ReservaDetalleBean getReservaDetalleBean() {
		return reservaDetalleBean;
	}

	public void setReservaDetalleBean(ReservaDetalleBean reservaDetalleBean) {
		this.reservaDetalleBean = reservaDetalleBean;
	}
	
}
