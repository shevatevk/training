package hu.neruon.java.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

public class LoggerInterceptor {
	private static final Logger logger = Logger
			.getLogger(LoggerInterceptor.class);

	@AroundInvoke 
	public Object intercept(InvocationContext context) throws Exception {

		logger.error(System.getProperty("weblogic.Name") + " calling method : "
				+ context.getMethod().getName());
		Object result = context.proceed();

		return result; 
	}
}