package hu.neuron.java.web.controllers.admin;

import hu.neuron.java.service.facade.UserFacadeRemote;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "adminBean")
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyUserModel userModel;

	@EJB(name = "UserFacade", mappedName = "UserFacade")
	private UserFacadeRemote userFacade;

	@PostConstruct
	public void init() {
		userModel = new LazyUserModel(userFacade);
	}

	public LazyUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyUserModel userModel) {
		this.userModel = userModel;
	}

	public UserFacadeRemote getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacadeRemote userFacade) {
		this.userFacade = userFacade;
	}

}
