// Problem Name:
// 1260. Shift 2D Grid

// Topic:
// Arrays, Matrix, In-Place Array Manipulation, Reversal Algorithm

// Approach:
// Step 1:
// Treat the 2D matrix as a 1D array.
//
// For a flattened index i:
//
// row = i / col
// column = i % col
//
// This allows us to apply array rotation
// directly on the matrix.
//
// Step 2:
// Since shifting the grid n times returns
// the grid to its original position:
//
// k = k % (row * col)
//
// Step 3:
// To rotate the flattened array to the right by k positions,
// use the reversal algorithm:
//
// 1. Reverse the entire array.
//
// 2. Reverse the first k elements.
//
// 3. Reverse the remaining elements.
//
// Example:
//
// Original:
// [1, 2, 3, 4, 5, 6]
//
// Shift right by 2:
//
// Reverse all:
// [6, 5, 4, 3, 2, 1]
//
// Reverse first 2:
// [5, 6, 4, 3, 2, 1]
//
// Reverse remaining:
// [5, 6, 1, 2, 3, 4]
//
// Step 4:
// Convert the modified matrix into
// List<List<Integer>> as required.

// Time Complexity:
// O(m × n)

// Space Complexity:
// O(1) auxiliary space
//
// (Ignoring the output list)

// Java Solution:
class Solution {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int row = grid.length;
        int col = grid[0].length;

        int n = row * col;

        k = k % n;

        if (k != 0) {

            // Reverse the complete flattened array
            reverse(grid, col, 0, n - 1);

            // Reverse the first k elements
            reverse(grid, col, 0, k - 1);

            // Reverse the remaining elements
            reverse(grid, col, k, n - 1);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int[] r : grid) {

            List<Integer> rowList = new ArrayList<>();

            for (int val : r) {
                rowList.add(val);
            }

            result.add(rowList);
        }

        return result;
    }

    private void reverse(int[][] grid, int col, int i, int j) {

        while (i < j) {

            int r1 = i / col;
            int c1 = i % col;

            int r2 = j / col;
            int c2 = j % col;

            int temp = grid[r1][c1];

            grid[r1][c1] = grid[r2][c2];
            grid[r2][c2] = temp;

            i++;
            j--;
        }
    }
}