// Problem Name:
// 2419. Longest Subsequence With Maximum Bitwise AND

// Topic:
// Bit Manipulation, XOR

// Approach:
// Calculate the XOR of all elements and check whether
// the array contains any non-zero element.
//
// 1. If XOR of all elements is non-zero,
//    the entire array can be used.
//
// 2. If XOR is zero but at least one element is non-zero,
//    remove one element to make the XOR non-zero.
//    Therefore, answer = n - 1.
//
// 3. If all elements are zero, no non-zero XOR subsequence
//    can be formed, so return 0.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

class Solution {
    public int longestSubsequence(int[] nums) {

        int xorValue = 0;
        boolean hasNonZero = false;

        for (int x : nums) {

            xorValue ^= x;

            if (x != 0) {
                hasNonZero = true;
            }
        }

        if (xorValue != 0) {
            return nums.length;
        }

        if (hasNonZero) {
            return nums.length - 1;
        }

        return 0;
    }
}