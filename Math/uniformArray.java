// Problem Name:
// 3733. Find the Number of Ways to Make a Uniform Array

// Topic:
// Array, Math, GCD

// Approach:
// A uniform array means all elements can be made equal using
// the allowed operation.
//
// For this problem, the key observation is that the answer
// depends on whether the array contains an even number.
//
// If at least one even number exists, we can make all elements
// have the same parity and construct a uniform array.
//
// Therefore, check whether nums1 contains an even element.
//
// Time Complexity:
// O(n)
//
// Space Complexity:
// O(1)

class Solution {

    public boolean uniformArray(int[] nums1) {

        for (int num : nums1) {
            if (num % 2 == 0) {
                return true;
            }
        }

        return false;
    }
}