/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import dao.ReservaDao;
import model.TReserva;
import model.TReservalog;
import net.sf.jasperreports.engine.JasperRunManager;
import vista.Conexion;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ReservaBean {

    private TReserva reserva;
    private List<TReserva> estadodereservas;
    private List<TReserva> reservas;
    private List<TReserva> soloreserva;
    private List<TReservalog> listareservaslog;
    
    public ArrayList<String> lista ;
    
    public int nrohabitacion;
    public String sino;
    public boolean editable;
    /**
     * Creates a new instance of ReservaBean
     */
    public ReservaBean() {
       nrohabitacion=1;
       createLista(nrohabitacion);
       editable=true;
       sino="";
       
       reserva = new TReserva();
    }
   
    
    public void createLista(int n)
    {
 
        lista= new ArrayList<String>();
        for(int i=0;i<n;i++)
        {
            lista.add("dobles");
        } 
    }
    
   
    public void HabilitarCuartos(String n)
    {
        sino=n;
        if(sino.equals("si"))
        {
            editable=false;
            nrohabitacion=2;
           
            
        }else
        {
            editable=true;
            nrohabitacion=1;
        }
         ElegirNroCuarto(nrohabitacion);
        //editable = !sino.equals("si");
    }
 
 
    public void ElegirNroCuarto(int n)
    {
        
         
        nrohabitacion=n;
         if(n==1){sino="no";}else{sino="si";}
         createLista(n);
       // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista", lista);
        System.out.println("Tamaño : " + lista.size());
        System.out.println("Nro : " + nrohabitacion); 
    }
    
    
    
    
    public void METODO()
    {
        
        
//        System.out.println("Tamaño : " + lista.size());
//        System.out.println("Nro : " + nrohabitacion); 
        for(int i=0;i<nrohabitacion;i++)
        {
            System.out.println(lista.get(i));
        }
    }
    
    
    public ArrayList<String> getLista() {
//        lista= new ArrayList<String>();
//        for(int i=0;i<nrohabitacion;i++)
//        {
//            lista.add("dobles");
//        } 
         
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public int getNrohabitacion() {
        return nrohabitacion;
    }

    public void setNrohabitacion(int nrohabitacion) {
        this.nrohabitacion = nrohabitacion;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }
    
     public TReserva getReserva() {
        return reserva;
    }

    public void setReserva(TReserva reserva) {
        this.reserva = reserva;
    }

    public List<TReserva> getEstadodereservas() {
        ReservaDao dao = new ReservaDao();
        estadodereservas=dao.listarestadoreserva();
        
        return estadodereservas;
    }

    public List<TReserva> getReservas() {
        ReservaDao dao = new ReservaDao();
        reservas=dao.listareserva(sino);
        return reservas;
    }

    public List<TReserva> getSoloreserva() {
        ReservaDao dao = new ReservaDao();
        soloreserva  = dao.listarsoloreserva();
        return soloreserva;
    }
    
    public void EXPORTAR(String id) 
    {
        Conexion conn = new  Conexion();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
                
        ServletContext  context = (ServletContext) ec.getContext();
        String tplPath = context.getRealPath("/reporte/reporte.jrxml");
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
//        File reportfile = new File(context.getRealPath("/reporte/reporte.jasper"));
               
         //   OutputStream os = null;
        try
        {
           // os= response.getOutputStream();
            conn.conectar();
            
            Map parametros = new HashMap();
            parametros.put("id", id);
//            JasperReport reporteJasper = JasperCompileManager.compileReport(tplPath);
////            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, parametros,conn.getConn());
//////            String pdfName = "/reporte.pdf";
//////            String pdfPath = ec.getRealPath(pdfName);
//////             JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
////             System.out.println("PDF ready!");
             
 File reportfile = new File(context.getRealPath("/reporte/reporte.jasper"));
  byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parametros, conn.getConn());   
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseContentLength(bytes.length);
                ec.setResponseHeader("Content-Disposition","inline;filename=\"reporte.pdf\"");
                OutputStream output = ec.getResponseOutputStream();
                output.write(bytes, 0, bytes.length);
                fc.responseComplete();
  
//             ec.responseReset(); 
//        ec.setResponseContentType(ec.getMimeType(pdfPath)); 
//        //ec.setResponseContentLength(contentLength); 
//        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + pdfName + "\""); 
//       InputStream input = new FileInputStream(pdfPath);
//        OutputStream output = ec.getResponseOutputStream();
//        IOUtils.copy(input, output);
 
        System.out.println("Sending to browser...");

//        fc.responseComplete();  
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
        }

    }
    
    public void LLENAR()
    {
        getReservas();
    }

    public List<TReservalog> getListareservaslog() {
        ReservaDao dao = new ReservaDao();
        listareservaslog=dao.listareservalog();
        return listareservaslog;
    }
    
}
