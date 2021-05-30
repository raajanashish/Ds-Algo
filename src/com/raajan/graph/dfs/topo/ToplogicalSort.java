package com.raajan.graph.dfs.topo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.raajan.graph.Graph;

/**
 * https://leetcode.com/problems/course-schedule-ii/discuss/59317/Two-AC-solution-in-Java-using-BFS-and-DFS-with-explanation
 * 
 * @author raajan
 *
 */
public class ToplogicalSort {
	Deque<Integer> order = new ArrayDeque<>();
	public static void main(String[] args) {

		// int[][] array = { {}, {}, { 3 }, { 1 }, { 0, 1 }, { 2, 0 } };
		int[][] array = { { 1, 2 }, { 3 }, { 3 }, {} };
		List<Integer>[] adjList = Graph.intialise(array.length, array);
		ToplogicalSort myObj = new ToplogicalSort();

		myObj.topologicalSortUsingDFS(adjList);
		myObj.topologicalSortUsingBFS(adjList);
	}

	private boolean topologicalSortUsingDFS(List<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		boolean[] inRec = new boolean[adjList.length];

		for (int i = 0; i < adjList.length; i++) {
			if (!visited[i]) {
				if (!dfsUtil(adjList, inRec, visited, i)) {
					return false;// topological sort not possible
				}
			}
		}
		// reversing the sequence for stack
		int[] orderArray = new int[adjList.length];
		for (int i = 0; !order.isEmpty(); i++)
		{
			orderArray[i] = order.pop();
		}

		System.out.println("DFS : " + Arrays.toString(orderArray));
		return true;
	}

	private boolean dfsUtil(List<Integer> adjList[], boolean[] inRec, boolean[] visited, int u) {
		if (inRec[u])
			return false;
		else if (visited[u]) {
			return true;
		} else {
			inRec[u] = true;
			visited[u] = true;
		}
		for (int i = 0; i < adjList[u].size(); i++) {
			if (!dfsUtil(adjList, inRec, visited, (int) adjList[u].get(i)))
				return false;
		}
		inRec[u] = false;
		order.push(u);
		return true;
	}

	private boolean topologicalSortUsingBFS(List<Integer>[] adjList) {
		int[] order = new int[adjList.length];
		if (bfsUtil(adjList, order)) {
			System.out.println("BFS : " + Arrays.toString(order));
			return true;
		}
		return false;

	}

	public boolean bfsUtil(List<Integer> adjList[], int[] topologicalOrder) {
		int[] degree = new int[adjList.length];
		Queue<Integer> queue = new LinkedList<>();
		int visited = 0;

		for (int u = 0; u < adjList.length; u++) {
			for (int v : adjList[u]) {
				degree[v]++;
			}
		}
		for (int i = 0; i < degree.length; i++) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int u = (int) queue.poll();
			topologicalOrder[visited] = u;
			visited++;
			for (int v : adjList[u]) {
				degree[v]--;
				if (degree[v] == 0) {
					queue.add(v);
				}
			}
		}
		if (visited == adjList.length)
			return true;
		else
			return false;
	}
}
