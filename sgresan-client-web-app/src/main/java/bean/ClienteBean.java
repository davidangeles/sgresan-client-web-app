/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.TCliente;

/**
 * pureba  carlos
 * @author Joel
 */
@ManagedBean
@SessionScoped
public class ClienteBean {

    private List<TCliente> clientes;
    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
    }

    public List<TCliente> getClientes() {
        ClienteDao dao = new ClienteDao();
        clientes = dao.listarcliente();
        return clientes;
    }

   
    
}
