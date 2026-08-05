// Problem Name:
// 3310. Remove Methods From Project

// Topic:
// Graph, DFS, Topological Concepts

// Approach:
// Step 1:
// Build the directed graph.
//
// An edge:
//
// u -> v
//
// means method u invokes method v.
//
// Also compute the indegree of every method.
//
// Step 2:
// Start a DFS from the suspicious method k.
//
// Every reachable method is marked as suspicious.
//
// While traversing,
// decrease the indegree of every visited neighbor.
//
// This effectively removes edges
// coming from suspicious methods.
//
// Step 3:
// Check whether any suspicious method
// still has a positive indegree.
//
// If yes, it means a non-suspicious method
// still invokes a suspicious method.
//
// Therefore, the suspicious methods
// cannot be removed safely.
//
// Return all methods.
//
// Step 4:
// Otherwise,
// all suspicious methods are isolated.
//
// Return only the methods
// that are not marked suspicious.

// Time Complexity:
// O(V + E)
//
// V = number of methods
// E = number of invocations

// Space Complexity:
// O(V + E)
//
// O(V + E) for the adjacency list
// O(V) for the indegree and visited arrays

// Java Solution:
class Solution {

    public void dfs(int curr,
                    List<List<Integer>> adj,
                    int[] inDegree,
                    boolean[] suspicious) {

        suspicious[curr] = true;

        for (int neighbor : adj.get(curr)) {

            inDegree[neighbor]--;

            if (!suspicious[neighbor]) {
                dfs(neighbor, adj, inDegree, suspicious);
            }
        }
    }

    public List<Integer> remainingMethods(int n,
                                          int k,
                                          int[][] invocations) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n];
        boolean[] suspicious = new boolean[n];

        // Build graph
        for (int[] edge : invocations) {

            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            inDegree[v]++;
        }

        // Mark all suspicious methods
        dfs(k, adj, inDegree, suspicious);

        List<Integer> result = new ArrayList<>();

        boolean cannotRemove = false;

        for (int i = 0; i < n; i++) {

            if (suspicious[i] && inDegree[i] > 0) {
                cannotRemove = true;
                break;
            }

            if (!suspicious[i]) {
                result.add(i);
            }
        }

        if (cannotRemove) {

            List<Integer> allMethods = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                allMethods.add(i);
            }

            return allMethods;
        }

        return result;
    }
}