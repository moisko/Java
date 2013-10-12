package com.github.algorithms;

public class GreatestCommonDivisor {

	public static int gcd(int m, int n) {
		if (m >= n) {
			int r = m % n;
			if (r == 0)
				return n;
			// Set m <- n
			// Set n <- r
			return gcd(n, r);
		}
		// m <-> n
		return gcd(n, m);
	}

	public static void main(String[] args) {
		System.out.println("gcd(5, 3)=" + gcd(5, 3));
		System.out.println("gcd(6, 5)=" + gcd(6, 5));
		System.out.println("gcd(119, 544)=" + gcd(119, 544));
		System.out.println("gcd(544, 119)=" + gcd(544, 119));
	}
}
