package com.raajan.dp.mcm;

/**
 * Find minimum number of egg drop trials in worst cases to reach a critical floor.
 * e: nuimber of eggs
 * f: number of floors
 * 
 * @author raajan
 *
 */
public class EggFloorProblem {
  private static int[][] t;

  public static void main(String[] args) {
    int e = 2;
    int f = 4;

    System.out.println(eggDrop(e, f));
    System.out.println(eggDropDp(e, f));
  }

  /**
   * Function to get minimum number of trials needed in worst case with e eggs and f floors
   * m.kk bbbbb
   * 
   * @param e
   * @param f
   * @return
   */
  public static int eggDrop(int e, int f) {
    // If there are no floors, then no trials needed. OR if there
    // is one floor, one trial needed.
    if (f == 0 || f == 1) {
      return f;
    }
    // We need k trials for one egg and k floors in worst case
    if (e == 1) {
      return f;
    }
    int minDropping = Integer.MAX_VALUE;
    for (int k = 1; k <= f; k++) {
      int tempDroppings = 1 + Math.max(eggDrop(e - 1, k - 1), eggDrop(e, f - k));
      minDropping = Math.min(tempDroppings, minDropping);
    }
    return minDropping;
  }


  public static int eggDropDp(int e, int f) {
    t = new int[e + 1][f + 1];
    int tempResult = 0;
    // intialise the lookup table
    // We need one trial for one floor and 0 trials for 0 floors
    for (int i = 1; i <= e; i++) {
      t[i][0] = 0;
      t[i][1] = 1;
    }
    // We always need j trials for one egg and j floors.
    for (int j = 1; j <= f; j++) {
      t[1][j] = j;
    }
    for (int i = 2; i <= e; i++) {
      for (int j = 2; j <= f; j++) {
        t[i][j] = Integer.MAX_VALUE;
        for (int k = 1; k <= j; k++) {
          tempResult = 1 + Math.max(t[i - 1][k - 1], t[i][j - k]);
          t[i][j] = Math.min(tempResult, t[i][j]);
        }

      }
    }
    // eggFloor[n][k] holds the result
    return t[e][f];

  }
}
