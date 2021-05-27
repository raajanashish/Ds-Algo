package com.raajan.graph.dfs.cycle;

import java.util.List;

import com.raajan.graph.Graph;

/**
 * https://www.geeksforgeeks.org/cycles-of-length-n-in-an-undirected-and-connected-graph/
 * 
 * Given an undirected and connected graph and a number n, count total number of
 * cycles of length n in the graph. A cycle of length n simply means that the
 * cycle contains n vertices and n edges. And we have to count all such cycles
 * that exist.
 * 
 * @author raajan
 *
 */
public class CycleOfNLength {/*

	public static void main(String[] args) {
		int[][] array = { { 1, 2 }, { 0, 2, 3 }, { 0, 1, 4, 3 }, { 1, 4, 2 }, { 2, 3 } };
		List<Integer>[] adjList = Graph.intialise(array.length, array);
		System.out.println(detectCycleOfLengtyth(adjList));
	}

	static int cycleCount = 0;
	static int len = 3;

	private static int detectCycleOfLengtyth(List<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		
		for(int v= 0 ; v<adjList.length-()
		cycleUtil(0, -1, adjList, visited, 1, );
		return cycleCount;
	}

	public static void cycleUtil(int v, int p, List<Integer>[] adjList, boolean[] visited, int count) {
		visited[v] = true;
		for (int i : adjList[v]) {
			if (!visited[i]) {
				cycleUtil(i, v, adjList, visited, count + 1);
			} else if (p != i) {

				cycleCount++;
			}
		}

	}*/
}
