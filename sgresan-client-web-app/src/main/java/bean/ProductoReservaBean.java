/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.lowagie.text.Image;
import dao.ProductoDao;
import dao.ReservaProductoDao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import model.TPersona;
import model.TProducto;
import model.TReserva;
import model.TResxprod;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ProductoReservaBean {

    private TReserva reserva;
    private TResxprod resxprod;
    
    private int accion;
    private String campo1,campo2,campo3;
    ReservaProductoDao pDao;
    ProductoDao proDao;
    
    private int idCategoria;
    private List<TProducto> listaproductos;
    private List<TResxprod> listaresxprod;
    private int cantidad;
    private double total;
    
    private StreamedContent imagen;
    private byte[] img;
    
    private double sumTotal;
    /**
     * Creates a new instance of ProductoReservaBean
     */
    public ProductoReservaBean() {
        reserva = new TReserva();
        resxprod = new TResxprod();
        resxprod.setTProducto(new TProducto());
        resxprod.setTReserva(new TReserva());
        
        accion=1;sumTotal=0.0;
        pDao= new ReservaProductoDao();
        proDao = new ProductoDao();
    }

    public void DEVOLVER_IMAGEN(TProducto prod){
         if(prod.getImagen()!=null){
             byte[] base64Bytes = Base64.decodeBase64(prod.getImagen());
             imagen =new DefaultStreamedContent(new ByteArrayInputStream(base64Bytes));
         }else{
             System.out.println("ERROR NO HAY BYTES"+prod.getNombreProducto());
         }
         
         
    }
    
     public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
           // Image image = imageService.find(Long.valueOf(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
        }
    }
    
    public void LIMPIAR_RB(){
        reserva = new TReserva();
campo1="";
campo2="";
campo3="";
    }
    
    public void BUSCAR(){
       reserva= pDao.SP_BUSCAR_RESERVA(accion, campo1, campo2, campo3);
        System.out.println("ID : "+reserva.getIdReserva()+"|"+reserva.getFechaEntrada()+"|"+reserva.getDescripcion());
    
        if(reserva.getIdReserva()!=null){
            listaresxprod= pDao.listarProductosByReserva(reserva.getIdReserva());
            SUMA();
        }
    
    }
    
    public void SUMA(){
        int tam =listaresxprod.size();
        sumTotal=0.0;
        if(tam!=0){
            for (int i = 0; i < tam; i++) {
                sumTotal=sumTotal+listaresxprod.get(i).getTotal();
                System.out.println("MONTO ACTUAL : "+listaresxprod.get(i).getTotal()  + " || MONTO ACUMULADO : "+sumTotal);
            }
        }
    }
    
    public void OBTENER_CANTIDAD(){
        System.out.println("CANT : "+ cantidad);
    }
    public void BUSCAR_PRODUCTO(){
        listaproductos=proDao.listarProductosByCategoria(idCategoria);
   
    }

    public void AGREGAR_PRODUCTO_RESERVA(TProducto prod){
        cantidad=prod.getStock();
        total=prod.getPrecioUnitario()*cantidad;
        resxprod.setTReserva(reserva);
        resxprod.setTProducto(prod);
        resxprod.setFecha(new Date());
        resxprod.setCantidad(cantidad);
        resxprod.setTotal(total);
        
        pDao.InsetartProducto(resxprod);
        
        listaresxprod= pDao.listarProductosByReserva(reserva.getIdReserva());
        SUMA();
        resxprod = new TResxprod();
        resxprod.setTProducto(new TProducto());
        resxprod.setTReserva(new TReserva());
    }
    
    public List<TProducto> getListaproductos() {
        return listaproductos;
    }
    
    
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public TReserva getReserva() {
        return reserva;
    }

    public void setReserva(TReserva reserva) {
        this.reserva = reserva;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TResxprod getResxprod() {
        return resxprod;
    }

    public void setResxprod(TResxprod resxprod) {
        this.resxprod = resxprod;
    }

    public List<TResxprod> getListaresxprod() {
        return listaresxprod;
    }

    public StreamedContent getImagen() {
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(double sumTotal) {
        this.sumTotal = sumTotal;
    }

    
    
    
    
}
