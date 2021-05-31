package com.raajan.graph.dfs.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.raajan.graph.Graph.Edge;

/**
 * This is implementaion of prims algorithim of spanning tree construction.
 * 
 * @author raajan
 *
 */
public class MstUsingPrimsAlgo {
	public static void main(String[] args) {
		int[][] adjMat = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },
				{ 0, 5, 7, 9, 0 } };
		MstUsingPrimsAlgo myObj = new MstUsingPrimsAlgo();
		myObj.mstUsingPrims(adjMat);
	}

	public void mstUsingPrims(int[][] adjMat) {
		if (adjMat.length == 0) {
			return;
		}

		int[] parent = new int[adjMat.length];
		int[] dist = new int[adjMat.length];
		HashSet<Integer> mstSet = new HashSet<Integer>();
		// initialising the distance array
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// adding the first vertex
		dist[0]=0;
		parent[0] = -1;
		while (mstSet.size() != adjMat.length) {
			int u = getMin(dist, mstSet);
			mstSet.add(u);

			for (int v = 0; v < adjMat.length; v++) {
				if (adjMat[u][v] != 0 && !mstSet.contains(v) && adjMat[u][v] < dist[v]) {
					dist[v] = adjMat[u][v];
					parent[v] = u;
				}
			}
		}

		List<Edge> edges = new ArrayList<Edge>();
		for (int v = 1; v < parent.length; v++) {
			int u = parent[v];
			System.out.println(u + " --> " + v + "  " + adjMat[u][v]);
		}

	}

	
	
	
	private int getMin(int[] dist, HashSet<Integer> mstSet) {
		int minValue = Integer.MAX_VALUE;
		int minKey = -1;
		for (int i = 0; i < dist.length; i++) {
			if (!mstSet.contains(i) && dist[i] < minValue) {
				minValue = dist[i];
				minKey = i;
			}
		}
		return minKey;
	}

}
