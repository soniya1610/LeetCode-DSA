package Greedy;

import java.util.*;
// Problem Name:
// 3016. Minimum Number of Pushes to Type Word II

// Topic:
// Greedy, Sorting, Frequency Counting

// Approach:
// Step 1:
// Count the frequency of each character
// in the word.
//
// Step 2:
// Sort the frequencies in descending order.
//
// To minimize the total number of pushes,
// the most frequent characters should be
// assigned to keys requiring the fewest pushes.
//
// Step 3:
// Assign characters greedily:
//
// - First 8 most frequent characters  -> 1 push
// - Next 8 characters                 -> 2 pushes
// - Remaining characters              -> 3, 4, ... pushes
//
// The push count for the i-th character is:
//
// (i / 8) + 1
//
// Step 4:
// Multiply each character's frequency
// by its assigned push count and
// add it to the answer.

// Time Complexity:
// O(n + 26 log 26)
//
// n = length of the word
// Sorting is over only 26 characters.

// Space Complexity:
// O(26)

// Java Solution:
class Solution {

    public int minimumPushes(String word) {

        int[] frequency = new int[26];

        // Count character frequencies
        for (char ch : word.toCharArray()) {
            frequency[ch - 'a']++;
        }

        Integer[] freq = Arrays.stream(frequency)
                .boxed()
                .toArray(Integer[]::new);

        Arrays.sort(freq, Comparator.reverseOrder());

        int answer = 0;

        for (int i = 0; i < 26; i++) {
            answer += freq[i] * ((i / 8) + 1);
        }

        return answer;
    }
}