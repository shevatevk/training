package hu.neuron.java.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class SessionAttributeListener
 * 
 */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
	private static final Logger logger = Logger
			.getLogger(SessionAttributeListener.class);

	/**
	 * Default constructor.
	 */
	public SessionAttributeListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		logger.info("Session : " + event.getSession().getId() + " Removed "
				+ event.getName() + " " + event.getValue());
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		logger.info("Session : " + event.getSession().getId() + " Added "
				+ event.getName() + " " + event.getValue());
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		logger.info("Session : " + event.getSession().getId() + "Replaced "
				+ event.getName() + " " + event.getValue());
	}

}
