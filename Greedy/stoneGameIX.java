// Problem Name:
// 2029. Stone Game IX

// Topic:
// Game Theory, Greedy, Math, Modulo

// Approach:
// Only the remainder of each stone modulo 3 matters.
//
// Count stones having remainder:
// 0 -> f[0]
// 1 -> f[1]
// 2 -> f[2]
//
// If the number of remainder-0 stones is even:
// Alice can win if both remainder-1 and remainder-2
// stones are available.
//
// If the number of remainder-0 stones is odd:
// Alice wins only when the difference between the
// counts of remainder-1 and remainder-2 stones is > 2.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

class Solution {
    public boolean stoneGameIX(int[] stones) {

        int[] f = {0, 0, 0};

        for (int s : stones) {
            f[s % 3]++;
        }

        if ((f[0] & 1) == 0) {
            return Math.min(f[1], f[2]) > 0;
        }

        return Math.abs(f[1] - f[2]) > 2;
    }
}