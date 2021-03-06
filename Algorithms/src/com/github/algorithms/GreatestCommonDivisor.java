package com.github.algorithms;

public class GreatestCommonDivisor {

	public static int gcd(int m, int n) {
		checkArgs(m, n);
		if (m >= n) {
			int r = m % n;
			if (r == 0)
				return n;
			// Set m <- n
			// Set n <- r
			return gcd(n, r);
		}
		// Exchange m <-> n
		return gcd(n, m);
	}

	private static void checkArgs(int m, int n) {
		String message = null;
		if (m < 0) {
			message = m + " must be from the set of positive integers";
		} else if (n < 0) {
			message = n + " must be from the set of positive integers";
		} else if (n == 0) {
			message = "The divisor could not be 0";
		}
		if (message != null) {
			throw new IllegalArgumentException(message);
		}
	}

}
