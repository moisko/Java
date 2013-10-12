package com.github.algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreatestCommonDivisorTest {

	@Test
	public void testGCD() throws Exception {
		int result = GreatestCommonDivisor.gcd(5, 3);
		assertEquals(1, result);
		result = GreatestCommonDivisor.gcd(6, 5);
		assertEquals(1, result);
		result = GreatestCommonDivisor.gcd(119, 544);
		assertEquals(17, result);
		result = GreatestCommonDivisor.gcd(544, 119);
		assertEquals(17, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGCDWithNegativeArgs() throws Exception {
		GreatestCommonDivisor.gcd(-544, 119);
	}
}
