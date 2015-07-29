package hu.neruon.java.service.queue;

import hu.neuron.java.service.vo.MessageVO;

public interface ReciverRemote {

	MessageVO consum() throws Exception;
}
