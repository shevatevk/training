package hu.neuron.java.annotations;

public class ReflectionExample {

	public static void main(String[] args) {
		Class<Impl> obj = Impl.class;
		if (obj.isAnnotationPresent(RuntimeLevelAnnoation.class)) {

			RuntimeLevelAnnoation annotation = obj.getAnnotation(RuntimeLevelAnnoation.class);
			RuntimeLevelAnnoation testerInfo = (RuntimeLevelAnnoation) annotation;

			System.out.println(testerInfo.test());

		}
	}
}
