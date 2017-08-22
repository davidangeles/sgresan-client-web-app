/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import dao.PersonaDao;
import dao.UsuarioDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.TCliente;
import model.TPersona;
import model.TUbigeo;
import model.TUsuario;
import pe.com.sgresan.common.Utils;

import org.primefaces.context.RequestContext;
import util.ValidacionCliente;

/**
 *
 * @author Monica
 */
@ManagedBean
@SessionScoped

public class PersonaBean {
    
    private TPersona persona;
    private TUsuario usuario;
    private TCliente cliente;
    private List<TUbigeo> listarUbigeoSel;
    private List<TCliente> listaclientes;
    private DataModel listapersona;
    private boolean esEdicion;
    
    PersonaDao dao  = new PersonaDao();
    
    @PostConstruct
    public void init() {
        usuario = new TUsuario();
        persona = new TPersona();
        persona.setTUbigeo(new TUbigeo());
        cliente = new TCliente();
        cliente.setTPersona(new TPersona());
    }
    
    public void LIMPIAR()
    {
        usuario = new TUsuario();
        persona = new TPersona();
        persona.setTUbigeo(new TUbigeo());
        cliente = new TCliente();
        cliente.setTPersona(new TPersona());
    }
    
    
//    public boolean esNombreValido()
//    {
//        boolean resultado = true;
//        if(usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el nombre del usuario"));
//            resultado = false;
//        }
//        
//        if(persona.getNombres() == null || persona.getNombres().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el nombre de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getApellidoP()== null || persona.getApellidoP().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el apellido paterno de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getApellidoM()== null || persona.getApellidoM().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el apellido materno de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getDni() == null || persona.getDni().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el DNI de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getDireccion() == null || persona.getDireccion().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar la direcciÃ³n de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getTelefono()== null || persona.getTelefono().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el telÃ©fono de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getCelular()== null || persona.getCelular().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el celular de la persona"));
//            resultado = false;
//        }
//        
//        if(persona.getEmail()== null || persona.getEmail().trim().length() == 0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar el correo de la persona"));
//            resultado = false;
//        }
//        
//      if(persona.getNombres() == "[^A-Za-zÃ±Ã‘Ã¡Ã©Ã­Ã³ÃºÃ¼Ã�Ã‰Ã�Ã“Ãš \\- ]"){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar sÃ³lo letras"));
//            resultado = false;
//      }
//        
//        return resultado;
//    }
       public boolean esVistaValida(){
        boolean resultado = true;
        if(!esNombreValido()){
            resultado = false;
        }
        else if(!esApellidoPaternoValido()){
            resultado = false;
        }
        else if(!esApellidoMaternoValido()){
            resultado = false;
        }
        else if(!esDNIValido()){
            resultado = false;
        }
        else if(!esDireccionValida()){
            resultado = false;
        }
        else if(!esCelularValido()){
            resultado = false;
        }
        else if(!esCorreoValido()){
            resultado = false;
        }
        return resultado;
    }

    public boolean esNombreValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getNombres())){
            resultado = mensaje("Debe ingresar el nombre de la persona");
        }
        else if(Utils.esSoloLetras(persona.getNombres())){
            resultado = mensaje("Debe ingresar sÃ³lo letras en el campo Nombre");
        }
        return resultado;
    }
    
    public boolean esApellidoPaternoValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getApellidoP())){
            resultado = mensaje("Debe ingresar el apellido paterno de la persona");
        }
        else if(Utils.esSoloLetras(persona.getApellidoP())){
            resultado = mensaje("Debe ingresar sÃ³lo letras en el campo Apellido Paterno");
        }
        return resultado;
    }
    
    public boolean esApellidoMaternoValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getApellidoM())){
            resultado = mensaje("Debe ingresar el apellido materno de la persona");
        }
        else if(Utils.esSoloLetras(persona.getApellidoM())){
            resultado = mensaje("Debe ingresar sÃ³lo letras en el campo Apellido Materno");
        }
        return resultado;
    }
    
    public boolean esDNIValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getDni())){
            resultado = mensaje("Debe ingresar el DNI de la persona");
        }
        else if(Utils.validaCantidad(persona.getDni(), 8)){
            resultado = mensaje("Debe ingresar 8 dÃ­gitos en el campo DNI");
        }
        else if(Utils.esSoloNumero(persona.getDni())){
            resultado = mensaje("Debe ingresar sÃ³lo nÃºmeros en el campo DNI");
        }
        
        return resultado;
    }
    
    public boolean esDireccionValida(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getDireccion())){
            resultado = mensaje("Debe ingresar la direcciÃ³n de la persona");
        }
        else if(Utils.esAlfaNumerico(persona.getDireccion())){
            resultado = mensaje("Debe ingresar alfanumÃ©ricos el campo DirecciÃ³n");
        }
        return resultado;
    }
    
    public boolean esCelularValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getCelular())){
            resultado = mensaje("Debe ingresar el celular de la persona");
        }
        else if(Utils.validaCantidad(persona.getCelular(), 9)){
            resultado = mensaje("Debe ingresar 9 dÃ­gitos en el campo Celular");
        }
        else if(Utils.esSoloNumero(persona.getCelular())){
            resultado = mensaje("Debe ingresar sÃ³lo nÃºmeros en el campo Celular");
        }
        
        return resultado;
    }
    
    public boolean esCorreoValido(){
        boolean resultado = true;
        if(Utils.isNullOrEmpty(persona.getEmail())){
            resultado = mensaje("Debe ingresar el correo de la persona");
        }
        else if(Utils.esCorreoValido(persona.getEmail())){
            resultado = mensaje("Debe ingresar el correo");
        }
        
        return resultado;
    }
    
    public boolean mensaje(String p){
        Utils.mensajeError(p);
        return false;
    }
    
    public void irAgregarJuridico()
    {
        RequestContext context = RequestContext.getCurrentInstance(); 
        boolean loggedP =true;
 
         
        UsuarioDao usuariodao = new UsuarioDao();
        usuario.setIdUsuario(usuario.getNombreUsuario());
        usuario.setEstado("Activo");
        usuario.setTipoUsuario("cliente");
        usuariodao.ingresarUsuario(usuario);
       
        
        //persona.setIdPersona(usuario.getNombreUsuario());
        persona.setTUsuario(usuario);
        persona.setEstado("Activo");
        dao.ingresarPersona(persona);
        
       
        
        ClienteDao clientedao = new ClienteDao();
        cliente.setIdCliente(usuario.getIdUsuario());
        cliente.setTPersona(persona);
        clientedao.ingresarCliente(cliente);
        
        
        
        usuario = new TUsuario();
        persona = new TPersona();
        persona.setTUbigeo(new TUbigeo());
        cliente = new TCliente();
        cliente.setTPersona(new TPersona());
        
        context.addCallbackParam("loggedP", loggedP); 
    }
    
    public void irAgregar() {
        try
        {
            ValidacionCliente util = new ValidacionCliente();
            RequestContext context = RequestContext.getCurrentInstance(); 
        boolean loggedP = false;
        
        if(!util.esVistaValida(usuario, persona)){
            loggedP = false;
            return;
        }

        UsuarioDao usuariodao = new UsuarioDao();
        usuario.setIdUsuario(usuario.getNombreUsuario());
        usuario.setEstado("Activo");
        usuario.setTipoUsuario("cliente");
        usuariodao.ingresarUsuario(usuario);
       
        
        //persona.setIdPersona(usuario.getNombreUsuario());
        persona.setTUsuario(usuario);
        persona.setEstado("Activo");
        dao.ingresarPersona(persona);
        
       
        
        ClienteDao clientedao = new ClienteDao();
        cliente.setIdCliente(usuario.getIdUsuario());
        cliente.setTPersona(persona);
        clientedao.ingresarCliente(cliente);
        
        loggedP =true;
        System.out.println(loggedP);
        context.addCallbackParam("loggedP", loggedP);  
        
        usuario = new TUsuario();
        persona = new TPersona();
        persona.setTUbigeo(new TUbigeo());
        cliente = new TCliente();
        cliente.setTPersona(new TPersona());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void irActualizar(String id) {
        PersonaDao obj = new PersonaDao();
//        obj.actualizarPersona(persona);
//        persona = new TPersona();
        persona = obj.buscarPorId(id);
        System.out.println(persona.getNombres());
        System.out.println(persona.getTUbigeo().getNombre());
    }
    
    public void ACTUALIZAR()
    {
        PersonaDao dao = new PersonaDao();
        dao.actualizarPersona(persona);
        
        usuario = new TUsuario();
        persona = new TPersona();
        persona.setTUbigeo(new TUbigeo());
        cliente = new TCliente();
        cliente.setTPersona(new TPersona());
    }
    public DataModel getListapersona() {
         PersonaDao profMgd = new PersonaDao();
        listapersona = new ListDataModel(profMgd.obtenerPersonaTodos());
        return listapersona;
    }

    public void setListapersona(DataModel listapersona) {
        this.listapersona = listapersona;
    }
    
    public void prepararPersona(String id) {
        PersonaDao obj = new PersonaDao();
        persona = obj.buscarPorId(id);
    }

    public TPersona getPersona() {
        return persona;
    }

    public void setPersona(TPersona persona) {
        this.persona = persona;
    }

    public List<TUbigeo> getListarUbigeoSel() {
        PersonaDao objTrb = new PersonaDao();
        listarUbigeoSel = objTrb.listarUbigeo();
        return listarUbigeoSel;
    }

     public void setListarUbigeoSel(List<TUbigeo> listarUbigeoSel) {
        this.listarUbigeoSel = listarUbigeoSel;
    }
    
    public String ingresar() {
        PersonaDao cliMgd = new PersonaDao();
        boolean resultado = isEsEdicion() ? cliMgd.actualizarPersona(persona) 
                            : cliMgd.ingresarPersona(persona);
        if (resultado) {
            return "persona";
        } else {
            return ""; //futuros errores
        }
    }

    public String getAccion() {
         return isEsEdicion() ? "Actualizar" : "Registrar";
    }

    public boolean isEsEdicion() {
        return esEdicion;
    }

    public void setEsEdicion(boolean esEdicion) {
        this.esEdicion = esEdicion;
    }

    public List<TCliente> getListaclientes() {
        ClienteDao dao =  new ClienteDao();
        listaclientes = dao.listarcliente();
        return listaclientes;
    }

    public TUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public TCliente getCliente() {
        return cliente;
    }

    public void setCliente(TCliente cliente) {
        this.cliente = cliente;
    }
   
    
}
