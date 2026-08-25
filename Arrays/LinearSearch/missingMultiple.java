// Problem Name:
// 2996. Smallest Missing Multiple of K

// Topic:
// Array, Linear Search

// Approach:
// Start checking multiples of k from k.
//
// For every multiple:
// - Check whether it exists in the array.
// - If it exists, move to the next multiple.
// - If it does not exist, return it.
//
// The `contains()` method performs a linear search
// through the array.
//
// Time Complexity:
// O(n * m), where m is the number of multiples checked
//
// Space Complexity:
// O(1)

class Solution {
    public int missingMultiple(int[] nums, int k) {

        int i = 1;

        while (contains(nums, i * k)) {
            i++;
        }

        return i * k;
    }

    private boolean contains(int[] nums, int target) {

        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }
}