package hu.neuron.java.web.beans;

import hu.neruon.java.service.queue.MessageReciverSyncRemote;
import hu.neruon.java.service.queue.MessageSenderSyncRemote;
import hu.neuron.java.service.vo.MessageVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

@ManagedBean(name = "syncQueue")
@ViewScoped
public class SyncQueue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SyncQueue.class);

	@EJB(mappedName = "MessageSenderSync", name = "MessageSenderSync")
	private MessageSenderSyncRemote messageSenderSyncRemote;

	@EJB(name = "MessageReciverSync", mappedName = "MessageReciverSync")
	private MessageReciverSyncRemote messageReciverSyncRemote;

	private String message;

	private List<MessageVO> messageVOs;

	@PostConstruct
	public void init() {
		messageVOs = new ArrayList<MessageVO>();
	}

	public void sendMessage() {
		try {

			logger.info("sendMessage");

			MessageVO messageVo = new MessageVO();

			messageVo.setMessage(message);
			messageVo.setDate(Calendar.getInstance());
			messageSenderSyncRemote.send(messageVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}

	public void checkMessages() {
		logger.info("checkMessages");
		try {
			messageVOs.add(messageReciverSyncRemote.consum());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MessageVO> getMessageVOs() {
		return messageVOs;
	}

	public void setMessageVOs(List<MessageVO> messageVOs) {
		this.messageVOs = messageVOs;
	}

}
