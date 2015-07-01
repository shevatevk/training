package hu.neuron.java.collectionc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TestRunner {

	List<Test> tests;
	List<TestResult> testResults;

	void test() {
		testResults = new ArrayList<>();
		tests = new ArrayList<>();

		tests.add(new ArrayListTest());
		Date datei = new Date();
		Collection<ComparableItem> items = ItemFactory.getItems();
		Date datei2 = new Date();
		long timei = datei2.getTime() - datei.getTime();
		System.out.println(timei);

		for (Test test : tests) {
			TestResult testResult = new TestResult();
			testResults.add(testResult);
			Date date = new Date();

			test.init(items);

			Date date2 = new Date();
			long time = date2.getTime() - date.getTime();
			testResult.setClazz(test.getClazz());
			testResult.setInit(time);

		}

		System.out.println(testResults);
	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		runner.test();
	}
}
