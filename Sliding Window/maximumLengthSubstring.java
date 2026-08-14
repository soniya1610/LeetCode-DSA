// Problem Name:
// 3090. Maximum Length Substring With Two Occurrences

// Topic:
// Sliding Window, Two Pointers, Frequency Array

// Approach:
// Maintain a sliding window [l, r] where every character
// appears at most 2 times.
//
// Expand the window by moving `r`.
//
// If the frequency of the current character becomes
// greater than 2, shrink the window from the left
// until the condition becomes valid again.
//
// For every valid window, update the maximum length.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

class Solution {
    public int maximumLengthSubstring(String s) {

        int res = 0;
        int[] fq = new int[26];

        for (int l = 0, r = 0; r < s.length(); r++) {

            fq[(s.charAt(r) & 31) - 1]++;

            while (fq[(s.charAt(r) & 31) - 1] > 2) {
                fq[(s.charAt(l++) & 31) - 1]--;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}