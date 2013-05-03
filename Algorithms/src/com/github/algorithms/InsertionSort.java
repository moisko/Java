package com.github.algorithms;

import java.util.Arrays;

public class InsertionSort {

	public int[] insertionSort(int[] a) {
		if (a == null) {
			return null;
		}
		for (int i = 1; i < a.length; i++) {
			// key <=> current element
			int key = a[i];
			while (i > 0 && key < a[i - 1]) {
				// swap
				int temp = a[i - 1];
				a[i - 1] = key;
				a[i] = temp;
				i--;
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a = initArray();
		InsertionSort insertionSort = new InsertionSort();
		System.out.println("Array to be sorted: " + Arrays.toString(a));
		int[] sortedArray = insertionSort.insertionSort(a);
		System.out.println("Sorted array: " + Arrays.toString(sortedArray));
	}

	private static int[] initArray() {
		int[] a = new int[6];
		a[0] = 5;
		a[1] = 2;
		a[2] = 4;
		a[3] = 6;
		a[4] = 1;
		a[5] = 3;
		return a;
	}
}
