package hu.neuron.java.core.example;

import org.springframework.beans.factory.annotation.Required;

public class HelloWordRunnerXML {

	private HelloWordBeanXML helloWordBeanXML;

	public HelloWordRunnerXML() {
	}

	public HelloWordRunnerXML(HelloWordBeanXML helloWordBeanXML) {
		super();
		this.helloWordBeanXML = helloWordBeanXML;
	}

	public HelloWordBeanXML getHelloWordBeanXML() {
		return helloWordBeanXML;
	}

//	@Required
	public void setHelloWordBeanXML(HelloWordBeanXML helloWordBeanXML) {
		this.helloWordBeanXML = helloWordBeanXML;
	}

	public void run() {
		helloWordBeanXML.hello();

	}
}
