package com.raajan.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * This class to represent the graph data structure. Its having both the ways of
 * graph implementation.
 * 
 * @author raajan
 *
 */
public class Graph {
	public int V;
	public List<Integer>[] adjList;
	public int[][] adjMat;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		adjList = new ArrayList[V];
	}

	public boolean addEdge(int u, int v) {
		return adjList[u].add(new Integer(v)) && adjList[v].add(new Integer(u));
	}

	public static List<Integer>[] intialise(int V, int[][] array) {
		Graph graph = new Graph(V);
		for (int i = 0; i < array.length; i++) {
			graph.adjList[i] = new ArrayList<Integer>();
			for (int j = 0; j < array[i].length; j++) {
				graph.adjList[i].add(new Integer(array[i][j]));
			}
		}
		return graph.adjList;
	}

	public int E;
	public Edge[] edges;

	public class Edge implements Comparable<Edge> {
		public int u;
		public int v;
		public int w;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		public Edge() {

		}

		@Override
		public int compareTo(Edge other) {
			return this.w - other.w;
		}

		@Override
		public String toString() {
			return this.u + "-->" + this.v + " : " + this.w;
		}
	}

	public Graph(int V, int E) {
		this.V = V;
		this.E = E;

		edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			edges[i] = new Edge();
		}

	}

	public static Graph intialiseWithEdges(int V, int[][] edges) {
		Graph graph = new Graph(V, edges.length);

		for (int i = 0; i < edges.length; i++) {
			int[] edge = edges[i];
			graph.edges[i].u = edge[0];
			graph.edges[i].v = edge[1];
			if (edge.length == 3) {
				graph.edges[i].w = edge[2];
			}

		}
		return graph;
	}

}
