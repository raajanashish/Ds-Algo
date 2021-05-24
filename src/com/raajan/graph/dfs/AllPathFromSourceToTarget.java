package com.raajan.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 * 
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
 * all possible paths from node 0 to node n - 1, and return them in any order.
 * 
 * The graph is given as follows: graph[i] is a list of all nodes you can visit
 * from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * 
 * @author raajan
 *
 */
public class AllPathFromSourceToTarget {
	List<List<Integer>> paths = new ArrayList<>();
	List<Integer> curPath = new ArrayList<>();
	int t;

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		t = graph.length - 1;
		curPath.add(0);
		pathUtil(graph, 0);
		return paths;
	}

	public void pathUtil(int[][] graph, int s) {
		if (s == t) {
			List<Integer> temp = new ArrayList<>();
			temp.addAll(curPath);
			paths.add(temp);
		} else {
			int[] adjVert = graph[s];
			for (int i = 0; i < adjVert.length; i++) {
				curPath.add(adjVert[i]);
				pathUtil(graph, adjVert[i]);
				curPath.remove(curPath.size() - 1);
			}
		}
	}
}
