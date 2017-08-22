package prueba;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.sgresan.controller.LoginBean;
import pe.com.sgresan.service.ParametroService;

public class PruebaConfig {	
	
	@SuppressWarnings("resource")
	@Test
	public void pruebaMapeo(){
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
			ParametroService parametroService = (ParametroService) cxt.getBean("parametroService");//cxt.getBeanDefinitionNames();
			parametroService.example();
			System.out.println(parametroService);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
