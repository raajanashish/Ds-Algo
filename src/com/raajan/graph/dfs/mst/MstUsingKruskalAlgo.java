package com.raajan.graph.dfs.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.raajan.graph.Graph;
import com.raajan.graph.Graph.Edge;
import com.raajan.graph.dfs.cycle.FindUnionOptimised;
import com.raajan.graph.dfs.cycle.FindUnionOptimised.Subset;

public class MstUsingKruskalAlgo {

	public static void main(String[] args) {

		int[][] array = { { 0, 1, 4 }, { 0, 7, 8 }, { 1, 2, 8 }, { 1, 7, 11, }, { 2, 3, 7 }, { 2, 8, 2 }, { 2, 5, 4 },
				 { 3, 4, 9 }, { 3, 5, 14 }, { 4, 5, 10 }, { 5, 6, 2 }, { 6, 7, 1, }, { 6, 8, 6 }, { 7, 8, 7 } };
		Graph graph = Graph.intialiseWithEdges(9, array);
		MstUsingKruskalAlgo myObj = new MstUsingKruskalAlgo();
		List<Edge> edges = Arrays.asList(graph.edges);
		List<Edge> mstEdges = myObj.mstUsingKruskal(graph.V, edges);
		System.out.println(mstEdges.toString());

	}

	public List<Edge> mstUsingKruskal(int V, List<Edge> edges) {
		FindUnionOptimised fu = FindUnionOptimised.initialise(V);
		Subset[] subsetArray = fu.subsetArray;
		List<Edge> mstEdges = new ArrayList<Graph.Edge>();
		// sor t the edges by weight
		Collections.sort(edges);
		int edgeCount = 0;
		Iterator<Edge> it = edges.listIterator();
		while (edgeCount < V - 1) {
			Edge edge = it.next();
			int uRoot = fu.find(subsetArray, edge.u);
			int vRoot = fu.find(subsetArray, edge.v);
			if (uRoot != vRoot) {
				edgeCount++;
				mstEdges.add(edge);
				fu.union(subsetArray, uRoot, vRoot);
			}

		}
		return mstEdges;
	}
}
