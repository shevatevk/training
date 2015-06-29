package hu.neuron.java.generic;

public class Main {

	public static void main(String[] args) {
		
		 System.out.format("E: %2$f,Pi: %1$f %n", Math.PI, Math.E);
		 
		Box<String> stringBox = new Box<>();
		stringBox.set("10");
		System.out.println(stringBox);

		Util.<String> print("Test");

		Util.<Box> print(stringBox);

		Util.print(stringBox);
		
		Box rawBox = stringBox;
		rawBox.set(8);
		System.out.println(stringBox);

		String string = stringBox.get();
		
	
	}
}
