// Problem Name:
// 3345. Smallest Divisible Digit Product I

// Topic:
// Math, Number Theory, GCD

// Approach:
// Step 1:
// Split the number into:
//
// q = tens digit
// r = units digit
//
// Step 2:
// Compute the required factor:
//
// req = t / gcd(nextTensDigit, t)
//
// where:
//
// nextTensDigit = q + (10 - q) / 10
//
// This determines the minimum step needed
// so that the digit product becomes divisible by t.
//
// Step 3:
// Find the smallest multiple of req
// that is greater than or equal to the current
// units digit.
//
// nxt = ceil(r / req) × req
//
// Step 4:
// Convert this value into the correct
// units digit.
//
// If nxt reaches 10, carry is handled
// automatically using:
//
// x = nxt - (nxt - 10) * (nxt / 10)
//
// Step 5:
// Combine the tens digit and the new
// units digit to obtain the smallest
// valid number greater than or equal to n.

// Time Complexity:
// O(log t)
//
// Due to the Euclidean GCD algorithm.

// Space Complexity:
// O(1)

// Java Solution:
class Solution {

    public int smallestNumber(int n, int t) {

        int q = n / 10;
        int r = n % 10;

        int req = t / gcd(q + (10 - q) / 10, t);

        int nxt = ((r + req - 1) / req) * req;

        int x = nxt - (nxt - 10) * (nxt / 10);

        return q * 10 + x;
    }

    private int gcd(int a, int b) {

        if (b == 0)
            return a;

        return gcd(b, a % b);
    }
}