// Problem Name:
// 877. Stone Game

// Topic:
// Game Theory, Math

// Approach:
// Step 1:
// The number of piles is always even.
//
// Step 2:
// The total number of stones is odd,
// so a tie is impossible.
//
// Step 3:
// Before the game starts,
// Player 1 (Alice) can choose a strategy
// based on the parity of pile indices.
//
// She can decide to always take either:
//
// - all even-indexed piles, or
// - all odd-indexed piles.
//
// Step 4:
// Alice first computes which parity
// (even-indexed or odd-indexed)
// contains more stones.
//
// Since she moves first,
// she can always force the game so that
// she collects all piles of that parity.
//
// Therefore, Alice is guaranteed
// to collect more stones than Bob.
//
// Hence, the answer is always true.

// Time Complexity:
// O(1)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}