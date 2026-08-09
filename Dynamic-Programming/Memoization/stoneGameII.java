// Problem Name:
// 1140. Stone Game II

// Topic:
// Dynamic Programming, Game Theory, Memoization, Recursion

// Approach:
// We use recursion with memoization.
//
// State:
// solveForAlice(piles, person, i, M)
//
// person = 1 -> Alice's turn
// person = 0 -> Bob's turn
//
// i = current index in piles
// M = current maximum allowed parameter
//
// On each turn, the player can take between
// 1 and 2 * M piles.
//
// Step 1:
// If i reaches the end of the array,
// there are no stones left to take.
//
// Step 2:
// For Alice's turn, we maximize the number
// of stones Alice can collect.
//
// For every possible x:
//
// stones += piles[i + x - 1]
//
// Alice takes these stones and the next state
// becomes:
//
// solveForAlice(..., 0, i + x, max(M, x))
//
// Step 3:
// For Bob's turn, Bob tries to minimize
// Alice's final number of stones.
//
// Therefore, we take the minimum over all
// possible choices.
//
// Step 4:
// Store every computed state in the 3D
// memoization array t to avoid recomputation.
//
// The state is:
//
// t[person][i][M]

// Time Complexity:
// O(n^3)
//
// There are O(2 * n * n) states and
// each state can try up to O(n) choices.

// Space Complexity:
// O(n^2)
//
// The memoization table contains
// O(2 * n * n) states.

// Java Solution:
class Solution {

    private int n;

    private int[][][] t = new int[2][101][101];

    private int solveForAlice(int[] piles,
                              int person,
                              int i,
                              int M) {

        // No piles left
        if (i >= n) {
            return 0;
        }

        // Already computed
        if (t[person][i][M] != -1) {
            return t[person][i][M];
        }

        // Alice maximizes her stones.
        // Bob minimizes Alice's stones.
        int result = (person == 1)
                ? -1
                : Integer.MAX_VALUE;

        int stones = 0;

        // Can take from 1 to 2 * M piles
        for (int x = 1;
             x <= Math.min(2 * M, n - i);
             x++) {

            stones += piles[i + x - 1];

            if (person == 1) {

                // Alice's turn:
                // maximize Alice's total.
                result = Math.max(
                    result,
                    stones + solveForAlice(
                        piles,
                        0,
                        i + x,
                        Math.max(M, x)
                    )
                );

            } else {

                // Bob's turn:
                // minimize Alice's total.
                result = Math.min(
                    result,
                    solveForAlice(
                        piles,
                        1,
                        i + x,
                        Math.max(M, x)
                    )
                );
            }
        }

        return t[person][i][M] = result;
    }

    public int stoneGameII(int[] piles) {

        n = piles.length;

        // Initialize memoization table
        // with -1 to represent uncomputed states.
        for (int[][] arr2D : t) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }

        // Alice starts from index 0 with M = 1.
        return solveForAlice(piles, 1, 0, 1);
    }
}