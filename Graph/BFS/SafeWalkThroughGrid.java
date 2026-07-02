package Graph.BFS;
import java.util.*;
// Problem Name:
// 3286. Find a Safe Walk Through a Grid

// Approach:
// Step 1:
// Treat grid as a graph where:
// - Each cell is a node
// - Moving to adjacent cell is an edge
//
// Edge Weight:
// - Moving to cell with value 0 -> cost 0
// - Moving to cell with value 1 -> cost 1
//
// Step 2:
// Use 0-1 BFS to find minimum health loss from (0,0) to (m-1,n-1)
//
// Why 0-1 BFS?
// - Graph edges have only two possible weights: 0 and 1
// - Instead of priority queue (Dijkstra), use deque
//
// Rules:
// - If next move cost is 0 -> push at front
// - If next move cost is 1 -> push at back
//
// Step 3:
// Maintain result[][] where:
// result[i][j] = minimum health lost to reach cell (i,j)
//
// Step 4:
// After reaching destination:
// remaining health = health - result[m-1][n-1]
//
// If remaining health >= 1 => return true
// Else return false

// Time Complexity:
// O(m * n)

// Space Complexity:
// O(m * n)

// Java Solution:
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();

        int[][] result = new int[m][n];
        for (int[] row : result) Arrays.fill(row, Integer.MAX_VALUE);

        Deque<int[]> dq = new ArrayDeque<>();

        result[0][0] = grid.get(0).get(0); // source = (0,0)
        dq.offerFirst(new int[]{0, 0});

        while (!dq.isEmpty()) {
            int[] cell = dq.pollFirst();
            int r = cell[0], c = cell[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int newCost = result[r][c] + grid.get(nr).get(nc);

                if (newCost < result[nr][nc]) {
                    result[nr][nc] = newCost;

                    if (grid.get(nr).get(nc) == 0)
                        dq.offerFirst(new int[]{nr, nc});
                    else
                        dq.offerLast(new int[]{nr, nc});
                }
            }
        }

        return health - result[m - 1][n - 1] >= 1;
    }
}
