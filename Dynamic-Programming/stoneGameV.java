// Problem Name:
// 1563. Stone Game V

// Topic:
// Dynamic Programming, Prefix Sum, Interval DP

// Approach:
// Use bottom-up interval DP.
//
// t[l][r] represents the maximum score Alice can obtain
// from the subarray stoneValue[l...r].
//
// For every interval [l, r], try every possible split point `mid`.
//
// leftSum  = sum of [l ... mid]
// rightSum = sum of [mid + 1 ... r]
//
// If leftSum < rightSum:
//     Alice must choose the left part.
//     Score = leftSum + t[l][mid]
//
// If leftSum > rightSum:
//     Alice must choose the right part.
//     Score = rightSum + t[mid + 1][r]
//
// If leftSum == rightSum:
//     Alice can choose either side.
//
// Prefix sum is used to calculate each subarray sum in O(1).
//
// Time Complexity:
// O(n^3)
//
// Space Complexity:
// O(n^2)

class Solution {
    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        // Prefix Sum
        int[] cumSum = new int[n];
        cumSum[0] = stoneValue[0];

        for (int i = 1; i < n; i++) {
            cumSum[i] = cumSum[i - 1] + stoneValue[i];
        }

        // t[l][r] = maximum score for subarray [l...r]
        int[][] t = new int[n + 1][n + 1];

        // Build intervals from smaller to larger
        for (int l = n - 1; l >= 0; l--) {

            for (int r = l + 1; r < n; r++) {

                int score = 0;

                // Try every possible split
                for (int mid = l; mid <= r - 1; mid++) {

                    int leftSum =
                        cumSum[mid] -
                        (l - 1 >= 0 ? cumSum[l - 1] : 0);

                    int rightSum =
                        cumSum[r] - cumSum[mid];

                    if (leftSum < rightSum) {

                        score = Math.max(
                            score,
                            leftSum + t[l][mid]
                        );

                    } else if (leftSum > rightSum) {

                        score = Math.max(
                            score,
                            rightSum + t[mid + 1][r]
                        );

                    } else {

                        score = Math.max(
                            score,
                            Math.max(
                                leftSum + t[l][mid],
                                rightSum + t[mid + 1][r]
                            )
                        );
                    }
                }

                t[l][r] = score;
            }
        }

        return t[0][n - 1];
    }
}