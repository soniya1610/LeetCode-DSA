// Problem Name:
// 1510. Stone Game IV

// Topic:
// Dynamic Programming, Game Theory

// Approach:
// We use Bottom-Up Dynamic Programming.
//
// dp[i] represents whether the current player
// can win when there are i stones remaining.
//
// Base Case:
// dp[0] = false
//
// If there are 0 stones left, the player cannot
// make any move, so the current player loses.
//
// For every i from 1 to n:
//
// Try taking every possible perfect square:
//
// 1², 2², 3², ...
//
// If there exists a square k² such that:
//
// dp[i - k²] == false
//
// then the current player can take k² stones
// and leave a losing position for the opponent.
//
// Therefore:
//
// dp[i] = true
//
// If no such square exists, dp[i] remains false.
//
// Finally, return dp[n].

// Time Complexity:
// O(n * sqrt(n))

// Space Complexity:
// O(n)

// Java Solution:
class Solution {

    public boolean winnerSquareGame(int n) {

        boolean[] dp = new boolean[n + 1];

        // Base case:
        // dp[0] = false

        for (int i = 1; i <= n; i++) {

            // Try every possible perfect square
            for (int k = 1; k * k <= i; k++) {

                int square = k * k;

                // If the opponent reaches a losing state,
                // current player can win.
                if (!dp[i - square]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}