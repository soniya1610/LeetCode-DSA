// Problem Name:
// 2904. Shortest and Lexicographically Smallest Beautiful String

// Topic:
// Sliding Window, Two Pointers, String

// Approach:
// We need a substring containing exactly k ones.
//
// Use a sliding window with two pointers:
//
// 1. Expand the window using `right`.
//    Whenever we encounter '1', increase `ones`.
//
// 2. If the window contains more than k ones,
//    move `left` forward until the window contains
//    at most k ones.
//
// 3. When the window contains exactly k ones,
//    remove unnecessary leading zeros. This gives the
//    shortest possible substring for the current right.
//
// 4. Compare the current valid substring with `ans`:
//    - Prefer shorter length.
//    - If lengths are equal, prefer lexicographically smaller.
//
// Time Complexity:
// O(n^2) in the worst case because substring() and
// comparison can take O(n).
//
// Space Complexity:
// O(n) for the resulting substring.

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;
        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // More than k ones -> shrink window
            while (ones > k) {

                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            // Remove unnecessary leading zeros
            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            // Window contains exactly k ones
            if (ones == k) {

                String curr = s.substring(left, right + 1);

                if (ans.equals("")
                        || curr.length() < ans.length()
                        || (curr.length() == ans.length()
                            && curr.compareTo(ans) < 0)) {

                    ans = curr;
                }
            }
        }

        return ans;
    }
}