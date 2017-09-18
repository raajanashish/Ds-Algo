package com.raajan.linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		System.out.println(getPalindrome("ABCBA"));
	}

	public static int[] convertToDecimal(int n, int base) {

		int j = 1;
		int digit;
		int number = n;
		int convertedNum = 0;
		;
		int digitSum = 0;
		while (number > 0) {
			digit = number % 10;
			number = number / 10;
			convertedNum = convertedNum + digit * j;
			j = j * base;
			digitSum = digitSum + digit;
		}
		int[] returnVal = { convertedNum, digitSum };
		return returnVal;
	}

	/*
	 * Complete the function below.
	 */
	static Set<String> stringSet = new HashSet<String>();
	static Stack<Integer> stack = new Stack<>();

	static int longestChain(String[] words) {

		for (int i = 0; i < words.length; i++) {
			stringSet.add(words[i]);
		}
		int max = 1;
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			int currentLen = computeChain(word);
			max = max > currentLen ? max : currentLen;
		}

		return max;
	}

	static int computeChain(String word) {
		int maxLength = 0;
		if (word.length() == 1 && stringSet.contains(word))
			return 1;
		if (word.length() == 1 && !stringSet.contains(word))
			return 0;
		if (!stringSet.contains(word))
			return 0;

		for (int i = 0; i < word.length(); i++) {
			String newString = word.substring(0, i) + word.substring(i + 1);
			int length = computeChain(newString) + 1;
			maxLength = maxLength > length ? maxLength : length;
		}

		return maxLength;

	}

	static int sortStack(int firstMin) {
		if (stack.isEmpty()) {
			stack.push(firstMin);
			return Integer.MAX_VALUE;
		}
		int secondMin = stack.pop();

		if (firstMin > secondMin) {
			int temp = firstMin;
			firstMin = secondMin;
			secondMin = temp;
		}
		System.out.println(firstMin + "     " + secondMin);
		int temp = sortStack(firstMin);
		if (temp > secondMin) {
			stack.push(secondMin);
			return temp;
		} else {
			stack.push(temp);
			return secondMin;
		}

	}

	public static void getCharCount(char[][] characters) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < characters.length; i++) {
			for (int j = 0; j < characters[0].length; j++) {

			}
		}
	}

	public static String encodeDecode(int choice, String key, String data) {

		String output = "";
		if (1 == choice)
			output = encode(key, data);

		return null;
	}

	private static String encode(String key, String data) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < key.length(); i++) {
			int num = Integer.valueOf(key.substring(i, i + 1));
			while (num > 0) {
				output.append(data.charAt(i));
				num--;
			}
		}
		output.append(data.substring(key.length(), data.length()));

		return output.toString();
	}

	private static String decode(String key, String data) {
		StringBuilder output = new StringBuilder();
		int dataIndex = -1;
		int repeatCount;
		for (int i = 0; i < key.length(); i++) {
			repeatCount = Integer.valueOf(key.substring(i, i + 1));
			dataIndex = dataIndex + repeatCount;
			output.append(data.substring(dataIndex, dataIndex + 1));
		}
		return output.subSequence(dataIndex, output.length()).toString();
	}

	static int getPalindrome(String inputString) {
		int[][] pd = new int[inputString.length() + 2][inputString.length() + 2];
		System.out.println("Entered");
		for (int i = 0; i < inputString.length(); i++) {
			pd[i + 1][i + 1] = 1;
		}

		for (int j = 0; j < inputString.length(); j++) {
			for (int i = 0; i <= j; i++) {
				if (pd[i + 2][j] == 1) {
					if (inputString.charAt(i) == inputString.charAt(j))
						pd[i + 1][j + 1] = 1;
				}
			}
		}
		int count = 0;
		for (int i = 1; i <= inputString.length(); i++) {
			for (int j = 1; j <= inputString.length(); j++) {
				if (pd[i][j] == 1)
					count++;
			}
		}

		return count;
	}

}
