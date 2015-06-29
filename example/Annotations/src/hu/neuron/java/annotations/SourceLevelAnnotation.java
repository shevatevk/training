package hu.neuron.java.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SourceLevelAnnotation {

	String test();

	SourceLevelAnnotationPart part();

	String testDef() default "def";
}
