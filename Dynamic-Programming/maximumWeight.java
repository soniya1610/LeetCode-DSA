```java
/*
 * Problem: Maximum Weight
 * Topic: Dynamic Programming, Binary Search, Weighted Interval Scheduling
 *
 * Approach:
 * 1. Sort intervals by start time, end time, weight, and original index.
 * 2. For every interval, use binary search to find the next interval
 *    whose start time is greater than the current interval's end time.
 * 3. Use DP where t[i][k] represents the best result using intervals
 *    from index i onward while selecting at most k intervals.
 * 4. At every state, consider two choices:
 *       - Skip the current interval.
 *       - Take the current interval and jump to the next compatible interval.
 * 5. If two choices have the same weight, choose the lexicographically
 *    smaller list of original indices.
 * 6. Since at most 4 intervals can be selected, K = 4.
 *
 * Time Complexity: O(n log n + n * K * K)
 * Space Complexity: O(n * K + n)
 */

class Solution {
    int n;
    int[][] intervals;
    int[] nextIdx;

    static class Node {
        long score = -1;
        List<Integer> idxs = new ArrayList<>();
    }

    Node[][] t;

    // Find the first interval whose start > current end
    int findNext(int r) {
        int lo = 0, hi = n - 1;
        int result = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (intervals[mid][0] > r) {
                result = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return result;
    }

    // Check lexicographical order of two index lists
    boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        n = intervalsList.size();

        // Store start, end, weight, and original index
        intervals = new int[n][4];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = intervalsList.get(i).get(0);
            intervals[i][1] = intervalsList.get(i).get(1);
            intervals[i][2] = intervalsList.get(i).get(2);
            intervals[i][3] = i;
        }

        // Sort intervals
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            if (a[2] != b[2]) return a[2] - b[2];
            return a[3] - b[3];
        });

        // Precompute next compatible interval
        nextIdx = new int[n];

        for (int i = 0; i < n; i++) {
            nextIdx[i] = findNext(intervals[i][1]);
        }

        final int K = 4;

        t = new Node[n + 1][K + 1];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= K; k++) {
                t[i][k] = new Node();
            }
        }

        // Bottom-up DP
        for (int i = n - 1; i >= 0; i--) {
            int weight = intervals[i][2];
            int idx = intervals[i][3];
            int j = nextIdx[i];

            for (int k = 1; k <= K; k++) {
                Node skip = t[i + 1][k];
                Node temp = t[j][k - 1];

                // Take current interval
                Node take = new Node();
                take.score = temp.score + weight;
                take.idxs = new ArrayList<>(temp.idxs);
                take.idxs.add(idx);

                // Keep original indices sorted
                Collections.sort(take.idxs);

                Node result;

                if (skip.score > take.score) {
                    result = skip;
                } else if (skip.score < take.score) {
                    result = take;
                } else {
                    // Tie-break using lexicographically smaller indices
                    result = isLexSmaller(skip.idxs, take.idxs)
                            ? skip
                            : take;
                }

                t[i][k] = result;
            }
        }

        Node res = t[0][K];

        int[] ans = new int[res.idxs.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.idxs.get(i);
        }

        return ans;
    }
}
```
