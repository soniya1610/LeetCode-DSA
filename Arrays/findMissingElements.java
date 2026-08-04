// Problem Name:
// Find Missing Elements Between Consecutive Numbers

// Topic:
// Arrays, Sorting

// Approach:
// Step 1:
// Sort the array in ascending order.
//
// This places all numbers in their
// correct order so consecutive elements
// can be compared easily.
//
// Step 2:
// Traverse every pair of adjacent elements.
//
// Let:
//
// current = nums[i]
// next = nums[i + 1]
//
// Step 3:
// If there is a gap between
// current and next,
//
// repeatedly add:
//
// current + 1
//
// to the answer until
// current + 1 == next.
//
// Step 4:
// Continue for every adjacent pair.
//
// The list will contain all missing
// integers between consecutive numbers.

// Time Complexity:
// O(n log n + m)
//
// O(n log n) for sorting
// O(m) to add all missing numbers,
// where m is the number of missing elements.

// Space Complexity:
// O(m)
//
// m = number of missing elements

// Java Solution:
class Solution {

    public List<Integer> findMissingElements(int[] nums) {

        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {

            int current = nums[i];
            int next = nums[i + 1];

            while (current + 1 < next) {
                list.add(current + 1);
                current++;
            }
        }

        return list;
    }
}