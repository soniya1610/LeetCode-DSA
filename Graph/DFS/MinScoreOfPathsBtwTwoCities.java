package Graph.DFS;
import java.util.*; 
// Problem Name:
// 2492. Minimum Score of a Path Between Two Cities

// Approach:
// Step 1:
// Build an undirected graph using the given roads.
//
// Each road is represented as:
// city1 <--> city2 with distance (score)
//
// Step 2:
// Start DFS from city 1.
//
// Since it is guaranteed that there is a path from city 1 to city n,
// every city reachable from city 1 belongs to the same connected component
// containing the destination.
//
// Step 3:
// During DFS,
// visit every reachable city and examine every road.
//
// Keep updating the minimum road distance encountered.
//
// Step 4:
// Return the minimum road distance found.
//
// Why does this work?
// The score of a path is the minimum edge on that path.
//
// Since we can revisit cities and roads, the minimum possible score
// between city 1 and city n is simply the smallest edge present in
// the connected component containing city 1.

// Time Complexity:
// O(V + E)
//
// V = number of cities
// E = number of roads

// Space Complexity:
// O(V + E)

// Java Solution:
class Solution {

    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private void dfs(Map<Integer, List<Pair>> adj,
                     int u,
                     boolean[] visited,
                     int[] result) {

        visited[u] = true;

        for (Pair p : adj.getOrDefault(u, new ArrayList<>())) {
            int v = p.node;
            int c = p.cost;

            result[0] = Math.min(result[0], c);

            if (!visited[v]) {
                dfs(adj, v, visited, result);
            }
        }
    }

    public int minScore(int n, int[][] roads) {

        Map<Integer, List<Pair>> adj = new HashMap<>();

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int c = road[2];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair(v, c));
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair(u, c));
        }

        boolean[] visited = new boolean[n + 1];
        int[] result = {Integer.MAX_VALUE};

        dfs(adj, 1, visited, result);

        return result[0];
    }
}