package org.gradle;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class PrimeNumberCallable implements Callable<Boolean> {
	private int potentialPrime;
	private CountDownLatch startSignal;

	public PrimeNumberCallable(int number, CountDownLatch startSignal) {
		this.potentialPrime = number;
		this.startSignal = startSignal;
	}

	public Boolean call() throws Exception {
		startSignal.countDown();
		startSignal.await();
		if (Math.abs(potentialPrime) == 2) {
			return true;
		}
		if (potentialPrime % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= potentialPrime; i += 2) {
			if (potentialPrime % i == 0) {
				return false;
			}
		}
		return true;
	}

}