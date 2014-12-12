package org.gradle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

	public static int MAX_THREADS = 8;

	public static Map<Integer, Boolean> calculatePrimes(int[] numbers) {
		// Creates a thread pool that reuses a fixed number of threads operating
		// off a shared unbounded queue.
		ExecutorService executor;
		CountDownLatch startSignal;
		if (numbers.length >= 8) {
			executor = Executors.newFixedThreadPool(MAX_THREADS);
			// executor = Executors.newCachedThreadPool();
			startSignal = new CountDownLatch(MAX_THREADS);
		} else {
			executor = Executors.newFixedThreadPool(numbers.length);
			startSignal = new CountDownLatch(numbers.length);
		}

		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		Map<Integer, Future<Boolean>> prelimResults = new HashMap<Integer, Future<Boolean>>();

		for (int i : numbers) {
			Callable<Boolean> c = new PrimeNumberCallable(i, startSignal);
			Future<Boolean> result = executor.submit(c);
			prelimResults.put(i, result);
		}

		for (Integer key : prelimResults.keySet()) {
			try {
				map.put(key, prelimResults.get(key).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();

		return map;
	}
}