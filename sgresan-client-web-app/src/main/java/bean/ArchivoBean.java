/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ArchivoDao;
import dao.ClienteDao;
import dao.ReservaDao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.TCliente;
import model.TPersona;
import model.TReserva;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.email;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ArchivoBean {
private UploadedFile file;
private TReserva reserva;
private List<TReserva> reservas;
private List<TReserva> reservasALL;
 private StreamedContent imagem;
    /**
     * Creates a new instance of ArchivoBean
     */
    public ArchivoBean() {
        reserva = new TReserva();
        reserva.setTCliente(new TCliente());
    }

    public void AGREGAR_BOLETA()
    {
        try
        {
//            System.out.println(reserva.getIdReserva());
//         ArchivoDao ar = new ArchivoDao();
//      
//          byte[] bytes = file.getContents();
//          reserva.setVoucher(bytes);
//          reserva.setDescripcion(file.getFileName());
//          ar.ModificarReserva(reserva);
           if(file.getContentType().substring(0, 1).equals("i"))
           {
               System.out.println(reserva.getIdReserva());
               ArchivoDao ar = new ArchivoDao();
               //byte[] bytes = event.getFile().getContents();
               byte[] bytes = IOUtils.toByteArray(file.getInputstream());
               reserva.setVoucher(bytes);
               //reserva.setDescripcion(file.getFileName());
               System.out.println(file.getFileName());
               reserva.setEstado("pre-reserva-cv");
               ar.ModificarReserva(reserva);
           }else
           {
               FacesContext context = FacesContext.getCurrentInstance();
         
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia",  "El Archivo que subio no es una imagen") );
      
           }
             
        }
        catch(Exception e)
        {
            System.out.println("Error : "+ e.getMessage());
        }
//        String tip = file.getContentType();
//        dato.setFecha(new java.util.Date());
//        
//        dato.setNombreArchivo(file.getFileName());
//        dato.setArchivo(bytes);
//        dato.setTipoArchivo(tip);
//        obj.Insertar(dato);
// 
//        
//        dato = new Dato();
    }
    
    public String devolverEstado(String str){
        if(str.equals("pre-reserva-cv")){
            return "pre-reserva";
        }else{
            return str;
        }
    }

     public void handleFileUpload(FileUploadEvent event) {
       try
       {
            System.out.println(reserva.getIdReserva());
         ArchivoDao ar = new ArchivoDao();
          //byte[] bytes = event.getFile().getContents();
         byte[] bytes = IOUtils.toByteArray(event.getFile().getInputstream());
          reserva.setVoucher(bytes); 
          reserva.setDescripcion(event.getFile().getFileName());
          ar.ModificarReserva(reserva);
       }
       catch(Exception e)
       {
           
       }
    }
    public void buscar(String id)
    {
        ArchivoDao ar = new ArchivoDao();
        reserva = ar.BuscaporId(id);
        System.out.println(reserva.getIdReserva());
    }
    
    public void ver(String id) throws IOException  
    {
 
     ArchivoDao ar = new ArchivoDao();
        reserva = ar.BuscaporId(id);
               imagem =new DefaultStreamedContent(new ByteArrayInputStream(reserva.getVoucher()));
        System.out.println(reserva.getIdReserva());
//        
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//        
//        ec.responseReset();
//        ec.setResponseContentType("image/jpeg");
//        ec.setResponseContentLength((int)reserva.getVoucher().length);
//        ec.setResponseHeader("Content-Disposition","inline;filename=\""+"est.JPG"+"\"");//attachment
//        
//        OutputStream output = ec.getResponseOutputStream();
//        output.write(reserva.getVoucher(),0, reserva.getVoucher().length);
        //fc.responseComplete();
  
    }
    
    public void ACTUALIZAR(String id)
    {
        TCliente cli = new TCliente();
        ArchivoDao ar = new ArchivoDao();
        reserva = ar.BuscaporId(id);
        reserva.setEstado("reservado"); 
        
        ClienteDao clidao = new ClienteDao();
        email e = new email();
 
         cli = clidao.buscarCliente(reserva.getTCliente().getIdCliente());
        ar.ModificarReserva(reserva);
        
        ReservaDetalleBean rBean = new ReservaDetalleBean();
        rBean.llenar();
        RequestContext.getCurrentInstance().update(":formRecep:timeline");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Proceso Exitoso",  "Se Aprobo la reserva "+reserva.getIdReserva()) );
      
    }
    
    private void HOSPEDAR(String id)
    {
        TCliente cli = new TCliente();
        ArchivoDao ar = new ArchivoDao();
        reserva = ar.BuscaporId(id);
        reserva.setEstado("hospedado");
        
         ClienteDao clidao = new ClienteDao();
        email e = new email();
 
         cli = clidao.buscarCliente(reserva.getTCliente().getIdCliente());
        ar.ModificarReserva(reserva);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Proceso Exitoso",  "Se hospedo ") );
        
    }
    
   public TCliente BUSCAR()
   {
       ArchivoDao dao = new ArchivoDao();
       TPersona pers = (TPersona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("persona");
       
      return dao.buscarcliente(pers.getIdPersona());
   }
    
    public List<TReserva> getReservas() {
        ArchivoDao obj = new ArchivoDao();
        reservas=obj.listareserva(BUSCAR().getIdCliente());
        return reservas;
    }

    public List<TReserva> getReservasALL() {
        ReservaDao dao = new ReservaDao();
        reservasALL = dao.listarestadoreserva();
        return reservasALL;
    }
    
    
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public TReserva getReserva() {
        return reserva;
    }

    public void setReserva(TReserva reserva) {
        this.reserva = reserva;
    }
    
    public StreamedContent getImagem() {
 return imagem;
 }
 
public void setImagem(StreamedContent imagem) {
 this.imagem = imagem;
 }
 
}
