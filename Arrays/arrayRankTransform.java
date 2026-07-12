// Problem Name:
// 1331. Rank Transform of an Array

// Topic:
// Arrays, Sorting, HashMap

// Approach:
// Step 1:
// Create a copy of the original array.
//
// Sort the copied array in ascending order.
//
// Step 2:
// Assign ranks.
//
// Traverse the sorted array.
//
// - If a number has not been assigned a rank,
//   assign the current rank and increment it.
//
// Equal numbers receive the same rank.
//
// Store the mapping:
//
// value -> rank
//
// Step 3:
// Traverse the original array.
//
// Replace every element with its corresponding
// rank from the HashMap.
//
// Step 4:
// Return the transformed array.

// Time Complexity:
// O(n log n)
// (Sorting dominates)

// Space Complexity:
// O(n)
// (Copy of array + HashMap)

// Java Solution:
class Solution {
    public int[] arrayRankTransform(int[] arr) {

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();

        int rank = 1;

        for (int num : sorted) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}