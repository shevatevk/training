package hu.neuron.java.web.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.neruon.java.service.queue.MessageReciverSyncRemote;
import hu.neruon.java.service.queue.MessageSenderRemote;
import hu.neruon.java.service.queue.MessageSenderSyncRemote;
import hu.neruon.java.service.queue.MessageStoreRemote;
import hu.neruon.java.service.queue.MessageType;
import hu.neruon.java.service.queue.ReciverRemote;
import hu.neuron.java.service.vo.MessageVO;
import hu.neuron.java.service.webservice.GenerateWebService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;

@ManagedBean(name = "genearetResult")
@ViewScoped
public class GenearetResult {

	@EJB(name = "ReciverForGenerate", mappedName = "ReciverForGenerate")
	private ReciverRemote reciverRemote;

	private List<MessageVO> messageVOs;

//	@WebServiceRef(wsdlLocation = "")
//	GenerateWebService generateWebService;

	@PostConstruct
	public void init() {
		messageVOs = new ArrayList<MessageVO>();
	}

	public void showResult() {
		try {
			MessageVO messageVO = reciverRemote.consum();
			messageVOs.add(messageVO);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}

	}

//	public void generate() {
//		generateWebService.generate("10");
//	}

	public List<MessageVO> getMessageVOs() {
		return messageVOs;
	}

	public void setMessageVOs(List<MessageVO> messageVOs) {
		this.messageVOs = messageVOs;
	}

}
