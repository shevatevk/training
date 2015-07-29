package hu.neruon.java.service.queue;

import hu.neuron.java.service.vo.MessageVO;

public interface MessageSenderForGenerateOutRemote {

	public void send(MessageVO message) throws Exception;
}
