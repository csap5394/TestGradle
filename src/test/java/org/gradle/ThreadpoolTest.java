package org.gradle;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ThreadpoolTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private boolean testPrime(int potentialPrime) {
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

	@Test
	public void test() {
		Map <Integer, Boolean> testprime;
		int [] testArrayPrime = {3,4};
		testprime = Server.calculatePrimes(testArrayPrime);
		assertTrue(testprime.get(3));
		assertFalse(testprime.get(4));
		int [] randomNumber = new int[20];
		for(int i = 0; i < 20; i++){
			randomNumber[i] = (int) (Math.random() * 50);
		}
		testprime = Server.calculatePrimes(randomNumber);
		for(Integer key: testprime.keySet()) {
			boolean serverResult = testprime.get(key);
			boolean testResult = testPrime(key);
			assertEquals(serverResult,testResult);
		}

	}

}