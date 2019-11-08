package com.xushy.algorithms;

import java.util.Arrays;


/**
 *冒泡排序
 *理论:每次比较相邻两个数的大小，如果位置错误，交换他们的位置。直到没有需要交换的。
 *例如：3 2 1
 *第一轮：比较3和2，3比2大，交换位置得到2 3 1;比较 3和1，3比1大交换位置得到2 1 3
 *第一轮过后可以得到最右边的数是最大的，第二轮就不用比较它了。
 *第二轮：比较2和1， 2比1大，交换位置得到 1，2，3
 */
public class BubbleSort {

	private static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {// -1为了防止溢出
				if (arr[j] > arr[j + 1]) {
					arr[j] = arr[j] ^ arr[j + 1];
					arr[j + 1] = arr[j + 1] ^ arr[j];
					arr[j] = arr[j] ^ arr[j + 1];
					// int temp = arr[j];
					// arr[j] = arr[j + 1];
					// arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 78, 23, 12, 34, 56, 56, 56 };
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
