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

	public void addMessageInfo(String detalhe) {
		addMessage(FacesMessage.SEVERITY_INFO, detalhe);
	}

	public void addMessageError(String detalhe) {
		addMessage(FacesMessage.SEVERITY_ERROR, detalhe);
	}

	public void addMessageFatal(String detalhe) {
		addMessage(FacesMessage.SEVERITY_FATAL, detalhe);
	}

	public void addMessageWarn(String detalhe) {
		addMessage(FacesMessage.SEVERITY_WARN, detalhe);
	}
	
	public void addMessageInfo(String sumario, String detalhe) {
		addMessage(FacesMessage.SEVERITY_INFO, detalhe, sumario);
	}

	public void addMessageError(String sumario, String detalhe) {
		addMessage(FacesMessage.SEVERITY_ERROR, detalhe, sumario);
	}

	public void addMessageFatal(String sumario, String detalhe) {
		addMessage(FacesMessage.SEVERITY_FATAL, detalhe, sumario);
	}

	public void addMessageWarn(String sumario, String detalhe) {
		addMessage(FacesMessage.SEVERITY_WARN, detalhe, sumario);
	}

	private void addMessage(Severity severityInfo, String detalhe) {
		addMessage(severityInfo, detalhe, null);
	}
	
	
	private void addMessage(Severity severityInfo, String detalhe, String sumario) {
		if(sumario == null){
			sumario = "Error!";
			if (FacesMessage.SEVERITY_WARN == severityInfo) {
				sumario = "Warning!";
			} else if (FacesMessage.SEVERITY_INFO == severityInfo) {
				sumario = "Information:";
			}
		}
		FacesMessage message = new FacesMessage(severityInfo, sumario,detalhe);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
