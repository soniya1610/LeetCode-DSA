// Problem Name:
// 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum

// Topic:
// Array, Simulation, Brute Force

// Approach:
// Step 1:
// Start with the first element as the
// sequential prefix sum.
//
// Step 2:
// Traverse the array and keep adding elements
// as long as they form a consecutive sequence:
//
// nums[i] == nums[i - 1] + 1
//
// Once the sequence breaks, stop.
//
// Step 3:
// Now we have the sum of the longest
// sequential prefix.
//
// Step 4:
// Check whether this sum already exists
// in the array.
//
// If it exists, increment the sum by 1
// and check again.
//
// Continue until we find a number that
// does not exist in the array.
//
// That number is the answer.

// Time Complexity:
// O(n²)
//
// In the worst case, the while loop can
// perform O(n) iterations and each iteration
// scans the entire array.

// Space Complexity:
// O(1)

// Java Solution:
class Solution {

    public int missingInteger(int[] nums) {

        int n = nums.length;

        // Sum of the sequential prefix
        int sequentialSum = nums[0];

        // Find sequential prefix sum
        for (int i = 1; i < n; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                sequentialSum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest integer
        // greater than or equal to the prefix sum
        // that does not exist in the array.
        while (true) {

            boolean found = false;

            for (int num : nums) {

                if (num == sequentialSum) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return sequentialSum;
            }

            sequentialSum++;
        }
    }
}