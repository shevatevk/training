package hu.neuron.java.service.facade.impl;

import hu.neuron.java.service.facade.UserFacadeRemote;
import hu.neuron.java.service.role.RoleServiceRemote;
import hu.neuron.java.service.user.UserServiceRemote;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

@Stateless(name = "UserFacade", mappedName = "UserFacade")
@Remote(UserFacadeRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserFacadeImpl implements UserFacadeRemote, Serializable {

	private static final long serialVersionUID = -1296528259371447796L;

	private static final Logger logger = Logger.getLogger(UserFacadeImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	UserServiceRemote userService;

	@EJB(mappedName = "RoleService", name = "RoleService")
	RoleServiceRemote roleServiceRemote;

	public UserFacadeImpl() {
	}

	@Override
	public UserVO findUserAndRolesByName(String name) throws Exception {
		logger.debug(entityManager);
		UserVO userVO = userService.findUserByName(name);
		if (userVO != null) {
			userVO = roleServiceRemote.setUpRoles(userVO);
		}
		return userVO;
	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {
		userService.registrationUser(userVO);

	}

	@Override
	public UserVO findUserByName(String name) throws Exception {

		return userService.findUserByName(name);
	}

	@Override
	public List<UserVO> getUserList(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) {

		return userService.getUserList(i, pageSize, sortField, dir, filter,
				filterColumnName);
	}

	@Override
	public RoleVO getRoleByName(String role) {

		return userService.getRoleByName(role);
	}

	@Override
	public void saveUser(UserVO selectedUser) {
		userService.saveUser(selectedUser);

	}

	@Override
	public UserVO setUpRoles(UserVO vo) throws Exception {

		return roleServiceRemote.setUpRoles(vo);
	}

	@Override
	public int getRowNumber() {

		return roleServiceRemote.getRowNumber();
	}

	@Override
	public List<RoleVO> getRoles() {

		return roleServiceRemote.getRoles();
	}

	@Override
	public void saveRole(RoleVO roleVO) {
		roleServiceRemote.saveRole(roleVO);

	}

	@Override
	public void updateRole(RoleVO roleVO) {
		roleServiceRemote.updateRole(roleVO);
	}

	@Override
	public void removeRole(RoleVO roleVO) {
		roleServiceRemote.removeRole(roleVO);

	}

	@Override
	public List<RoleVO> getRoles(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) {
		return roleServiceRemote.getRoles(i, pageSize, sortField, dir, filter,
				filterColumnName);
	}

	@Override
	public int getRoleCount() {
		return roleServiceRemote.getRoleCount();
	}

	@Override
	public List<UserVO> getUserList() {
		return userService.getUserList();

	}

}
