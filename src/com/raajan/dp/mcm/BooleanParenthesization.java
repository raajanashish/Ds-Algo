package com.raajan.dp.mcm;

public class BooleanParenthesization {
  private static int[][][] t;

  public static void main(String[] args) {
    char[] exp = {'T', '|', 'T', '&', 'F', '^', 'T'};
    // char[] exp = {'F', '&','T'};

    System.out.println("Recursive : " + parenthesizationCount(exp, 0, exp.length - 1, true));

    t = new int[exp.length][exp.length][2];
    for (int k = 0; k < 2; k++) {
      for (int i = 0; i < exp.length; i++) {
        for (int j = 0; j < exp.length; j++) {
          t[i][j][k] = -1;
        }
      }
    }
    System.out.println("Memozisation : " + parenthesizationCountMemo(exp, 0, exp.length - 1, true));

  }

  public static int parenthesizationCount(char[] exp, int i, int j, boolean isTrue) {
    if (i > j) {
      return 0;
    }

    if (i == j) {
      if (true == isTrue) {
        return exp[i] == 'T' ? 1 : 0;
      } else {
        return exp[i] == 'F' ? 1 : 0;
      }
    }

    int count = 0;
    for (int k = i + 1; k < j; k = k + 2) {
      int leftTrue = parenthesizationCount(exp, i, k - 1, true);
      int leftFalse = parenthesizationCount(exp, i, k - 1, false);
      int rightTrue = parenthesizationCount(exp, k + 1, j, true);
      int rightFalse = parenthesizationCount(exp, k + 1, j, false);

      if (exp[k] == '|') {
        if (isTrue) {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
        } else {
          count = count + leftFalse * rightFalse;
        }
      } else if (exp[k] == '&') {
        if (isTrue) {
          count = count + leftTrue * rightTrue;
        } else {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
        }
      } else if (exp[k] == '^') {
        if (isTrue) {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue;
        } else {
          count = count + leftFalse * rightFalse + leftTrue * rightTrue;
        }
      }
    }

    return count;
  }


  public static int parenthesizationCountMemo(char[] exp, int i, int j, boolean isTrue) {
    if (i > j) {
      return 0;
    }

    if (i == j) {
      if (true == isTrue) {
        return exp[i] == 'T' ? 1 : 0;
      } else {
        return exp[i] == 'F' ? 1 : 0;
      }
    }

    int count = 0;
    for (int k = i + 1; k < j; k = k + 2) {
      int leftTrue = 0;
      int leftFalse = 0;
      int rightTrue = 0;
      int rightFalse = 0;

      if (t[i][k - 1][1] == -1) {
        leftTrue = parenthesizationCount(exp, i, k - 1, true);
        t[i][k - 1][1] = leftTrue;
      } else {
        leftTrue = t[i][k - 1][1];
      }
      if (t[i][k - 1][0] == -1) {
        leftFalse = parenthesizationCount(exp, i, k - 1, false);
      } else {
        leftTrue = t[i][k - 1][0];
        t[i][k - 1][0] = leftTrue;
      }
      if (t[k + 1][j][1] == -1) {
        rightTrue = parenthesizationCount(exp, k + 1, j, true);
        t[k + 1][j][1] = rightTrue;
      } else {
        rightTrue = t[k + 1][j][1];
      }
      if (t[k + 1][j][0] == -1) {
        rightFalse = parenthesizationCount(exp, k + 1, j, false);
        t[k + 1][j][0] = rightFalse;
      } else {
        rightFalse = t[k + 1][j][0];
      }
      if (exp[k] == '|') {
        if (isTrue) {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
        } else {
          count = count + leftFalse * rightFalse;
        }
      } else if (exp[k] == '&') {
        if (isTrue) {
          count = count + leftTrue * rightTrue;
        } else {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
        }
      } else if (exp[k] == '^') {
        if (isTrue) {
          count = count + leftTrue * rightFalse + leftFalse * rightTrue;
        } else {
          count = count + leftFalse * rightFalse + leftTrue * rightTrue;
        }
      }
    }

    return count;
  }
}
