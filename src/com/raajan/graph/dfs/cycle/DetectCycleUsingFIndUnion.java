package com.raajan.graph.dfs.cycle;

import java.util.stream.IntStream;

import com.raajan.graph.Graph;
import com.raajan.graph.Graph.Edge;

/**
 * 
 * https://www.geeksforgeeks.org/union-find/
 * 
 * A disjoint-set data structure is a data structure that keeps track of a set
 * of elements partitioned into a number of disjoint (non-overlapping) subsets.
 * A union-find algorithm is an algorithm that performs two useful operations on
 * such a data structure: Find: Determine which subset a particular element is
 * in. This can be used for determining if two elements are in the same subset.
 * Union: Join two subsets into a single subset. In this post, we will discuss
 * the application of Disjoint Set Data Structure. The application is to check
 * whether a given graph contains a cycle or not. Union-Find Algorithm can be
 * used to check whether an undirected graph contains cycle or not. Note that we
 * have discussed an algorithm to detect cycle. This is another method based on
 * Union-Find. This method assumes that the graph doesn’t contain any
 * self-loops. We can keep track of the subsets in a 1D array, let’s call it
 * parent[]. Let us consider the following graph
 * 
 * @author raajan
 *
 */

public class DetectCycleUsingFIndUnion {
	static int[] parent;

	public static void main(String[] args) {
		int[][] array = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 4 },{1,0} };

		Graph graph = Graph.intialiseWithEdges(5, array);
		System.out.println(detectCycle(graph.V, graph.edges));
	}

	public static boolean detectCycle(int vs, Edge[] edges) {
		parent = new int[vs];
		for (int i = 0; i < vs; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < edges.length; i++) {
			Edge edge = edges[i];
			int uParent = find(parent, edge.u);
			int vParent = find(parent, edge.v);
			if (uParent == vParent) {
				return true;
			}
			union(parent, uParent, vParent);

		}
		return false;
	}

	public static int find(int[] parent, int i) {
		if (parent[i] == i) {
			return i;
		}
		return find(parent, parent[i]);
	}

	public static int union(int[] parent, int u, int v) {
		return parent[u] = v;

	}
}
