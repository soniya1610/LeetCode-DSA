// Problem Name:
// 3336. Find the Number of Subsequences With Equal GCD

// Topic:
// Dynamic Programming (DP), GCD, Math, Space Optimization

// Approach (Space Optimized DP):
// Step 1:
// Let:
//
// dp[firstGCD][secondGCD]
//
// represent the number of ways to process the remaining elements
// such that:
//
// - First subsequence has GCD = firstGCD
// - Second subsequence has GCD = secondGCD
//
// Step 2:
// Initialize the base case.
//
// After processing all elements:
//
// - Both subsequences must be non-empty.
// - Their GCDs must be equal.
//
// Only then the answer is 1,
// otherwise 0.
//
// Step 3:
// Process the array from right to left.
//
// For every element nums[i], there are three choices:
//
// 1. Skip the element.
//
// 2. Put it into the first subsequence.
//
//    New GCD = gcd(firstGCD, nums[i])
//
// 3. Put it into the second subsequence.
//
//    New GCD = gcd(secondGCD, nums[i])
//
// Sum all three possibilities.
//
// Step 4:
// Since each DP layer depends only on the next layer,
// store only two 2D arrays:
//
// prev = DP for i + 1
// curr = DP for i
//
// This reduces space from O(n × M²)
// to O(M²).

// Time Complexity:
// O(n × M²)
//
// M = Maximum element in nums

// Space Complexity:
// O(M²)

// Java Solution:
import java.util.*;
class Solution {
    int MOD = 1_000_000_007;

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subsequencePairCount(int[] nums) {

        int maxEl = 0;
        for (int x : nums)
            maxEl = Math.max(maxEl, x);

        int[][] prev = new int[maxEl + 1][maxEl + 1];

        // Base case
        for (int first = 0; first <= maxEl; first++) {
            for (int second = 0; second <= maxEl; second++) {

                boolean bothNonEmpty = (first != 0 && second != 0);
                boolean gcdsMatch = (first == second);

                prev[first][second] =
                        (bothNonEmpty && gcdsMatch) ? 1 : 0;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {

            int[][] curr = new int[maxEl + 1][maxEl + 1];

            for (int first = 0; first <= maxEl; first++) {
                for (int second = 0; second <= maxEl; second++) {

                    int skip = prev[first][second];

                    int take1 =
                            prev[gcd(first, nums[i])][second];

                    int take2 =
                            prev[first][gcd(second, nums[i])];

                    curr[first][second] =
                            (int) ((0L + skip + take1 + take2) % MOD);
                }
            }

            prev = curr;
        }

        return prev[0][0];
    }
}