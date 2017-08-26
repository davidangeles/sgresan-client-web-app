package pe.com.sgresan.mapper;

import java.util.List;
import java.util.Map;

import pe.com.sgresan.model.Cliente;

public interface ClienteDao {
	
	Cliente buscaporId(String idCliente);
	
	Cliente buscaporIdPersona(String idPersona);
	
	List<Cliente> buscarTodos();

}
