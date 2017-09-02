package com.raajan.stack;

import java.util.Random;
import java.util.Stack;

public class StackProblems {

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



   /**
    * Sort stack using recursion
    * 
    * @param stack
    * @param tempStack
    */
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


   /**
    * Sort stack using temporary stack
    * 
    * @param stack
    * @param tempStack
    */
   public static void sortStackUsingTempStack(Stack<Integer> stack, Stack<Integer> tempStack) {
      if (!stack.isEmpty()) {
         int top = stack.pop();
         if (!tempStack.isEmpty() && top < tempStack.peek()) {
            // tempStack will always be sorted, so go deep until "top" find its location
            while (!tempStack.isEmpty() && top < tempStack.peek()) {
               stack.push(tempStack.pop());
            }
            tempStack.push(top);
         } else {
            tempStack.push(top);
         }
         sortStackUsingTempStack(stack, tempStack);
      }
   }



}
