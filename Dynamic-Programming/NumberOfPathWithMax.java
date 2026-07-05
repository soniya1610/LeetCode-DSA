import java.util.*;
// Problem Name:
// 1301. Number of Paths with Maximum Score

// Approach:
// Step 1:
// Use Dynamic Programming.
//
// Maintain two DP tables:
//
// tScore[i][j] = Maximum score that can be collected to reach cell (i,j).
//
// tPaths[i][j] = Number of paths that achieve tScore[i][j].
//
// Step 2:
// Start from the top-left cell ('E').
//
// Traverse the board row by row.
//
// Ignore blocked cells ('X').
//
// Step 3:
// For every valid cell, consider three previous cells:
//
// - Up        (i-1, j)
// - Left      (i, j-1)
// - Diagonal  (i-1, j-1)
//
// These represent the reverse of the allowed moves
// (Down, Right and Down-Right from the original problem).
//
// Step 4:
// For each previous cell:
// - If reachable, add current cell's value
//   ('S' and 'E' contribute 0).
//
// Step 5:
// Choose the maximum score among the three possible moves.
//
// - If only one direction gives the maximum score,
//   take its score and path count.
//
// - If multiple directions give the same maximum score,
//   sum their path counts.
//
// Apply modulo 1e9+7.
//
// Step 6:
// The answer is:
//
// tScore[n-1][n-1] -> Maximum score
//
// tPaths[n-1][n-1] -> Number of maximum-score paths

// Time Complexity:
// O(n²)

// Space Complexity:
// O(n²)

// Java Solution:
class Solution {
    int n;
    int MOD = 1_000_000_007;

    private int getIntFromChar(char ch) {
        return ch != 'S' ? ch - '0' : 0;
    }

    private boolean isValid(int i, int j, List<String> board) {
        return i >= 0 && i < n &&
               j >= 0 && j < n &&
               board.get(i).charAt(j) != 'X';
    }

    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        int[][] tScore = new int[n][n];
        int[][] tPaths = new int[n][n];

        tScore[0][0] = 0;
        tPaths[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                char ch = board.get(i).charAt(j);

                if (ch == 'E' || ch == 'X')
                    continue;

                int upScore = 0, upPaths = 0;
                int leftScore = 0, leftPaths = 0;
                int diagScore = 0, diagPaths = 0;

                if (isValid(i - 1, j, board)) {
                    upScore = tScore[i - 1][j];
                    upPaths = tPaths[i - 1][j];

                    if (upPaths > 0)
                        upScore += getIntFromChar(ch);
                }

                if (isValid(i, j - 1, board)) {
                    leftScore = tScore[i][j - 1];
                    leftPaths = tPaths[i][j - 1];

                    if (leftPaths > 0)
                        leftScore += getIntFromChar(ch);
                }

                if (isValid(i - 1, j - 1, board)) {
                    diagScore = tScore[i - 1][j - 1];
                    diagPaths = tPaths[i - 1][j - 1];

                    if (diagPaths > 0)
                        diagScore += getIntFromChar(ch);
                }

                int bestScore;
                int bestPaths;

                if (upScore == leftScore && leftScore == diagScore) {
                    bestScore = upScore;
                    bestPaths = upPaths + leftPaths + diagPaths;
                } else if (upScore == leftScore) {
                    bestScore = upScore;
                    bestPaths = upPaths + leftPaths;

                    if (diagScore > bestScore ||
                        (diagScore == bestScore && diagPaths > bestPaths)) {
                        bestScore = diagScore;
                        bestPaths = diagPaths;
                    }

                } else if (leftScore == diagScore) {
                    bestScore = leftScore;
                    bestPaths = leftPaths + diagPaths;

                    if (upScore > bestScore ||
                        (upScore == bestScore && upPaths > bestPaths)) {
                        bestScore = upScore;
                        bestPaths = upPaths;
                    }

                } else {
                    bestScore = upScore;
                    bestPaths = upPaths;

                    if (leftScore > bestScore ||
                        (leftScore == bestScore && leftPaths > bestPaths)) {
                        bestScore = leftScore;
                        bestPaths = leftPaths;
                    }

                    if (diagScore > bestScore ||
                        (diagScore == bestScore && diagPaths > bestPaths)) {
                        bestScore = diagScore;
                        bestPaths = diagPaths;
                    }
                }

                tScore[i][j] = bestScore;
                tPaths[i][j] = bestPaths % MOD;
            }
        }

        return new int[]{
            tScore[n - 1][n - 1],
            tPaths[n - 1][n - 1]
        };
    }
}