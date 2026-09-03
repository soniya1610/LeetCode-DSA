/*
 * Problem: Uniform Array
 * Topic: Arrays, Bit Manipulation, Parity
 *
 * Approach:
 * Find the minimum element and check whether the array
 * contains at least one odd number.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public boolean uniformArray(int[] A) {
        int min = A[0];
        int odd = 0;

        for (int x : A) {
            min = Math.min(min, x);
            odd |= x & 1;
        }

        return (min & 1) == odd;
    }
}