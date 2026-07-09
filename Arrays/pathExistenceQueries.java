// Problem Name:
// 3784. Path Existence Queries in a Graph I

// Topic:
// Arrays, Prefix Processing, Graph (Connected Components)

// Approach:
// Step 1:
// Build connected components.
//
// Since nums is sorted, two adjacent indices belong to the
// same connected component if:
//
// nums[i] - nums[i-1] <= maxDiff
//
// Otherwise, a new connected component starts.
//
// Store the component ID of every index.
//
// Step 2:
// Process each query.
//
// For query [u, v]:
//
// - If both indices belong to the same component,
//   a path exists.
//
// - Otherwise,
//   no path exists.
//
// Since component IDs are already computed,
// each query is answered in O(1).

// Time Complexity:
// Building components: O(n)
// Processing queries: O(q)
//
// Total:
// O(n + q)

// Space Complexity:
// O(n)

// Java Solution:
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[] component = new int[n];

        int compId = 0;
        component[0] = compId;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                compId++;
            }
            component[i] = compId;
        }

        boolean[] result = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            result[i] = (component[queries[i][0]] == component[queries[i][1]]);
        }

        return result;
    }
}