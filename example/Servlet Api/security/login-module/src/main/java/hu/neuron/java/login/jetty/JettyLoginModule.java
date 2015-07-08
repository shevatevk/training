package hu.neuron.java.login.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.eclipse.jetty.jaas.spi.AbstractLoginModule;
import org.eclipse.jetty.jaas.spi.UserInfo;
import org.eclipse.jetty.security.authentication.DigestAuthenticator;
import org.eclipse.jetty.util.security.Credential;

public class JettyLoginModule extends AbstractLoginModule {

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		return super.login();
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return super.logout();
	}

	@Override
	public UserInfo getUserInfo(String username) throws Exception {

		if (!"user".equals(username)) {
			return null;
		}

		Credential credential = new Credential() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean check(Object arg0) {
				if (arg0 instanceof String) {
					return "user".equals(arg0);
				}
				return true;
			}

		};
		List<String> roles = new ArrayList<String>();
		roles.add("user");

		return new UserInfo(username, credential, roles);
	}

}
