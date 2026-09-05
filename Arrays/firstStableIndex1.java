```java
/*
 * Problem: First Stable Index
 * Topic: Arrays, Prefix Maximum, Suffix Minimum
 *
 * Approach:
 * 1. Build a suffix minimum array where minFromIndex[i]
 *    stores the minimum element from index i to the end.
 * 2. Traverse from left to right while maintaining the
 *    maximum element seen so far.
 * 3. For each index i, calculate:
 *       score = maxEl - minFromIndex[i]
 * 4. Return the first index where score <= k.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] minFromIndex = new int[n];
        int minEl = Integer.MAX_VALUE;

        // Build suffix minimum array
        for (int i = n - 1; i >= 0; i--) {
            minEl = Math.min(minEl, nums[i]);
            minFromIndex[i] = minEl;
        }

        int maxEl = 0;

        // Find the first stable index
        for (int i = 0; i < n; i++) {
            maxEl = Math.max(maxEl, nums[i]);

            if (maxEl - minFromIndex[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
```
