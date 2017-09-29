/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CajaDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.TCaja;
import model.TTrabajador;
import model.TUsuario;
import pe.com.sgresan.entidad.Caja;
import pe.com.sgresan.model.Usuario;
import pe.com.sgresan.service.ConsultaService;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class CajaBean {

	@ManagedProperty(value = ConsultaService.EL_NAME)
	private ConsultaService consultaService;
	
    private TCaja caja;
    private List<TCaja> listacaja;
    
    private List<Caja> listaRCaja;
    private Date fec;
    
 private double saldoR;
 private double arqueo;
 public boolean AC;
 public boolean CC;
    /**
     * Creates a new instance of CajaBean
     */
    public CajaBean() {
        caja = new TCaja();
        caja.setTTrabajador(new TTrabajador());
        saldoR=0.0;arqueo=0.0;
        fec=new Date(); 
        /*try{
        	listaRCaja=consultaService.obtenerDataCaja(new Date());
            
        }catch(Exception e){
        	System.out.println("Error controlado"+ e.getMessage());
        }*/
        COMPROBAR();
   
        
    }

    public void COMPROBAR()
    {
       
        CajaDao dao = new CajaDao();
        int valorAp=dao.PK("Apertura de Caja", "00:00:00");
        int valorCi=dao.PK("Cierre de Caja", "00:00:00");
        
        if(valorAp==0)
        {
            AC = true;
            CC=false;
        }else
        {
            if (valorAp == 1)
            {
                if(valorCi == 0)
                {
                    AC = false;
                    CC=true;
                }else
                {
                    AC = true;
                     CC=false;
                }
            }else
            {
           String d = dao.fech().getHours() + ":" + dao.fech().getMinutes() + ":" + dao.fech().getSeconds();

                if(dao.PK("Cierre de Caja", d) == 0)
                {
                    AC = false;
                    CC=true;
                }else
                {
                    AC = true;
                     CC=false;
                }
            }
            
        }
        System.out.println("Abrir Caja : "+AC+"\n Cerrar Caja : "+CC);
 
    }

    public boolean isCC() {
        return CC;
    }

    public void setCC(boolean CC) {
        this.CC = CC;
    }

  
    
    public boolean isAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }
    
    public TCaja getCaja() {
        return caja;
    }

    public void setCaja(TCaja caja) {
        this.caja = caja;
    }
 
    public void BUSCAR(){
    	System.out.println("FECHA BSUQUEDA : "+fec);
    	try {
			listaRCaja=consultaService.obtenerDataCaja(fec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void APERTURAR()
    {   CajaDao dao = new CajaDao();
        caja.setDescripcion("Apertura de Caja");
        caja.setSaldo(dao.Saldo());
        COMPROBAR();
    }
    
    public void CERRAR()
    {   CajaDao dao = new CajaDao();
        caja.setDescripcion("Cierre de Caja");
        caja.setSaldo(dao.Saldo());
        System.out.println(caja.getDescripcion()+"\n"+caja.getSaldo());
 
    }
    
    public void CIERRE()
    {
        System.out.println(saldoR);
        arqueo = saldoR-caja.getSaldo();
        caja.setArqueo(arqueo);
    }
    
    public void INSERTAR()
    {
        CajaDao dao = new CajaDao();
        TTrabajador re = new TTrabajador();
        re.setIdRecepcionista(((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getNombreUsuario());
        caja.setTTrabajador(re);
        dao.InsetartReserva(caja);
        COMPROBAR();
        LIMPIAR();
    }
    public void LIMPIAR()
    {
        caja= new TCaja();
        caja.setTTrabajador(new TTrabajador());
    }
    public List<TCaja> getListacaja() {
        CajaDao dao = new CajaDao();
        listacaja = dao.listarHoy();
        return listacaja;
    }

    public double getSaldoR() {
        return saldoR;
    }

    public void setSaldoR(double saldoR) {
        this.saldoR = saldoR;
    }

    public double getArqueo() {
        return arqueo;
    }

    public void setArqueo(double arqueo) {
        this.arqueo = arqueo;
    }

	public ConsultaService getConsultaService() {
		return consultaService;
	}

	public void setConsultaService(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}

	public List<Caja> getListaRCaja() throws Exception {
		listaRCaja=consultaService.obtenerDataCaja(fec);
		return listaRCaja;
	}

	public void setListaRCaja(List<Caja> listaRCaja) {
		this.listaRCaja = listaRCaja;
	}

	public Date getFec() {
		return fec;
	}

	public void setFec(Date fec) {
		this.fec = fec;
	}

 
   
    
    
}
