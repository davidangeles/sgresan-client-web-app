package prueba;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.sgresan.service.UtilitarioService;

public class PruebaConfig {	
	
	@SuppressWarnings("resource")
	public void pruebaMapeo(){
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
			UtilitarioService parametroService = (UtilitarioService) cxt.getBean("parametroService");//cxt.getBeanDefinitionNames();
			//parametroService.example();
			System.out.println(parametroService);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
