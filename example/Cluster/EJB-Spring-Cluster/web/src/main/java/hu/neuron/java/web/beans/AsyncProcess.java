package hu.neuron.java.web.beans;

import hu.neuron.java.service.AsyncProcessorRemote;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "asyncProcess")
@SessionScoped
public class AsyncProcess implements Serializable {
	private static final Logger logger = Logger.getLogger(AsyncProcess.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(mappedName = "AsyncProcessorBean", name = "AsyncProcessorBean")
	private AsyncProcessorRemote processorRemote;

	private Integer number;

	private Boolean inProgress = Boolean.FALSE;

	private Integer progress;

	public void start() {
		logger.info("start AsyncProcess");
		progress = 0;
		inProgress = Boolean.TRUE;
		processorRemote.addJob(getNumber());
	}

	public Integer checkProgress() {
		logger.info("chcek AsyncProcess");
		Integer progress = processorRemote.getProgress();
		if (progress == number) {
			inProgress = Boolean.FALSE;

		}

		Double i = (double) ((double) progress / (double) number) * 100;
		setProgress(i.intValue());
		return this.progress;
	}

	public Integer getNumber() {

		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getInProgress() {
		return inProgress;
	}

	public void setInProgress(Boolean inProgress) {
		this.inProgress = inProgress;
	}

	public Integer getProgress() {
		checkProgress();
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

}
