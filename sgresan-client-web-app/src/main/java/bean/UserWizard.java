/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FlowEvent;

/**
 *
 * @author Joel
 */
@ManagedBean(name = "UserWizard")
@SessionScoped
public class UserWizard {

	private boolean skip;

	private static Logger logger = Logger.getLogger(UserWizard.class.getSimpleName());

	public String onFlowProcess(FlowEvent event) {
		logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());
		logger.log(Level.INFO, "Next step:{0}", event.getNewStep());

		if (event.getNewStep().equals("dormitoriotab")) {
			System.out.println("Aca empieza la busqueda :V");
		}

		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

}
