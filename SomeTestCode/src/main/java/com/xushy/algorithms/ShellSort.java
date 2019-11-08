package com.xushy.algorithms;

/**
 * @author Administrator
 * @description 希尔排序
 */
public class ShellSort {
	public static int[] shellSort(int[] array) {
		int gap = array.length;
		while (true) {
			gap /= 2; // 增量每次减半
			for (int i = 0; i < gap; i++) {
				for (int j = i + gap; j < array.length; j += gap) {// 这个循环里其实就是一个插入排序
					int temp = array[j];
					int k = j - gap;
					while (k >= 0 && array[k] > temp) {
						array[k + gap] = array[k];
						k -= gap;
					}
					array[k + gap] = temp;
				}
			}
			if (gap == 1)
				break;
		}
		return array;
	}
}
