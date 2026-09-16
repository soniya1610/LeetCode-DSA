/*
 * Problem: Number of Sets
 * Topic: Dynamic Programming, Prefix/Suffix Sum, Combinatorics
 *
 * Approach:
 * 1. Use DP where dp[k][i] represents the number of ways
 *    to form k sets using positions from i onward.
 * 2. For k = 0, every valid starting position has one way.
 * 3. For each k:
 *      - skip: dp[k][i + 1]
 *      - take: sum of dp[k - 1][j] for all j > i
 * 4. Use a suffix sum array (prevRowSum) to calculate the
 *    "take" transition efficiently instead of looping over
 *    all possible j.
 * 5. Take the result modulo 1e9 + 7.
 *
 * Time Complexity: O(n * K)
 * Space Complexity: O(n * K)
 */

class Solution {
    static final int MOD = 1_000_000_007;

    int[][] dp = new int[1001][1001];

    public int numberOfSets(int n, int K) {

        // Base case: 0 sets
        for (int i = 0; i <= n; i++) {
            dp[0][i] = (i < n) ? 1 : 0;
        }

        for (int k = 1; k <= K; k++) {

            int[] prevRowSum = new int[n + 1];

            // Suffix sum of the previous DP row
            for (int x = n - 1; x >= 0; x--) {
                prevRowSum[x] =
                        (int) ((prevRowSum[x + 1] + dp[k - 1][x]) % MOD);
            }

            for (int i = n - 1; i >= 0; i--) {

                // Skip current position
                int skip = dp[k][i + 1];

                // Choose a position after i
                int take = prevRowSum[i + 1];

                dp[k][i] = (take + skip) % MOD;
            }
        }

        return dp[K][0];
    }
}
