package hu.neuron.java.service.webservice.impl;

import hu.neuron.java.service.user.UserServiceRemote;
import hu.neuron.java.service.vo.UserVO;
import hu.neuron.java.service.webservice.UserListWebServiceVo;
import hu.neuron.java.service.webservice.UserWebService;
import hu.neuron.java.service.webservice.UserWebServiceVo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Session Bean implementation class UserServiceBean
 */
@Stateless(mappedName = "UserWebService", name = "UserWebServiceImpl")
@WebService(name = "UserWebServicePort", serviceName = "UserWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.service.webservice.UserWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserWebServiceImpl implements UserWebService {

	UserServiceRemote serviceRemote;

	public void initEJB() {
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			env.put(Context.SECURITY_PRINCIPAL, "weblogic");
			env.put(Context.SECURITY_CREDENTIALS, "welcome1");
			env.put(Context.PROVIDER_URL, "t3://localhost:7004,localhost:7003");
			Context ctx;

			ctx = new InitialContext(env);

			serviceRemote = (UserServiceRemote) ctx
					.lookup("UserService#hu.neuron.java.service.user.UserServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public UserListWebServiceVo getUserListWebMethod() {
		initEJB();
		List<UserVO> userVOs = serviceRemote.getUserList();
		UserListWebServiceVo rv = new UserListWebServiceVo();
		rv.setList(new ArrayList<UserWebServiceVo>());
		for (UserVO userVO : userVOs) {
			rv.getList().add(mapper.map(userVO, UserWebServiceVo.class));
		}

		return rv;
	}
}
