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
	int V;
	public static List<Integer>[] adjList;
	public static int[][] adjMat;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		adjList = new ArrayList[V];
	}

	public boolean addEdge(int u, int v) {
		return adjList[u].add(new Integer(v)) && adjList[v].add(new Integer(u));
	}

	public static List<Integer>[] intialise(int V,int[][] array) {
		new Graph(V);
		for (int i = 0; i < array.length; i++) {
			adjList[i] = new ArrayList<Integer>();
			for (int j = 0; j < array[i].length; j++) {
				adjList[i].add(new Integer(array[i][j]));
			}
		}
		return adjList;
	}
}
