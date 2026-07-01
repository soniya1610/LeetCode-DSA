// Problem Name:
// 2812. Find the Safest Path in a Grid

// Approach:
// Step 1: Compute distance of every cell from nearest thief
// - Use Multi-Source BFS.
// - Put all thief cells into queue initially.
// - BFS level gives minimum distance from a thief.
//
// Step 2: Binary Search on Safeness Factor (SF)
// - SF = minimum distance from thief along chosen path.
// - We want maximum possible SF.
//
// Step 3: Check if path exists for given SF
// - Use BFS from (0,0) to (n-1,n-1).
// - Only visit cells where distNearestThief[i][j] >= SF.
// - If destination reachable => SF valid.
//
// Binary Search:
// - If SF is possible, try larger.
// - Else try smaller.

// Time Complexity:
// Step 1 BFS  -> O(n²)
// Check BFS   -> O(n²)
// Binary Search -> O(log(maxDist))
//
// Total:
// O(n² * log(maxDist))

// Space Complexity:
// O(n²)

// Java Solution:
class Solution {
    int n;
    int[][] directions = {{1,0}, {-1,0}, {0,-1}, {0,1}};

    boolean check(int[][] distNearestThief, int sf) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        if (distNearestThief[0][0] < sf)
            return false;

        que.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!que.isEmpty()) {
            int[] curr = que.poll();
            int i = curr[0];
            int j = curr[1];

            if (i == n - 1 && j == n - 1)
                return true;

            for (int[] dir : directions) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if (ni >= 0 && ni < n &&
                    nj >= 0 && nj < n &&
                    !visited[ni][nj] &&
                    distNearestThief[ni][nj] >= sf) {

                    que.add(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }

        return false;
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();

        // Step 1: Distance from nearest thief
        int[][] distNearestThief = new int[n][n];
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    que.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int level = 0;

        while (!que.isEmpty()) {
            int size = que.size();

            while (size-- > 0) {
                int[] curr = que.poll();
                int i = curr[0];
                int j = curr[1];

                distNearestThief[i][j] = level;

                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if (ni < 0 || ni >= n ||
                        nj < 0 || nj >= n ||
                        visited[ni][nj])
                        continue;

                    que.add(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
            level++;
        }

        // Step 2: Binary Search on safeness factor
        int left = 0;
        int right = 400;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(distNearestThief, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}