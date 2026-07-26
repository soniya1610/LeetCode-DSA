// Problem Name:
// 628. Maximum Product of Three Numbers

// Topic:
// Arrays, Sorting, Greedy, Math

// Approach:
// Step 1:
// Sort the array in ascending order.
//
// The maximum product of three numbers can be formed in
// only two possible ways:
//
// Case 1:
// The three largest numbers:
//
// A[n - 1] * A[n - 2] * A[n - 3]
//
// Case 2:
// The largest number and the two smallest numbers:
//
// A[n - 1] * A[0] * A[1]
//
// Why consider the two smallest numbers?
// Because two negative numbers produce a positive product.
//
// Example:
// [-10, -10, 1, 2, 3]
//
// (-10) × (-10) × 3 = 300
//
// This is greater than:
// 3 × 2 × 1 = 6
//
// Step 2:
// Return the maximum of these two possibilities.

// Time Complexity:
// O(n log n)
// (Due to sorting)

// Space Complexity:
// O(1)
// (Ignoring sorting space)

// Java Solution:
class Solution {
    public int maximumProduct(int[] A) {

        Arrays.sort(A);

        int n = A.length;

        return Math.max(
                A[n - 1] * A[n - 2] * A[n - 3],
                A[n - 1] * A[0] * A[1]);
    }
}