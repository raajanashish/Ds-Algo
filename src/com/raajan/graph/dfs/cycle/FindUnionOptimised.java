package com.raajan.graph.dfs.cycle;

public class FindUnionOptimised {
	public Subset[] subsetArray;

	public int find(Subset[] subsetArray, int i) {
		if (subsetArray[i].parent != i) {
			subsetArray[i].parent = find(subsetArray, subsetArray[i].parent);
		}
		return subsetArray[i].parent;
	}

	public void union(Subset[] subsetArray, int aRoot, int bRoot) {
		if (subsetArray[aRoot].rank < subsetArray[bRoot].rank)
			subsetArray[aRoot].parent = bRoot;

		else {
			subsetArray[bRoot].parent = aRoot;
			if (aRoot == bRoot) {
				subsetArray[aRoot].rank++;
			}
		}
	}

	public static FindUnionOptimised initialise(int V) {
		FindUnionOptimised fu = new FindUnionOptimised();
		fu.subsetArray = new Subset[V];
		for (int i = 0; i < fu.subsetArray.length; i++) {
			fu.subsetArray[i] = fu.new Subset(i, 0);
		}
		return fu;
	}

	public class Subset {
		int parent;
		int rank;

		public Subset() {
			// TODO Auto-generated constructor stub
		}

		public Subset(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;

		}
	}

}
