package hu.neuron.java.service.impl;

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.service.UserService;
import hu.neuron.java.service.converter.RoleConverter;
import hu.neuron.java.service.converter.UserConverter;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	public UserServiceImpl() {
	}

	@Override
	public UserVO findUserByName(String name) throws Exception {
		UserVO vo = UserConverter.toVO(userDao.findUserByName(name));
		return vo;

	}

	@Override
	public UserVO setUpRoles(UserVO vo) throws Exception {
		List<Role> roles;
		try {
			roles = roleDao.findRolesByUserId(vo.getId());
			vo.setRoles(RoleConverter.toVO(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {

		User user = userDao.save(UserConverter.toEntity(userVO));
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
				ret.add(UserConverter.toVO(m));
			
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

		return RoleConverter.toVO(roleDao.findAll());
	}

	@Override
	public RoleVO getRoleByName(String role) {
		RoleVO vo = null;
		try {
			vo = RoleConverter.toVO(roleDao.findRoleByName(role));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void saveUser(UserVO selectedUser) {
		
		userDao.save(UserConverter.toEntity(selectedUser));

	}

}
