package mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import session.TbUserSession;


public class MBGeneric implements Serializable {
	
	@EJB
	private TbUserSession userSessionLocal;

	private static final long serialVersionUID = -4704358361982942050L;

	public void addMessageInfo(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message);
	}

	public void addMessageError(String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message);
	}

	public void addMessageFatal(String message) {
		addMessage(FacesMessage.SEVERITY_FATAL, message);
	}

	public void addMessageWarn(String message) {
		addMessage(FacesMessage.SEVERITY_WARN, message);
	}
	
	public void addMessageInfo(String sumary, String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message, sumary);
	}

	public void addMessageError(String sumary, String message) {
		addMessage(FacesMessage.SEVERITY_ERROR, message, sumary);
	}

	public void addMessageFatal(String sumary, String message) {
		addMessage(FacesMessage.SEVERITY_FATAL, message, sumary);
	}

	public void addMessageWarn(String sumary, String message) {
		addMessage(FacesMessage.SEVERITY_WARN, message, sumary);
	}

	private void addMessage(Severity severityInfo, String message) {
		addMessage(severityInfo, message, null);
	}
	
	
	private void addMessage(Severity severityInfo, String message, String sumary) {
		if(sumary == null){
			sumary = "Error!";
			if (FacesMessage.SEVERITY_WARN == severityInfo) {
				sumary = "Warning!";
			} else if (FacesMessage.SEVERITY_INFO == severityInfo) {
				sumary = "Information:";
			}
		}
		FacesMessage facesMessage = new FacesMessage(severityInfo, sumary, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
