// Problem Name:
// 2958. Length of Longest Subarray With at Most K Frequency

// Topic:
// Array, HashMap, Sliding Window, Two Pointers

// Approach:
// Use a sliding window [i, j] to maintain a subarray
// where every element appears at most k times.
//
// Step 1:
// Expand the window by moving j forward and increase
// the frequency of nums[j].
//
// Step 2:
// If the frequency of nums[j] becomes greater than k,
// move i forward and decrease frequencies until the
// window becomes valid again.
//
// Step 3:
// For every valid window, update the maximum length.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(n)

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        int n = nums.length;

        HashMap<Integer, Integer> m = new HashMap<>();

        int i = 0;
        int j = 0;
        int res = 0;

        while (j < n) {

            m.put(nums[j], m.getOrDefault(nums[j], 0) + 1);

            while (m.get(nums[j]) > k) {
                m.put(nums[i], m.get(nums[i]) - 1);
                i++;
            }

            res = Math.max(res, j - i + 1);

            j++;
        }

        return res;
    }
}