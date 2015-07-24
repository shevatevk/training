package hu.neuron.java.service.facade.impl;

import hu.neuron.java.service.UserServiceLocal;
import hu.neuron.java.service.facade.UserFacadeLocal;
import hu.neuron.java.service.facade.UserFacadeRemote;
import hu.neuron.java.service.vo.UserVO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "UserFacade", mappedName = "UserFacade")
@Local(UserFacadeLocal.class)
@Remote(UserFacadeRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserFacadeImpl implements UserFacadeLocal, UserFacadeRemote,
		Serializable {

	@EJB
	UserServiceLocal userService;

	public UserFacadeImpl() {
	}

	@Override
	public UserVO findUserAndRolesByName(String name) throws Exception {
		UserVO userVO = userService.findUserByName(name);
		if (userVO != null) {
			userVO = userService.setUpRoles(userVO);
		}
		return userVO;
	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {
		userService.registrationUser(userVO);

	}

}
