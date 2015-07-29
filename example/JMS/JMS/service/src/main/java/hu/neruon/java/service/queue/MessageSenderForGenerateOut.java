package hu.neruon.java.service.queue;

import hu.neuron.java.service.vo.MessageVO;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@Singleton(mappedName = "MessageSenderForGenerateOut", name = "MessageSenderForGenerateOut")
@Remote(MessageSenderForGenerateOutRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageSenderForGenerateOut implements
		MessageSenderForGenerateOutRemote {
	private static final Logger logger = Logger
			.getLogger(MessageSenderForGenerateOut.class);

	@Resource(name = "hu.neuron.java.ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(name = "hu.neuron.java.Out")
	private Queue queue;

	public void send(MessageVO message) throws Exception {

		Connection connection = null;
		MessageProducer producer = null;
		Session session = null;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			producer = session.createProducer(queue);

			ObjectMessage messageOut = session.createObjectMessage();

			messageOut.setObject(message);

			producer.send(messageOut);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (JMSException e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				producer.close();
			} catch (JMSException e2) {
				logger.error(e2.getMessage(), e2);
			}
			try {
				connection.close();
			} catch (JMSException e3) {
				logger.error(e3.getMessage(), e3);
			}
		}

	}
}
