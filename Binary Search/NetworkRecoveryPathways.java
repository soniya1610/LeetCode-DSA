// Problem Name:
// Find Maximum Path Score

// Approach:
// Step 1:
// Build adjacency list using only online nodes.
// Ignore edges where source or destination node is offline.
//
// Each edge contains:
// u -> v with weight w
//
// Step 2:
// Binary Search on answer (path score)
//
// Path score = minimum edge weight present in chosen path.
// We want to maximize this minimum edge weight.
//
// Search range:
// left  = minimum edge weight
// right = maximum edge weight
//
// Step 3:
// For each candidate score = mid,
// check whether a valid path exists from node 0 to node n-1 such that:
// 1. Every edge used has weight >= mid
// 2. Total path cost <= k
//
// Step 4:
// Use Dijkstra in check():
//
// - Start from node 0
// - Ignore edges with weight < mid
// - Find shortest path cost to all nodes
//
// If destination reachable within k:
// -> mid is valid
// Else:
// -> mid is invalid
//
// Binary Search Logic:
// If score = mid works,
// try larger score
//
// Otherwise,
// try smaller score

// Why Binary Search?
// Because of monotonic property:
//
// If score 5 is possible,
// then score 4, 3, 2... are also possible.
//
// If score 8 is impossible,
// then 9, 10... are also impossible.

// Time Complexity:
// Binary Search * Dijkstra
// O(log(W) * (E log V))
//
// W = max edge weight
// E = edges
// V = nodes

// Space Complexity:
// O(V + E)

// Java Solution:

import java.util.*;
class Solution {

    private boolean check(int mid, int n, long k, Map<Integer, List<int[]>> adj) {
        long[] result = new long[n];
        Arrays.fill(result, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        result[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0];
            int node = (int) top[1];

            if (d > k) return false;

            if (node == n - 1) return true;

            if (d > result[node]) continue;

            for (int[] vec : adj.getOrDefault(node, Collections.emptyList())) {
                int adjNode = vec[0];
                int edgeCost = vec[1];

                if (edgeCost < mid) continue;

                if (d + edgeCost < result[adjNode]) {
                    result[adjNode] = d + edgeCost;
                    pq.offer(new long[]{d + edgeCost, adjNode});
                }
            }
        }

        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();

        int l = Integer.MAX_VALUE;
        int r = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (!online[u] || !online[v]) continue;

            adj.computeIfAbsent(u, x -> new ArrayList<>())
               .add(new int[]{v, w});

            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        int answer = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (check(mid, n, k, adj)) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return answer;
    }
}