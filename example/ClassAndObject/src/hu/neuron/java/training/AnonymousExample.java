package hu.neuron.java.training;

public class AnonymousExample {

	final String field = "Hello outer";

	interface AnonymousInterface {
		void print();
	}

	public static void main(String[] args) {
		AnonymousExample anonymousExample = new AnonymousExample();
		anonymousExample.test();
	}

	private void test() {
//		final String field = "Hello";
		final AnonymousInterface anonymousInterface = new AnonymousInterface() {
//			final String field = "Hello inner";

			@Override
			public void print() {
				System.out.println(field);
				AnonymousExample.this.print();
			}
		};

		anonymousInterface.print();

	}

	private void print() {
		System.out.println(field);

	}
}
