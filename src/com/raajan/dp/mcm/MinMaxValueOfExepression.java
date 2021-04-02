package com.raajan.dp.mcm;

public class MinMaxValueOfExepression {

  public static void main(String[] args) {

    int[] numbers = {1, 2, 3, 4, 5};
    char[] operators = {'+', '*', '+', '*'};

    System.out.println(minValueOfExpression(1, numbers.length, numbers, operators));
  }

  /**
   * Not correct , needs to be corrected
   * 
   * @param i
   * @param j
   * @param numbers
   * @param operators
   * @return
   */
  public static int minValueOfExpression(int i, int j, int[] numbers, char[] operators) {
    if (i == j) {
      return numbers[i - 1];
    }
    int minValue = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int result = 0;
      if (operators[k - 1] == '+') {
        result = minValueOfExpression(i, k, numbers, operators)
            + minValueOfExpression(k + 1, j, numbers, operators);
      } else {
        result = minValueOfExpression(i, k, numbers, operators)
            * minValueOfExpression(k + 1, j, numbers, operators);
      }

      if (result < minValue)
        return minValue = result;
    }
    return minValue;
  }
}
