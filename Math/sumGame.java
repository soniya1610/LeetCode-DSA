// Problem Name:
// 1927. Sum Game

// Topic:
// Math, Game Theory, String

// Approach:
// Treat '?' as the average digit value, 4.5.
//
// Calculate the difference between the sum of the
// first half and the second half.
//
// - For the first half, add each digit's value.
// - For the second half, subtract each digit's value.
//
// If the final difference is 0, Alice cannot guarantee
// a win, so return false.
//
// Otherwise, Alice can win.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        double result = 0.0;

        // First half
        for (int i = 0; i < n / 2; i++) {
            result += getVal(num.charAt(i));
        }

        // Second half
        for (int i = n / 2; i < n; i++) {
            result -= getVal(num.charAt(i));
        }

        return result != 0.0;
    }

    private double getVal(char ch) {
        return ch == '?' ? 4.5 : ch - '0';
    }
}