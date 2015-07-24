package hu.neuron.java.service.impl;

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.service.UserServiceLocal;
import hu.neuron.java.service.UserServiceRemote;
import hu.neuron.java.service.converter.RoleConverter;
import hu.neuron.java.service.converter.UserConverter;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless
@Local(UserServiceLocal.class)
@Remote(UserServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserServiceImpl implements UserServiceLocal, UserServiceRemote,
		Serializable {

	private static final long serialVersionUID = 7081140322696230547L;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@EJB
	RoleConverter roleConverter;

	@EJB
	UserConverter userConverter;

	public UserServiceImpl() {
	}

	@Override
	public UserVO findUserByName(String name) throws Exception {
		UserVO vo = userConverter.toVO(userDao.findUserByName(name));
		return vo;

	}

	@Override
	public UserVO setUpRoles(UserVO vo) throws Exception {
		List<Role> roles;
		try {
			roles = roleDao.findRolesByUserId(vo.getId());
			vo.setRoles(roleConverter.toVO(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {

		User user = userDao.save(userConverter.toEntity(userVO));
		Role userRole = roleDao.findRoleByName("ROLE_USER");
		roleDao.addRoleToUser(userRole.getId(), user.getId());
	}

	@Override
	public List<UserVO> getUserList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		List<UserVO> ret = new ArrayList<UserVO>(size);
		Page<User> entities;

		if (filter.length() != 0 && filterColumnName.equals("userName")) {
			entities = userDao.findByUserNameStartsWith(filter, pageRequest);
		} else {
			entities = userDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<User> contents = entities.getContent();
			for (User m : contents) {
				ret.add(userConverter.toVO(m));

			}
		}
		return ret;
	}

	@Override
	public int getRowNumber() {

		return (int) userDao.count();
	}

	@Override
	public List<RoleVO> getRoles() {

		return roleConverter.toVO(roleDao.findAll());
	}

	@Override
	public RoleVO getRoleByName(String role) {
		RoleVO vo = null;
		try {
			vo = roleConverter.toVO(roleDao.findRoleByName(role));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void saveUser(UserVO selectedUser) {

		userDao.save(userConverter.toEntity(selectedUser));

	}

	@Override
	public void saveRole(RoleVO roleVO) {
		roleDao.save(roleConverter.toEntity(roleVO));
	}

	@Override
	public void updateRole(RoleVO roleVO) {
		roleDao.save(roleConverter.toEntity(roleVO));

	}

	@Override
	public void removeRole(RoleVO roleVO) {
		roleDao.delete(roleVO.getId());

	}

	@Override
	public List<RoleVO> getRoles(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {

		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Role> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = roleDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = roleDao.findAll(pageRequest);
		}

		List<RoleVO> ret = roleConverter.toVO(entities.getContent());

		return ret;

	}

	@Override
	public int getRoleCount() {

		return (int) roleDao.count();
	}

}
