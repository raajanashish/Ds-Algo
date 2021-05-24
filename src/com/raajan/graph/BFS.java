package com.raajan.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

	public static void main(String[] args) {
		int[][] array = { { 1, 2 }, { 0, 3 }, { 0, 3, 5, 6 }, { 1, 2, 4 }, { 3 }, { 2 }, { 2 } };
		int V = array.length;
		List<Integer>[] adjList = Graph.intialise(V, array);
		boolean[] visited = new boolean[V];
		bfs(0, adjList, visited);

	}

	public static void bfs(int v, List<

			Integer>[] adjList, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(v);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int s = queue.poll();
			System.out.print(s + "  ");
			Iterator<Integer> adjVert = adjList[s].listIterator();
			while (adjVert.hasNext()) {
				Integer next = adjVert.next();
				if (!visited[next.intValue()]) {
					visited[next] = true;
					queue.add(next);
				}

			}
		}

	}
}
