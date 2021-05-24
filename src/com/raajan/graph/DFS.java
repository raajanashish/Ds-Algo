package com.raajan.graph;

import java.util.Iterator;
import java.util.List;

public class DFS {

	public static void main(String[] args) {
		int[][] array = { { 1, 2 }, { 0, 3 }, { 0, 3, 5, 6 }, { 1, 2, 4 }, { 3 }, { 2 }, { 2 }, { 8 }, { 8 } };
		int V = array.length;
		List<Integer>[] adjList = Graph.intialise(V,array);
		boolean[] visited = new boolean[V];
		dfs(0, adjList, visited);
		System.out.println();
		dfsDisconnected(adjList, visited);

	}

	public static void dfs(int sourceVert, List<Integer>[] adjList, boolean[] visited) {
		visited[sourceVert] = true;
		System.out.print(sourceVert + "  ");
		Iterator<Integer> adjVerts = adjList[sourceVert].listIterator();
		while (adjVerts.hasNext()) {
			int adjV = adjVerts.next();
			if (!visited[adjV]) {
				dfs(adjV, adjList, visited);
			}
		}
	}

	public static void dfsDisconnected(List<Integer>[] adjList, boolean[] visited) {
		for (int v = 0; v < adjList.length; v++) {
			dfs(v, adjList, visited);
		}
	}

}
