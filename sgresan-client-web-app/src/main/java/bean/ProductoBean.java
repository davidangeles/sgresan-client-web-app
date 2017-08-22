/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProductoDao;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.TCategoria;
import model.TProducto;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ProductoBean {

    private TProducto producto;

    private List<TCategoria> listaCategorias;
    private List<TProducto> listaProductos;

    ProductoDao pDao;
    private UploadedFile file;
    private StreamedContent imagen;

    public ProductoBean() {
        producto = new TProducto();
        producto.setTCategoria(new TCategoria());
        pDao = new ProductoDao();
    }

    public void Inicializar() {
        producto = new TProducto();
        producto.setTCategoria(new TCategoria());
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        System.out.println("FILE :: " + event.getFile().getFileName());
        try {
            byte[] bytes = IOUtils.toByteArray(event.getFile().getInputstream());
            producto.setImagen(Base64.encodeBase64String(bytes));
            imagen = new DefaultStreamedContent(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
        }
    }

    public void SUBIR_IMAGEN(FileUploadEvent event) {
        System.out.println("PRUEBA");
        //System.out.println("ARCHIVO : "+event.getFile().getFileName() );
        /*try{
            if(file.getContentType().substring(0, 1).equals("i"))
           {
      
         
              
               byte[] bytes = IOUtils.toByteArray(file.getInputstream());
               producto.setImagen(bytes);
               System.out.println(file.getFileName());
           }else
           {
               System.out.println("EL ARCHIVO QUE SUBIO ES INCORRECTO");
               FacesContext context = FacesContext.getCurrentInstance();
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia",  "El Archivo que subio no es una imagen") );
      
           }
        }catch(Exception e){
            System.out.println("ERROR : "+e.getMessage());
        }*/
    }

    public void AGREGARPRODUCTO() {

        try {
            System.out.println("FILE : " + file.getContentType());
            if (file.getContentType().substring(0, 1).equals("i")) {

                byte[] bytes = IOUtils.toByteArray(file.getInputstream());
                String strBase64 = Base64.encodeBase64String(bytes);
                producto.setImagen(strBase64);
                System.out.println(file.getFileName());
                pDao.InsetartProducto(producto);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se agrego el producto " + producto.getNombreProducto()));

            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "El Archivo que subio no es una imagen"));

            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }

        Inicializar();
    }

    public void ACTUALIZARPRODUCTO() {
        pDao.ActualizarProducto(producto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se actualizo los datos del producto " + producto.getNombreProducto()));

        Inicializar();
    }

    public void ELIMINARPRODUCTO(int id) {
        BUSCARPRODUCTO(id);
        pDao.EliminarProducto(producto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se elimino el producto " + producto.getNombreProducto()));

        Inicializar();
    }

    public void BUSCARPRODUCTO(int id) {
        producto = pDao.BuscaporIdProducto(id);
        if (producto.getImagen() != null) {
            byte[] base64Bytes = Base64.decodeBase64(producto.getImagen());
            imagen = new DefaultStreamedContent(new ByteArrayInputStream(base64Bytes));
        }
    }

    public List<TCategoria> getListaCategorias() {
        listaCategorias = pDao.listarCategoria();
        return listaCategorias;
    }

    public List<TProducto> getListaProductos() {
        listaProductos = pDao.listarProductos();
        return listaProductos;
    }

    public TProducto getProducto() {
        return producto;
    }

    public void setProducto(TProducto producto) {
        this.producto = producto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImagen() {
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

}
