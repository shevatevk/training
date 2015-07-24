package hu.neuron.java.web.controllers.admin;

import hu.neuron.java.service.UserServiceLocal;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "adminBean")
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyUserModel userModel;

	@EJB(name = "UserService")
	private UserServiceLocal userService;

	@PostConstruct
	public void init() {
		userModel = new LazyUserModel(getUserService());
	}

	public LazyUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyUserModel userModel) {
		this.userModel = userModel;
	}

	public UserServiceLocal getUserService() {
		return userService;
	}

	public void setUserService(UserServiceLocal userService) {
		this.userService = userService;
	}

}
