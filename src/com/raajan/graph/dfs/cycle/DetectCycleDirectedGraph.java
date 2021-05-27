package com.raajan.graph.dfs.cycle;

import java.util.Iterator;
import java.util.List;

import com.raajan.graph.Graph;

/**
 * 
 * https://leetcode.com/problems/find-eventual-safe-states/
 * 
 *  This will etect the
 * cycle in graph
 * 
 * @author raajan
 *
 */
public class DetectCycleDirectedGraph {

	public static void main(String[] args) {
		int[][] array = { { 1 }, { 2, 3 }, { 4, 1 }, {}, { 3 } };
		List<Integer>[] adjList = Graph.intialise(array.length, array);
		System.out.println(detectCycle(adjList));
	}

	public static boolean detectCycle(List<Integer>[] adjList) {
		boolean[] recStack = new boolean[adjList.length];
		boolean[] visited = new boolean[adjList.length];
		for (int i = 0; i < adjList.length; i++) {
			if (detectCycleUtil(i, adjList, recStack, visited)) {
				return true;
			}
		}
		return false;

	}

	private static boolean detectCycleUtil(int i, List<Integer>[] adjList, boolean[] recStack, boolean[] visited) {
		if (recStack[i]) {
			return true;
		}
		// this is just to avoid re detection for a node.
		if (visited[i]) {
			return false;
		}
		recStack[i] = true;
		visited[i] = true;

		Iterator<Integer> it = adjList[i].listIterator();
		while (it.hasNext()) {
			int v = it.next().intValue();
			if (detectCycleUtil(v, adjList, recStack, visited)) {
				return true;

			}
		}
		recStack[i] = false;
		return false;
	}
}
