// Problem Name:
// 486. Predict the Winner

// Topic:
// Dynamic Programming, Recursion, Game Theory, Memoization

// Approach:
// Step 1:
// If the array length is even,
// Player 1 can always guarantee a win.
//
// So, return true immediately.
//
// Step 2:
// Define:
//
// maxDiff(i, j)
//
// = maximum score difference
// (current player - opponent)
// that can be achieved using
// the subarray A[i...j].
//
// Step 3:
// Base Case:
//
// If only one element remains,
// the current player picks it.
//
// maxDiff(i, i) = A[i]
//
// Step 4:
// Recursive Choices:
//
// Choice 1:
// Pick the left element.
//
// Score difference:
//
// A[i] - maxDiff(i + 1, j)
//
// Choice 2:
// Pick the right element.
//
// Score difference:
//
// A[j] - maxDiff(i, j - 1)
//
// Take the maximum of both choices.
//
// Step 5:
// Store computed results in a DP table
// to avoid recomputation.
//
// Step 6:
// If the final score difference is
// non-negative, Player 1 can win
// or tie, so return true.

// Time Complexity:
// O(n²)
//
// There are O(n²) states,
// each computed once.

// Space Complexity:
// O(n²)
// (Memoization table)
// + O(n) recursion stack

// Java Solution:
class Solution {

    public boolean predictTheWinner(int[] A) {

        int n = A.length;

        // Player 1 always has a winning strategy
        // when the number of elements is even.
        if ((n & 1) == 0)
            return true;

        int[][] dp = new int[n][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return maxDiff(0, n - 1, A, dp) >= 0;
    }

    private int maxDiff(int i, int j, int[] A, int[][] dp) {

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i == j)
            return dp[i][j] = A[i];

        int pickLeft = A[i] - maxDiff(i + 1, j, A, dp);

        int pickRight = A[j] - maxDiff(i, j - 1, A, dp);

        return dp[i][j] = Math.max(pickLeft, pickRight);
    }
}