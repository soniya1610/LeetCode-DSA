// Problem Name:
// 1979. Find Greatest Common Divisor of Array

// Topic:
// Arrays, Math, GCD

// Approach:
// Step 1:
// Find the minimum and maximum elements
// of the array.
//
// Step 2:
// The GCD of all elements in the array
// is equal to:
//
// gcd(minimum, maximum)
//
// Step 3:
// Use the Euclidean Algorithm:
//
// gcd(a, b) = gcd(b, a % b)
//
// Continue until b becomes 0.
//
// The remaining value of a is the GCD.

// Time Complexity:
// O(n)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {

    private int gcd(int a, int b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    public int findGCD(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }
}