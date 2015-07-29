package hu.neuron.java.service.webservice;

import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface GenerateWebService {

	String generate(String message);

}
