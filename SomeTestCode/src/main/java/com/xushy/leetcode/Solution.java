package com.xushy.leetcode;

import java.lang.Thread.State;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args) {
		// SnapshotArray snapshotArr = new SnapshotArray(4);
		// System.out.println(snapshotArr.snap());
		// System.out.println(snapshotArr.snap());
		// snapshotArr.get(3, 1);
		// snapshotArr.set(2, 4);
		// System.out.println(snapshotArr.snap());
		// snapshotArr.set(1, 4);
		// System.out.println(firstBadVersion(3));
		// Collections.sort(list);
		int[] nums1 = { 9, 9, 9 };
		int[] nums2 = { 3, 4 };

		String command1 = "URRURRR";
		int[][] aa = { { 7, 7 }, { 0, 5 }, { 2, 7 }, { 8, 6 }, { 8, 7 },
				{ 6, 5 }, { 4, 4 }, { 0, 3 }, { 3, 6 } };
		// quickSort(nums2);
		long start = System.nanoTime();
		String command = "URR";
		// int[][] aa1 = { {} };
		// System.out.println(robot(command, aa1, 3, 2));
		int[][] aa2 = { { 2, 2 } };
		System.out.println(climbStairs(35));
		// int[][] aa3 = { { 4, 2 } };
		// System.out.println(robot(command, aa2, 3, 2));
		// System.out.println(robot(command1, aa, 4915, 1966));

		// System.out.println((new Date().getTime() - start) / (1000));
	}

	public static int firstBadVersion(int n) {
		int low = 1, high = n;
		int mid = 0;
		while (low < high) {
			mid = low + (high - low) / 2;
			if (isBadVersion(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}

		}
		return low;
	}

	public static boolean isBadVersion(int version) {
		boolean isBadVersion = false;
		switch (version) {
		case 1:
			isBadVersion = false;
			break;
		case 2:
			isBadVersion = false;
			break;
		case 3:
			isBadVersion = true;
			break;
		case 4:
			isBadVersion = true;
			break;
		case 5:
			isBadVersion = true;
			break;
		default:
			break;
		}
		return isBadVersion;
	}

	// 349 借助list
	public static int[] intersection(int[] nums1, int[] nums2) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				if (nums1[i] == nums2[j] && !list.contains(nums1[i])) {
					list.add(nums1[i]);
				}
			}
		}
		int[] ret = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	// 350
	public static int[] intersection2(int[] nums1, int[] nums2) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums1.length; i++) {
			if (!map.containsKey(nums1[i])) {
				map.put(nums1[i], 1);
			} else {
				map.put(nums1[i], map.get(nums1[i]).intValue() + 1);
			}
		}
		for (int i = 0; i < nums2.length; i++) {
			if (!map2.containsKey(nums2[i])) {
				map2.put(nums2[i], 1);
			} else {
				map2.put(nums2[i], map2.get(nums2[i]).intValue() + 1);
			}
		}
		int repeat = 0;
		for (Integer key1 : map.keySet()) {
			for (Integer key2 : map2.keySet()) {
				if (key1.equals(key2)) {
					Integer value1 = map.get(key1);
					Integer value2 = map2.get(key2);
					repeat = value1 > value2 ? value2 : value1;
					for (int i = 0; i < repeat; i++) {
						list.add(key1);
					}
				}
			}
		}
		int[] ret = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	// 367. 有效的完全平方数(暴力破解法)
	public static boolean isPerfectSquare(int num) {
		boolean ret = false;
		for (long i = 0; i <= num; i++) {
			if (i * i == num) {
				ret = true;
				break;
			} else if (i * i > num) {
				break;
			}
		}
		return ret;
	}

	// 367. 有效的完全平方数(二分查找)
	public static boolean isPerfectSquare2(int num) {
		long low = 0, mid = 0, high = num;
		boolean ret = false;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (mid * mid == num) {
				ret = true;
				break;
			} else if (mid * mid < num) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return ret;
	}

	public static int guessNumber(int n) {
		int low = 0, mid = 0, high = n, guess = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			guess = guess(mid);
			if (guess == 0) {
				break;
			} else if (guess == 1) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return mid;
	}

	public static int guess(int num) {
		int i = 6;
		if (i < num) {
			return -1;
		} else if (i == num) {
			return 0;
		} else {
			return 1;
		}
	}

	public static boolean isSubsequence(String s, String t) {
		char[] chars = s.toCharArray();
		char[] tchars = t.toCharArray();
		int i = 0, start = 0;
		ArrayList<Character> list = new ArrayList<Character>();
		for (char c : chars) {
			for (i = start; i < tchars.length; i++) {
				if (c == tchars[i]) {
					list.add(c);
					start = i + 1;
					break;
				}
			}
		}
		return list.size() == chars.length;
	}

	// 441排列硬币
	public static int arrangeCoins(int n) {
		long line = 0;
		while (line <= n) {
			if ((line * line + line) / 2 - n > 0) {
				break;
			}
			++line;
		}
		return (int) (line - 1);
	}

	// 475.供暖器
	public int findRadius(int[] houses, int[] heaters) {
		return 1;
	}

	// 3. 无重复字符的最长子串
	public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<Character>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	//
	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		Queue<Character> queue = new LinkedList<Character>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			if (!queue.contains(s.charAt(j))) {
				queue.offer(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				queue.poll();
				i++;
			}
		}
		return ans;
	}

	// 739. 每日温度
	// 执行用时 :301 ms, 在所有 java 提交中击败了26.93% 的用户;内存消耗 : 42.4 MB,在所有
	// java提交中击败了94.31%的用户
	public static int[] dailyTemperatures(int[] T) {
		int[] ret = new int[T.length];
		int day = 0;
		for (int i = 0; i < T.length; i++) {
			day = 0;
			for (int j = i + 1; j < T.length; j++) {
				++day;
				if (T[i] < T[j]) {
					ret[i] = day;
					break;
				}
			}
		}
		return ret;
	}

	// 29.两数相除(2147483647,2)超出时间限制
	public static int divide(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		if (dividend == Integer.MIN_VALUE && divisor == 1) {
			return Integer.MIN_VALUE;
		}
		int left = Math.abs(divisor);
		int a = divisor - left == 0 ? 0 : -1;
		int right = dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math
				.abs(dividend);
		int b = dividend - right == 0 ? 0 : -1;
		int sum = 0;
		int count = 0;
		while (sum < right && sum >= 0) {
			sum += left;
			++count;
		}
		if ((sum > right || sum < 0) && Integer.MIN_VALUE != dividend) {
			--count;
		}
		return a + b != 0 && a != b ? -count : count;
	}

	public static int divide2(int dividend, int divisor) {
		boolean sign = (dividend ^ divisor) > 0;
		if (dividend > 0) {

		}
		return 1;
	}

	public int findContentChildren(int[] g, int[] s) {
		return 1;
	}

	public static int[] insertSort(int[] arr) {
		for (int i = 0, j = i; i < arr.length - 1; j = ++i) {
			int ai = arr[i + 1];
			while (ai < arr[j]) {
				arr[j + 1] = arr[j];
				if (j-- == 0) {
					break;
				}
			}
			arr[j + 1] = ai;
		}
		return arr;
	}

	// 73, 74, 75, 71, 69, 72, 76, 73
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

	// 快速排序
	public static int[] quickSort(int[] arr) {

		return arr;
	}

	public static int romanToInt(String s) {
		int sum = 0;
		for (int i = 0; i < s.length();) {
			char char1 = s.charAt(i);
			if (i + 2 <= s.length()
					&& ((char1 == 'I' && (s.charAt(i + 1) == 'V' || s
							.charAt(i + 1) == 'X'))
							|| (char1 == 'X' && (s.charAt(i + 1) == 'L' || s
									.charAt(i + 1) == 'C')) || ((char1 == 'C' && (s
							.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M'))))) {
				sum += getInt(s.substring(i, i = i + 2));
			} else {
				sum += getInt(s.substring(i, ++i));
			}
		}
		return sum;
	}

	public static int getInt(String str) {// MMCCCXCIX
											// 1000+1000+100+100+100+90+9
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("I", 1);
		map.put("IV", 4);
		map.put("V", 5);
		map.put("IX", 9);
		map.put("X", 10);
		map.put("XL", 40);
		map.put("L", 50);
		map.put("XC", 90);
		map.put("C", 100);
		map.put("CD", 400);
		map.put("D", 500);
		map.put("CM", 900);
		map.put("M", 1000);
		if (map.containsKey(str)) {
			return map.get(str);
		} else {
			return map.get(str.substring(0, 1)) + map.get(str.substring(1, 2));
		}
	}

	// LCP 3. 机器人大冒险
	public static boolean robot(String command, int[][] obstacles, int x, int y) {
		char[] chars = command.toCharArray();
		int countX = 0;
		int countY = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'U') {
				++countY;
			} else if (chars[i] == 'R') {
				++countX;
			}
		}
		for (int i = 0; i < obstacles.length; i++) {
			int[] o = obstacles[i];
			if (o.length == 2 && isPass(chars, countX, countY, o[0], o[1])) {
				return false;
			}
		}
		return true;
	}

	private static boolean isPass(char[] chars, int countX, int countY, int i,
			int j) {
		int t = i / countX;
		int locationX = t * countX;
		int locationY = t * countY;
		if (locationX == i) {
			for (int k = chars.length - 1; k >= 0; k--) {
				if (j == locationY) {
					return true;
				} else if (locationY > j) {
					return false;
				}
				if (chars[k] == 'U') {
					--locationY;
				} else if (chars[k] == 'R') {
					return false;
				}
			}
		} else {
			for (int k = 0; k < chars.length; k++) {
				if (chars[k] == 'U') {
					++locationY;
				} else if (chars[k] == 'R') {
					++locationX;
				}
				if (i == locationX && j == locationY) {
					return true;
				} else if (locationX > i || locationY > j) {
					return false;
				}
			}
		}
		return true;
	}

	// 4. 寻找两个有序数组的中位数 时间复杂度不符合题目要求的O(log(m+n))
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int count = nums1.length + nums2.length;
		int mid = count / 2;
		int[] nums3 = Arrays.copyOf(nums1, nums1.length + nums2.length);
		System.arraycopy(nums2, 0, nums3, nums1.length, nums2.length);
		Arrays.sort(nums3);
		int i = count % 2;
		if (i == 0) {
			return (double) (nums3[mid - 1] + nums3[mid]) / 2.0;
		} else {
			return nums3[mid];
		}
	}

	// 4. 寻找两个有序数组的中位数 O(log(m+n))
	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

		return 0.0;
	}

	// 6. Z 字形变换
	// 3行
	// L C I R
	// E T O E S I I G
	// E D H N
	// 4行
	// L D R
	// E OE II
	// E C I H N
	// T S G
	// 遍历字符数组 通过当前行是第一行或最后一行判断接下来向上走还是向下走！！
	public static String convert(String s, int numRows) {
		if (numRows == 1)
			return s;

		List<StringBuilder> rows = new ArrayList<StringBuilder>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			// Math.min 用來计算最多能显示多少行，因为有可能参数行数比字符数多
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1) {// 判断该向哪个方向走
				goingDown = !goingDown;
			}
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows)
			ret.append(row);
		return ret.toString();
	}

	// 7 整数反转
	public static int reverse(int x) {
		long ret = 0;
		String str = String.valueOf(x);
		int count = str.contains("-") ? str.length() - 1 : str.length();
		while (x / 10 != 0) {
			ret += (x % 10) * Math.pow(10, --count);
			if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
				ret = 0;
				break;
			}
			x = x / 10;
		}
		if (x < 10 && x > -10) {
			ret += x;
		}
		return (int) ret;
	}

	// 7 整数反转 官方题解
	public int reverse1(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10
					|| (rev == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;
			if (rev < Integer.MIN_VALUE / 10
					|| (rev == Integer.MIN_VALUE / 10 && pop < -8))
				return 0;
			rev = rev * 10 + pop;
		}
		return rev;
	}

	// 8. 字符串转换整数 (atoi)
	public static int myAtoi(String str) {
		char[] chars = str.toCharArray();
		int ret = 0;
		int bit = 0;
		int flag = 1;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				continue;
			} else if (chars[i] == '-') {
				flag = -1;
			} else if (chars[i] == '0' || chars[i] == '1' || chars[i] == '2'
					|| chars[i] == '3' || chars[i] == '4' || chars[i] == '5'
					|| chars[i] == '6' || chars[i] == '7' || chars[i] == '8'
					|| chars[i] == '9') {
				bit = chars[i] - '0';
				if (ret > Integer.MAX_VALUE / 10) {
				}
				if (ret == Integer.MAX_VALUE / 10 && bit > 7 && flag == 1) {
					ret = Integer.MAX_VALUE;
					break;
				} else if (ret == Integer.MAX_VALUE / 10 && bit > 8
						&& flag == -1) {
					ret = Integer.MIN_VALUE;
					break;
				}
				ret = ret * 10 + bit;
			} else {
				break;
			}
		}
		return flag * ret;
	}

	// 26. 删除排序数组中的重复项
	public static int removeDuplicates(int[] nums) {
		int count = nums.length;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				if (nums[i] == nums[j]) {
					for (int k = j; k < nums.length - 1; k++) {
						nums[k] = nums[k + 1];
					}
					--count;
				}
			}
		}
		return count;
	}

	// 11.盛最多水的容器
	// 执行用时 :303 ms, 在所有 java 提交中击败了25.40%的用户
	// 内存消耗 :39.8 MB, 在所有 java 提交中击败了92.78%的用户
	public static int maxArea(int[] height) {
		int maxArea = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				maxArea = Math.max(maxArea,
						(j - i) * Math.min(height[i], height[j]));
			}
		}
		return maxArea;
	}

	// 11.盛最多水的容器 (双指针法)
	// 执行用时 :4 ms, 在所有 java 提交中击败了86.26%的用户
	// 内存消耗 :40 MB, 在所有 java 提交中击败了92.10%的用户
	public static int maxArea1(int[] height) {
		int maxArea = 0;
		int l = 0;
		int r = height.length - 1;
		while (l < r) {
			maxArea = Math.max(maxArea,
					(r - l) * Math.min(height[r], height[l]));
			if (height[r] < height[l]) {
				r--;
			} else {
				l++;
			}
		}
		return maxArea;
	}

	// 20 有效的括号
	public static boolean isValid(String s) {
		if (s.length() == 0) {
			return true;
		}
		Stack<Character> stack = new Stack<Character>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((chars[i] == ')' || chars[i] == ']' || chars[i] == '}')) {
				if (stack.size() == 0) {
					return false;
				}
				int j = chars[i] - stack.peek();
				if (0 < j && j <= 2) {
					stack.pop();
				} else {
					return false;
				}
			} else {
				stack.push(chars[i]);
			}
		}
		return stack.size() == 0;
	}

	List<String> ret = new LinkedList<String>();

	HashMap<Integer, String> map = new HashMap<Integer, String>();

	// 2-9
	public List<String> letterCombinations(String digits) {
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");

		backtrack("", digits);

		return ret;
	}

	private void backtrack(String string, String digits) {
		for (int i = 0; i < digits.length(); i++) {
			String temp = map.get(digits.substring(0, 1));
			for (int j = 0; j < temp.length(); j++) {
				backtrack(string, digits);
			}
		}
	}

	// 27. 移除元素
	public static int removeElement(int[] nums, int val) {
		int count = nums.length;
		for (int i = 0; i < count;) {
			if (nums[i] == val) {
				--count;
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
			}
			if (nums[i] != val) {
				i++;
			}
		}
		return count;
	}

	// 28. 实现 strStr()
	public static int strStr(String haystack, String needle) {
		int hL = haystack.length();
		int nL = needle.length();
		if (nL > hL) {
			return -1;
		}
		if (nL == 0 || needle.equals(haystack)) {
			return 0;
		}
		char[] chars = haystack.toCharArray();
		for (int i = 0; i < chars.length - nL + 1; i++) {
			if (needle.equals(haystack.substring(i, i + nL))) {
				return i;
			}
		}
		return -1;
	}

	// 50. Pow(x, n)
	public static double myPow(double x, int n) {
		double ret = 1;
		for (int i = n; i != 0; i /= 2) {
			if (i % 2 != 0)
				ret *= x;
			x *= x;
		}
		return n > 0 ? ret : 1 / ret;
	}

	// 66. 加一
	public static int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] != 9) {
				digits[i]++;
				break;
			} else {
				digits[i] = 0;
				if (i == 0) {
					int[] copy = new int[digits.length + 1];
					System.arraycopy(digits, 0, copy, 1, digits.length);
					copy[0] = 1;
					return copy;
				}
			}
		}
		return digits;
	}

	// C(min,1)+C(min,2)+...+C(min,min)= min*(min-1)*...*i/i*(i-1)*...1
	// 5 11111 2111 1211 1121 1112 221 212 122
	// 6 111111 21111 12111 11211 11121 11112 2211 1221 1122 2121 2112 1212 222
	public static int climbStairs(int n) {
		int min = n / 2;
		int count = 0;
		for (int i = min; i >= 1; i--) {
			count += cal(n - i, i);
		}
		return count + 1;
	}

	public static int cal(int min, int i) {
		long a = 1;
		long b = 1;
		for (int j = i + 1; j <= min; j++) {
			a *= j;
		}
		for (int j = 1; j <= min - i; j++) {
			b *= j;
		}
		return (int) (a / b);
	}
}
