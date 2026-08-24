// Problem Name:
// 1872. Stone Game VIII

// Topic:
// Dynamic Programming, Prefix Sum, Game Theory

// Approach:
// First calculate the prefix sum of the stones.
//
// prefixSum[i] represents the sum of stones[0...i].
//
// Use DP where:
//     t[i] = maximum score difference the current player
//            can achieve starting from index i.
//
// Base Case:
//     t[n - 1] = prefixSum[n - 1]
//
// For every i from n - 2 down to 1:
//
//     take = prefixSum[i] - t[i + 1]
//     skip = t[i + 1]
//
// The current player chooses the better option:
//
//     t[i] = max(take, skip)
//
// Finally, return t[1].
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(n)

class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Prefix Sum
        int[] prefixSum = new int[n];

        prefixSum[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }

        // DP
        int[] t = new int[n];

        // Base case
        t[n - 1] = prefixSum[n - 1];

        for (int i = n - 2; i >= 1; i--) {

            int take = prefixSum[i] - t[i + 1];
            int skip = t[i + 1];

            t[i] = Math.max(take, skip);
        }

        return t[1];
    }
}