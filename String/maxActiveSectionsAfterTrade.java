// Problem Name:
// 3499. Maximize Active Sections After Trade

// Topic:
// Strings, Greedy, Two Pointers

// Approach:
// Step 1:
// Count the total number of active sections ('1').
//
// Initially, this is the answer if no trade is performed.
//
// Step 2:
// Add a '1' at both ends of the string.
//
// This simplifies handling boundary cases by ensuring
// every valid trade is surrounded by active sections.
//
// Step 3:
// Traverse the string block by block.
//
// Maintain:
//
// c10 = size of the left 0-block
// c11 = size of the middle 1-block
// c20 = size of the right 0-block
//
// The pattern is:
//
// 0...0  1...1  0...0
//  c10    c11    c20
//
// Step 4:
// A trade removes the middle 1-block and merges
// the two surrounding 0-blocks into active sections.
//
// Hence, the number of active sections becomes:
//
// ones + c10 + c20
//
// Update the maximum answer.
//
// Step 5:
// Slide the window.
//
// The current right 0-block becomes the left 0-block
// for the next iteration.
//
// Continue until no complete
// 0-block -> 1-block -> 0-block pattern exists.

// Time Complexity:
// O(n)
//
// Each character is visited at most once.

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {

        int ones = 0;

        for (char c : s.toCharArray()) {
            if (c == '1')
                ones++;
        }

        s = "1" + s + "1";

        int n = s.length();
        int i = 0;

        int ans = ones;

        // Skip initial 1-block
        while (i < n && s.charAt(i) == '1')
            i++;

        // Read first 0-block
        int c10 = 0;
        while (i < n && s.charAt(i) == '0') {
            c10++;
            i++;
        }

        while (i < n) {

            // Read middle 1-block
            int c11 = 0;
            while (i < n && s.charAt(i) == '1') {
                c11++;
                i++;
            }

            if (c11 == 0)
                break;

            // Read right 0-block
            int c20 = 0;
            while (i < n && s.charAt(i) == '0') {
                c20++;
                i++;
            }

            if (c20 == 0)
                break;

            ans = Math.max(ans, ones + c10 + c20);

            // Slide the window
            c10 = c20;
        }

        return ans;
    }
}