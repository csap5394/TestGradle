package org.gradle;



import java.util.Map;

public class TestClient {

	public static void main(String[] args) {
		int[] numbers = new int[10000];
		for (int i = 0; i < 10000; i++) {
			numbers[i] = i;
		}
		long start = System.currentTimeMillis();
		Map<Integer, Boolean> result = Server.calculatePrimes(numbers);
		long stop = System.currentTimeMillis();

		for (Integer key : result.keySet()) {
			System.out.println("Is " + key + " prime? - " + result.get(key));
		}
		System.out.println("Program used time: " + (stop - start) + "millis");
	}

}