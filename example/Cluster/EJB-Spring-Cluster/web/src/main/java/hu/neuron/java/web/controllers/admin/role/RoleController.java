package hu.neuron.java.web.controllers.admin.role;

import hu.neuron.java.service.facade.UserFacadeRemote;
import hu.neuron.java.service.vo.RoleVO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

	@EJB(name = "UserFacade", mappedName = "UserFacade")
	private UserFacadeRemote userFacade;

	private LazyRoleModel lazyRoleModel;

	@PostConstruct
	public void init() {
		setLazyRoleModel(new LazyRoleModel(userFacade));
	}

	public void saveNewRole() {
		RoleVO roleVO = new RoleVO();
		roleVO.setName(newRoleName);
		userFacade.saveRole(roleVO);

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
			userFacade.removeRole(selectedRole);

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
			userFacade.saveRole(selectedRole);

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

	public UserFacadeRemote getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacadeRemote userFacade) {
		this.userFacade = userFacade;
	}

}
