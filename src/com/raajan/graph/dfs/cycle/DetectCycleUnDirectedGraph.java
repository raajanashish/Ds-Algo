package com.raajan.graph.dfs.cycle;

import java.util.List;

import com.raajan.graph.Graph;

/**
 * https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * 
 * Detect the cycle in undirected graph
 * 
 * @author raajan
 *
 */
public class DetectCycleUnDirectedGraph {
	public static void main(String[] args) {
		int[][] array = { { 0 } };
		List<Integer>[] adjList = Graph.intialise(array.length, array);
		System.out.println(detectCycle(adjList));
	}

	private static boolean detectCycle(List<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		for (int v = 0; v < adjList.length; v++) {
			if (!visited[v]) {
				if (detectCycle(v, -1, adjList, visited)) {
					return true;
				}
			}
		}
		return false;

	}

	private static boolean detectCycle(int v, int p, List<Integer>[] adjList, boolean[] visited) {
		visited[v] = true;

		for (int w : adjList[v]) {
			// If an adjacent is not
			// visited, then recur for that
			// adjacent
			if (!visited[w]) {
				if (detectCycle(w, v, adjList, visited)) {
					return true;
				}
			}
			// If an adjacent is visited
			// and not parent of current
			// vertex, then there is a cycle.
			else if (p != w) {
				return true;
			}

		}
		return false;
	}

}
