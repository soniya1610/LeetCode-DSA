// Problem Name:
// 3014. Minimum Number of Pushes to Type Word I

// Topic:
// Math, Greedy

// Approach:
// Step 1:
// There are 8 keys available.
//
// Each key can store one character at each push level.
//
// - First 8 characters require 1 push.
// - Next 8 characters require 2 pushes.
// - Next 8 characters require 3 pushes.
// - And so on.
//
// Step 2:
// Let:
//
// q = word.length() / 8
// r = word.length() % 8
//
// where:
//
// q = number of complete groups of 8 characters
// r = remaining characters.
//
// Step 3:
// Every complete group contributes:
//
// 8 × (group number)
//
// Using arithmetic simplification:
//
// Total pushes for complete groups
// = 4 × q × (q + 1)
//
// Remaining characters each require
// (q + 1) pushes.
//
// Contribution:
//
// r × (q + 1)
//
// Step 4:
// Combine both:
//
// (4 × q + r) × (q + 1)

// Time Complexity:
// O(1)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int minimumPushes(String word) {

        int q = word.length() >> 3;   // word.length() / 8
        int r = word.length() & 7;    // word.length() % 8

        return ((q << 2) + r) * (q + 1);
    }
}