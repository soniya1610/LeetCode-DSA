// Problem Name:
// 1406. Stone Game III

// Topic:
// Dynamic Programming, Game Theory

// Approach:
// Step 1:
// Let:
//
// dp[i]
//
// represent the maximum score difference
// (current player - opponent)
// starting from index i.
//
// Since only the next three states are needed,
// use a rolling array of size 4.
//
// Step 2:
// Process the array from right to left.
//
// At every index, the current player can
// take 1, 2, or 3 stones.
//
// Step 3:
// Maintain the sum of the chosen stones.
//
// If the player takes j stones:
//
// currentScore = sum
//
// The opponent will then achieve
// dp[i + j] score difference.
//
// Therefore, the current player's
// score difference becomes:
//
// sum - dp[i + j]
//
// Choose the maximum among all
// valid choices.
//
// Step 4:
// Store the best score difference
// for the current index.
//
// Step 5:
// After processing:
//
// dp[0] > 0  -> Alice wins
// dp[0] = 0  -> Tie
// dp[0] < 0  -> Bob wins

// Time Complexity:
// O(n)
//
// Each index considers at most
// three choices.

// Space Complexity:
// O(1)
//
// Uses a rolling DP array of size 4.

// Java Solution:
class Solution {

    static final String[] result = {
        "Bob",
        "Tie",
        "Alice"
    };

    public String stoneGameIII(int[] A) {

        int n = A.length;

        // Rolling DP
        int[] dp = {0, 0, 0, 0};

        for (int i = n - 1; i >= 0; i--) {

            dp[i & 3] = Integer.MIN_VALUE;

            int sum = 0;

            // Try taking 1, 2, or 3 stones
            for (int take = 1; take <= 3 && i + take <= n; take++) {

                sum += A[i + take - 1];

                dp[i & 3] = Math.max(
                    dp[i & 3],
                    sum - dp[(i + take) & 3]
                );
            }
        }

        return result[Integer.signum(dp[0]) + 1];
    }
}