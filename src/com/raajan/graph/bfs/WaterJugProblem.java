package com.raajan.graph.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * https://leetcode.com/problems/water-and-jug-problem/
 * 
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters.
 * There is an infinite amount of water supply available. Determine whether it
 * is possible to measure exactly targetCapacity liters using these two jugs.
 * 
 * If targetCapacity liters of water are measurable, you must have
 * targetCapacity liters of water contained within one or both buckets by the
 * end.
 * 
 * Operations allowed:
 * 
 * Fill any of the jugs with water. Empty any of the jugs. Pour water from one
 * jug into another till the other jug is completely full, or the first jug
 * itself is empty.
 * 
 * @author raajan
 *
 */
public class WaterJugProblem {

	public boolean canMeasureWater(int x, int y, int t) {
		if (x + y < t) {
			return false;
		}
		Queue<State> queue = new ArrayDeque<>();
		Set<State> visited = new HashSet<>();
		State init = new State(0, 0);
		queue.offer(init);
		visited.add(init);
		while (!queue.isEmpty()) {
			State state = queue.poll();
			if (state.a + state.b == t) {
				return true;
			}
			int a = state.a;
			int b = state.b;
			State[] nextStates = { new State(0, b), new State(a, 0), new State(x, b), new State(a, y),
					new State(Math.min(a + b, x), a + b <= x ? 0 : b - (x - a)),
					new State(a + b <= y ? 0 : a - (y - b), Math.min(a + b, y)) };
			for (int i = 0; i < 6; i++) {
				if (!visited.contains(nextStates[i])) {
					queue.add(nextStates[i]);
					visited.add(nextStates[i]);
				}
			}
		}
		return false;
	}

	class State {
		public int a;
		public int b;

		State(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int hashCode() {
			return 31 * a + b;
		}

		public boolean equals(Object o) {
			State other = (State) o;
			return this.a == other.a && this.b == other.b;
		}

	}
}
