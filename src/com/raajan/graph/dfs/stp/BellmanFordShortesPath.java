package com.raajan.graph.dfs.stp;

import java.util.Arrays;

import com.raajan.graph.Graph;
import com.raajan.graph.Graph.Edge;

public class BellmanFordShortesPath {

	public static void main(String[] args) {
		int[][] edgeArray = new int[][] { { 0, 1, -1 }, { 0, 2, 4 }, { 1, 2, 3 }, { 1, 3, 2 }, { 1, 4, 2 }, { 3, 2, 5 },
				{ 3, 1, -2 }, { 4, 3, 1 } };

		Graph graph = Graph.intialiseWithEdges(5, edgeArray);
		BellmanFordShortesPath myObj = new BellmanFordShortesPath();
		int[] dist = myObj.mstUsingBellman(graph, 0);
		System.out.println(Arrays.toString(dist));
	}

	public int[] mstUsingBellman(Graph graph, int source) {
		int V = graph.V;

		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		Edge[] edges = graph.edges;
		for (int i = 1; i < dist.length; i++) {
			for (int j = 0; j < edges.length; j++) {
				int u = edges[j].u;
				int v = edges[j].v;
				int w = edges[j].w;

				dist[v] = Math.min(dist[v], dist[u] + w);
			}
		}
		// Step 3: check for negative-weight cycles. The above
		// step guarantees shortest distances if graph doesn't
		// contain negative weight cycle. If we get a shorter
		// path, then there is a cycle.
		for (int j = 0; j < edges.length; ++j) {
			int u = edges[j].u;
			int v = edges[j].v;
			int weight = edges[j].w;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("Graph contains negative weight cycle");
			}
		}

		return dist;
	}

}
