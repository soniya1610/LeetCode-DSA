/*
 * Problem: First Stable Index
 * Topic: Arrays, Prefix Maximum, Suffix Minimum
 *
 * Approach:
 * 1. Build a suffix minimum array where suffix[i] stores
 *    the minimum value from index i to the end.
 * 2. Traverse the array while maintaining the maximum value
 *    seen so far.
 * 3. For each index i, calculate:
 *       score = prefixMaximum - suffixMinimum
 * 4. Return the first index where score <= k.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffix = new int[n];

        int mn = Integer.MAX_VALUE;

        // Build suffix minimum
        for (int i = n - 1; i >= 0; i--) {
            mn = Math.min(mn, nums[i]);
            suffix[i] = mn;
        }

        int mx = 0;

        // Find the first index with score <= k
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);

            int score = mx - suffix[i];

            if (score <= k) {
                return i;
            }
        }

        return -1;
    }
}