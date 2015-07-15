package hu.neuron.java.core.example;

public class HelloWordRunnerStaticFactory {

	private static HelloWordBeanXML helloWordBeanXML = new HelloWordBeanXML();
	private static HelloWordRunnerXML helloWordRunnerXML = new HelloWordRunnerXML(helloWordBeanXML);

	private HelloWordRunnerStaticFactory() {
	}

	public static HelloWordRunnerXML createInstance() {
		return helloWordRunnerXML;
	}
}
