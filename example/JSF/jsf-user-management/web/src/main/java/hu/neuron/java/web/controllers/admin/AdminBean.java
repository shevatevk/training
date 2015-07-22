package hu.neuron.java.web.controllers.admin;

import hu.neuron.java.service.UserService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "adminBean")
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyUserModel userModel;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		userModel = new LazyUserModel(userService);
	}

	public LazyUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyUserModel userModel) {
		this.userModel = userModel;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
