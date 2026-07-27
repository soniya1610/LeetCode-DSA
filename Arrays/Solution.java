public // Problem Name:
// 1464. Maximum Product of Two Elements in an Array

// Topic:
// Arrays, Greedy

// Approach:
// Step 1:
// Traverse the array once.
//
// Maintain:
//
// max1 = largest element
// max2 = second largest element
//
// Step 2:
// For every element:
//
// - If it is greater than or equal to max1:
// max2 = max1
// max1 = current element
//
// - Otherwise, if it is greater than or equal to max2:
// max2 = current element
//
// Step 3:
// After finding the two largest elements,
// compute:
//
// (max1 - 1) × (max2 - 1)
//
// Step 4:
// Return the result.

// Time Complexity:
// O(n)

// Space Complexity:
// O(1)

// Java Solution:
class Solution {
    public int maxProduct(int[] nums) {

        int max1 = 1;
        int max2 = 1;

        for (int x : nums) {

            if (x >= max1) {
                max2 = max1;
                max1 = x;
            } else if (x >= max2) {
                max2 = x;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }
}
