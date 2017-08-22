package pe.com.sgresan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sgresan.mapper.ParametrodetalleDao;
import pe.com.sgresan.model.ParametroDetalle;


@Service(ParametroService.BEAN_NAME)
public class ParametroService {
	
    public static final String BEAN_NAME = "parametroService";

    public static final String EL_NAME = "#{parametroService}";
	
	@Autowired
    private ParametrodetalleDao parametrodetalleDao;
	
	public void example() throws Exception {
		ParametroDetalle obj = parametrodetalleDao.getParametroDetailPk(1);
		System.out.println(obj);
    }

}
