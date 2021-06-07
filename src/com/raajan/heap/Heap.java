package com.raajan.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap {

	public int[] array;
	public int capacity;
	public int size;

	public Heap(int capacity, int[] arr) {
		this.capacity = capacity;
		this.size = arr.length;
		this.array = Arrays.copyOf(arr, capacity);
	}

	public Heap() {

	}

	public int[] buildMinHeap(int[] array, int size) {
		// Index of last non-leaf node
		int startIndex = parent(size - 1);

		// Perform reverse level order traversal from last non-leaf node and heapify
		// each node
		while (startIndex >= 0) {
			heapifyMin(array, size, startIndex);
			startIndex--;
		}

		return array;
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public int left(int i) {
		return (2 * i) + 1;
	}

	public int right(int i) {
		return (2 * i) + 2;
	}

	public void heapifyMin(int[] heapArray, int heapSize, int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;

		// If left child is smaller than smallest so far
		if (l < heapSize && heapArray[l] < heapArray[smallest]) {
			smallest = l;
		}

		// If right child is smaller than smallest so far
		if (r < heapSize && heapArray[r] < heapArray[smallest]) {
			smallest = r;
		}

		// If smallest is not root
		if (i != smallest) {
			swap(heapArray, i, smallest);
			// Recursively heapify the affected sub-tree
			heapifyMin(heapArray, heapSize, smallest);
		}

	}

	private void swap(int[] heap, int i, int smallest) {
		int temp = heap[i];
		heap[i] = heap[smallest];
		heap[smallest] = temp;
	}

	public int[] buildMaxHeap(int[] array, int size) {
		// Index of last non-leaf node
		int startIndex = parent(size - 1);

		// Perform reverse level order traversal from last non-leaf node and heapify
		// each node
		while (startIndex >= 0) {
			heapifyMax(array, size, startIndex);
			startIndex--;
		}

		return array;
	}

	public void heapifyMax(int[] heapArray, int heapSize, int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;

		// If left child is smaller than smallest so far
		if (l < heapSize && heapArray[l] > heapArray[largest]) {
			largest = l;
		}

		// If right child is smaller than smallest so far
		if (r < heapSize && heapArray[r] > heapArray[largest]) {
			largest = r;
		}

		// If smallest is not root
		if (i != largest) {
			swap(heapArray, i, largest);
			// Recursively heapify the affected sub-tree
			heapifyMax(heapArray, heapSize, largest);
		}
	}

	public static void main(String[] args) {
		int[] minArr = { 17, 15, 8, 9, 10, 13, 6, 4, 5, 3, 1 };
		Heap heap = new Heap(minArr.length, minArr);
		heap.buildMinHeap(heap.array, heap.size);
		System.out.println(Arrays.toString(heap.array));

		//int[] maxArr = { 17, 15, 8, 9, 10, 13, 6, 4, 5, 3, 1 };
		int[] maxArr = {1, 3, 6, 4, 10, 13, 8, 9, 5, 15, 17};
		Heap maxHeap = new Heap(maxArr.length, maxArr);
		maxHeap.buildMaxHeap(maxHeap.array, maxHeap.size);
		System.out.println(Arrays.toString(maxHeap.array));
		  PriorityQueue<Integer> pQueue
          = new PriorityQueue<Integer>((a, b) -> b - a);

      // Adding items to the pQueue using add()
      pQueue.add(1);
      pQueue.add(3);
      pQueue.add(-1);
      pQueue.add(-3);
      pQueue.add(5);
      pQueue.add(3);
      pQueue.add(6);
      pQueue.add(7);
	}

}
