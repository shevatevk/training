package hu.neuron.java.web.webservice;

import hu.neuron.java.service.facade.UserFacadeRemote;
import hu.neuron.java.service.vo.UserVO;
import hu.neuron.java.service.webservice.UserListWebServiceVo;
import hu.neuron.java.service.webservice.UserWebService;
import hu.neuron.java.service.webservice.UserWebServiceVo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Session Bean implementation class UserServiceBean
 */
@Component
@WebService(name = "UserWebServiceWebPort", serviceName = "UserWebServiceInWeb", targetNamespace = "http://hu.neuron")
public class UserWebServiceImpl extends SpringBeanAutowiringSupport {
	private static final Logger logger = Logger
			.getLogger(UserWebServiceImpl.class);
	@EJB(name = "UserFacade", mappedName = "UserFacade")
	private UserFacadeRemote userFacadeRemote;

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public UserListWebServiceVo getUserListWebMethod() {
		logger.info("call UserWebServiceImpl");

		List<UserVO> userVOs = userFacadeRemote.getUserList();
		UserListWebServiceVo rv = new UserListWebServiceVo();
		rv.setList(new ArrayList<UserWebServiceVo>());
		for (UserVO userVO : userVOs) {
			rv.getList().add(mapper.map(userVO, UserWebServiceVo.class));
		}

		return rv;
	}
}
