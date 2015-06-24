package hu.neuron.java.training;

public class StaticOuterClass {

	private String field;

	public StaticOuterClass(String field) {
		this.field = field;
	}

	public void print() {
		System.out.println("outer static :" + field);
		StaticInnerClass innerClass = new StaticInnerClass(field + " inner");
		innerClass.print();
	}

	public static class StaticInnerClass {
		private String fieldinner;

		public StaticInnerClass(String fieldinner) {
			this.fieldinner = fieldinner;
		}

		public void print() {
			System.out.println("inner static :" + fieldinner);
			// System.out.println("inner:" +StaticOuterClass.this.field);
		}
	}
}
