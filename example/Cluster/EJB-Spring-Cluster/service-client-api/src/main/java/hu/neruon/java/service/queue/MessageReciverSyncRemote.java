package hu.neruon.java.service.queue;

import hu.neuron.java.service.vo.MessageVO;

public interface MessageReciverSyncRemote {

	MessageVO consum() throws Exception;
}
