package hu.neuron.java.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.neuron.java.common.service.UserService;
import hu.neuron.java.common.vo.UserVO;
import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dao.UserDAO;
import hu.neuron.java.core.dto.RoleDTO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleDAO roleDAO;

	@Override
	public UserVO findUserByName(String name) throws Exception {
		return userDAO.toVO(userDAO.findUserByName(name));
	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {

		Long userId = userDAO.save(userDAO.toDTO(userVO));
		RoleDTO userRole = roleDAO.findRoleByName("ROLE_USER");
		roleDAO.addRoleToUser(userRole.getId(), userId);
	}

}
