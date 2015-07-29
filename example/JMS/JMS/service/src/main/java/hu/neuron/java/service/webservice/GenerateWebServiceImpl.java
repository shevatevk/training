package hu.neuron.java.service.webservice;

import hu.neruon.java.service.queue.MessageSenderForGenerateRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(mappedName = "GenerateWebService", name = "GenerateWebService")
@WebService(name = "GenerateWebServicePort", serviceName = "GenerateWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.service.webservice.GenerateWebService")
public class GenerateWebServiceImpl implements GenerateWebService {

	@EJB(mappedName = "MessageSenderForGenerate", name = "MessageSenderForGenerate")
	MessageSenderForGenerateRemote forGenerateRemote;

	@Override
	public String generate(String message) {
		try {
			forGenerateRemote.send(message);
			return "OK";
		} catch (Exception e) {

			e.printStackTrace();
			return "ERROR";
		}
	}

}
