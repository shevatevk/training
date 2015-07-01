package hu.neuron.java.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeLevelAnnoation {
	
	String test();

}
