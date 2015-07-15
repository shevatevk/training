package hu.neuron.java.core.test.example;

import hu.neuron.java.core.example.HelloWordBeanXML;
import hu.neuron.java.core.example.HelloWordRunnerConstXML;
import hu.neuron.java.core.example.HelloWordRunnerXML;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-example.xml")
public class HelloWordXMLSpringTest {
	@Autowired
	@Qualifier("helloWordBeanXML")
	private HelloWordBeanXML beanXML;

	@Autowired
	@Qualifier("helloWordBeanXML")
	private HelloWordBeanXML beanXML2;

	@Autowired
	@Qualifier("helloWordBeanXMLPrototype")
	private HelloWordBeanXML helloWordBeanXMLPrototype;

	@Autowired
	@Qualifier("helloWordBeanXMLPrototype")
	private HelloWordBeanXML helloWordBeanXMLPrototype2;

	@Autowired
	@Qualifier("helloWordRunnerXML")
	private HelloWordRunnerXML helloWordRunnerXML;

	@Autowired
	private HelloWordRunnerConstXML helloWordRunnerConstXML;

	@Autowired
	@Qualifier("helloWordRunnerXMLFromFactory")
	private HelloWordRunnerXML helloWordRunnerXMLFromFactory;

	@Autowired
	@Qualifier("helloWordRunnerXMLFromFactory")
	private HelloWordRunnerXML helloWordRunnerXMLFromFactory2;

	@Autowired
	@Qualifier("helloWordRunnerXMLFromStaticFactory")
	private HelloWordRunnerXML helloWordRunnerXMLFromStaticFactory;

	@Autowired
	@Qualifier("helloWordRunnerXMLFromStaticFactory")
	private HelloWordRunnerXML helloWordRunnerXMLFromStaticFactory2;

	@Test
	public void testBean() {

		beanXML.hello();

		beanXML2.hello();

	}

	@Test
	public void testBeanPrototype() {

		helloWordBeanXMLPrototype.hello();

		helloWordBeanXMLPrototype2.hello();

	}

	@Test
	public void testXMLDI() {

		helloWordRunnerXML.run();

		helloWordRunnerConstXML.run();
	}

	@Test
	public void testXMLFactoy() {

		helloWordRunnerXMLFromFactory.run();

		helloWordRunnerXMLFromFactory2.run();

		helloWordRunnerXMLFromStaticFactory.run();

		helloWordRunnerXMLFromStaticFactory2.run();
	}

}
