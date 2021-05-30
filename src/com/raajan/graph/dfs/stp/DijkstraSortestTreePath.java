package com.raajan.graph.dfs.stp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Dijkstra’s algorithm is very similar to Prim’s algorithm for minimum spanning
 * tree. Like Prim’s MST, we generate a SPT (shortest path tree) with given
 * source as root. We maintain two sets, one set contains vertices included in
 * shortest path tree, other set includes vertices not yet included in shortest
 * path tree. At every step of the algorithm, we find a vertex which is in the
 * other set (set of not yet included) and has a minimum distance from the
 * source. Below are the detailed steps used in Dijkstra’s algorithm to find the
 * shortest path from a single source vertex to all other vertices in the given
 * graph.
 * 
 * @author raajan
 *
 */
public class DijkstraSortestTreePath {
	public static void main(String[] args) {
		int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		DijkstraSortestTreePath myObj = new DijkstraSortestTreePath();
		int[] res = myObj.dijkstra(graph, 0);
		System.out.println(Arrays.toString(res));

	}

	public int[] dijkstra(int[][] graph, int s) {
		int[] dist = new int[graph.length];
		Set<Integer> stpSet = new HashSet<Integer>();

		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[0] = 0;

		for (int i = 0; i < graph.length; i++) {
			int u = minDist(dist, stpSet);
			stpSet.add(u);

			for (int v = 0; v < dist.length; v++) {
				if (!stpSet.contains(v) && graph[u][v] != 0 && dist[v] > graph[u][v] + dist[u]) {
					dist[v] = graph[u][v] + dist[u];
				}
			}
		}

		return dist;
	}

	private int minDist(int[] dist, Set<Integer> stpSet) {
		int minIndex = -1;
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < dist.length; i++) {
			if (!stpSet.contains(i) && dist[i] < minDist) {
				minDist = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}
