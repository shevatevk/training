package hu.neuron.java.service;

import hu.neuron.java.service.facade.UserFacadeRemote;
import hu.neuron.java.service.vo.RoleVO;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.junit.Before;
import org.junit.Test;

public class UserFacadeImplTest {



	@EJB(name = "UserFacade", mappedName = "UserFacade")
	private UserFacadeRemote userFacade;

	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.java.jpa.hibernate.hbm2ddl.auto", "update");
		p.put("hu.neuron.java.jpa.hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");

		p.put("hu.neuron.JpaDataSource", "new://Resource?type=DataSource");
		p.put("hu.neuron.JpaDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.JpaDataSource.JdbcUrl", "jdbc:hsqldb:mem:protected");

		EJBContainer ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@Test
	public void test() throws Exception {

		RoleVO roleVO = new RoleVO();
		roleVO.setName("TEST");
		userFacade.saveRole(roleVO);
		roleVO = userFacade.getRoleByName("TEST");
		System.out.println(roleVO);

	}
}
