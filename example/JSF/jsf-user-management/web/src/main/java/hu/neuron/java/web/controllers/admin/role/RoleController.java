package hu.neuron.java.web.controllers.admin.role;

import java.io.Serializable;

import hu.neuron.java.service.UserService;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "roleController")
public class RoleController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private RoleVO selectedRole;

	private String newRoleName;

	private String updateRoleName;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private LazyRoleModel lazyRoleModel;

	@PostConstruct
	public void init() {
		setLazyRoleModel(new LazyRoleModel(getUserService()));
	}

	public void saveNewRole() {
		RoleVO roleVO = new RoleVO();
		roleVO.setName(newRoleName);
		userService.saveRole(roleVO);

	}

	public void onRowSelect(SelectEvent event) {
		selectedRole = (RoleVO) event.getObject();
		updateRoleName = selectedRole.getName();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedRole.getName()));
	}

	public void removeRole() {
		try {
			userService.removeRole(selectedRole);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Deleted: " + selectedRole.getName()));
			selectedRole = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Deleted: "));
		}
	}

	public void updateRole() {
		try {
			selectedRole.setName(updateRoleName);
			userService.saveRole(selectedRole);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedRole.getName()));
			selectedRole = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
	}

	public LazyRoleModel getLazyRoleModel() {
		return lazyRoleModel;
	}

	public void setLazyRoleModel(LazyRoleModel lazyRoleModel) {
		this.lazyRoleModel = lazyRoleModel;
	}

	public RoleVO getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(RoleVO selectedRole) {
		this.selectedRole = selectedRole;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getNewRoleName() {
		return newRoleName;
	}

	public void setNewRoleName(String newRoleName) {
		this.newRoleName = newRoleName;
	}

	public String getUpdateRoleName() {
		return updateRoleName;
	}

	public void setUpdateRoleName(String updateRoleName) {
		this.updateRoleName = updateRoleName;
	}
}
