package com.raajan.stack;

import java.util.Random;
import java.util.Stack;

public class StackProblems {

   public static void sortStackUsingRecursion(Stack<Integer> stack) {
      if (stack.isEmpty())
         return;
      int top = stack.pop();
      sortStackUsingRecursion(stack);
      sortedInsert(stack, top);
   }

   private static void sortedInsert(Stack<Integer> stack, int element) {
      if (stack.isEmpty() || element > stack.peek())
         stack.push(element);
      else {
         int top = stack.pop();
         sortedInsert(stack, element);
         stack.push(top);
      }
   }


   public static void main(String[] args) {
      Stack<Integer> stack = new Stack<>();
      int i = 0;

      Random random = new Random();
      while (i < 20) {
         stack.push(random.nextInt() % 31);
         i++;
      }
      System.out.println(stack.toString());
      // sortStackUsingRecursion(stack);
      Stack<Integer> tempStack = new Stack<>();
      sortStackUsingTempStack(stack, tempStack);
      System.out.println(tempStack.toString());

   }

   public static void sortStackUsingTempStack(Stack<Integer> stack, Stack<Integer> tempStack) {
      if (!stack.isEmpty()) {
         int top = stack.pop();
         if (!tempStack.isEmpty() && top < tempStack.peek()) {
            stack.push(tempStack.pop());
            sortStackUsingTempStack(stack, tempStack);
            tempStack.push(top);
         } else {
            tempStack.push(top);
            sortStackUsingTempStack(stack, tempStack);
         }
      }
      return;
   }

}
